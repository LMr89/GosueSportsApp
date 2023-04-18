package com.g4.dev.gosuesprortsapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.g4.dev.gosuesprortsapp.core.entity.Category
import com.g4.dev.gosuesprortsapp.databinding.ItemCategoryCardViewBinding
import com.g4.dev.gosuesprortsapp.util.messages.MessageType
import com.g4.dev.gosuesprortsapp.util.messages.MessageUtil

class CategoryAdapter(val categories:List<Category>, onCategoryClickListener: OnCategoryClickListener):Adapter<CategoryAdapter.CategoryViewHolder>() {

     var onClickListenerCategory:OnCategoryClickListener

    init {
        onClickListenerCategory  = onCategoryClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val _binding = ItemCategoryCardViewBinding.inflate(LayoutInflater.from(parent.context),parent, false)

        return  CategoryViewHolder(_binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        with(holder){
            showData(categories[position])

        }
    }

    override fun getItemCount(): Int = categories.size


    inner class CategoryViewHolder(val mBinding:ItemCategoryCardViewBinding):ViewHolder(mBinding.root){
        fun showData(cat:Category){
            with(mBinding){
                tvCategoryName.text = cat.nombre
            }

            mBinding.root.setOnClickListener {

                onClickListenerCategory.onTransferIdForProducts(cat.idCategoria)
            }
        }

    }

}