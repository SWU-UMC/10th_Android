package com.example.week2

import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class BuyFragment : Fragment(R.layout.fragment_buy) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tab1 = view.findViewById<LinearLayout>(R.id.tab1)
        val tab2 = view.findViewById<LinearLayout>(R.id.tab2)
        val tab3 = view.findViewById<LinearLayout>(R.id.tab3)

        val text1 = tab1.getChildAt(0) as TextView
        val text2 = tab2.getChildAt(0) as TextView
        val text3 = tab3.getChildAt(0) as TextView

        changeFragment(Tab1Fragment())
        selectTab(text1, text2, text3, text1)

        tab1.setOnClickListener {
            changeFragment(Tab1Fragment())
            selectTab(text1, text2, text3, text1)
        }

        tab2.setOnClickListener {
            changeFragment(Tab2Fragment())
            selectTab(text1, text2, text3, text2)
        }

        tab3.setOnClickListener {
            changeFragment(Tab3Fragment())
            selectTab(text1, text2, text3, text3)
        }
    }

    private fun changeFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.tabContent, fragment)
            .commit()
    }

    private fun selectTab(
        t1: TextView,
        t2: TextView,
        t3: TextView,
        selected: TextView
    ) {
        t1.setTypeface(null, Typeface.NORMAL)
        t2.setTypeface(null, Typeface.NORMAL)
        t3.setTypeface(null, Typeface.NORMAL)

        selected.setTypeface(null, Typeface.BOLD)
    }
}