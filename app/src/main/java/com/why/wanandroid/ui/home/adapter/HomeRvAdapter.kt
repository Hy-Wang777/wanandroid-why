package com.why.wanandroid.ui.home.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.why.wanandroid.R
import com.why.wanandroid.model.HomeList
import com.why.wanandroid.utils.DateUtils

/******************************************
 * 类描述：
 *
 * @author: why
 * @time: 2020/4/14 5:20 PM
 ******************************************/

class HomeRvAdapter(private val layout: Int) : BaseQuickAdapter<HomeList, BaseViewHolder>(layout) {

    init {
        addChildClickViewIds(R.id.root_item_home_list)
    }

    override fun convert(holder: BaseViewHolder, item: HomeList) {

        holder.setText(R.id.tv_item_title, item.title)
        holder.setText(R.id.tv_item_user_name, if (item.shareUser.isEmpty()) item.author else item.shareUser)
        holder.setText(R.id.tv_item_time, DateUtils.getFormatDate(item.publishTime, "yyyy-MM-dd HH:mm"))
        holder.setText(R.id.tv_item_tag, item.superChapterName)

       holder.getView<TextView>(R.id.tv_item_title).setOnClickListener {
           clickImageListener(item.link)
       }
    }


    lateinit var clickImageListener: (url: String) -> Unit
}