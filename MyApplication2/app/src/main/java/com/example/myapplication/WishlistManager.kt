package com.example.myapplication // 👈 은지님의 실제 패키지명 (빨간 줄 나오면 Alt+Enter)

import java.util.ArrayList

object WishlistManager {
    // 하트가 눌린 상품들만 담는 리스트
    val wishItems = ArrayList<NikeItem>()

    // 위시리스트에 추가하거나 삭제하는 함수
    fun toggleWish(item: NikeItem) {
        if (wishItems.contains(item)) {
            wishItems.remove(item)
        } else {
            wishItems.add(item)
        }
    }
}