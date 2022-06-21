package com.illabank.demoapp.model


class ImageListData {

    data class ImageData(
        val carouselImage: Int,
        val listData: List<ListData>
    )

    data class ListData(
        val listIcon: Int,
        val listText: String
    )
}