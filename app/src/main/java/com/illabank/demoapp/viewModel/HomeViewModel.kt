package com.illabank.demoapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.illabank.demoapp.model.ImageListData.ImageData
import com.illabank.demoapp.model.ImageListData.ListData
import com.illabank.demoapp.repositories.HomeRepository
import com.illabank.demoapp.view.base.BaseViewModel

class HomeViewModel : BaseViewModel() {

    private val homeRepository = HomeRepository()

    private val dataImageData = MutableLiveData<List<ImageData>>()
    val observeImageData: LiveData<List<ImageData>> = dataImageData

    private val dataListData = MutableLiveData<List<ListData>>()
    val observeListData: LiveData<List<ListData>> = dataListData

    fun getImageListData() {
        dataImageData.postValue(homeRepository.getImageListData())
    }

    fun getListData(pos: Int) {
        dataListData.postValue(homeRepository.getImageListData()[pos].listData)
    }
}