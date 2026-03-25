package com.example.week1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import android.widget.ImageView
import android.graphics.Color

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val text1 : TextView = findViewById<TextView>(R.id.text1)
        val text2 : TextView = findViewById<TextView>(R.id.text2)
        val text3 : TextView = findViewById<TextView>(R.id.text3)
        val text4 : TextView = findViewById<TextView>(R.id.text4)
        val text5 : TextView = findViewById<TextView>(R.id.text5)
        val good : ImageView = findViewById<ImageView>(R.id.good)
        val happy : ImageView = findViewById<ImageView>(R.id.happy)
        val soso : ImageView = findViewById<ImageView>(R.id.soso)
        val bad : ImageView = findViewById<ImageView>(R.id.bad)
        val angry : ImageView = findViewById<ImageView>(R.id.angry)

        fun resetTextColors() {
            text1.setTextColor(Color.BLACK)
            text2.setTextColor(Color.BLACK)
            text3.setTextColor(Color.BLACK)
            text4.setTextColor(Color.BLACK)
            text5.setTextColor(Color.BLACK)
        }

        good.setOnClickListener {
            resetTextColors()
            text1.setTextColor(Color.parseColor("#F9DC77"))
        }

        happy.setOnClickListener {
            resetTextColors()
            text2.setTextColor(Color.parseColor("#AEE9FE"))
        }

        soso.setOnClickListener {
            resetTextColors()
            text3.setTextColor(Color.parseColor("#94A5FE"))
        }

        bad.setOnClickListener {
            resetTextColors()
            text4.setTextColor(Color.parseColor("#77C48D"))
        }

        angry.setOnClickListener {
            resetTextColors()
            text5.setTextColor(Color.parseColor("#D94F49"))
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

}