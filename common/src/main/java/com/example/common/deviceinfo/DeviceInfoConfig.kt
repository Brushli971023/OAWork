package com.example.common.deviceinfo

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import android.os.Build
import android.telephony.TelephonyManager
import android.text.TextUtils
import androidx.annotation.RequiresApi

class DeviceInfoConfig private constructor() {
    private var context: Context? = null
    fun init(context: Context?) {
        this.context = context
    }

    /**
     * 获取厂商信息
     * @return
     */
    val manufacturer: String
        get() = Build.MANUFACTURER

    /**
     * 获取机型信息
     * @return
     */
    val model: String
        get() = Build.MODEL

    /**
     * 获取Android系统版本
     * @return
     */
    val osVersion: String
        get() = Build.VERSION.SDK_INT.toString()

    /**
     * 获取设备号 包括: GSM - IMEI  CDMA - MEID
     * @return
     */
    @get:RequiresApi(api = Build.VERSION_CODES.M)
    @get:SuppressLint("MissingPermission")
    val deviceID: String?
        get() {
            val systemService =
                (context!!.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager)
            @SuppressLint("HardwareIds") var deviceID =
                systemService.getDeviceId(TelephonyManager.PHONE_TYPE_GSM)
            if (TextUtils.isEmpty(deviceID)) {
                deviceID = systemService.getDeviceId(TelephonyManager.PHONE_TYPE_CDMA)
            }
            return deviceID
        }

    val location: String
        get() {
            val systemService =
                (context!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager)
            @SuppressLint("MissingPermission") val lastKnownLocation =
                systemService.getLastKnownLocation(LocationManager.GPS_PROVIDER)!!
            return lastKnownLocation.longitude.toString() + "," + lastKnownLocation.latitude
        }

    companion object {
        @Volatile
        private var singleton: DeviceInfoConfig? = null

        val instance: DeviceInfoConfig?
            get() {
                if (singleton == null) {
                    synchronized(DeviceInfoConfig::class.java) {
                        if (singleton == null) {
                            singleton = DeviceInfoConfig()
                        }
                    }
                }
                return singleton
            }
    }
}