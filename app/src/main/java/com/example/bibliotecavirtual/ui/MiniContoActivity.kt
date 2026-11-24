package com.example.bibliotecavirtual.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.bibliotecavirtual.databinding.ActivityMiniContoBinding
import com.example.bibliotecavirtual.model.MiniConto

class MiniContoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMiniContoBinding
    private lateinit var miniConto: MiniConto
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMiniContoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        setupViews()
        setupListeners()
    }

    private fun loadData(){
        miniConto = intent.getSerializableExtra("mini_conto") as MiniConto
    }

    private fun setupViews(){
        binding.imgFoto.setImageURI(miniConto.foto.toUri())
        binding.tvNome.text = miniConto.nome
        binding.tvGenero.text = "Gênero: " + miniConto.genero
        binding.tvSinpose.text = miniConto.sinopse
    }

    private fun setupListeners(){
        binding.btnLer.setOnClickListener{
            intent = Intent(this, LeituraActivity::class.java)
            intent.putExtra("mini_conto", miniConto)
            startActivity(intent)
        }

        binding.btnVoltar.setOnClickListener{
            finish()
        }
    }
}