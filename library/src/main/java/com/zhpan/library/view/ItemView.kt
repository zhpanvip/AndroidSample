package com.zhpan.library.view

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import android.widget.TextView
import com.blankj.utilcode.util.ConvertUtils
import com.zhpan.library.R

/**
 * <pre>
 * Created by zhpan on 2020/1/4.
 * Description:
 *</pre>
 */
class ItemView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : RelativeLayout(context, attrs, defStyleAttr) {

    private val mTextView: TextView

    init {
        inflate(context, R.layout.item_view, this)
        mTextView = findViewById(R.id.item_text)
        layoutParams?.height = ConvertUtils.dp2px(45f)
        if (attrs != null) {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ItemView)
            val string = typedArray.getString(R.styleable.ItemView_item_text)
            mTextView.text = string
            typedArray.recycle()
        }
    }

    fun setItemText(text: CharSequence) {
        mTextView.text = text
    }
}
