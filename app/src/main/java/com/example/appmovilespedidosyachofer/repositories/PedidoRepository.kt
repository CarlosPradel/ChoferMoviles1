package com.example.appmovilespedidosyachofer.repositories

import com.example.appmovilespedidosyachofer.api.JSONPlaceHolderService
import com.example.appmovilespedidosyachofer.models.pedido.Order
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object PedidoRepository {

    fun obtenerPedidosSinAsignar(
        token: String,
        onSuccess: (List<Order>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)

        service.obtenerPedidosSinAsignar("Bearer $token").enqueue(object : Callback<List<Order>> {
            override fun onResponse(call: Call<List<Order>>, response: Response<List<Order>>) {
                if (response.isSuccessful) {
                    val pedidos = response.body()
                    println("Pedidos no asignados obtenidos: $pedidos")
                    onSuccess(pedidos ?: emptyList())
                } else {
                    println("Error en la respuesta: ${response.errorBody()?.string()}")
                    onError(Throwable("Error al obtener pedidos"))
                }
            }

            override fun onFailure(call: Call<List<Order>>, t: Throwable) {
                println("Error de red: ${t.message}")
                onError(t)
            }
        })
    }
}



