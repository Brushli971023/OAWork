package com.example.common.deviceinfo

import android.content.Context
import android.content.pm.PackageManager

class AppInfoConfig private constructor() {
    private var context: Context? = null
    fun init(context: Context?) {
        this.context = context
    }

    val packageNames: String
        get() = context!!.packageName

    val versionCode: String?
        get() {
            val packageManager = context!!.packageManager
            try {
                return packageManager.getPackageInfo(
                    context!!.packageName,
                    0
                ).versionCode.toString() + ""
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            return null
        }

    val versionName: String?
        get() {
            val packageManager = context!!.packageManager
            try {
                return packageManager.getPackageInfo(context!!.packageName, 0).versionName + ""
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            return null
        }

    companion object {
        @Volatile
        private var singleton: AppInfoConfig? = null

        val instance: AppInfoConfig?
            get() {
                if (singleton == null) {
                    synchronized(AppInfoConfig::class.java) {
                        if (singleton == null) {
                            singleton = AppInfoConfig()
                        }
                    }
                }
                return singleton
            }
    }
}