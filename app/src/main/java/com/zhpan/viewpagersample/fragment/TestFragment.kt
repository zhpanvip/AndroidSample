package com.zhpan.viewpagersample.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.integration.testapp.cards.Card
import androidx.viewpager2.integration.testapp.cards.CardView

/**
 * <pre>
 * Created by zhangpan on 2019-12-17.
 * Description:
</pre> *
 */
class TestFragment : Fragment() {
    private var position: Int = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("TestFragment", "onAttach,position:$position")
        val bundle = arguments
        position = bundle!!.getInt(KEY_POSITION)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("TestFragment", "onCreate,position:$position")
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("TestFragment", "onCreateView,position:$position")
        val cardView = CardView(inflater, container)
        cardView.bind(Card.fromBundle(arguments!!))
        return cardView.view
    }

    override fun onStart() {
        super.onStart()
        Log.e("TestFragment", "onStart,position:$position")
    }

    override fun onResume() {
        super.onResume()
        Log.e("TestFragment", "onResume,position:$position")
    }


    override fun onPause() {
        super.onPause()
        Log.e("TestFragment", "onPause,position:$position")
    }

    override fun onStop() {
        super.onStop()
        Log.e("TestFragment", "onStop,position:$position")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("TestFragment", "onDestroyView,position:$position")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("TestFragment", "onDestroy,position:$position")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("TestFragment", "onDetach,position:$position")
    }

    companion object {

        private const val KEY_POSITION = "position"

        fun getInstance(card: Card, position: Int): TestFragment {
            val fragment = TestFragment()
            val bundle = card.toBundle()
            bundle.putInt(KEY_POSITION, position)
            fragment.arguments = bundle
            return fragment
        }
    }
}
