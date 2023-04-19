package com.g4.dev.gosuesprortsapp.data.network.service

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.g4.dev.gosuesprortsapp.core.entity.Booking
import com.g4.dev.gosuesprortsapp.core.retrofit.RetrofitHelper
import com.g4.dev.gosuesprortsapp.data.model.request.booking.BookingRequest
import com.g4.dev.gosuesprortsapp.data.model.response.common.CommonResponseServer
import com.g4.dev.gosuesprortsapp.data.network.interfaces.BookingApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class BookingNetworkService() {
    val retrofit = RetrofitHelper.getRetrofit()

    var bookingResponse = MutableLiveData<CommonResponseServer>()

    suspend fun  postNewBooking(bookingRequest: BookingRequest):CommonResponseServer{
        return withContext(Dispatchers.IO){

            val response = retrofit
                .create(BookingApiClient::class.java)
                .postNewBooking(bookingRequest)


            response.body()!!
        }
    }

    suspend fun  getBookingByUserId(userId:Int):List<Booking>{
        return withContext(Dispatchers.IO){

            val response = retrofit
                .create(BookingApiClient::class.java)
                .getBookingByUserId(userId)


            response.body()!!
        }
    }
    suspend fun  cancelBookin(bookingId:Int):CommonResponseServer{
        return withContext(Dispatchers.IO){

            val response = retrofit
                .create(BookingApiClient::class.java)
                .cancelBooking(bookingId)


            response.body()!!
        }
    }


    fun createBookingUsingCallback(bookingRequest: BookingRequest):MutableLiveData<CommonResponseServer>{

        val request = retrofit
            .create(BookingApiClient::class.java)
            .postNewBookingCall(bookingRequest)

        request.enqueue(object :Callback<CommonResponseServer>{
            override fun onResponse(
                call: Call<CommonResponseServer>,
                response: Response<CommonResponseServer>
            ) {
                bookingResponse.value = response.body()
            }

            override fun onFailure(call: Call<CommonResponseServer>, t: Throwable) {
               Log.e("ERROR BOOKING --------", t.message.toString())
            }
        })

        return bookingResponse
    }
}