package com.lugq.nodeadapterdemo.adapter

import android.util.Log
import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.lugq.nodeadapterdemo.entity.FirstNode
import com.lugq.nodeadapterdemo.entity.SecondNode
import java.util.ArrayList

class NodeTreeAdapter() : BaseNodeAdapter() {
    val TAG = NodeTreeAdapter::class.java.simpleName

    private lateinit var mSecondProvider: SecondProvider

    init {
        val mFirstProvider = FirstProvider()
        mSecondProvider = SecondProvider()
        mSecondProvider.setSelectedListener(object : SecondProvider.SelectedListener {
            override fun getSelectedItems(num: Int) {
                notifyDataSetChanged()
                val hash = mSecondProvider.getSelectedItems()
                for (item in hash.iterator()) {
                    item.key;
                    Log.i(TAG, "item.key = $item.key")
                }
            }
        })
        addNodeProvider(FirstProvider())
        addNodeProvider(mSecondProvider)
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