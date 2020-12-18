package com.lugq.nodeadapterdemo

import android.app.Application
import com.blankj.utilcode.util.Utils

/**
 * @Description
 * @Author Lu Guoqiang
 * @Time 12/18/20 4:32 PM
 */
class App:Application() {
    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
    }
}