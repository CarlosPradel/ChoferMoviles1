package com.example.appmovilespedidosyachofer.models

data class Pedido(
    val id: Long,
    val usuarioId: Long,
    val restauranteId: Long,
    val total: Double,
    val direccionEntrega: String,
    val latitud: Double,
    val longitud: Double,
    val estado: Int // 0 = sin asignar, 1 = aceptado, etc.
)
