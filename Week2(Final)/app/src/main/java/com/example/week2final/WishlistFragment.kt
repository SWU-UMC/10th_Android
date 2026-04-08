package com.example.myapplication // 👈 은지님 패키지명 확인!

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class WishlistFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // fragment_wishlist.xml 레이아웃 파일을 화면에 붙여줍니다.
        return inflater.inflate(R.layout.fragment_wishlist, container, false)
    }
}