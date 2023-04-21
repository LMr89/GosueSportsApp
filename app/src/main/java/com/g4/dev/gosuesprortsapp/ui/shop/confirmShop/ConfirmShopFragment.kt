package com.g4.dev.gosuesprortsapp.ui.shop.confirmShop

import android.annotation.SuppressLint
import android.content.DialogInterface
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.g4.dev.gosuesprortsapp.GosueSportApplicationClass
import com.g4.dev.gosuesprortsapp.MainActivity
import com.g4.dev.gosuesprortsapp.R
import com.g4.dev.gosuesprortsapp.adapter.OnNotifyChange
import com.g4.dev.gosuesprortsapp.adapter.ProductInSaleAdapter
import com.g4.dev.gosuesprortsapp.data.model.request.payment.ChargeRequest
import com.g4.dev.gosuesprortsapp.data.model.request.payment.Metadata
import com.g4.dev.gosuesprortsapp.data.network.repository.sale.SaleTemporal
import com.g4.dev.gosuesprortsapp.databinding.AppBarMainBinding
import com.g4.dev.gosuesprortsapp.databinding.FragmentConfirmShopBinding
import com.g4.dev.gosuesprortsapp.ui.shop.ShopViewModel
import com.g4.dev.gosuesprortsapp.ui.shop.paymentDialog.PaymentDialog
import com.g4.dev.gosuesprortsapp.ui.shop.paymentDialog.PaymentViewModel
import com.g4.dev.gosuesprortsapp.ui.shop.paymentDialog.TemporalPaymentData
import com.g4.dev.gosuesprortsapp.util.alertsDialog.ConfirmSaleAlertDialog
import com.g4.dev.gosuesprortsapp.util.messages.MessageType
import com.g4.dev.gosuesprortsapp.util.messages.MessageUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.Date

class ConfirmShopFragment : Fragment() ,OnNotifyChange,OnClickListener{

    lateinit var  mainBinding: FragmentConfirmShopBinding
    lateinit var  confirmShopViewModel: ConfirmShopViewModel
    lateinit var  alertDialog: ConfirmSaleAlertDialog
    lateinit var  paymentDialog: PaymentDialog

    lateinit var mainActivity: MainActivity
    lateinit var  paymentViewModel : PaymentViewModel


     var isAuthorizedForSale = false

    lateinit var  chargeForSale:ChargeRequest

    companion object {
        fun newInstance() = ConfirmShopFragment()
    }

