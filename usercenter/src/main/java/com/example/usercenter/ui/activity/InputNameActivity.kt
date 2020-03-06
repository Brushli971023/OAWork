package com.example.usercenter.ui.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.example.common.activity.BaseActivity
import com.example.usercenter.R
import kotlinx.android.synthetic.main.activity_input_name.*
import java.util.regex.Pattern

class InputNameActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input_name)

        initEvent()
    }

    private fun initEvent() {
        /**
         * 正则校验改变next状态
         */
        edit_inputName!!.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!isRight(edit_inputName!!.text.toString().trim())){
                    btu_inputName_next.setBackgroundResource(R.drawable.button_background_blue_fill)
                    btu_inputName_next.isEnabled = true
                }else{
                    btu_inputName_next.setBackgroundResource(R.drawable.button_background_gray_fill)
                    btu_inputName_next.isEnabled = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        btu_inputName_next.setOnClickListener {
            Toast.makeText(this,"注册成功", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    /**
     * 正则校验（电话号码）
     */
    private fun isRight(number : String): Boolean {
        var regExp : String = "[`~!@#\$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]"
        val compile = Pattern.compile(regExp)
        val matcher = compile.matcher(number)
        return matcher.find()
    }
}
