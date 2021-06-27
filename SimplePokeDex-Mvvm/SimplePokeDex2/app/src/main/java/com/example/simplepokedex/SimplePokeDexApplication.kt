package com.example.simplepokedex

import android.app.Application

class SimplePokeDexApplication: Application() {
    val serviceLocator = ServiceLocator()
}