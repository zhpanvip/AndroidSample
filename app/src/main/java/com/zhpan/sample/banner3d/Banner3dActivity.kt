package com.zhpan.sample.banner3d

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.sample.R
import com.zhpan.sample.banner3d.SensorLayout.DIRECTION_RIGHT

class Banner3dActivity : AppCompatActivity() {

	private lateinit var sensorLayout: SensorLayout
	private lateinit var sensorLayout2: SensorLayout

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_banner_3d)
		sensorLayout = findViewById(R.id.sensor_layout_pg)
		sensorLayout2 = findViewById(R.id.sensor_layout_bg)
		sensorLayout2.setDirection(DIRECTION_RIGHT)
		val view = findViewById<View>(R.id.view)
	}

	override fun onDestroy() {
		super.onDestroy()
		sensorLayout.unregister()
		sensorLayout2.unregister()
	}

}