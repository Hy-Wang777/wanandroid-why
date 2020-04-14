package com.why.wanandroid.ui.home.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.why.wanandroid.R
import com.why.wanandroid.network.model.HomeTopData
import com.why.wanandroid.utils.DateUtils

/******************************************
 * 类描述：
 *
 * @author: why
 * @time: 2020/4/14 5:20 PM
 ******************************************/

class HomeHeaderRvAdapter(private val layout: Int) : BaseQuickAdapter<HomeTopData, BaseViewHolder>(layout) {

    override fun convert(holder: BaseViewHolder, item: HomeTopData) {

        holder.setText(R.id.tv_item_title, item.title)
        holder.setText(R.id.tv_item_user_name, if (item.shareUser.isEmpty()) item.author else item.shareUser)
        holder.setText(R.id.tv_item_time, DateUtils.getFormatDate(item.publishTime, "yyyy-MM-dd HH:mm"))
        holder.setText(R.id.tv_item_tag, item.superChapterName)
    }

}