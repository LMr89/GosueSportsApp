package com.g4.dev.gosuesprortsapp.adapter

import com.g4.dev.gosuesprortsapp.core.entity.Product

interface OnTransferProductListener {
    fun onTransfer(product: Product)
}