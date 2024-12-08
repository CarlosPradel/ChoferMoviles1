package com.example.appmovilespedidosyachofer.repositories

import com.example.appmovilespedidosyachofer.api.JSONPlaceHolderService
import com.example.appmovilespedidosyacliente.models.LoginResponse
import com.example.appmovilespedidosyacliente.models.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UsuarioRepository {

    fun postCrearUsuario(
        usuario: Usuario,
        onSuccess: (Usuario) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        service.crearUsuario(usuario).enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (response.isSuccessful) {
                    response.body()?.let { createdUsuario ->
                        onSuccess(createdUsuario)
                    } ?: onError(Throwable("Error: Respuesta vacía"))
                } else {
                    onError(Throwable("Error en la respuesta: ${response.errorBody()?.string()}"))
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                println("Error: ${t.message}")
                onError(t)
            }
        })
    }


    fun postLogin(
        email: String,
        password: String,
        onSuccess: (String) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)
        val credentials = mapOf("email" to email, "password" to password)

        service.inicioSesionUsuario(credentials).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { loginResponse ->
                        onSuccess(loginResponse.access_token)
                    } ?: onError(Throwable("Error: Respuesta vacía"))
                } else {
                    val error = response.errorBody()?.string() ?: "Error desconocido"
                    onError(Throwable(error))
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                onError(t)
            }
        })
    }


    fun me(
        token: String,
        onSuccess: (Usuario) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        val service = retrofit.create(JSONPlaceHolderService::class.java)

        service.obtenerUsuario("Bearer $token").enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (response.isSuccessful) {
                    response.body()?.let { usuario ->
                        onSuccess(usuario)
                    } ?: onError(Throwable("Error: Respuesta vacía"))
                } else {
                    onError(Throwable("Error en la respuesta: ${response.errorBody()?.string()}"))
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                onError(t)
            }
        })
    }

}
