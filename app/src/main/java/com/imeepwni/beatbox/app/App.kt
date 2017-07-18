package com.imeepwni.beatbox.app

import android.app.Application
import com.imeepwni.beatbox.BuildConfig
import com.imeepwni.beatbox.R
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.LogcatLogStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

/**
 * Created by Guofeng on 2017/7/16.
 */
class App : Application() {
    companion object {
        lateinit var app: App
        fun getInstance() = app
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        initLogger()
    }

    private fun initLogger() {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)
                .methodCount(2)
                .methodOffset(5)
                .logStrategy(LogcatLogStrategy())
                .tag(getString(R.string.app_name))
                .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }
}