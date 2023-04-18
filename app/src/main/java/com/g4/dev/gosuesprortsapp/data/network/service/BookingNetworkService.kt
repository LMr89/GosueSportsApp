package com.g4.dev.gosuesprortsapp.data.network.service

import android.util.Log
import com.g4.dev.gosuesprortsapp.core.entity.Booking
import com.g4.dev.gosuesprortsapp.core.retrofit.RetrofitHelper
import com.g4.dev.gosuesprortsapp.data.model.request.booking.BookingRequest
import com.g4.dev.gosuesprortsapp.data.model.response.common.CommonResponseServer
import com.g4.dev.gosuesprortsapp.data.network.interfaces.BookingApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class BookingNetworkService() {
    val retrofit = RetrofitHelper.getRetrofit()

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
}