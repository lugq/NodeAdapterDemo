package com.lugq.nodeadapterdemo.adapter

import android.util.Log
import android.view.View
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lugq.nodeadapterdemo.R

class FirstProvider : BaseNodeProvider() {
    val TAG = FirstProvider::class.java.simpleName

    override val itemViewType: Int
        get() = 1

    /**
     * 返回一级节点布局
     */
    override val layoutId: Int
        get() = R.layout.item_node_first


    override fun convert(helper: BaseViewHolder, item: BaseNode) {

    }

    override fun onClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {
        super.onClick(helper, view, data, position)
        Log.i(TAG, "点击了$position")
        getAdapter()?.expandOrCollapse(position, false, true, 110)
    }

    /*
    fun onClick(
        @NotNull helper: BaseViewHolder?, @NotNull view: View?, data: BaseNode?,
        position: Int
    ) {
        getAdapter()!!.expandOrCollapse(position)
    }*/
}