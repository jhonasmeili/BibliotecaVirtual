package com.example.bibliotecavirtual.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.lifecycle.lifecycleScope
import com.example.bibliotecavirtual.databinding.ActivityCadastroBinding
import com.example.bibliotecavirtual.db.MiniContoDatabase
import com.example.bibliotecavirtual.model.MiniConto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding
    private lateinit var launcherGaleria: ActivityResultLauncher<Array<String>>
    private lateinit var uriSelecionada: String
    private lateinit var miniConto: MiniConto
    private lateinit var db: MiniContoDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = MiniContoDatabase.getInstance(this)
        loadData()
        setupViews()
        setupLauncher()
        setupListeners()
    }

    private fun loadData(){
        miniConto = intent.getSerializableExtra("mini_conto") as MiniConto
    }

    private fun setupViews(){
        binding.imgFoto.setImageURI(miniConto.foto.toUri())
        uriSelecionada = miniConto.foto
        binding.edtNome.text = Editable.Factory.getInstance().newEditable(miniConto.nome)
        binding.edtGenero.text = Editable.Factory.getInstance().newEditable(miniConto.genero)
        binding.edtSinopse.text = Editable.Factory.getInstance().newEditable(miniConto.sinopse)
        binding.edtTexto.text = Editable.Factory.getInstance().newEditable(miniConto.texto)
    }

    private fun setupLauncher(){
        launcherGaleria = registerForActivityResult(
            ActivityResultContracts.OpenDocument()
        ){uri ->
            if(uri != null){
                contentResolver.takePersistableUriPermission(
                    uri, Intent.FLAG_GRANT_READ_URI_PERMISSION
                )

                uriSelecionada = uri.toString()
                binding.imgFoto.setImageURI(uri)
            }
        }
    }

    private fun setupListeners(){
        binding.imgFoto.setOnClickListener {
            launcherGaleria.launch(arrayOf("image/*"))
        }

        binding.btnSalvar.setOnClickListener {

            val foto = uriSelecionada
            val nome = binding.edtNome.text.toString()
            val genero = binding.edtGenero.text.toString()
            val sinopse = binding.edtSinopse.text.toString()
            val texto = binding.edtTexto.text.toString()

            if(nome.isNotBlank() && genero.isNotBlank() && sinopse.isNotBlank() && texto.isNotBlank()){
                miniConto.foto = foto
                miniConto.nome = nome
                miniConto.genero = genero
                miniConto.sinopse = sinopse
                miniConto.texto = texto

                atualizarNoBanco(miniConto)

            }else{
                Toast.makeText(this, "Preencha todos os Campos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnVoltar.setOnClickListener {
            finish()
        }


    }

    private fun atualizarNoBanco(miniConto: MiniConto){
        lifecycleScope.launch(Dispatchers.IO){
            db.miniContoDao().atualizar(miniConto)
            finish()
        }
    }
}