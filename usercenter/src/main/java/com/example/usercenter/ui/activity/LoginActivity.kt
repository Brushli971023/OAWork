package com.example.usercenter.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import com.alibaba.android.arouter.facade.annotation.Route
import com.example.common.activity.BaseActivity
import com.example.common.common.BaseConstant
import com.example.usercenter.R
import kotlinx.android.synthetic.main.activity_login.*
import java.util.regex.Pattern

@Route(path = BaseConstant.ARouterToUserCenter)
class LoginActivity : BaseActivity() {

    private var spinner_language : Spinner? = null
    private var spinner_header : Spinner? = null
    private var edit_login : EditText? = null
    private var text_toRegister : TextView? = null
    private var btu_next : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
        initEvent()
    }

    private fun initView() {
        spinner_language = findViewById(R.id.spinner_language)
        spinner_header = findViewById(R.id.spinner_head)
        edit_login = findViewById(R.id.edit_login)
        text_toRegister = findViewById(R.id.text_toRegister)
        btu_next = findViewById(R.id.btu_login_next)
    }

    private fun initEvent() {
        var languageAdapter : ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_spinner_item,
            arrayOf("简体中文","English"))
        spinner_language!!.adapter = languageAdapter

        var headerAdapter : ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_spinner_item,
            arrayOf("+86","+010"))
        spinner_header!!.adapter = headerAdapter

        /**
         * 正则校验改变next状态
         */
        edit_login!!.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (isPhoneNumber(edit_login!!.text.toString().trim())){
                    btu_login_next.setBackgroundResource(R.drawable.button_background_blue_fill)
                    btu_login_next.isEnabled = true
                }else{
                    btu_login_next.setBackgroundResource(R.drawable.button_background_gray_fill)
                    btu_login_next.isEnabled = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        text_toRegister!!.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }

        btu_login_next.setOnClickListener {
            Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show()
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
