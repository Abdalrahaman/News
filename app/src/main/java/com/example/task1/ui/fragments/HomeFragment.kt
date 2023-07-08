package com.example.task1.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.task1.R
import com.example.task1.databinding.FragmentHomeBinding
import com.example.task1.ui.adapters.ArticleAdapter
import com.example.task1.ui.adapters.ArticleClickListener
import com.example.task1.ui.adapters.SliderAdapter
import com.example.task1.ui.adapters.SliderClickListener
import com.example.task1.ui.base.BaseFragment
import com.example.task1.ui.dialogs.AlertDialogFragment
import com.example.task1.ui.helper.NavigationCommand
import com.example.task1.ui.viewmodels.HomeViewModel
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding

    override val _viewModel by activityViewModels<HomeViewModel>()

    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate<FragmentHomeBinding?>(
                inflater,
                R.layout.fragment_home, container, false
            ).apply {
                viewModel = _viewModel
            }
        binding.imageSlider.apply {
            setIndicatorAnimation(IndicatorAnimationType.WORM)
            setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
            autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
            indicatorSelectedColor = Color.WHITE
            indicatorUnselectedColor = Color.GRAY
            scrollTimeInSec = 4
            startAutoCycle()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        setupAdapters()
        handleObservers()

        _viewModel.getTopHeadlines()
        _viewModel.getNews()
    }

    private fun setupAdapters() {
        val viewModel = binding.viewModel
        if (viewModel != null) {
            sliderAdapter =
                SliderAdapter(SliderClickListener { article ->
                    if (isGuest())
                        showLoginFirstDialog()
                    else
                        _viewModel.navigationCommand.postValue(
                            NavigationCommand.To(
                                HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                                    article
                                )
                            )
                        )
                })
            articleAdapter = ArticleAdapter(ArticleClickListener { article ->
                if (isGuest())
                    showLoginFirstDialog()
                else
                    _viewModel.navigationCommand.postValue(
                        NavigationCommand.To(
                            HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                                article
                            )
                        )
                    )
            })
            binding.imageSlider.setSliderAdapter(sliderAdapter)
            binding.articlesRecyclerView.adapter = articleAdapter
        } else {
            Log.w("Home Fragment", "ViewModel not initialized when attempting to set up adapter.")
        }
    }

    private fun handleObservers() {
        _viewModel.topHeadlinesResult.observe(viewLifecycleOwner) {
            it?.let {
                sliderAdapter.renewItems(it.toMutableList())
            }
        }
        _viewModel.newsResult.observe(viewLifecycleOwner) {
            it?.let {
                articleAdapter.submitList(it)
            }
        }
    }

    private fun showLoginFirstDialog() {
        AlertDialogFragment(
            R.string.login,
            R.string.login_first__message
        ) {
            _viewModel.navigationCommand.postValue(
                NavigationCommand.To(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
            )
        }.show(childFragmentManager, AlertDialogFragment.TAG)
    }
}