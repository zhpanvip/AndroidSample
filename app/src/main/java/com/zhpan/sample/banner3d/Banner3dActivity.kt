package com.zhpan.sample.banner3d

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.bannerview.constants.IndicatorGravity
import com.zhpan.indicator.IndicatorView
import com.zhpan.indicator.base.IIndicator
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle
import com.zhpan.sample.R

class Banner3dActivity : AppCompatActivity() {

	private lateinit var mBannerForeground: BannerViewPager<Banner3DData>
	private lateinit var mIvBackGround: ImageView

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_banner_3d)
		mBannerForeground = findViewById(R.id.bvp_foreground)
		mIvBackGround = findViewById(R.id.iv_background)

		val arrayList = ArrayList<Banner3DData>()
		arrayList.add(Banner3DData(R.drawable.background1, R.drawable.mid1, R.drawable.foreground1))
		arrayList.add(Banner3DData(R.drawable.background2, R.drawable.mid2, R.drawable.foreground2))
		val banner3DData =
			Banner3DData(R.drawable.background3, R.drawable.mid3, R.drawable.foreground3)
		banner3DData.type = 1
		arrayList.add(banner3DData)
		mIvBackGround.setImageResource(arrayList[0].background!!)

		mBannerForeground.apply {
			adapter = ForegroundAdapter()
			setAutoPlay(true)
			setIndicatorStyle(IndicatorStyle.ROUND_RECT)
			setIndicatorSlideMode(IndicatorSlideMode.COLOR)
			setIndicatorSliderWidth(
				resources.getDimensionPixelOffset(R.dimen.dp_9)
			)
			setIndicatorHeight(resources.getDimensionPixelOffset(R.dimen.dp_3))
			setIndicatorMargin(0, 0, resources.getDimensionPixelOffset(R.dimen.dp_16), 0)
			setIndicatorSliderColor(
				resources.getColor(R.color.gray_BB),
				resources.getColor(R.color.dark_gray)
			)
			setIndicatorGravity(IndicatorGravity.END)
			setScrollDuration(800)
			setIndicatorSliderGap(resources.getDimensionPixelOffset(R.dimen.dp_5))
		}.registerOnPageChangeCallback(object : OnPageChangeCallback() {
			override fun onPageSelected(position: Int) {
				val banner3DData = mBannerForeground.data[position]
				mIvBackGround.setImageResource(banner3DData?.background!!)
			}
		}).create(arrayList)
	}

}