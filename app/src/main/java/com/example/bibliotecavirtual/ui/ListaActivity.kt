package com.example.bibliotecavirtual.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bibliotecavirtual.adapter.MiniContoAdapter
import com.example.bibliotecavirtual.databinding.ActivityListaBinding
import com.example.bibliotecavirtual.db.MiniContoDatabase
import com.example.bibliotecavirtual.model.MiniConto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListaBinding
    private lateinit var miniContos: MutableList<MiniConto>
    private lateinit var adapter: MiniContoAdapter
    private lateinit var db: MiniContoDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadData()
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    fun loadData(){
        miniContos = mutableListOf()
        db = MiniContoDatabase.getInstance(this)

        lifecycleScope.launch(Dispatchers.IO){
            miniContos = db.miniContoDao().listarTodos().toMutableList()

            withContext(Dispatchers.Main){
                adapter.updateLista(miniContos)
            }
        }
    }

    fun setupRecyclerView(){
        adapter = MiniContoAdapter(miniContos){miniConto ->
            val intent = Intent(this, MiniContoActivity::class.java)
            intent.putExtra("mini_conto", miniConto)
            startActivity(intent)
        }

        binding.rvListaMiniContos.apply {
            layoutManager = LinearLayoutManager(this@ListaActivity)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = this@ListaActivity.adapter
        }
    }
}