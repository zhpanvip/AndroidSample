package com.zhpan.sample.banner3d

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle
import com.zhpan.sample.R

class Banner3dActivity : AppCompatActivity() {

	private lateinit var mBannerForeground: BannerViewPager<Banner3DData>

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_banner_3d)
		mBannerForeground = findViewById(R.id.bvp_foreground)
		val arrayList1 = ArrayList<Banner3DData>()
		arrayList1.add(Banner3DData())
		arrayList1.add(Banner3DData())
		arrayList1.add(Banner3DData())
		arrayList1.add(Banner3DData())
		mBannerForeground.apply {
			adapter = Banner3DAdapter()
			setAutoPlay(false)
			setIndicatorStyle(IndicatorStyle.DASH)
			setIndicatorSlideMode(IndicatorSlideMode.SCALE)
			setIndicatorSliderWidth(
				resources.getDimensionPixelOffset(R.dimen.dp_8),
				resources.getDimensionPixelOffset(R.dimen.dp_13)
			)
			setIndicatorSliderColor(
				resources.getColor(R.color.magnolia_50),
				resources.getColor(R.color.magnolia_100)
			)
			setIndicatorSliderGap(resources.getDimensionPixelOffset(R.dimen.dp_3))
			create(arrayList1)
		}
	}
}