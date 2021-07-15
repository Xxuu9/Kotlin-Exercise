package com.kim.deryk.byung.dogviewerapplication

import android.app.Application

class DogViewerApplication: Application() {
    lateinit var serviceLocator: ServiceLocator

    override fun onCreate() {
        super.onCreate()
        serviceLocator = ServiceLocator(applicationContext)
    }
}