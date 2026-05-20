package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var textHappy: TextView
    lateinit var textExcited: TextView
    lateinit var textNormal: TextView
    lateinit var textAnxious: TextView
    lateinit var textAngry: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emotion_select)

        val btnBack = findViewById<ImageButton>(R.id.btnBack)

        val itemHappy = findViewById<LinearLayout>(R.id.itemHappy)
        val itemExcited = findViewById<LinearLayout>(R.id.itemExcited)
        val itemNormal = findViewById<LinearLayout>(R.id.itemNormal)
        val itemAnxious = findViewById<LinearLayout>(R.id.itemAnxious)
        val itemAngry = findViewById<LinearLayout>(R.id.itemAngry)

        textHappy = findViewById(R.id.textHappy)
        textExcited = findViewById(R.id.textExcited)
        textNormal = findViewById(R.id.textNormal)
        textAnxious = findViewById(R.id.textAnxious)
        textAngry = findViewById(R.id.textAngry)

        btnBack.setOnClickListener {
            finish()
        }

        itemHappy.setOnClickListener {
            resetTextColor()
            textHappy.setTextColor(Color.parseColor("#FF9800"))
            showEmotion("행복")
        }

        itemExcited.setOnClickListener {
            resetTextColor()
            textExcited.setTextColor(Color.parseColor("#2196F3"))
            showEmotion("흥분")
        }

        itemNormal.setOnClickListener {
            resetTextColor()
            textNormal.setTextColor(Color.parseColor("#9E9E9E"))
            showEmotion("평범")
        }

        itemAnxious.setOnClickListener {
            resetTextColor()
            textAnxious.setTextColor(Color.parseColor("#4CAF50"))
            showEmotion("불안")
        }

        itemAngry.setOnClickListener {
            resetTextColor()
            textAngry.setTextColor(Color.parseColor("#F44336"))
            showEmotion("화남")
        }
    }

    private fun resetTextColor() {
        textHappy.setTextColor(Color.BLACK)
        textExcited.setTextColor(Color.BLACK)
        textNormal.setTextColor(Color.BLACK)
        textAnxious.setTextColor(Color.BLACK)
        textAngry.setTextColor(Color.BLACK)
    }

    private fun showEmotion(emotion: String) {
        Toast.makeText(this, "선택한 감정: $emotion", Toast.LENGTH_SHORT).show()
    }
}