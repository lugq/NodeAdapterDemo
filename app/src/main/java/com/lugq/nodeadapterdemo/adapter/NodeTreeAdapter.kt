package com.lugq.nodeadapterdemo.adapter

import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.lugq.nodeadapterdemo.entity.FirstNode
import com.lugq.nodeadapterdemo.entity.SecondNode

class NodeTreeAdapter() : BaseNodeAdapter() {

    init {
        addNodeProvider(FirstProvider())
        addNodeProvider(SecondProvider())
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


}