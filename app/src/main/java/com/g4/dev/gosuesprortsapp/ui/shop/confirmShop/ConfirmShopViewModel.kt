package com.g4.dev.gosuesprortsapp.ui.shop.confirmShop

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.g4.dev.gosuesprortsapp.GosueSportApplicationClass
import com.g4.dev.gosuesprortsapp.data.model.request.sale.*
import com.g4.dev.gosuesprortsapp.data.network.repository.sale.ProductSale
import com.g4.dev.gosuesprortsapp.data.network.repository.sale.SaleTemporal
import com.g4.dev.gosuesprortsapp.domain.saleUseCase.NewSaleUseCase
import com.g4.dev.gosuesprortsapp.util.GosueSportCommonConstants
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ConfirmShopViewModel : ViewModel() {
    val listItems = MutableLiveData<List<ProductSale>>()

    val subtotalMount = MutableLiveData<Double>()
    val igvMount = MutableLiveData<Double>()
    val totalMount = MutableLiveData<Double>()
    val isSaleSuccessful = MutableLiveData<Boolean>()

    private var mSubTotal = 0.0
    private var mIgv = 0.0
    private var mTotal = 0.0
    private val postNewSaleUseCase = NewSaleUseCase()


    init {
        listItems.postValue(SaleTemporal.productsForSale)
    }

    fun calculateAllMounts(){
        calculateSubTotal()
        calculateIgv()
        calculateTotal()
    }

    private  fun calculateSubTotal():Double {
        mSubTotal = 0.0
        for ( prod in SaleTemporal.productsForSale){
            mSubTotal += (prod.idProduct.precioUnitario * prod.stockChose)
        }
        subtotalMount.postValue(
            mSubTotal

        )
        return mSubTotal
    }
    private  fun calculateIgv():Double{
        mIgv = 0.0
        mIgv = kotlin.math.round((mSubTotal * 0.18)*10) / 10
        igvMount.postValue(mIgv)
        return  mIgv
    }
    private  fun calculateTotal():Double{
        mTotal = 0.0
        mTotal = mSubTotal  + mIgv
        totalMount.postValue(mTotal)
        return  mIgv
    }



     fun postSale()
    {
        viewModelScope.launch {

            val sale = SaleRequest(
                detalleVentas = convertSalesListToDetalleVenta(),
                idCliente = IdCliente(2),
                idUsuario =  IdUsuario(GosueSportApplicationClass.SHARED_PREFERENCE_MANAGER.getPreference(
                    GosueSportCommonConstants.USER_ID_PREFERENCE
                )!!.toInt())
            )
            Log.i("SALE FOR SERVER", sale.toString())
            val response = postNewSaleUseCase(sale)

             if (response!!.httpStatus == 200){
                isSaleSuccessful.postValue(true)

            }else{
                isSaleSuccessful.postValue(false)
            }

        }
    }

    private  fun convertSalesListToDetalleVenta():List<DetalleVenta>{
        return  SaleTemporal
            .productsForSale
            .map {prodSale->
                DetalleVenta(idProducto = IdProducto(prodSale.idProduct.idProducto),
                            cantidad = prodSale.stockChose)
            }
    }






}