package com.g4.dev.gosuesprortsapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.g4.dev.gosuesprortsapp.core.entity.Product
import com.g4.dev.gosuesprortsapp.data.network.repository.sale.SaleTemporal
import com.g4.dev.gosuesprortsapp.databinding.ItemProductCategoryBinding

class ProductAdapter(val productsList: List<Product>, val onTransferProductListener: OnTransferProductListener):Adapter<ProductAdapter.ProductViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val mBinding = ItemProductCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  ProductViewHolder(mBinding)
    }

    override fun getItemCount(): Int = productsList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        with(holder){
            showData(productsList[position])
        }
    }

    inner  class  ProductViewHolder(val mBinding: ItemProductCategoryBinding ): ViewHolder(mBinding.root){

        fun showData(product: Product){
            with(mBinding){
                tvNomProduct.text = product.nombre
                tvStock.text=  if (product.stock <=0)  "Agotado" else "Stock: ${product.stock} U"
                tvUnitPrice.text = "Precio: S/.${product.precioUnitario}"

                mBinding.root.setOnClickListener{
                    onTransferProductListener.onTransfer(product)
                }
                SaleTemporal.currentProductSelected = product

                Glide.with(mBinding.root)
                    .load(product.imgUrl)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    .into(imgItem)
            }
        }
    }
}