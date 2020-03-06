package com.example.common.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class CustomTitleBar : LinearLayout {
    private val tv_customview_titlebar_left: ImageView? = null
    private val tv_customview_titlebar_title: TextView? = null
    private val tv_customview_titlebar_right: TextView? = null

    constructor(context: Context?) : super(context) {}
    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        initView(context, attrs)
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
    }

    private fun initView(
        context: Context,
        attrs: AttributeSet?
    ) { //        View view = LayoutInflater.from(context).inflate(R.layout.customview_titlebar, null);
//
//        tv_customview_titlebar_left = view.findViewById(R.id.tv_customview_titlebar_left);
//        tv_customview_titlebar_right = view.findViewById(R.id.tv_customview_titlebar_right);
//        tv_customview_titlebar_title = view.findViewById(R.id.tv_customview_titlebar_title);
//
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTitleBar);
//        int titleBarName = typedArray.getResourceId(R.styleable.CustomTitleBar_titleBarName, 0);
//        int leftSrc = typedArray.getResourceId(R.styleable.CustomTitleBar_leftSrc, 0);
//        int reightSrc = typedArray.getResourceId(R.styleable.CustomTitleBar_reightSrc, 0);
//        if (titleBarName != 0){
//            tv_customview_titlebar_title.setText(titleBarName);
//        }
//        if (leftSrc != 0){
//            tv_customview_titlebar_left.setImageResource(leftSrc);
//        }
//        if (titleBarName != 0){
//            tv_customview_titlebar_right.setBackgroundResource(reightSrc);
//        }
//
//        this.addView(view);
//        /**
//         * 释放资源
//         */
//        typedArray.recycle();
    }

    /**
     * @param listener
     * @Desc 左边图片点击事件
     */
    fun leftOnClick(listener: OnClickListener?) {
        tv_customview_titlebar_left!!.setOnClickListener(listener)
    }

    /**
     * @param listener
     * @Desc 右边图片点击事件
     */
    fun rightOnClick(listener: OnClickListener?) {
        tv_customview_titlebar_right!!.setOnClickListener(listener)
    }
}