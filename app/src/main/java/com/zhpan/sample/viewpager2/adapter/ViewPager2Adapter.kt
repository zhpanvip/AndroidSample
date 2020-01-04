package com.zhpan.sample.viewpager2.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zhpan.sample.R

import java.util.ArrayList

/**
 * <pre>
 * Created by zhpan on 2019/12/9.
 * Description:
</pre> *
 */
class ViewPager2Adapter : RecyclerView.Adapter<ViewPager2Adapter.PagerViewHolder>() {
    private var mList: List<Int> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false)
        return PagerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bindData(mList[position])
    }

    fun setList(list: List<Int>) {
        mList = list
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mTextView: TextView = itemView.findViewById(R.id.tv_text)
        private var colors = arrayOf("#CCFF99","#41F1E5","#8D41F1","#FF99CC")

        fun bindData(i: Int) {
            mTextView.text = i.toString()
            mTextView.setBackgroundColor(Color.parseColor(colors[i]))
        }
    }
}
