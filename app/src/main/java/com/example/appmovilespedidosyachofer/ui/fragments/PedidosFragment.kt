package com.example.appmovilespedidosyachofer.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appmovilespedidosyachofer.databinding.FragmentPedidosBinding
import com.example.appmovilespedidosyachofer.repositories.PedidoRepository
import com.example.appmovilespedidosyachofer.ui.adapters.PedidoAdapter

class PedidosFragment : Fragment() {

    private var _binding: FragmentPedidosBinding? = null
    private val binding get() = _binding!!
    private lateinit var pedidoAdapter: PedidoAdapter
    private var token: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPedidosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreference = requireActivity().getSharedPreferences("APP_PREFS", 0)
        token = sharedPreference.getString("ACCESS_TOKEN", null)

        setupRecyclerView()
        cargarPedidos()
    }

    private fun setupRecyclerView() {
        pedidoAdapter = PedidoAdapter(emptyList())
        binding.recyclerViewPedidos.apply {
            adapter = pedidoAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun cargarPedidos() {
        token?.let {
            PedidoRepository.obtenerPedidosSinAsignar(
                token = it,
                onSuccess = { pedidos ->
                    if (pedidos.isEmpty()) {
                        Toast.makeText(requireContext(), "No hay pedidos disponibles", Toast.LENGTH_LONG).show()
                    } else {
                        pedidoAdapter.submitList(pedidos)
                    }
                },
                onError = { error ->
                    Toast.makeText(requireContext(), "Error: ${error.message}", Toast.LENGTH_LONG).show()
                }
            )
        } ?: Toast.makeText(
            requireContext(),
            "Autenticación no encontrada, por favor inicie sesión.",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
