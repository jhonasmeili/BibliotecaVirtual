package com.example.bibliotecavirtual.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.lifecycle.lifecycleScope
import com.example.bibliotecavirtual.databinding.ActivityMiniContoBinding
import com.example.bibliotecavirtual.db.MiniContoDatabase
import com.example.bibliotecavirtual.model.MiniConto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MiniContoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMiniContoBinding
    private lateinit var miniConto: MiniConto
    private lateinit var db: MiniContoDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMiniContoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = MiniContoDatabase.getInstance(this)
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

        binding.btnEditar.setOnClickListener{
            intent = Intent(this, EditarActivity::class.java)
            intent.putExtra("mini_conto", miniConto)
            startActivity(intent)
            finish()
        }

        binding.btnVoltar.setOnClickListener{
            finish()
        }

        binding.btnExcluir.setOnClickListener{
            lifecycleScope.launch(Dispatchers.IO){
                db.miniContoDao().deletar(miniConto)
                finish()
            }
        }
    }
}