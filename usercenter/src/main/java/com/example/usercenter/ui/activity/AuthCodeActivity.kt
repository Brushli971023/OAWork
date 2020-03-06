package com.example.usercenter.ui.activity

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import com.example.common.activity.BaseActivity
import com.example.usercenter.R
import com.example.usercenter.ui.custom.PhoneCode
import kotlinx.android.synthetic.main.activity_auth_code.*


class AuthCodeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_code)

        /**
         * 验证码监听
         */
        phoneCode.setOnInputListener(object : PhoneCode.OnInputListener{
            override fun onSucess(code: String?) {
                if (code == "157790"){
                    startActivity(Intent(this@AuthCodeActivity,InputNameActivity::class.java))
                    finish()
                }else{
                    Toast.makeText(this@AuthCodeActivity,"验证码错误！",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onInput() {

            }

        })

        /**
         * 倒计时监听
         */
        text_countDown.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                // 1. 创建一个通知(必须设置channelId)
                val context = applicationContext
                val channelId = "ChannelId" // 通知渠道

                val notification: Notification = Notification.Builder(context)
                    .setChannelId(channelId)
                    .setSmallIcon(R.drawable.ic_action_back)
                    .setContentTitle("验证码")
                    .setContentText("157790")
                    .build()
                // 2. 获取系统的通知管理器(必须设置channelId)
                val notificationManager = context
                    .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                val channel = NotificationChannel(
                    channelId,
                    "通知的渠道名称",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                notificationManager.createNotificationChannel(channel)
                // 3. 发送通知(Notification与NotificationManager的channelId必须对应)
                notificationManager.notify(1, notification)
            }else{
                // 创建通知(标题、内容、图标)
                // 创建通知(标题、内容、图标)
                val notification: Notification = Notification.Builder(this)
                    .setContentTitle("验证码")
                    .setContentText("157790")
                    .setSmallIcon(R.drawable.ic_action_back)
                    .build()
                // 创建通知管理器
                val manager = this
                    .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                // 发送通知
                manager.notify(1, notification)
            }
            //启动倒计时
            text_countDown.sendVerifyCode()
        }
    }

}
