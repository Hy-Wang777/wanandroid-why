package com.why.wanandroid.base

import android.content.Context
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.webkit.WebSettings
import com.why.wanandroid.R
import com.why.wanandroid.closeFragment
import kotlinx.android.synthetic.main.fragment_base_web.*
import kotlinx.android.synthetic.main.layout_title.*

/******************************************
 * 类描述：
 *
 * @author: why
 * @time: 2020/4/15 2:33 PM
 ******************************************/

open class BaseWebFragment : BaseFragment() {

    override fun getLayoutRes(): Int = R.layout.fragment_base_web

    override fun initView(view: View) {
        image_title_back.setOnClickListener {
            closeFragment()
        }

        text_title_name.text = "文章详情"
        initWebSettings()
    }

    private fun initWebSettings() {

        //声明WebSettings子类
        val webSettings: WebSettings = web_view.settings

        webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        webSettings.setGeolocationEnabled(true)
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.javaScriptEnabled = true
        //设置自适应屏幕，两者合用
        //设置自适应屏幕，两者合用
        webSettings.useWideViewPort = true //将图片调整到适合webview的大小

        webSettings.loadWithOverviewMode = true // 缩放至屏幕的大小

        //适配宽屏设备
        //适配宽屏设备
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN

        //缩放操作
        //缩放操作
        webSettings.setSupportZoom(true) //支持缩放，默认为true。是下面那个的前提。

        webSettings.builtInZoomControls = true //设置内置的缩放控件。若为false，则该WebView不可缩放

        webSettings.displayZoomControls = false //隐藏原生的缩放控件

        //其他细节操作
        //其他细节操作
        webSettings.cacheMode = WebSettings.LOAD_DEFAULT //（默认）根据cache-control决定是否从网络上取数据。

        webSettings.allowFileAccess = true //设置可以访问文件

        webSettings.javaScriptCanOpenWindowsAutomatically = true //支持通过JS打开新窗口

        webSettings.loadsImagesAutomatically = true //支持自动加载图片

        webSettings.defaultTextEncodingName = "utf-8" //设置编码格式

        webSettings.domStorageEnabled = true
        webSettings.setAppCacheMaxSize(1024 * 1024 * 8.toLong()) //8MB

        webSettings.databaseEnabled = true
        val dbPath = context!!.getDir("database", Context.MODE_PRIVATE).path
        webSettings.databasePath = dbPath
        val appCacheDir = context!!.getDir("cache", Context.MODE_PRIVATE).path
        webSettings.setAppCachePath(appCacheDir)
        webSettings.textZoom = 100
        webSettings.pluginState = WebSettings.PluginState.ON
    }

    override fun onDestroy() {
        super.onDestroy()
        if (web_view != null) { // 如果先调用destroy()方法，则会命中if (isDestroyed()) return;这一行代码，需要先onDetachedFromWindow()，再
            // destory()
            val parent: ViewParent = web_view.parent
            (parent as ViewGroup).removeView(web_view)

            web_view.stopLoading()
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            web_view.settings.javaScriptEnabled = false
            web_view.clearHistory()
            web_view.removeAllViews()
            try {
                web_view.destroy()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        closeFragment()
        return super.onKeyDown(keyCode, event)
    }
}