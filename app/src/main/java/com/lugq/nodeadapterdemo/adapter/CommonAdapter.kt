package com.lugq.nodeadapterdemo.adapter

import android.util.Log
import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.lugq.nodeadapterdemo.entity.FirstNodeJ
import com.lugq.nodeadapterdemo.listener.SelectedListener

class CommonAdapter : BaseNodeAdapter() {
    companion object {
        const val TAG = "NodeTreeAdapter"
    }

    private var mFirstProvider: CommonProvider1
    private var mSecondProvider: CommonProvider2

    init {
        val listener = MySelectedListener()
        mFirstProvider = CommonProvider1()
        mSecondProvider = CommonProvider2()
        mFirstProvider.setSelectedListener(listener)
        mSecondProvider.setSelectedListener(listener)
        //addNodeProvider(mFirstProvider)
        //addNodeProvider(mSecondProvider)
        addFullSpanNodeProvider(mFirstProvider)
        addFullSpanNodeProvider(mSecondProvider)
    }

    inner class MySelectedListener : SelectedListener {
        override fun getSelectedItems(num: Int) {
            notifyDataSetChanged()
            val hash = mSecondProvider.getSelectedItems()
            /*
            for (item in hash.iterator()) {
                val value = item.value
                Log.i(TAG, "item.key = $item.key 长度= ${value.size}")
            }*/

            Log.i(TAG, "有选择变化")
            notifyDataSetChanged()
        }
    }

    /**
     * 返回两个节点类型
     */
    override fun getItemType(data: List<BaseNode>, position: Int): Int {
        val node = data[position]
        if (node is FirstNodeJ) {
            return 1
        } else if (node is FirstNodeJ.SecondeNodeJ) {
            return 2
        }
        return -1
    }

    /**
     * 获取所有已选中的一级节点
     */
    fun getFirstNodeSelectedItems(): MutableList<BaseNode> {
        return mFirstProvider.getSelectedItems()
    }

    fun getSecondNodeSelectedItems(): MutableList<BaseNode> {
        return mSecondProvider.getSelectedItems()
    }

}