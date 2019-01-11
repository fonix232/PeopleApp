package me.fonix232.peopleapp

import android.app.Application
import org.koin.android.ext.android.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(dbModule, networkModule, clientModule, appModule))
    }
}