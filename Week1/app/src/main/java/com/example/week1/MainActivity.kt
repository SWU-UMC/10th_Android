package com.example.week1

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.week1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        fun resetTextColors() {
            binding.textgood.setTextColor(Color.BLACK)
            binding.texthappy.setTextColor(Color.BLACK)
            binding.textsoso.setTextColor(Color.BLACK)
            binding.textbad.setTextColor(Color.BLACK)
            binding.textangry.setTextColor(Color.BLACK)
        }

        binding.good.setOnClickListener {
            resetTextColors()
            binding.textgood.setTextColor(
                ContextCompat.getColor(this, R.color.good)
            )
        }

        binding.happy.setOnClickListener {
            resetTextColors()
            binding.texthappy.setTextColor(
                ContextCompat.getColor(this, R.color.happy)
            )
        }

        binding.soso.setOnClickListener {
            resetTextColors()
            binding.textsoso.setTextColor(
                ContextCompat.getColor(this, R.color.soso)
            )
        }

        binding.bad.setOnClickListener {
            resetTextColors()
            binding.textbad.setTextColor(
                ContextCompat.getColor(this, R.color.bad)
            )
        }

        binding.angry.setOnClickListener {
            resetTextColors()
            binding.textangry.setTextColor(
                ContextCompat.getColor(this, R.color.angry)
            )
        }
    }
}