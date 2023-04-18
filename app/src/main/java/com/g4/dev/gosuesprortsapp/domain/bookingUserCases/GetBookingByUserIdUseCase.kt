package com.g4.dev.gosuesprortsapp.domain.bookingUserCases

import com.g4.dev.gosuesprortsapp.core.entity.Booking
import com.g4.dev.gosuesprortsapp.data.network.service.BookingNetworkService

class GetBookingByUserIdUseCase {
    val service = BookingNetworkService()

    suspend operator fun invoke(userId:Int):List<Booking>{
        return  service.getBookingByUserId(userId)
    }

}