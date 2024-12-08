package com.example.appmovilespedidosyachofer.api

import com.example.appmovilespedidosyachofer.models.pedido.Order
import com.example.appmovilespedidosyacliente.models.LoginResponse
import com.example.appmovilespedidosyacliente.models.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface JSONPlaceHolderService {
    @POST("api/users")
    fun crearUsuario(@Body usuario: Usuario): Call<Usuario>

    @POST("api/users/login")
    fun inicioSesionUsuario(@Body credentials: Map<String, String>): Call<LoginResponse>

    @GET("api/orders")
    fun verPedidosUsuario(@Header("Authorization") token: String): Call<List<Order>>

    @GET("api/orders/free")
    fun obtenerPedidosSinAsignar(@Header("Authorization") token: String): Call<List<Order>>

    @GET("api/me")
    fun obtenerUsuario(@Header("Authorization") token: String): Call<Usuario>

}
