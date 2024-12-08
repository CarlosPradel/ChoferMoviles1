package com.example.appmovilespedidosyachofer.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovilespedidosyachofer.databinding.ItemPedidoBinding
import com.example.appmovilespedidosyachofer.models.pedido.Order

class PedidoAdapter(private var pedidos: List<Order>) :
    RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder>() {

    fun submitList(newPedidos: List<Order>) {
        pedidos = newPedidos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidoViewHolder {
        val binding = ItemPedidoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PedidoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PedidoViewHolder, position: Int) {
        val pedido = pedidos[position]
        holder.bind(pedido)
    }

    override fun getItemCount(): Int = pedidos.size

    class PedidoViewHolder(private val binding: ItemPedidoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pedido: Order) {
            binding.textViewDireccion.text = pedido.address
            binding.textViewTotal.text = "${pedido.total} Bs"
            binding.textViewEstado.text = when (pedido.status) {
                "1" -> "Pendiente"
                "2" -> "En camino"
                "3" -> "Entregado"
                else -> "Desconocido"
            }

            println("Detalles del pedido: ${pedido.order_details}")
        }
    }
}

