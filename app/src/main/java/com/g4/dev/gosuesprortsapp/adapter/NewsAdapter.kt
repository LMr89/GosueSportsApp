package com.g4.dev.gosuesprortsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.g4.dev.gosuesprortsapp.GosueSportApplicationClass
import com.g4.dev.gosuesprortsapp.data.model.response.news.NewsResponse
import com.g4.dev.gosuesprortsapp.databinding.ItemNewsCardViewBinding

class NewsAdapter(val newsList: List<NewsResponse>) : Adapter<NewsAdapter.NewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  NewsViewHolder(binding)
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        with(holder){
            setUpData(newsList[position])
        }
    }



    inner class NewsViewHolder(val mBinding:ItemNewsCardViewBinding) : ViewHolder(mBinding.root){
        fun setUpData(news:NewsResponse){
            with(mBinding){
                tvNewTitle.text = news.title
                tvNewDescription.text = news.description
                tvNewDate.text = news.date
                Glide
                    .with(GosueSportApplicationClass.INSTANCE.applicationContext)
                    .load(news.image)
                    .diskCacheStrategy(DiskCacheStrategy.DATA)
                    // .preload(newImg.width, newImg.height)
                    .into(newImg)
            }
        }

    }

}