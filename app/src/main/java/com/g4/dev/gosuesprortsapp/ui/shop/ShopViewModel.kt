package com.g4.dev.gosuesprortsapp.ui.shop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.g4.dev.gosuesprortsapp.core.entity.Category
import com.g4.dev.gosuesprortsapp.core.entity.Product
import com.g4.dev.gosuesprortsapp.domain.categoryUseCases.GetAllCategories
import com.g4.dev.gosuesprortsapp.domain.productUseCases.GetAllProductsByCategory
import kotlinx.coroutines.launch

class ShopViewModel : ViewModel() {

    val categoriesList = MutableLiveData<List<Category>>()
    val productsList = MutableLiveData<List<Product>>()
    val isLoading = MutableLiveData<Boolean>()

    val getCategoriesUseCase = GetAllCategories()
    val getAllProductsByCategory= GetAllProductsByCategory()


    fun getAllCategories(){
        isLoading.postValue(true)
        viewModelScope.launch {
            val categories = getCategoriesUseCase()
            categoriesList.postValue(categories)
        }
        isLoading.postValue(false)
    }

    fun getProductsByCategory(idCategory:Int){
        viewModelScope.launch {
            isLoading.postValue(true)
            productsList.postValue(
                getAllProductsByCategory(idCategory = idCategory ,
                pageNumber = 1 , pageSize = 4)!!.content
            )
            isLoading.postValue(false)

        }
    }




}