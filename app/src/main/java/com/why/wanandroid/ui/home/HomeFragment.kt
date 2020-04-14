package com.why.wanandroid.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.why.wanandroid.R
import com.why.wanandroid.base.IBaseFragment
import com.why.wanandroid.network.model.HomeBannerData
import com.why.wanandroid.network.model.HomeListData
import com.why.wanandroid.network.model.HomeTopData
import com.why.wanandroid.ui.home.adapter.HomeHeaderRvAdapter
import com.why.wanandroid.ui.home.adapter.HomeRvAdapter
import com.why.wanandroid.ui.home.adapter.ImageBannerAdapter
import com.youth.banner.Banner
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.frgament_home.*

/******************************************
 * 类描述：
 *
 * @author: why
 * @time: 2020/4/13 5:59 PM
 ******************************************/

class HomeFragment : IBaseFragment<HomeController.HomePresenter>(), HomeController.HomeView {

    private val homeAdapter by lazy { HomeRvAdapter(R.layout.item_rv_home) }
    private val homeHeaderAdapter by lazy { HomeHeaderRvAdapter(R.layout.item_rv_home) }
    override fun getLayoutRes(): Int = R.layout.frgament_home

    override fun initView(view: View) {

        rv_home?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeAdapter
        }
    }

    override fun initData() {
        presenter?.getHomeData()

    }

    override fun getHomeResult(banner: List<HomeBannerData>, top: List<HomeTopData>, home: HomeListData) {
        homeAdapter.addHeaderView(getHeaderView(banner, top))
        homeAdapter.addData(home.datas)
    }

    private fun getHeaderView(banner: List<HomeBannerData>, top: List<HomeTopData>): View {
        val headerView: View = LayoutInflater.from(context).inflate(R.layout.item_rv_home_header, rv_home?.parent as ViewGroup, false)

        val bannerView = headerView.findViewById<Banner<*, *>>(R.id.banner_home_header)
        val recycler = headerView.findViewById<RecyclerView>(R.id.rv_home_header_hot)

        bannerView.apply {
            adapter = ImageBannerAdapter(banner)
            indicator = CircleIndicator(context)
        }


        recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeHeaderAdapter
        }

        homeHeaderAdapter.addData(top)

        return headerView
    }


    override fun setPresenter(presenter: HomeController.HomePresenter?) {
        this.presenter = HomePresenter(this)
    }


}



