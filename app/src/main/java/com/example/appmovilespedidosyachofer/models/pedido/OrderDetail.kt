package com.example.appmovilespedidosyachofer.models.pedido

data class OrderDetail(
    val id: Int,
    val quantity: Int,
    val price: String,
    val product: Product
)