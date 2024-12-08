package com.example.appmovilespedidosyachofer.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appmovilespedidosyachofer.R
import com.example.appmovilespedidosyachofer.ui.fragments.MapsFragment
import com.example.appmovilespedidosyachofer.ui.fragments.PedidosFragment
import com.example.appmovilespedidosyachofer.ui.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, MapsFragment())
            .commit()

        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_map -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, MapsFragment())
                        .commit()
                    true
                }
                R.id.action_pedidos -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, PedidosFragment())
                        .commit()
                    true
                }
                R.id.action_profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, ProfileFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}

