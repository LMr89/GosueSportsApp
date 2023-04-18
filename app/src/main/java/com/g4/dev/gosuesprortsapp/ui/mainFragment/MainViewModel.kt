package com.g4.dev.gosuesprortsapp.ui.mainFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.g4.dev.gosuesprortsapp.data.model.response.news.NewsResponse
import com.g4.dev.gosuesprortsapp.domain.newsUseCases.GetGammingNews
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val newsList = MutableLiveData<List<NewsResponse>>()
    val isLoading = MutableLiveData<Boolean>()

    val newsUseCase = GetGammingNews()



    fun getNews(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val list = newsUseCase()
            newsList.postValue(list)
            isLoading.postValue(false)
        }
    }


}