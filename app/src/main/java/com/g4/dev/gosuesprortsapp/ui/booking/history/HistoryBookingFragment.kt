package com.g4.dev.gosuesprortsapp.ui.booking.history

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.g4.dev.gosuesprortsapp.R
import com.g4.dev.gosuesprortsapp.adapter.BookingAdapter
import com.g4.dev.gosuesprortsapp.adapter.OnTransferBookingData
import com.g4.dev.gosuesprortsapp.databinding.FragmentHistoryBookingBinding
import com.g4.dev.gosuesprortsapp.databinding.FragmentMainBinding
import com.g4.dev.gosuesprortsapp.util.messages.MessageType
import com.g4.dev.gosuesprortsapp.util.messages.MessageUtil

class HistoryBookingFragment : Fragment(), OnTransferBookingData {

    lateinit var  mainBinding: FragmentHistoryBookingBinding
    lateinit var historyViewModel :HistoryBookingViewModel
    companion object {
        fun newInstance() = HistoryBookingFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainBinding = FragmentHistoryBookingBinding.inflate(inflater, container, false)


        return mainBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        historyViewModel = ViewModelProvider(this)
            .get(HistoryBookingViewModel::class.java)
        initComponents()
        initObservers()
    }

    override fun onResume() {
        super.onResume()
        historyViewModel.getBookings()


    }
    private fun initComponents(){
        with(mainBinding.recBooking){
            layoutManager = LinearLayoutManager(activity as Context)
        }
    }
    private fun initObservers(){
        historyViewModel.bookingList.observe(viewLifecycleOwner){
            if (it.isEmpty()){
                mainBinding.tvBookingMessage.isVisible = true
            }
            else{
                mainBinding.tvBookingMessage.isVisible = false
                mainBinding.recBooking.adapter = null
                mainBinding.recBooking.adapter = BookingAdapter(it,this)
            }


        }
        historyViewModel.responseServer.observe(viewLifecycleOwner){
            showMessage(it.mensaje)
        }
        historyViewModel.isLoading.observe(viewLifecycleOwner){
            mainBinding.pgLoadingBooking.isVisible = it
        }
    }

    private fun  showMessage(message:String){
        MessageUtil.sendMessage(mainBinding.root, message, MessageType.SUCCESS)

    }

    override fun onTranserData(bookingId: Int) {
        historyViewModel.cancelBooking(bookingId)

        historyViewModel.getBookings()
    }

}