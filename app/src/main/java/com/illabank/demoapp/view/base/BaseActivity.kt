package com.illabank.demoapp.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel> : AppCompatActivity() {

    lateinit var mBinding: T
    lateinit var mViewModel: V

    abstract fun getViewModel(): V
    abstract fun getContentLayout(): Int
    abstract fun getBindingVariable(): Int

    fun initBinding() {
        mBinding = DataBindingUtil.setContentView(this, getContentLayout())
        mViewModel = getViewModel()
        mBinding.setVariable(getBindingVariable(), mViewModel)
    }
}