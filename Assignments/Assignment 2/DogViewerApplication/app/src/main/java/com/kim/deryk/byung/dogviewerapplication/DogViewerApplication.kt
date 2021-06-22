package com.kim.deryk.byung.dogviewerapplication

import android.app.Application

class DogViewerApplication: Application() {
    val serviceLocator: ServiceLocator = ServiceLocator()
}