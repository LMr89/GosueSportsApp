package com.g4.dev.gosuesprortsapp.ui.shop.history

import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.g4.dev.gosuesprortsapp.adapter.OnTransferUrl
import com.g4.dev.gosuesprortsapp.adapter.SaleAdapter
import com.g4.dev.gosuesprortsapp.databinding.FragmentSaleHistoryBinding

class SaleHistoryFragment : Fragment(), OnTransferUrl {

    companion object {
        fun newInstance() = SaleHistoryFragment()
    }

    lateinit var mBinding:FragmentSaleHistoryBinding
    private lateinit var saleHistoryViewModel: SaleHistoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentSaleHistoryBinding.inflate(inflater, container, false)

        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        saleHistoryViewModel = ViewModelProvider(this).get(SaleHistoryViewModel::class.java)
        with(mBinding.redSalesHistory){
            layoutManager = LinearLayoutManager(activity as Context)
        }

    }

    override fun onResume() {
        super.onResume()
        setObservers()
        saleHistoryViewModel.getAllSales()
    }



    private fun setObservers(){
        saleHistoryViewModel.saleList.observe(viewLifecycleOwner){
            mBinding.redSalesHistory.adapter = SaleAdapter(it!!, this)
        }
        saleHistoryViewModel.isLoadingSales.observe(viewLifecycleOwner){
            mBinding.pgSalesLoading.isVisible = it
        }

    }

    override fun onDownloadUrl(ticketUrl: String, saleId:Int) {
        val request = DownloadManager.Request(Uri.parse(ticketUrl))
            .setTitle("TicketVenta-${saleId}")
            .setDescription("Descarga ticket venta")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setAllowedOverMetered(true)

        val service = requireActivity().getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        service.enqueue(request)
    }


}