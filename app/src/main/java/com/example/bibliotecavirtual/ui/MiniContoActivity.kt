package com.example.bibliotecavirtual.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bibliotecavirtual.databinding.ActivityMiniContoBinding

class MiniContoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMiniContoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMiniContoBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}