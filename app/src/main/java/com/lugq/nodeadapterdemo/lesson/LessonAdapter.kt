package com.lugq.nodeadapterdemo.lesson

import com.chad.library.adapter.base.BaseNodeAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.lugq.nodeadapterdemo.adapter.RootFooterNodeProvider

class LessonAdapter : BaseNodeAdapter() {
    var listData: ArrayList<LessonFirstNode>? = null

    init {
        addFullSpanNodeProvider(LessonProvider1())
        addFullSpanNodeProvider(LessonProvider2())
        addFullSpanNodeProvider(RootFooterNodeProvider())
    }

    /**
     * 返回两个节点类型
     */
    override fun getItemType(data: List<BaseNode>, position: Int): Int {
        return when (data[position]) {
            is LessonFirstNode -> {
                1
            }
            is LessonFirstNode.SecondeNodeJ -> {
                2
            }
            is LessonFirstNode.FooterNode -> {
                3
            }
            else -> -1
        }
    }

    fun firstRefresh(data: List<LessonFirstNode>?) {
        if (null == listData)
            listData = ArrayList()
        listData?.clear()
        if (null != data)
            listData?.addAll(0, data)
    }

}