package com.example.usercenter.ui.custom

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.util.AttributeSet
import android.widget.TextView

/**
 * @author AZhung
 * @date 2020/3/7
 * @description
 */
@SuppressLint("AppCompatCustomView")
class CountDownText(mContext: Context, attrSet: AttributeSet) : TextView(mContext, attrSet) {
    private val mHandler: Handler = Handler();
    private var mCountTime = 60


    init {
        this.text = "点击获取验证码"
    }

    /*
           倒计时，并处理点击事件
        */
    fun sendVerifyCode() {
        mHandler.postDelayed(countDown, 0)
    }

    /*
        倒计时
     */
    private val countDown = object : Runnable {
        @SuppressLint("SetTextI18n")
        override fun run() {
            this@CountDownText.text = mCountTime.toString() +"秒后可重新获取验证码"
            this@CountDownText.setTextColor(Color.GRAY)
            this@CountDownText.isEnabled = false

            if (mCountTime > 0) {
                mHandler.postDelayed(this, 1000)
            } else {
                resetCounter()
            }
            mCountTime--
        }
    }

    fun removeRunable() {
        mHandler.removeCallbacks(countDown)
    }

    //重置按钮状态
    fun resetCounter(vararg text: String) {
        this.isEnabled = true
        if (text.isNotEmpty() && "" != text[0]) {
            this.text = text[0]
        } else {
            this.text = "点击获取验证码"
        }
        this.setTextColor(Color.BLUE)
        mCountTime = 60
    }
}