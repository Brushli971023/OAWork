package com.example.oa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.alibaba.android.arouter.launcher.ARouter
import com.example.common.activity.BaseActivity
import com.example.common.common.BaseConstant

class MainActivity : BaseActivity(){

    private var btu_into : Button? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        initEvent()
    }

    private fun initView() {
        btu_into = findViewById(R.id.btu_intoApp)
    }

    private fun initEvent() {
        btu_into!!.setOnClickListener {
            ARouter.getInstance().build(BaseConstant.ARouterToUserCenter).navigation()
        }
    }
}
