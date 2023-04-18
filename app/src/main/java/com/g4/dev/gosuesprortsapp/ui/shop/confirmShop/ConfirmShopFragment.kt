package com.g4.dev.gosuesprortsapp.ui.shop.confirmShop

import android.content.DialogInterface
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.g4.dev.gosuesprortsapp.R
import com.g4.dev.gosuesprortsapp.adapter.OnNotifyChange
import com.g4.dev.gosuesprortsapp.adapter.ProductInSaleAdapter
import com.g4.dev.gosuesprortsapp.data.network.repository.sale.SaleTemporal
import com.g4.dev.gosuesprortsapp.databinding.AppBarMainBinding
import com.g4.dev.gosuesprortsapp.databinding.FragmentConfirmShopBinding
import com.g4.dev.gosuesprortsapp.ui.shop.ShopViewModel
import com.g4.dev.gosuesprortsapp.ui.shop.paymentDialog.PaymentDialog
import com.g4.dev.gosuesprortsapp.util.alertsDialog.ConfirmSaleAlertDialog
import com.g4.dev.gosuesprortsapp.util.messages.MessageType
import com.g4.dev.gosuesprortsapp.util.messages.MessageUtil
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ConfirmShopFragment : Fragment() ,OnNotifyChange,OnClickListener{

    lateinit var  mainBinding: FragmentConfirmShopBinding
    lateinit var  confirmShopViewModel: ConfirmShopViewModel
    lateinit var  alertDialog: ConfirmSaleAlertDialog
    lateinit var  paymentDialog: PaymentDialog
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
        return mainBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ConfirmShopViewModel::class.java)

    }

    override fun onResume() {
        super.onResume()
        initRecyclerView()
        initObservers()
        initListeners()
        initDialog()
    }
    private fun initListeners(){
        mainBinding.btnConfirmSale.setOnClickListener (this)
        mainBinding.bnAddPayment.setOnClickListener(this)

    }
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
        }

        confirmShopViewModel.isSaleSuccessful.observe(viewLifecycleOwner){success ->
            if (success){
                MessageUtil.sendMessage(mainBinding.root, "Venta Registrada con exito", MessageType.SUCCESS)
                resetTemporalSale()
                returnToShopFragment()

            }else{
                MessageUtil.sendMessage(mainBinding.root, "Fallo la operacion", MessageType.ERROR)
            }
        }
        confirmShopViewModel.calculateAllMounts()

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
            confirmShopViewModel.postSale()
        })

        paymentDialog = PaymentDialog()
    }

    override fun onClick(v: View) {
        when (v.id){
            R.id.bnAddPayment ->  {
                paymentDialog.show(parentFragmentManager, "paymentDialog")
            }
            R.id.btnConfirmSale -> {
                if (SaleTemporal.productsForSale.size > 0){
                    alertDialog.show(parentFragmentManager,"dialog")
                    return
                }
                MessageUtil.sendMessage(mainBinding.root, "No hay items para la venta", MessageType.WARNING)
            }
        }

    }

}