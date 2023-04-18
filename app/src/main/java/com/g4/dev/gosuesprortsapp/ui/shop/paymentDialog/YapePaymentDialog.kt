package com.g4.dev.gosuesprortsapp.ui.shop.paymentDialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import com.g4.dev.gosuesprortsapp.R
import com.g4.dev.gosuesprortsapp.databinding.CardPaymentDialogBinding
import com.g4.dev.gosuesprortsapp.databinding.PaymentDialogBinding
import com.g4.dev.gosuesprortsapp.databinding.YapePaymentDialogBinding

class YapePaymentDialog (

): DialogFragment(), View.OnClickListener {

    private lateinit var  mBinding: YapePaymentDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mBinding = YapePaymentDialogBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(mBinding.root)



        isCancelable = false

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onResume() {
        super.onResume()
        setListeners()
    }

    private fun setListeners()
    {
        mBinding.btnMakeSale.setOnClickListener(this);
        mBinding.btnCancelYapePayment.setOnClickListener(this)
    }
    override fun onClick(v: View) {
        when(v.id){
            R.id.btnCancelYapePayment -> {
                dismiss()
            }
        }
    }
}