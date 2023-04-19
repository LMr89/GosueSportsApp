package com.g4.dev.gosuesprortsapp.ui.booking.history

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.g4.dev.gosuesprortsapp.GosueSportApplicationClass
import com.g4.dev.gosuesprortsapp.core.entity.Booking
import com.g4.dev.gosuesprortsapp.data.model.request.sale.IdUsuario
import com.g4.dev.gosuesprortsapp.data.model.response.common.CommonResponseServer
import com.g4.dev.gosuesprortsapp.domain.bookingUserCases.CancelBookingUseCase
import com.g4.dev.gosuesprortsapp.domain.bookingUserCases.GetBookingByUserIdUseCase
import com.g4.dev.gosuesprortsapp.util.GosueSportCommonConstants
import kotlinx.coroutines.launch

class HistoryBookingViewModel : ViewModel() {
    val bookingList= MutableLiveData<List<Booking>>()
    val responseServer = MutableLiveData<CommonResponseServer>()
    val isLoading = MutableLiveData<Boolean>()

    val cancelBookingUseCase = CancelBookingUseCase()
    val getBookingUseCase = GetBookingByUserIdUseCase()


    fun getBookings(){
       viewModelScope.launch {
           bookingList.postValue(emptyList())
           isLoading.postValue(true)
           val idUsuario =
               GosueSportApplicationClass.SHARED_PREFERENCE_MANAGER.getPreference(
                   GosueSportCommonConstants.USER_ID_PREFERENCE
               )!!.toInt()

           val response = getBookingUseCase(idUsuario)

           bookingList.postValue(response)
           isLoading.postValue(false)
       }
    }

    fun cancelBooking(bookingId:Int){
        viewModelScope.launch {
            isLoading.postValue(true)
            val response = cancelBookingUseCase(bookingId)
            responseServer.postValue(response)

            isLoading.postValue(false)

        }
    }






}