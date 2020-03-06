package com.example.common.common.utils

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.util.*

object LogShow {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    fun show(e: Throwable) {
        Log.d("Error====", Objects.requireNonNull(e.message))
    }
}