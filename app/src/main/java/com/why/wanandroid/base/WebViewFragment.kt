package com.why.wanandroid.base

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import com.why.wanandroid.utils.Constants
import kotlinx.android.synthetic.main.fragment_base_web.*
import org.greenrobot.eventbus.EventBus
import java.util.*

/******************************************
 * 类描述：
 *
 * @author: why
 * @time: 2020/4/14 6:50 PM
 ******************************************/

class WebViewFragment : BaseWebFragment() {

    private lateinit var url: String

    override fun initData() {
        arguments?.let {
            url = it.getString("url", "")
        }

        web_view.loadUrl(url)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val params = HashMap<String, String>()
        params["action"] = Constants.ACTION_HIDE_MAIN_INDEX
        EventBus.getDefault().post(params)
    }
}