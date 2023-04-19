package com.g4.dev.gosuesprortsapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.g4.dev.gosuesprortsapp.data.model.response.sale.SaleResponse
import com.g4.dev.gosuesprortsapp.databinding.ItemSaleHistoryCardViewBinding

class SaleAdapter(val saleList:List<SaleResponse>,
    val onTransferUrl: OnTransferUrl) : Adapter<SaleAdapter.SaleViewHoler>() {

    inner class SaleViewHoler(val mBinding: ItemSaleHistoryCardViewBinding) : ViewHolder(mBinding.root){
            fun loadData(saleResponse: SaleResponse){
                with(mBinding){
                    etClient.setText(saleResponse.clienteNombre)
                    etDate.setText(saleResponse.fecha)
                    etIgv.setText(saleResponse.igv.toString())
                    etTotal.setText(saleResponse.total.toString())
                    etItems.setText("${saleResponse.totalItems} productos adquiridos")

                    /**
                     * btnDownloadTicket.setOnClickListener{
                    Log.i("URL FOR DOWNLOAD", saleResponse.ticketUri)
                    onTransferUrl.onDownloadUrl(saleResponse.ticketUri.replace("http", "https"), saleResponse.idVenta)
                    }
                     */

                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleViewHoler {
        val mBinding = ItemSaleHistoryCardViewBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return  SaleViewHoler(mBinding)
    }

    override fun getItemCount(): Int = saleList.size

    override fun onBindViewHolder(holder: SaleViewHoler, position: Int) {
        with(holder){
            loadData(saleList[position])
        }
    }
}