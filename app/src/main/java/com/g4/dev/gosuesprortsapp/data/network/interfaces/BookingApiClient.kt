package com.g4.dev.gosuesprortsapp.data.network.interfaces

import com.g4.dev.gosuesprortsapp.core.entity.Booking
import com.g4.dev.gosuesprortsapp.data.model.request.booking.BookingRequest
import com.g4.dev.gosuesprortsapp.data.model.response.common.CommonResponseServer
import com.g4.dev.gosuesprortsapp.util.RetrofitUrlConstants
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface BookingApiClient {

    @POST(RetrofitUrlConstants.POST_NEW_BOOKING)
    suspend fun  postNewBooking(booking:BookingRequest):Response<CommonResponseServer>


    @GET(RetrofitUrlConstants.GET_BOOKING_BY_USER_ID_URL)
    suspend fun  getBookingByUserId(@Query("idUsuario") userId:Int):Response<List<Booking>>

    @DELETE(RetrofitUrlConstants.CANCEL_BOOKING_URL+"{id}")
    suspend fun  cancelBooking(@Path("id") bookinId:Int):Response<CommonResponseServer>
}