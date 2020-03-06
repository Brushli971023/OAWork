package com.example.common.common.utils

import android.util.Log

object LogUtils {
    fun e(string: String?) {
        Log.e("user===", string)
    }

    fun d(string: String?) {
        Log.d("user===", string)
    }
}