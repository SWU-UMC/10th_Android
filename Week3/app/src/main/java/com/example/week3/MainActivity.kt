package com.example.week3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.week3.ui.BottomBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var dataStoreManager: DataStoreManager

    private var selectedTab by mutableStateOf("home")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        dataStoreManager = DataStoreManager(this)

        lifecycleScope.launch {
            val existingData = dataStoreManager.getAllProducts().first()

            if (existingData.isEmpty()) {
                val dummyData = listOf(
                    Product("AirJordan1", "$115", R.drawable.jordan1),
                    Product("AirJordan2", "$120", R.drawable.jordan2),
                    Product("Nike Everyday Plus", "$10", R.drawable.socks)
                )

                dataStoreManager.saveAllProducts(dummyData)
            }
        }

        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }

        val composeView = findViewById<ComposeView>(R.id.compose_bottom_bar)

        composeView.setContent {

            BottomBar(
                selectedTab = selectedTab
            ) { selected ->

                selectedTab = selected

                when (selected) {
                    "home" -> loadFragment(HomeFragment())
                    "purchase" -> loadFragment(PurchaseFragment())
                    "wishlist" -> loadFragment(WishlistFragment())
                    "cart" -> loadFragment(CartFragment())
                    "profile" -> loadFragment(ProfileFragment())
                }
            }
        }
    }

    fun moveToPurchase() {

        selectedTab = "purchase"

        loadFragment(PurchaseFragment())
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }
}