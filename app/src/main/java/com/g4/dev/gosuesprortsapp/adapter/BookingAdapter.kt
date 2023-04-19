package com.g4.dev.gosuesprortsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.g4.dev.gosuesprortsapp.core.entity.Booking
import com.g4.dev.gosuesprortsapp.databinding.ItemBookingCardViewBinding
import com.g4.dev.gosuesprortsapp.ui.booking.BookingViewModel

class BookingAdapter(val bookigList: List<Booking>,val onTransferBookingData: OnTransferBookingData) : Adapter<BookingAdapter.BookingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        val mBinding = ItemBookingCardViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  BookingViewHolder(mBinding)
    }

    override fun getItemCount(): Int = bookigList.size

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        with(holder){
            showData(bookigList[position])
        }
    }


    inner class BookingViewHolder(val mBinding:ItemBookingCardViewBinding): ViewHolder(mBinding.root){
        fun showData(booking: Booking){
            with(mBinding){

                btnCancelBooking.setOnClickListener{
                    onTransferBookingData.onTranserData(bookingId = booking.idReserva)

                }

                etBookingDate.setText(booking.fechaInicio)
                etBookingUsuario.setText(booking.usuario)
                etBookingTime.setText(booking.tiempo.toString())
                etBookingMount.setText(booking.monto.toString())
                if (!booking.estado){
                    etBookingEstate.setText("Ya atendido")
                    btnCancelBooking.isVisible = false
                    return
                }
                etBookingEstate.setText("Por Ir al local")
                btnCancelBooking.isVisible = true



            }


        }



    }
}