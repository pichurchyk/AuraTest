package com.pichurchyk.auratest

import android.app.Application
import android.content.Context
import com.pichurchyk.auratest.di.mainModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

class Application : Application() {
    private var appContext: Context? = null

    override fun onCreate() {
        super.onCreate()
        appContext = this

        initKoin(
            mainModule,
            androidModule,
        )
    }

    private fun initKoin(vararg modules: Module) {
        startKoin {
            modules(
                *modules
            )
        }
    }

    private val androidModule = module {
        single { this@Application.appContext }
    }
}