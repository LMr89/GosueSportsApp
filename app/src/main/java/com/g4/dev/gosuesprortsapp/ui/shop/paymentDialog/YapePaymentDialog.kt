package com.g4.dev.gosuesprortsapp.ui.shop.paymentDialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.g4.dev.gosuesprortsapp.MainActivity
import com.g4.dev.gosuesprortsapp.R
import com.g4.dev.gosuesprortsapp.data.model.request.payment.YapeTokenRequest
import com.g4.dev.gosuesprortsapp.databinding.YapePaymentDialogBinding
import com.g4.dev.gosuesprortsapp.util.messages.MessageType
import com.g4.dev.gosuesprortsapp.util.messages.MessageUtil

class YapePaymentDialog (

): DialogFragment(), View.OnClickListener {

    private lateinit var  mBinding: YapePaymentDialogBinding
    lateinit var mainActivity: MainActivity
    lateinit var  paymentViewModel :PaymentViewModel

    var isDialogInitialized = true

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mBinding = YapePaymentDialogBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(mBinding.root)



        isCancelable = false

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mainActivity=activity as MainActivity
        paymentViewModel = ViewModelProvider(mainActivity)
            .get(PaymentViewModel::class.java)
        return dialog
    }

    override fun onResume() {
        super.onResume()
        setListeners()
        isDialogInitialized = true
        setObservers()
    }



    private fun setObservers() {
        paymentViewModel.isAuthorized.observe(mainActivity){
            dismissDialogWhenIsAuthorized(it)
        }
        paymentViewModel.isLoading.observe(mainActivity){
            mBinding.pgYapeLoading.isVisible = it
        }

    }
    private fun dismissDialogWhenIsAuthorized(close:Boolean){
        if (!isDialogInitialized){
            if (close){
                TemporalPaymentData.IS_ON_STORE_PAYMENT = false
                dismiss()
            }else{
                MessageUtil.sendMessage(mBinding.root,"Yape invalido", MessageType.WARNING)
            }
        }


    }

    private fun validateUserInput():Boolean{
        if(mBinding.etYapeNumber.text.toString().isEmpty() || mBinding.etYapeNumber.text.toString().length <9){
            MessageUtil.sendMessage(mBinding.root, "El numero de yape es de 9 digitos",MessageType.WARNING)
            return  false
        }

        if(mBinding.etAgreementCode.text.toString().isEmpty() || mBinding.etAgreementCode.text.toString().length >6){
            MessageUtil.sendMessage(mBinding.root, "El codigo de aprobacion es de solo 6 digitos",MessageType.WARNING)
            return  false
        }

        return true
    }
    private fun setListeners()
    {
        mBinding.btnMakeSale.setOnClickListener(this);
        mBinding.btnCancelYapePayment.setOnClickListener(this)
    }

    private  fun convertUserInputIntoClass(): YapeTokenRequest?{
        if (validateUserInput()){
            return  YapeTokenRequest(
                   mBinding.etAgreementCode.text.toString(),
                mBinding.etYapeNumber.text.toString(),
                    TemporalPaymentData.SALE_AMOUNT.toString()

            )
        }
        return  null
    }
    override fun onClick(v: View) {
        when(v.id){
            R.id.btnCancelYapePayment -> {
                dismiss()
            }
            R.id.btnMakeSale -> {
                isDialogInitialized = false
                if (convertUserInputIntoClass() != null){
                    sendForApiResponse(convertUserInputIntoClass()!!)
                }

            }
        }
    }

    private  fun sendForApiResponse(yape: YapeTokenRequest){
        paymentViewModel.createYapeViewModel(yape)
    }
}