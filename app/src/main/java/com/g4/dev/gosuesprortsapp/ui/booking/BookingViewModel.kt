package com.g4.dev.gosuesprortsapp.ui.booking

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.g4.dev.gosuesprortsapp.core.entity.Computer
import com.g4.dev.gosuesprortsapp.data.model.request.booking.BookingRequest
import com.g4.dev.gosuesprortsapp.data.model.response.common.CommonResponseServer
import com.g4.dev.gosuesprortsapp.domain.bookingUserCases.PostNewBookingUseCase
import com.g4.dev.gosuesprortsapp.domain.computerUseCases.GetAllComputers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date


class BookingViewModel : ViewModel() {

    val computerList = MutableLiveData<List<String>>()
    val actualDate = MutableLiveData<String>()
    val bookingResponse = MutableLiveData<CommonResponseServer>()
    var isLoading = MutableLiveData<Boolean>()

    init {
        actualDate.postValue(
            SimpleDateFormat("dd/MM/yyyy").format(Date())
        )
    }

    val computerUserCase = GetAllComputers()
    val postNewBookingUseCaseCase = PostNewBookingUseCase()


    fun getAllComputers(){
        viewModelScope.launch {
            computerList.postValue(
                computerUserCase()
                    .map { com -> returnOnlyId(com) }
            )
        }
    }

    fun postNewBooking(bookingRequest: BookingRequest){
        viewModelScope.launch {
           postNewBookingUseCaseCase(bookingRequest)


        }
    }

    private fun returnOnlyId(computer: Computer):String{
        return "COM-${ computer.idOrdenador}"
    }


}