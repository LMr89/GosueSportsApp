package com.g4.dev.gosuesprortsapp.ui.shop.paymentDialog

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.g4.dev.gosuesprortsapp.MainActivity
import com.g4.dev.gosuesprortsapp.R
import com.g4.dev.gosuesprortsapp.data.model.request.payment.CreditCardTokenRequest
import com.g4.dev.gosuesprortsapp.databinding.CardPaymentDialogBinding
import com.g4.dev.gosuesprortsapp.util.messages.MessageType
import com.g4.dev.gosuesprortsapp.util.messages.MessageUtil

class CardPaymenDialog (

): DialogFragment(), View.OnClickListener {

    private lateinit var  mBinding: CardPaymentDialogBinding
    lateinit var mainActivity:MainActivity
    lateinit var  paymentViewModel :PaymentViewModel

    var isDialogInitialized = true

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mBinding = CardPaymentDialogBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(mBinding.root)


        isCancelable =false

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        mainActivity=activity as MainActivity
        paymentViewModel = ViewModelProvider(mainActivity)
            .get(PaymentViewModel::class.java)
        setListeners()
        return dialog
    }

    override fun onResume() {
        super.onResume()
        isDialogInitialized = true
        setObservers()
    }

    private fun setObservers() {
        paymentViewModel.isAuthorized.observe(mainActivity){
            dismissDialogWhenIsAuthorized(it)
        }
        paymentViewModel.isLoading.observe(mainActivity){
            mBinding.pgCardLoading.isVisible = it
        }

    }

    private fun setListeners()
    {
        mBinding.btnAddCarForSale.setOnClickListener(this)
        mBinding.btnCancelCardPayment.setOnClickListener(this)

    }

    private fun dismissDialogWhenIsAuthorized(close:Boolean){
        if (!isDialogInitialized){
            if (close){
                TemporalPaymentData.IS_ON_STORE_PAYMENT = false
                dismiss()
            }else{
                MessageUtil.sendMessage(mBinding.root,"Tarjeta invalida", MessageType.WARNING)
            }
        }
    }

    private  fun convertUserInputIntoClass():CreditCardTokenRequest?{
        if (validateUserInput()){
            val slashDate = mBinding.etCardDate.text.toString().indexOf("/")
            return  CreditCardTokenRequest(
                mBinding.etCardNumber.text.toString().trim(),
                mBinding.etCvv.text.toString().trim(),
                mBinding.etCardDate.text.toString().substring(0 ,slashDate).toInt(),
                mBinding.etCardDate.text.toString().substring(slashDate+1),
                "pinkicerebro@gmail.com"

            )
        }
        return  null
    }

    private fun validateUserInput():Boolean{
        if(mBinding.etCardNumber.text.toString().isEmpty() || mBinding.etCardNumber.text.toString().length <16){
            MessageUtil.sendMessage(mBinding.root, "Porfavor llena los numeros de la tarjeta",MessageType.WARNING)
            return  false
        }
        if(!validateCreditCardDate(mBinding.etCardDate.text.toString())){
            MessageUtil.sendMessage(mBinding.root, "Porfavor llena correctamente la fecha",MessageType.WARNING)
            return  false
        }
        if(mBinding.etCvv.text.toString().isEmpty() || mBinding.etCvv.text.toString().length >3){
            MessageUtil.sendMessage(mBinding.root, "Porfavor llena correctamente el codigo de seguridad",MessageType.WARNING)
            return  false
        }
        if(mBinding.etOwnerName.text.toString().isEmpty()){
            MessageUtil.sendMessage(mBinding.root, "Porfavor llena el nombre del propietario de la tarjeta",MessageType.WARNING)
            return  false
        }
        return true
    }

    private fun validateCreditCardDate(creditCardDate:String):Boolean{
        if (creditCardDate.isEmpty()){
            return false
        }
        val dateRegex = "^((0[1-9])|(1[0-2]))\\/([0-9][0-9][0-9][0-9])$".toRegex()
        return  creditCardDate.matches(dateRegex)
    }

    private  fun sendForApiResponse(creditCardTokenRequest: CreditCardTokenRequest){
        paymentViewModel.createCreditCardViewModel(creditCardTokenRequest)
    }


    override fun onClick(v: View) {
        when(v.id){
            R.id.btnCancelCardPayment -> {
                dismiss()
            }
            R.id.btnAddCarForSale -> {
                isDialogInitialized = false
                if (convertUserInputIntoClass() != null){
                    sendForApiResponse(convertUserInputIntoClass()!!)
                }

            }
        }
    }
}