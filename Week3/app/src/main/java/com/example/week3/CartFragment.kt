package com.example.week3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment

class CartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                CartScreen(
                    onOrderClick = {
                        (activity as? MainActivity)?.moveToPurchase()
                    }
                )
            }
        }
    }
}

@Composable
fun CartScreen(
    onOrderClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(120.dp))

            Image(
                painter = painterResource(id = R.drawable.bagcircle),
                contentDescription = "장바구니",
                modifier = Modifier.size(140.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "장바구니가 비어있습니다.\n제품을 추가하면 여기에 표시됩니다.",
                fontSize = 18.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                lineHeight = 28.sp
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 90.dp)
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .height(58.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(Color.Black)
                .clickable {
                    onOrderClick()
                },
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "주문하기",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}