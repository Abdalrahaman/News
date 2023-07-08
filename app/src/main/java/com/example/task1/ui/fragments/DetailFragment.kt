package com.example.task1.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.task1.R
import com.example.task1.databinding.FragmentDetailBinding
import com.example.task1.ui.base.BaseFragment
import com.example.task1.ui.viewmodels.HomeViewModel

class DetailFragment : BaseFragment() {
    private lateinit var binding: FragmentDetailBinding

    override val _viewModel by activityViewModels<HomeViewModel>()

    private val articleDetailArgs by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate<FragmentDetailBinding?>(
                inflater,
                R.layout.fragment_detail, container, false
            ).apply {
                article = articleDetailArgs.article
            }
        return binding.root
    }
}