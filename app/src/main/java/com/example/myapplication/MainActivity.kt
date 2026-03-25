package com.example.myapplication

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ivHappy = findViewById<ImageView>(R.id.iv_happy)
        val tvHappy = findViewById<TextView>(R.id.tv_happy)
        
        val ivExcited = findViewById<ImageView>(R.id.iv_excited)
        val tvExcited = findViewById<TextView>(R.id.tv_excited)
        
        val ivSoso = findViewById<ImageView>(R.id.iv_soso)
        val tvSoso = findViewById<TextView>(R.id.tv_soso)
        
        val ivUnrest = findViewById<ImageView>(R.id.iv_unrest)
        val tvUnrest = findViewById<TextView>(R.id.tv_unrest)
        
        val ivUpset = findViewById<ImageView>(R.id.iv_upset)
        val tvUpset = findViewById<TextView>(R.id.tv_upset)

        val textViews = listOf(tvHappy, tvExcited, tvSoso, tvUnrest, tvUpset)

        fun resetColors() {
            textViews.forEach { it.setTextColor(ContextCompat.getColor(this, R.color.black)) }
        }

        ivHappy.setOnClickListener {
            resetColors()
            tvHappy.setTextColor(ContextCompat.getColor(this, R.color.happy_color))
        }

        ivExcited.setOnClickListener {
            resetColors()
            tvExcited.setTextColor(ContextCompat.getColor(this, R.color.excited_color))
        }

        ivSoso.setOnClickListener {
            resetColors()
            tvSoso.setTextColor(ContextCompat.getColor(this, R.color.soso_color))
        }

        ivUnrest.setOnClickListener {
            resetColors()
            tvUnrest.setTextColor(ContextCompat.getColor(this, R.color.unrest_color))
        }

        ivUpset.setOnClickListener {
            resetColors()
            tvUpset.setTextColor(ContextCompat.getColor(this, R.color.upset_color))
        }
    }
}