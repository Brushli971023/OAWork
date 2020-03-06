package com.example.common.base

/**
 * Author:LIZHUANG
 * Date:2019-11-30
 */
class BasePresenter<V : IView?, M : IModel?> {
    var mView: V? = null
    var mModel: M? = null
    /**
     * 绑定view
     */
    fun AttachView(view: V) {
        mView = view
    }

    /**
     * 解绑view
     */
    fun DettachView() {
        if (mView != null) {
            mView = null
            System.gc()
        }
    }
}