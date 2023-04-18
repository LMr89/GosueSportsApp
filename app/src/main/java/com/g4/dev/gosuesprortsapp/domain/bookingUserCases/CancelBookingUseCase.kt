package com.g4.dev.gosuesprortsapp.domain.bookingUserCases

import com.g4.dev.gosuesprortsapp.data.model.request.booking.BookingRequest
import com.g4.dev.gosuesprortsapp.data.model.response.common.CommonResponseServer
import com.g4.dev.gosuesprortsapp.data.network.service.BookingNetworkService

class CancelBookingUseCase {
    val service = BookingNetworkService()

    suspend operator  fun invoke(bookingId:Int): CommonResponseServer {

        return  service.cancelBookin(bookingId)
    }
}