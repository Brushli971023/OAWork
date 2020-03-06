package com.example.common.common

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.os.Parcelable
import android.util.Log
import java.util.*

/**
 * 网络状态判断
 */
class NetConnectManager  //TODO 私有化构造
private constructor() {
    //TODO application的上下文
    private var applicationContext: Context? = null
    //TODO 让其他类获取网络状态
    //TODO 网络连接状态
    var isNetConnectStatus = false
        private set
    //TODO 让其他类获取网络类型
    //TODO 网络类型
    var isNetType: String? = null
        private set
    //TODO 连接管理
    private var connectivityManager: ConnectivityManager? = null
    //TODO 使用链表 可能有多个页面需要网络监听
    private val iNetConnectListenerList: MutableList<INetConnectListener> =
        LinkedList()

    //TODO 初始化
    fun init(applicationContext: Context) {
        this.applicationContext = applicationContext
        //TODO 获取网络连接状态
        connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager!!.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {
            isNetConnectStatus = true
        } else {
            isNetConnectStatus = false
        }
        //TODO 注册广播去监听当前网络连接的变化
        val intentFilter = IntentFilter()
        //TODO 监听系统广播
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        //TODO 监听
        applicationContext.registerReceiver(broadcastReceiver, intentFilter)
    }

    //TODO 注册网络监听
    fun registerNetConnectListener(netConnectListener: INetConnectListener?) {
        if (!iNetConnectListenerList.contains(netConnectListener) && netConnectListener != null) {
            iNetConnectListenerList.add(netConnectListener)
        }
    }

    //TODO 注销网络监听
    fun unRegisterNetConnectListener(netConnectListener: INetConnectListener?) {
        if (iNetConnectListenerList.contains(netConnectListener) && netConnectListener != null) {
            iNetConnectListenerList.remove(netConnectListener)
        }
    }

    //TODO 回调更新网络状态
    private fun notifyConnectChanged() {
        for (iNetConnectListener in iNetConnectListenerList) {
            if (isNetConnectStatus) {
                iNetConnectListener.onConnected()
            } else {
                iNetConnectListener.onDisConnected()
            }
        }
    }

    //TODO 通过广播来监听网络
    private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(
            context: Context,
            intent: Intent
        ) { //TODO 监听wifi的打开与关闭，与wifi的连接无关
            if (WifiManager.WIFI_STATE_CHANGED_ACTION == intent.action) {
                val wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0)
                if (wifiState == WifiManager.WIFI_STATE_DISABLED) { //wifi关闭
                    Log.d("netstatus", "wifi已关闭")
                } else if (wifiState == WifiManager.WIFI_STATE_ENABLED) { //wifi开启
                    Log.d("netstatus", "wifi已开启")
                } else if (wifiState == WifiManager.WIFI_STATE_ENABLING) { //wifi开启中
                    Log.d("netstatus", "wifi开启中")
                } else if (wifiState == WifiManager.WIFI_STATE_DISABLING) { //wifi关闭中
                    Log.d("netstatus", "wifi关闭中")
                }
            }
            //TODO 监听wifi的连接状态即是否连上了一个有效无线路由
            if (WifiManager.NETWORK_STATE_CHANGED_ACTION == intent.action) {
                val parcelableExtra =
                    intent.getParcelableExtra<Parcelable>(WifiManager.EXTRA_NETWORK_INFO)
                if (parcelableExtra != null) {
                    Log.d("netstatus", "wifi parcelableExtra不为空")
                    val networkInfo = parcelableExtra as NetworkInfo
                    if (networkInfo.state == NetworkInfo.State.CONNECTED) { //已连接网络
                        Log.d("netstatus", "wifi 已连接网络")
                        if (networkInfo.isAvailable) { //并且网络可用
                            Log.d("netstatus", "wifi 已连接网络，并且可用")
                        } else { //并且网络不可用
                            Log.d("netstatus", "wifi 已连接网络，但不可用")
                        }
                    } else { //网络未连接
                        Log.d("netstatus", "wifi 未连接网络")
                    }
                } else {
                    Log.d("netstatus", "wifi parcelableExtra为空")
                }
            }
            //TODO 监听网络连接，总网络判断，即包括wifi和移动网络的监听
            if (ConnectivityManager.CONNECTIVITY_ACTION == intent.action) {
                val networkInfo =
                    intent.getParcelableExtra<NetworkInfo>(ConnectivityManager.EXTRA_NETWORK_INFO)
                //TODO 连上的网络类型判断：wifi还是移动网络
                if (networkInfo.type == ConnectivityManager.TYPE_WIFI) {
                    isNetType = "WIFI"
                } else if (networkInfo.type == ConnectivityManager.TYPE_MOBILE) {
                    isNetType = "移动流量"
                }
            }
            //TODO 监听网络
            if (intent.action == ConnectivityManager.CONNECTIVITY_ACTION) {
                connectivityManager =
                    applicationContext!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val networkInfo = connectivityManager!!.activeNetworkInfo
                if (networkInfo != null && networkInfo.isConnected) {
                    isNetConnectStatus = true
                } else {
                    isNetConnectStatus = false
                }
                //TODO 回调通知网络连接的变化
                notifyConnectChanged()
            }
        }
    }

    companion object {
        /**
         * 单例模式
         */
//TODO 私有化对象
        private var netConnectManager: NetConnectManager? = null

        //TODO 对外提供对象
        val instance: NetConnectManager?
            get() {
                if (netConnectManager == null) {
                    netConnectManager = NetConnectManager()
                }
                return netConnectManager
            }
    }
}