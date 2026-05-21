package com.example.week2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                HomeScreen()
            }
        }
    }

    @Composable
    fun HomeScreen() {
        val dummyProducts = remember {
            mutableStateListOf(
                Product(1, "Air Jordan XXXVI", "Basketball Shoes", "US$185", R.drawable.img_air_jordan_xxxvi, category = "Basketball Shoes"),
                Product(2, "Nike Air Force 1 '07", "Men's Shoes", "US$115", R.drawable.img_nike_air_force, category = "Men's Shoes"),
                Product(3, "Nike Everyday Plus Cushioned", "Training Socks", "US$20", R.drawable.img_nike_everyday_plus_cushioned, category = "Training Socks")
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            item {
                Column(modifier = Modifier.padding(start = 20.dp, top = 20.dp)) {
                    Text(
                        text = stringResource(id = R.string.home_discover),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.nike_black)
                    )
                    Text(
                        text = stringResource(id = R.string.home_date),
                        fontSize = 14.sp,
                        color = colorResource(id = R.color.nike_gray)
                    )
                }
            }

            item {
                Image(
                    painter = painterResource(id = R.drawable.ic_homeimg),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    contentScale = ContentScale.FillWidth
                )
            }

            item {
                Column(modifier = Modifier.padding(start = 20.dp, top = 20.dp)) {
                    Text(
                        text = "What's new",
                        fontSize = 12.sp,
                        color = colorResource(id = R.color.nike_gray)
                    )
                    Text(
                        text = "나이키 최신 상품",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.nike_black)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        items = dummyProducts,
                        key = { it.id }
                    ) { product ->
                        ProductItem(
                            product = product,
                            onItemClick = { navigateToDetail(it) },
                            onWishlistClick = { clickedProduct ->
                                val index = dummyProducts.indexOfFirst { it.id == clickedProduct.id }
                                if (index != -1) {
                                    dummyProducts[index] = dummyProducts[index].copy(isWishlisted = !dummyProducts[index].isWishlisted)
                                }
                            },
                            // fillParentMaxWidth()를 사용하여 기존 세로 리스트일 때와 동일한 크기를 유지
                            modifier = Modifier.fillParentMaxWidth()
                        )
                    }
                }
            }
        }
    }

    private fun navigateToDetail(product: Product) {
        val bundle = Bundle().apply {
            putParcelable("product", product)
        }
        findNavController().navigate(R.id.action_homeFragment_to_productDetailFragment, bundle)
    }
}
