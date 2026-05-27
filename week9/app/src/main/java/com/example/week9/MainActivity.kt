package com.example.week9

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.week9.ui.Week9App
import com.example.week9.ui.theme.Week9Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week9Theme {
                Week9App()
            }
        }
    }
}


