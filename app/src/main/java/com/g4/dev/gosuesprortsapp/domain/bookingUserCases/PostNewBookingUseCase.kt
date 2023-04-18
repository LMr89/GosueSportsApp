package com.g4.dev.gosuesprortsapp.domain.bookingUserCases

import com.g4.dev.gosuesprortsapp.data.model.request.booking.BookingRequest
import com.g4.dev.gosuesprortsapp.data.model.response.common.CommonResponseServer
import com.g4.dev.gosuesprortsapp.data.network.service.BookingNetworkService

class PostNewBookingUseCase {
    val service = BookingNetworkService()


    suspend operator  fun invoke(bookingRequest: BookingRequest):CommonResponseServer{

        return  service.postNewBooking(bookingRequest)
    }
}