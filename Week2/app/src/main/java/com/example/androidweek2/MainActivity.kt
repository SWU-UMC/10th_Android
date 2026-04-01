package com.example.androidweek2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.androidweek2.databinding.ActivityMainBinding // 이 줄이 빨간색이면 아래 설명 참고!

class MainActivity : AppCompatActivity() {

    // 1. 바인딩 객체 선언
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2. 바인딩 초기화 및 화면 설정
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 3. 네비게이션 지도를 담는 그릇(NavHost) 찾기
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.main_fragmentContainer) as NavHostFragment

        // 4. 길 찾기 대장(NavController) 꺼내기
        val navController = navHostFragment.navController

        // 5. 하단 바(BottomNavigationView)와 연결하기
        binding.mainBnv.setupWithNavController(navController)
    }
}