package com.example.appmovilespedidosyachofer.models.pedido

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val restaurant_id: Int,
    val image: String
)
