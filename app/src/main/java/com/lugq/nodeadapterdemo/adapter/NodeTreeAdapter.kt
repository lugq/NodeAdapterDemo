package com.lugq.nodeadapterdemo.adapter

import android.util.Log
import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.lugq.nodeadapterdemo.entity.FirstNode
import com.lugq.nodeadapterdemo.entity.SecondNode
import com.lugq.nodeadapterdemo.listener.SelectedListener
import java.util.ArrayList

class NodeTreeAdapter() : BaseNodeAdapter() {
    val TAG = NodeTreeAdapter::class.java.simpleName

    private lateinit var mSecondProvider: SecondProvider

    init {
        val listener = MySelectedListener()
        val mFirstProvider = FirstProvider()
        mFirstProvider.setSelectedListener(listener)
        mSecondProvider = SecondProvider()
        mSecondProvider.setSelectedListener(object : SelectedListener {
            override fun getSelectedItems(num: Int) {
                notifyDataSetChanged()
                val hash = mSecondProvider.getSelectedItems()
                for (item in hash.iterator()) {
                    item.key
                    val value = item.value
                    Log.i(TAG, "item.key = $item.key 长度= ${value.size}")
                }
            }
        })
        addNodeProvider(mFirstProvider)
        addNodeProvider(mSecondProvider)
    }

    inner class MySelectedListener : SelectedListener {
        override fun getSelectedItems(num: Int) {
            Log.i(TAG, "有选择变化")
            notifyDataSetChanged()
        }
    }

    /**
     * 返回两个节点类型
     */
    override fun getItemType(data: List<BaseNode>, position: Int): Int {
        val node = data[position]
        if (node is FirstNode) {
            return 1
        } else if (node is SecondNode) {
            return 2
        }
        return -1
    }

    fun getSelectedItems() {
        val selectedList: MutableList<BaseNode> = ArrayList()

        mSecondProvider.getSelectedItems()

        mSecondProvider.getSelectedItems()
    }

}