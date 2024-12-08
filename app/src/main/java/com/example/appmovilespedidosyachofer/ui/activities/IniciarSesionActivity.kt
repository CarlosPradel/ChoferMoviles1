package com.example.appmovilespedidosyachofer.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appmovilespedidosyachofer.databinding.ActivityIniciarSesionBinding
import com.example.appmovilespedidosyachofer.repositories.UsuarioRepository

class IniciarSesionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIniciarSesionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIniciarSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            iniciarSesion()
        }
    }

    private fun iniciarSesion() {
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()

        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        UsuarioRepository.postLogin(
            email = email,
            password = password,
            onSuccess = { token ->
                val sharedPreferences = getSharedPreferences("APP_PREFS", MODE_PRIVATE)
                sharedPreferences.edit().putString("ACCESS_TOKEN", token).apply()

                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            },
            onError = { error ->
                Toast.makeText(this, "Error al iniciar sesión: ${error.message}", Toast.LENGTH_LONG).show()
            }
        )
    }
}


