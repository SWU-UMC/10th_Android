package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class BuyFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 이 줄이 "내 육체는 buyfragment.xml이야"라고 연결해주는 핵심입니다!
        return inflater.inflate(R.layout.buyfragment, container, false)
    }
}