package com.why.wanandroid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.why.wanandroid.R
import com.why.wanandroid.base.IBaseFragment
import com.why.wanandroid.base.WebViewFragment
import com.why.wanandroid.model.HomeBannerData
import com.why.wanandroid.model.HomeList
import com.why.wanandroid.model.HomeListData
import com.why.wanandroid.model.HomeTopData
import com.why.wanandroid.showFragment
import com.why.wanandroid.ui.home.adapter.HomeHeaderRvAdapter
import com.why.wanandroid.ui.home.adapter.HomeRvAdapter
import com.why.wanandroid.ui.home.adapter.ImageBannerAdapter
import com.why.wanandroid.utils.RequestState
import com.youth.banner.Banner
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.frgament_home.*

/******************************************
 * 类描述：
 *
 * @author: why
 * @time: 2020/4/13 5:59 PM
 ******************************************/

class HomeFragment : IBaseFragment<HomeController.HomePresenter>(), HomeController.HomeView, OnRefreshListener, OnLoadMoreListener {

    private val state by lazy { RequestState() }
    private val homeAdapter by lazy { HomeRvAdapter(R.layout.item_rv_home) }
    private val homeHeaderAdapter by lazy { HomeHeaderRvAdapter(R.layout.item_rv_home) }
    override fun getLayoutRes(): Int = R.layout.frgament_home

    override fun initView(view: View) {

        rv_home?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeAdapter
        }

        homeAdapter.setOnItemChildClickListener { adapter, view, position ->
            adapter.getItem(position)?.let {
                val home = it as HomeList

                when (view.id) {
                    R.id.root_item_home_list -> {
                        val instance = WebViewFragment()
                        val bundle = Bundle()
                        bundle.putString("url", home.link)
                        instance.arguments = bundle

                        showFragment(instance, WebViewFragment::class.java.name)

                    }
                    else -> {
                    }
                }
            }
        }

        homeHeaderAdapter.setOnItemChildClickListener { adapter, view, position ->
            adapter.getItem(position)?.let {
                val home = it as HomeTopData
                when (view.id) {
                    R.id.root_item_home_list -> {
                        val instance = WebViewFragment()
                        val bundle = Bundle()
                        bundle.putString("url", home.link)
                        instance.arguments = bundle
                        showFragment(instance, WebViewFragment::class.java.name)
                    }
                    else -> {
                    }
                }
            }
        }

        refresh_home?.setOnRefreshListener(this)
        refresh_home?.setOnLoadMoreListener(this)
    }

    override fun initData() {
        presenter?.getHomeData()

    }

    override fun getHomeResult(banner: List<HomeBannerData>?, top: List<HomeTopData>?, home: HomeListData?) {
        refresh_home?.finishRefresh()

        if (homeAdapter.hasHeaderLayout()) {
            homeAdapter.removeAllHeaderView()
        }

        if (homeAdapter.data.isNotEmpty()) {
            homeAdapter.setNewInstance(null)
        }

        homeAdapter.addHeaderView(getHeaderView(banner, top))

        home?.let {
            homeAdapter.addData(home.datas)
        }

    }

    override fun getListMoreResult(home: HomeListData?) {

        refresh_home?.finishLoadMore()

        home?.let {
            if (!home.over) {
                homeAdapter.addData(home.datas)

                if (home.datas.size < state.size) {
                    refresh_home.finishLoadMoreWithNoMoreData()
                } else {
                    refresh_home.finishLoadMore()
                }
            } else {
                state.isBottom = true
                refresh_home.finishLoadMoreWithNoMoreData()
            }
        }.apply {
            state.isBottom = true
            refresh_home.finishLoadMoreWithNoMoreData()
        }


    }

    private fun getHeaderView(banner: List<HomeBannerData>?, top: List<HomeTopData>?): View {
        val headerView: View = LayoutInflater.from(context).inflate(R.layout.item_rv_home_header, rv_home?.parent as ViewGroup, false)

        val bannerView = headerView.findViewById<Banner<*, *>>(R.id.banner_home_header)
        val recycler = headerView.findViewById<RecyclerView>(R.id.rv_home_header_hot)
        banner?.let {
            bannerView.apply {
                val bannerAdapter = ImageBannerAdapter(banner)
                adapter = bannerAdapter
                indicator = CircleIndicator(context)
                indicatorConfig.normalColor = resources.getColor(R.color.colorGray9B)
                indicatorConfig.selectedColor = resources.getColor(R.color.colorMain)

                bannerAdapter.clickImageListener = {
                    val instance = WebViewFragment()
                    val bundle = Bundle()
                    bundle.putString("url", it)
                    instance.arguments = bundle
                    showFragment(instance, WebViewFragment::class.java.name)
                }
            }
        }


        recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeHeaderAdapter
        }

        top?.let {
            homeHeaderAdapter.addData(top)
        }


        return headerView
    }


    override fun setPresenter(presenter: HomeController.HomePresenter?) {
        this.presenter = HomePresenter(this)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        state.page = 0
        state.isBottom = false
        state.isRefresh = true
        presenter?.getHomeData()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        if (state.isBottom) {
            refresh_home?.finishLoadMoreWithNoMoreData()
        } else {
            presenter?.getListMore(state.page++)
        }

    }


}



