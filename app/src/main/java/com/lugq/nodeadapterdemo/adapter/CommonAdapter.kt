package com.lugq.nodeadapterdemo.adapter

import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.lugq.nodeadapterdemo.entity.FirstNodeJ
import com.oushangfeng.pinnedsectionitemdecoration.PinnedHeaderItemDecoration

class CommonAdapter : BaseNodeAdapter() {
    var listData: ArrayList<FirstNodeJ>? = null

    companion object {
        //const val TAG = "NodeTreeAdapter"
    }

    init {
        addFullSpanNodeProvider(CommonProvider1())
        addFullSpanNodeProvider(CommonProvider2())
        addFullSpanNodeProvider(RootFooterNodeProvider())
    }

    /**
     * 返回两个节点类型
     */
    override fun getItemType(data: List<BaseNode>, position: Int): Int {
        return when (data[position]) {
            is FirstNodeJ -> {
                1
            }
            is FirstNodeJ.SecondeNodeJ -> {
                2
            }
            is FirstNodeJ.FooterNode -> {
                3
            }
            else -> -1
        }
    }

    fun firstRefresh(data: List<FirstNodeJ>?) {
        if (null == listData)
            listData = ArrayList()
        listData?.clear()
        if (null != data)
            listData?.addAll(0, data)
    }

    fun loadRefresh(data: List<FirstNodeJ>) {
        listData?.addAll(data)
    }

}