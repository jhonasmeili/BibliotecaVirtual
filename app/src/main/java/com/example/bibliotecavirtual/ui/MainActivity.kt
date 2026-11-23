package com.example.bibliotecavirtual.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.bibliotecavirtual.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners(){
        binding.btnLivros.setOnClickListener {
            val intent = Intent(this, ListaActivity::class.java)
            startActivity(intent)
        }

        binding.btnAdicionar.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        binding.btnSuporte.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = "mailto:Suporte@BibliotecaVirtual.com.br".toUri()
            startActivity(intent)
        }

        binding.btnAvaliar.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = "market://details?".toUri()
            startActivity(intent)
        }
    }
}