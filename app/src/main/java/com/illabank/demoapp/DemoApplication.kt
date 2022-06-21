package com.illabank.demoapp

import android.app.Application

class DemoApplication : Application() {

    lateinit var instance: DemoApplication

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}