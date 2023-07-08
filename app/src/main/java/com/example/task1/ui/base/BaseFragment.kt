package com.example.task1.ui.base

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.task1.data.prefs.LoginPrefs
import com.example.task1.ui.helper.NavigationCommand
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    abstract val _viewModel: BaseViewModel

    @Inject
    lateinit var loginPrefs: LoginPrefs

    override fun onStart() {
        super.onStart()
        _viewModel.showToast.observe(viewLifecycleOwner) {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        }
        _viewModel.navigationCommand.observe(viewLifecycleOwner) { command ->
            when (command) {
                is NavigationCommand.To -> findNavController().navigate(command.directions)
                is NavigationCommand.Back -> findNavController().popBackStack()
                is NavigationCommand.BackTo -> findNavController().popBackStack(
                    command.destinationId,
                    false
                )
            }
        }
    }

    fun isGuest() = !loginPrefs.get()
}