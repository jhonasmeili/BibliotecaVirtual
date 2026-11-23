package com.example.bibliotecavirtual.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.bibliotecavirtual.databinding.ItemMiniContoBinding
import com.example.bibliotecavirtual.model.MiniConto

class MiniContoAdapter(
    private var miniContos: List<MiniConto>,
    private var onClick: (MiniConto) -> Unit
) : RecyclerView.Adapter<MiniContoAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMiniContoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(miniContos[position])
    }

    override fun getItemCount(): Int = miniContos.size

    fun updateLista(novosMiniContos: List<MiniConto>){
        this.miniContos = novosMiniContos
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ItemMiniContoBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(miniConto: MiniConto){
            binding.imgFoto.setImageURI(miniConto.foto.toUri())
            binding.tvNome.text = miniConto.nome
            binding.tvGenero.text = "Gênero: " + miniConto.genero
            binding.root.setOnClickListener{onClick(miniConto)}
        }
    }
}