    private lateinit var viewModel: ConfirmShopViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        confirmShopViewModel = ViewModelProvider(this).get(ConfirmShopViewModel::class.java)
        mainBinding = FragmentConfirmShopBinding.inflate(inflater, container, false)
        mainActivity=activity as MainActivity
        paymentViewModel = ViewModelProvider(mainActivity)
            .get(PaymentViewModel::class.java)
        return mainBinding.root
    }
    override fun onResume() {
        super.onResume()
        initRecyclerView()
        initObservers()
        initListeners()
        initDialog()
        changeButtonColor(false)

    }
    private fun initListeners(){
        mainBinding.btnConfirmSale.setOnClickListener (this)
        mainBinding.bnAddPayment.setOnClickListener(this)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        paymentViewModel.resetFields()
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.i("DESTROY-----", "Se destruyo el fragmento")
    }


    @SuppressLint("SimpleDateFormat")
    private fun initObservers(){
        confirmShopViewModel.listItems.observe(viewLifecycleOwner){
            mainBinding.recProductsSalecteds.adapter = ProductInSaleAdapter(it, this)
        }

        confirmShopViewModel.subtotalMount.observe(viewLifecycleOwner){
            mainBinding.tvSubtotal.text = "S/.${it}"

        }
        confirmShopViewModel.igvMount.observe(viewLifecycleOwner){
            mainBinding.tvIgv.text = "S/.${it}"
        }
        confirmShopViewModel.totalMount.observe(viewLifecycleOwner){
            mainBinding.tvTotal.text = "S/.${it}"
            TemporalPaymentData.SALE_AMOUNT = (mainBinding.tvTotal.text.toString().substring(3).toDouble() * 100).toLong()
        }

        confirmShopViewModel.isSaleSuccessful.observe(viewLifecycleOwner){success ->
            if (success){
                TemporalPaymentData.PAYMENT_SUCCESSFULLY = true
                Toast.makeText(activity, "Venta exitosa", Toast.LENGTH_LONG).show()

                resetTemporalSale()
                returnToShopFragment()
            }else{
                MessageUtil.sendMessage(mainBinding.root, "Fallo la operacion", MessageType.ERROR)
            }
        }
        confirmShopViewModel.calculateAllMounts()

        paymentViewModel.isAuthorized.observe(mainActivity){authorized ->
            if (authorized){
                changeButtonColor(authorized)
                isAuthorizedForSale = authorized
                showMessage()
            }
        }

        paymentViewModel.isLoading.observe(mainActivity){
            mainBinding.pgLoadingSale.isVisible = it
        }
        paymentViewModel.authorizeToken.observe(mainActivity){authorizeToken ->
            if (authorizeToken.isNotEmpty()){
                chargeForSale = ChargeRequest(
                    TemporalPaymentData.SALE_AMOUNT.toString(), //Multiplicacamos por centimos  x100
                    "PEN",
                    authorizeToken,
                    "Venta realizada desde el celular chanchito",
                    "appChefsito@gmail.com",
                    1,
                    Metadata("ORD-${SimpleDateFormat("ddMMyyyyHHmmss").format(Date())}")
                )
            }
        }

        paymentViewModel.isChargeSaleSuccess.observe(mainActivity){success ->
            if (success){
                createSaleForServiceWeb()
            }
        }

        paymentViewModel.isOnLocalPayment.observe(mainActivity){changeColor ->
            changeButtonColor(changeColor)
        }
    }

    private fun showMessage(){
       // MessageUtil.sendMessage(mainBinding.root, "Metodo de pago aprobado", MessageType.SUCCESS)
    }

    private fun resetTemporalSale(){
        SaleTemporal.productsForSale.clear()
    }

    private fun returnToShopFragment(){
        requireActivity()
            .findNavController(R.id.nav_host_fragment_content_main)
            .navigate(R.id.nav_shop)
    }



    private fun initRecyclerView() {
        with(mainBinding.recProductsSalecteds){
            layoutManager = LinearLayoutManager(requireActivity().applicationContext)
        }
    }

    override fun notifyChange() {
        confirmShopViewModel.calculateAllMounts()
    }

    private fun initDialog(){

        alertDialog = ConfirmSaleAlertDialog(onSubmitClickListener ={
           // confirmShopViewModel.postSale()
            if (TemporalPaymentData.IS_ON_STORE_PAYMENT){
                confirmShopViewModel.postSale()
            }else{
                paymentViewModel.createSaleChargeViewModel( chargeForSale)
            }


        })

        paymentDialog = PaymentDialog(){
            changeButtonColor(it)
        }
    }

    private  fun changeButtonColor(change:Boolean){
        if (change) {
            mainBinding.btnConfirmSale.setBackgroundColor(
                ContextCompat.getColor(
                    GosueSportApplicationClass.INSTANCE, R.color.snackbarexito))
        } else{
            mainBinding.btnConfirmSale.setBackgroundColor(
                ContextCompat.getColor(
                    GosueSportApplicationClass.INSTANCE, R.color.snackbarerror))
        }
    }

    private fun createSaleForServiceWeb(){
        confirmShopViewModel.postSale()
    }

    override fun onClick(v: View) {
        when (v.id){
            R.id.bnAddPayment ->  {
                paymentDialog.show(parentFragmentManager, "paymentDialog")
            }
            R.id.btnConfirmSale -> {
                if (TemporalPaymentData.IS_ON_STORE_PAYMENT){
                    alertDialog.show(parentFragmentManager,"dialog")
                    return

                }

                if (!isAuthorizedForSale){
                    MessageUtil.sendMessage(mainBinding.root, "Porfavor escoge un metodo de pago", MessageType.WARNING)
                    return
                }

                if (SaleTemporal.productsForSale.isEmpty()){
                    MessageUtil.sendMessage(mainBinding.root, "No hay items para la venta", MessageType.WARNING)
                    return
                }



                alertDialog.show(parentFragmentManager,"dialog")



            }
        }

    }

}