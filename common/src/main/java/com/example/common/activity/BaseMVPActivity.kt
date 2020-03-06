package com.example.common.activity

import android.os.Bundle
import android.os.PersistableBundle
import com.example.common.base.BasePresenter
import com.example.common.base.IModel
import com.example.common.base.IView

/**
 * Author:LIZHUANG
 * Date:2019-11-30
 */
abstract class BaseMVPActivity<P : BasePresenter<IView?, IModel?>?> : BaseActivity() {
    private val presenter: BasePresenter<IView, IModel>? = null
    override fun onCreate(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(layoutId!!)
    }

    abstract val layoutId: Int?
}