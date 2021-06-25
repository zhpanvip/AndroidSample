package com.zhpan.sample.view.viewpager2.activity

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.integration.testapp.cards.Card
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.zhpan.sample.R
import com.zhpan.sample.view.viewpager2.fragment.TestFragment

class VP2FragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        val viewPager=findViewById<ViewPager2>(R.id.view_pager)
        val tabLayout=findViewById<TabLayout>(R.id.tab_layout)
//        view_pager.offscreenPageLimit=1
        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return Card.DECK.size
            }

            override fun createFragment(position: Int): Fragment {
                return TestFragment.getInstance(Card.DECK[position],position)
            }
        }

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = Card.DECK[position].toString()
        }.attach()
    }

}
