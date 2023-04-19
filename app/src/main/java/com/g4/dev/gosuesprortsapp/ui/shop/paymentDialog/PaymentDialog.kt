package com.g4.dev.gosuesprortsapp.ui.shop.paymentDialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.g4.dev.gosuesprortsapp.MainActivity
import com.g4.dev.gosuesprortsapp.R
import com.g4.dev.gosuesprortsapp.databinding.ConfirmSaleDialogBinding
import com.g4.dev.gosuesprortsapp.databinding.PaymentDialogBinding

class PaymentDialog (
        val onNotifiyForLocalSale : (confirm:Boolean) -> Unit
    ): DialogFragment(), OnClickListener {

        private lateinit var  mBinding: PaymentDialogBinding

        lateinit var  cardPaymenDialog: CardPaymenDialog
        lateinit var  yapePaymentDialog: YapePaymentDialog


        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            mBinding = PaymentDialogBinding.inflate(LayoutInflater.from(context))
            val builder = AlertDialog.Builder(requireActivity())
            builder.setView(mBinding.root)

            val dialog = builder.create()
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            return dialog
        }

    override fun onResume() {
        super.onResume()
        initDialogs()
        initListeners()
        setObserbers()
    }

    private fun initDialogs(){
        cardPaymenDialog = CardPaymenDialog()
        yapePaymentDialog = YapePaymentDialog()
    }

    private fun initListeners(){
        mBinding.btnStorePayment.setOnClickListener(this)
        mBinding.btnCardPayment.setOnClickListener(this)
        mBinding.btnYapePayment.setOnClickListener(this)
    }
    private fun setObserbers(){

    }

    override fun onClick(v: View) {
        when(v.id){

            R.id.btnCardPayment -> {

                cardPaymenDialog.show(parentFragmentManager, "cardDialog")
                dismiss()

            }
            R.id.btnYapePayment ->{

                yapePaymentDialog.show(parentFragmentManager, "cardYape")
                dismiss()

            }
            R.id.btnStorePayment -> {
                TemporalPaymentData.IS_ON_STORE_PAYMENT= true
                onNotifiyForLocalSale(true)
                dismiss()

            }

        }
    }
}