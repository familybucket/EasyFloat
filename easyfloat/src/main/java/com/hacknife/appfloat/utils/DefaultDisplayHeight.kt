package com.hacknife.appfloat.utils

import android.content.Context
import com.hacknife.appfloat.interfaces.OnDisplayHeight

/**
 * @author: liuzhenfeng
 * @function: 获取屏幕有效高度的实现类
 * @date: 2020-02-16  16:26
 */
internal class DefaultDisplayHeight :
    OnDisplayHeight {

    override fun getDisplayRealHeight(context: Context) = DisplayUtils.rejectedNavHeight(context)

}