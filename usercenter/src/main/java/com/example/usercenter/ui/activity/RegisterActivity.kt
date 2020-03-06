package com.example.usercenter.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.common.activity.BaseActivity
import com.example.usercenter.R
import kotlinx.android.synthetic.main.activity_register.*
import java.util.regex.Pattern

class RegisterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initEvent()
    }

    private fun initEvent() {
        var headerAdapter : ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_spinner_item,
            arrayOf("+86","+010"))
        spinner_register_head!!.adapter = headerAdapter


        /**
         * 正则校验改变next状态
         */
        edit_register!!.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (isPhoneNumber(edit_register!!.text.toString().trim())){
                    btu_register_next.setBackgroundResource(R.drawable.button_background_blue_fill)
                    btu_register_next.isEnabled = true
                }else{
                    btu_register_next.setBackgroundResource(R.drawable.button_background_gray_fill)
                    btu_register_next.isEnabled = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        btu_register_next.setOnClickListener {
            if (checkbox_register.isChecked){
                startActivity(Intent(this,AuthCodeActivity::class.java))
                finish()
            }else{
                Toast.makeText(this,"请阅读并同意服务协议和隐私政策",Toast.LENGTH_SHORT).show()
            }
        }
    }


    /**
     * 正则校验（电话号码）
     */
    private fun isPhoneNumber(number : String): Boolean {
        var regExp : String = "^1[3|4|5|7|8]\\d{9}$"
        val compile = Pattern.compile(regExp)
        val matcher = compile.matcher(number)
        return matcher.find()
    }
}
