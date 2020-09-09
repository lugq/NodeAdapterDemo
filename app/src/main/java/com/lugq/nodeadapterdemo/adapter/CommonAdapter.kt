package com.lugq.nodeadapterdemo.adapter

import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.lugq.nodeadapterdemo.entity.FirstNodeJ

class CommonAdapter : BaseNodeAdapter() {
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
        val node = data[position]
        if (node is FirstNodeJ) {
            return 1
        } else if (node is FirstNodeJ.SecondeNodeJ) {
            return 2
        } else if (node is FirstNodeJ.FooterNode) {
            return 3
        }
        return -1
    }

}