package com.illabank.demoapp.repositories

import com.illabank.demoapp.R
import com.illabank.demoapp.model.ImageListData.ImageData
import com.illabank.demoapp.model.ImageListData.ListData
import com.illabank.demoapp.utils.Constants

class HomeRepository {

    private val imageListData = mutableListOf<ImageData>()

    private val imageList = arrayOf(
        R.drawable.img_1,
        R.drawable.img_2,
        R.drawable.img_3,
        R.drawable.img_4,
        R.drawable.img_5,
        R.drawable.img_6,
        R.drawable.img_7
    )

    fun getImageListData(
    ): List<ImageData> {
        imageListData.clear()
        for (i in imageList.indices) {
            val listData = mutableListOf<ListData>()
            for (j in 1..Constants.CAROUSEL_LIST_DATA_COUNT) {
                listData.add(
                    ListData(
                        R.drawable.ic_checklist,
                        "Travel Checklist ${(i * 10) + j}"
                    )
                )
            }
            imageListData.add(
                ImageData(
                    imageList[i],
                    listData
                )
            )
        }
        return imageListData
    }
}