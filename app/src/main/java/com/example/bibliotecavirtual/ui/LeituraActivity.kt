package com.example.bibliotecavirtual.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bibliotecavirtual.databinding.ActivityLeituraBinding

class LeituraActivity : AppCompatActivity(){
    private lateinit var binding: ActivityLeituraBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeituraBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}