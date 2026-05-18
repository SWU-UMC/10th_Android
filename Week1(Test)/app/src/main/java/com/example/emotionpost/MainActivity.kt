package com.example.emotionpost

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val ivHappy = findViewById<ImageView>(R.id.yellow_happy)
        val tvHappy = findViewById<TextView>(R.id.tv_happy_text)

        ivHappy.setOnClickListener {

            tvHappy.setTextColor(Color.parseColor("#FFD600"))
        }

        val ivPHappy = findViewById<ImageView>(R.id.purple_happy)
        val tvPHappy = findViewById<TextView>(R.id.tv_excited_text)

        ivPHappy.setOnClickListener {

            tvPHappy.setTextColor(Color.parseColor("#87CEEB"))

    }


        val ivNormal = findViewById<ImageView>(R.id.purple_soso)
        val tvNormal = findViewById<TextView>(R.id.tv_soso_text)

        ivNormal.setOnClickListener {

            tvNormal.setTextColor(Color.parseColor("#D1C4E9"))

        }

        val ivStressed = findViewById<ImageView>(R.id.green_sad)
        val tvStressed = findViewById<TextView>(R.id.tv_green_sad_text)

        ivStressed.setOnClickListener {

            tvStressed.setTextColor(Color.parseColor("#A5D6A7"))

        }

        val ivGrumpy = findViewById<ImageView>(R.id.angry_red)
        val tvGrumpy = findViewById<TextView>(R.id.tv_angry_red_text)

        ivGrumpy.setOnClickListener {

            tvGrumpy.setTextColor(Color.parseColor("#FF5252"))

        }
}
}