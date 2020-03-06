package com.example.oa

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

/**
 * @author 李荘
 * @date 2020-03-06
 * @desc application
 */
open class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initArouter();
    }

    /**
     * 初始化Arouter
     */
    private fun initArouter() {
        ARouter.openLog()
        ARouter.openDebug()
        ARouter.init(this)
    }
}