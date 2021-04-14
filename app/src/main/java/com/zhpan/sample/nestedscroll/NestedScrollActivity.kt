package com.zhpan.sample.nestedscroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.integration.testapp.cards.Card
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.zhpan.sample.R

class NestedScrollActivity : AppCompatActivity() {
    private lateinit var viewPager2: ViewPager2;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nested_scroll)
        viewPager2 = findViewById(R.id.view_pager2)
        viewPager2.adapter = NestedFragmentAdapter(this)
        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = Card.DECK[position].toString()
        }.attach()
    }
}