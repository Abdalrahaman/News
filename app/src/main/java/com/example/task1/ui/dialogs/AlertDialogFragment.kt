package com.example.task1.ui.dialogs

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.task1.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AlertDialogFragment(
    private val title: Int,
    private val message: Int,
    private val positiveButtonClicked: () -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(getText(R.string.login)) { _, _ ->
                positiveButtonClicked()
            }
            .create()

    companion object {
        const val TAG = "AlertDialogFragment"
    }
}