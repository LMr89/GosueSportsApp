package com.g4.dev.gosuesprortsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.g4.dev.gosuesprortsapp.data.network.repository.sale.ProductSale
import com.g4.dev.gosuesprortsapp.data.network.repository.sale.SaleTemporal
import com.g4.dev.gosuesprortsapp.databinding.ItemProductInSaleBinding

class ProductInSaleAdapter(val listProducts:List<ProductSale>,val  onNotifyChange: OnNotifyChange):Adapter<ProductInSaleAdapter.ProductInSaleViewHoler>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductInSaleViewHoler {
       val binding = ItemProductInSaleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  ProductInSaleViewHoler(binding)
    }

    override fun getItemCount(): Int = listProducts.size

    override fun onBindViewHolder(holder: ProductInSaleViewHoler, position: Int) {
        with(holder){
            showData(listProducts[position])
        }
    }


    inner class  ProductInSaleViewHoler( val  mBinding: ItemProductInSaleBinding) : ViewHolder(mBinding.root){
        fun showData(productSale: ProductSale){
            with( mBinding){
                val index = SaleTemporal.productsForSale.indexOf(productSale)
                tvNameProd .text = productSale.idProduct.nombre
                tvPrice.text = "S/.${productSale.idProduct.precioUnitario}"
                tvProductTotal.text = "S/.${productSale.idProduct.precioUnitario * productSale.stockChose}"
                etUnitsChoseSale.setText(productSale.stockChose.toString())

                btnQuitProduct.setOnClickListener{

                    SaleTemporal.productsForSale.remove(productSale)
                    notifyItemRemoved(index)
                    onNotifyChange.notifyChange()
                }

                btnLessUnitsSale.setOnClickListener{
                    if (productSale.stockChose != 1){
                        productSale.stockChose -= 1
                        notifyItemChanged(index)
                        onNotifyChange.notifyChange()
                    }
                }

                btnMoreUnitsSale.setOnClickListener {
                    if (productSale.idProduct.stock != productSale.stockChose ){
                        productSale.stockChose += 1
                        notifyItemChanged(index)
                        onNotifyChange.notifyChange()
                    }

                }



            }
        }
    }
}

