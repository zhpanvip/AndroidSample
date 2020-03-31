package com.zhpan.sample.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.integration.testapp.cards.Card
import com.zhpan.sample.R
import com.zhpan.sample.viewpager2.fragment.TestLifecycleFragment

class FragmentLifecycleActivity : AppCompatActivity() {
    private lateinit var fragment: Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_lifecycle)
        fragment = TestLifecycleFragment.getInstance(Card.DECK[0], 0)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.ll_fragment, fragment)
//        fragmentTransaction.setMaxLifecycle(fragment, Lifecycle.State.RESUMED)
        fragmentTransaction.commit()
    }

    fun onClick(view: View) {
        val maxLifecycle: Lifecycle.State = when (view.id) {
            R.id.btn_created -> Lifecycle.State.CREATED
            R.id.btn_started -> Lifecycle.State.STARTED
            else -> Lifecycle.State.RESUMED
        }
        setMaxLifecycle(maxLifecycle)
    }

    private fun setMaxLifecycle(created: Lifecycle.State) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.setMaxLifecycle(fragment, created)
        fragmentTransaction.commit()
    }

}
