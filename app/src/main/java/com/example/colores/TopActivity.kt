package com.example.colores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.colores.databinding.ActivityColoresBinding
import com.example.colores.databinding.ActivityTopBinding

class TopActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTopBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var myIntent = intent
        binding.score.text = myIntent.getIntExtra("score",0).toString()
    }
}