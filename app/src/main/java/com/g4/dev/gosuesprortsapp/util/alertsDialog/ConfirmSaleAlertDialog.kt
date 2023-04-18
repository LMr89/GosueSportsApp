package com.g4.dev.gosuesprortsapp.util.alertsDialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View.OnClickListener
import androidx.fragment.app.DialogFragment
import com.g4.dev.gosuesprortsapp.databinding.ConfirmSaleDialogBinding

class ConfirmSaleAlertDialog(
    private val onSubmitClickListener:() -> Unit
):DialogFragment() {

    private lateinit var  mBinding:ConfirmSaleDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mBinding = ConfirmSaleDialogBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(mBinding.root)

        mBinding.btnConfirmSaleAlert.setOnClickListener {
            onSubmitClickListener.invoke()
            dismiss()

        }

        mBinding.btnCancelAlert.setOnClickListener { dismiss() }
        isCancelable = false

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }
}