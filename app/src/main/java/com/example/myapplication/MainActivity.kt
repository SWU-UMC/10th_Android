package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textViews = listOf(
            binding.tvHappy,
            binding.tvExcited,
            binding.tvSoso,
            binding.tvUnrest,
            binding.tvUpset
        )

        fun resetColors() {
            textViews.forEach { it.setTextColor(ContextCompat.getColor(this, R.color.black)) }
        }

        binding.ivHappy.setOnClickListener {
            resetColors()
            binding.tvHappy.setTextColor(ContextCompat.getColor(this, R.color.happy_color))
        }

        binding.ivExcited.setOnClickListener {
            resetColors()
            binding.tvExcited.setTextColor(ContextCompat.getColor(this, R.color.excited_color))
        }

        binding.ivSoso.setOnClickListener {
            resetColors()
            binding.tvSoso.setTextColor(ContextCompat.getColor(this, R.color.soso_color))
        }

        binding.ivUnrest.setOnClickListener {
            resetColors()
            binding.tvUnrest.setTextColor(ContextCompat.getColor(this, R.color.unrest_color))
        }

        binding.ivUpset.setOnClickListener {
            resetColors()
            binding.tvUpset.setTextColor(ContextCompat.getColor(this, R.color.upset_color))
        }
    }
}
