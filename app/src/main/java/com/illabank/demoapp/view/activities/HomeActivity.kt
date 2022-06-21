package com.illabank.demoapp.view.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.illabank.demoapp.BR
import com.illabank.demoapp.R
import com.illabank.demoapp.databinding.ActivityHomeBinding
import com.illabank.demoapp.model.ImageListData.ListData
import com.illabank.demoapp.view.adapters.ListDataAdapter
import com.illabank.demoapp.view.adapters.ViewPagerAdapter
import com.illabank.demoapp.view.base.BaseActivity
import com.illabank.demoapp.viewModel.HomeViewModel

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    lateinit var listDataAdapter: ListDataAdapter
    private var listData = arrayListOf<ListData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initViews()
        getData()
        observeData()
    }

    private fun getData() {
        mViewModel.getImageListData()
        mViewModel.getListData(0)
    }

    private fun observeData() {
        mViewModel.observeImageData.observe(this) { imageData ->
            viewPagerAdapter.addItems(imageData)
        }

        mViewModel.observeListData.observe(this) { mListData ->
            listData.clear()
            listData.addAll(mListData)
            listDataAdapter.notifyDataSetChanged()
        }
    }

    private fun initViews() {

        setUpListData()

        setUpViewPager()
        mBinding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                inputString: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                inputString: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                listDataAdapter.filter.filter(inputString)
            }

            override fun afterTextChanged(editable: Editable?) {
            }

        })
    }

    private fun setUpViewPager() {
        viewPagerAdapter = ViewPagerAdapter()
        mBinding.vpImage.adapter = viewPagerAdapter
        TabLayoutMediator(mBinding.tabPageIndicator, mBinding.vpImage) { _, _ ->
        }.attach()

        mBinding.vpImage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mBinding.edtSearch.setText("")
                mBinding.edtSearch.clearFocus()
                mViewModel.getListData(position)
            }
        })
    }

    private fun setUpListData() {
        listDataAdapter = ListDataAdapter(listData)
        mBinding.rvDataList.adapter = listDataAdapter
        mBinding.rvDataList.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun getViewModel() = HomeViewModel()

    override fun getContentLayout() = R.layout.activity_home

    override fun getBindingVariable() = BR.homeViewModel
}