package com.example.appmovilespedidosyachofer.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appmovilespedidosyachofer.R
import com.example.appmovilespedidosyachofer.databinding.ActivityRegistroBinding
import com.example.appmovilespedidosyachofer.repositories.UsuarioRepository
import com.example.appmovilespedidosyachofer.ui.viewmodels.UsuarioViewModel
import com.example.appmovilespedidosyacliente.models.Usuario

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    private val viewModel: UsuarioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonRegister.setOnClickListener {
            registrarUsuario()
        }
    }

    private fun registrarUsuario() {
        val name = binding.editTextName.text.toString()
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()

        if (name.isBlank() || email.isBlank() || password.isBlank()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            return
        }

        val usuario = Usuario(name, email, password, role = 2)

        viewModel.crearUsuario(usuario,
            onSuccess = {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, IniciarSesionActivity::class.java))
            },
            onError = { error ->
                Toast.makeText(this, "Error al registrar: ${error.message}", Toast.LENGTH_LONG).show()
            }
        )
    }
}
