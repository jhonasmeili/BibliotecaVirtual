package com.example.bibliotecavirtual.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bibliotecavirtual.databinding.ActivityLeituraBinding
import com.example.bibliotecavirtual.model.MiniConto

class LeituraActivity : AppCompatActivity(){
    private lateinit var binding: ActivityLeituraBinding
    private lateinit var miniConto: MiniConto
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeituraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        setupViews()
    }

    private fun loadData(){
        miniConto = intent.getSerializableExtra("mini_conto") as MiniConto
    }

    private fun setupViews(){
        binding.header.text = miniConto.nome
        binding.tvTexto.text = miniConto.texto
    }
}