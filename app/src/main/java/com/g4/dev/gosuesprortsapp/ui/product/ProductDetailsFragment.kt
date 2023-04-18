package com.g4.dev.gosuesprortsapp.ui.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.g4.dev.gosuesprortsapp.R
import com.g4.dev.gosuesprortsapp.core.entity.Product
import com.g4.dev.gosuesprortsapp.data.network.repository.sale.ProductSale
import com.g4.dev.gosuesprortsapp.data.network.repository.sale.SaleTemporal
import com.g4.dev.gosuesprortsapp.databinding.FragmentProductDetailsBinding
import com.g4.dev.gosuesprortsapp.util.messages.MessageType
import com.g4.dev.gosuesprortsapp.util.messages.MessageUtil

class ProductDetailsFragment : Fragment(), OnClickListener {
    lateinit var  mBinding:FragmentProductDetailsBinding


     var  MAX_STOCK_PRODUCT:Int = 0
    var CURRENT_PRODUCT_STOCK_CHOSE:Int = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        mBinding = FragmentProductDetailsBinding.inflate(inflater, container , false)
        init()
        return mBinding.root
    }

    override fun onResume() {
        super.onResume()
        showDataFromRepository()
    }

    override fun onStart() {
        super.onStart()
        verifyForProductSoldOut()
    }
    private  fun init(){
        setUpListeners()
        mBinding.btnLessUnits.isEnabled = false
        mBinding.etUnitsChose.setText(CURRENT_PRODUCT_STOCK_CHOSE.toString())
    }

    private fun setUpListeners(){
        mBinding.btnMoreUnits.setOnClickListener(this)
        mBinding.btnLessUnits.setOnClickListener(this)
        mBinding.btnAddToCar.setOnClickListener(this)


    }
    private fun verifyForProductSoldOut(){
        if (SaleTemporal.currentProductSelected!!.stock == 0){
            enableOrDisableButtons(false)
            return
        }
        enableOrDisableButtons(true)
    }

    private fun enableOrDisableButtons(enable:Boolean){
        mBinding.btnMoreUnits.isEnabled = enable
        mBinding.btnLessUnits.isEnabled = enable
        mBinding.btnAddToCar.isEnabled = enable
    }

    private fun showDataFromRepository() {
        val choseProduct = SaleTemporal.currentProductSelected
        MAX_STOCK_PRODUCT = choseProduct!!.stock

        with(mBinding){
            etProductTitle.text = choseProduct.nombre
            tvProductPrice.text  = "Precio: S/.${choseProduct.precioUnitario}"
            tvProductStock.text = "Stock: ${MAX_STOCK_PRODUCT}"

            Glide
                .with(requireActivity().applicationContext)
                .load(choseProduct.imgUrl)
                .into(imgProduct)
        }


    }
    private fun updateStockChoose(){
        mBinding.etUnitsChose.setText("")
        mBinding.etUnitsChose.setText(CURRENT_PRODUCT_STOCK_CHOSE.toString())

        if (CURRENT_PRODUCT_STOCK_CHOSE == 1){
            mBinding.btnLessUnits.isEnabled = false
            return
        }
        if (CURRENT_PRODUCT_STOCK_CHOSE == MAX_STOCK_PRODUCT ){
            mBinding.btnMoreUnits.isEnabled = false
        }
    }

    override fun onClick(v: View) {


        if (v.id == R.id.btnAddToCar){


            val itemFound = SaleTemporal
                .productsForSale
                .find { ps-> ps.idProduct.idProducto == SaleTemporal.currentProductSelected!!.idProducto }


            if (itemFound != null){
                itemFound.stockChose = CURRENT_PRODUCT_STOCK_CHOSE
                MessageUtil.sendMessage(mBinding.root, "Producto modificado", MessageType.SUCCESS)
                return

            }
            SaleTemporal
                .productsForSale
                .add(ProductSale(SaleTemporal.currentProductSelected!!, CURRENT_PRODUCT_STOCK_CHOSE))
            MessageUtil.sendMessage(mBinding.root, "Producto agregado", MessageType.SUCCESS)





        }

        if (CURRENT_PRODUCT_STOCK_CHOSE == MAX_STOCK_PRODUCT){
            when(v.id){
                R.id.btnLessUnits -> {
                    CURRENT_PRODUCT_STOCK_CHOSE -=1
                    updateStockChoose()
                    mBinding.btnMoreUnits.isEnabled = true

                }
            }
            return
        }
        if (CURRENT_PRODUCT_STOCK_CHOSE >1){

            when(v.id){
                R.id.btnMoreUnits -> {
                    CURRENT_PRODUCT_STOCK_CHOSE +=1
                    updateStockChoose()
                }
                R.id.btnLessUnits -> {
                    CURRENT_PRODUCT_STOCK_CHOSE -=1
                    updateStockChoose()
                }
            }

        }else if (CURRENT_PRODUCT_STOCK_CHOSE == 1 ){

            when(v.id){
                R.id.btnMoreUnits -> {
                    CURRENT_PRODUCT_STOCK_CHOSE +=1
                    updateStockChoose()
                    mBinding.btnLessUnits.isEnabled = true
                }
            }
        }
    }


}