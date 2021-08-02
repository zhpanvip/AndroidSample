package com.zhpan.sample.banner3d

import android.widget.ImageView
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder
import com.zhpan.sample.R
import com.zhpan.sample.banner3d.SensorLayout.DIRECTION_RIGHT

/**
 *
 * @author zhangpan
 * @date 2021/7/29
 */
class ForegroundAdapter : BaseBannerAdapter<Banner3DData>() {

  override fun bindData(
    holder: BaseViewHolder<Banner3DData>?,
    data: Banner3DData?,
    position: Int,
    pageSize: Int
  ) {
    holder?.findViewById<SensorLayout>(R.id.sensor_layout)?.setDirection(DIRECTION_RIGHT)
    holder?.findViewById<ImageView>(R.id.iv_mid)?.setImageResource(data?.mid!!)
    holder?.findViewById<ImageView>(R.id.iv_foreground)?.setImageResource(data?.foreground!!)
  }

  override fun getLayoutId(viewType: Int): Int {
    return R.layout.item_3d_foreground
  }
}