package com.illabank.demoapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.illabank.demoapp.R
import com.illabank.demoapp.databinding.RowViewpagerBinding
import com.illabank.demoapp.model.ImageListData.ImageData


class ViewPagerAdapter :
    RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    private var imageDataList: List<ImageData> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mBinding =
            DataBindingUtil.inflate<RowViewpagerBinding>(
                LayoutInflater.from(parent.context),
                R.layout.row_viewpager,
                parent,
                false
            )
        return ViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(imageDataList[position])
    }

    override fun getItemCount(): Int {
        return imageDataList.size
    }

    class ViewHolder(private var mBinding: RowViewpagerBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun setData(imageData: ImageData) {
            mBinding.imgCarousel.setImageDrawable(
                ContextCompat.getDrawable(
                    mBinding.root.context,
                    imageData.carouselImage
                )
            )
        }
    }

    fun addItems(list: List<ImageData>) {
        imageDataList = list
        notifyDataSetChanged()
    }
}