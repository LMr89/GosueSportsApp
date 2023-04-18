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

class CardPaymenDialog (

): DialogFragment(), View.OnClickListener {

    private lateinit var  mBinding: CardPaymentDialogBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mBinding = CardPaymentDialogBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(mBinding.root)



        isCancelable =false


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
        mBinding.btnAddCarForSale.setOnClickListener(this);
        mBinding.btnCancelCardPayment.setOnClickListener(this)
    }
    override fun onClick(v: View) {
        when(v.id){
            R.id.btnCancelCardPayment -> {
                dismiss()
            }
        }
    }
}