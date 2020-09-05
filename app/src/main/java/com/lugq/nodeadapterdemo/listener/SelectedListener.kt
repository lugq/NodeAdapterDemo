package com.lugq.nodeadapterdemo.listener

/**
 * 只要列表中有checkbox操作就回调
 */
interface SelectedListener {
    fun getSelectedItems(num: Int)
}