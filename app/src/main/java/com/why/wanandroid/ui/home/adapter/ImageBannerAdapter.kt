package com.why.wanandroid.ui.home.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.why.wanandroid.network.model.HomeBannerData
import com.youth.banner.adapter.BannerAdapter


/******************************************
 * 类描述：
 *
 * @author: why
 * @time: 2020/4/14 5:02 PM
 ******************************************/

class ImageBannerAdapter(list: List<HomeBannerData>) : BannerAdapter<HomeBannerData, ImageBannerAdapter.BannerViewHolder>(list) {

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {
        val imageView = ImageView(parent?.context)
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return BannerViewHolder(imageView)
    }

    override fun onBindView(holder: BannerViewHolder?, data: HomeBannerData?, position: Int, size: Int) {

        holder?.let {
            Glide.with(it.imageVIew.context).load(data?.imagePath).into(it.imageVIew)
        }

    }

    class BannerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageVIew: ImageView = view as ImageView

    }

}