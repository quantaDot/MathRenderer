package com.qdot.mathrenderer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.qdot.mathrenderer.databinding.ActivityMainBinding
import com.qdot.mathrendererlib.TextAlign

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.renderButton.setOnClickListener {
            binding.mathView.apply {
                text = binding.inputEditText.text.toString()
                textAlignment = TextAlign.CENTER
                textColor = "#FFFFFF"
                mathBackgroundColor = "#000000"
            }
        }
    }
}