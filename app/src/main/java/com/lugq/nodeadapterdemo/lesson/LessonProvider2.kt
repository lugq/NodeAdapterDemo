package com.lugq.nodeadapterdemo.adapter

import android.util.Log
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lugq.nodeadapterdemo.R
import com.lugq.nodeadapterdemo.entity.FirstNodeJ
import com.lugq.nodeadapterdemo.entity.LessonFirstNode

class LessonProvider2 : BaseNodeProvider() {
    companion object {
        // 必须带.java 不然闪退
        var TAG = "CommonProvider2"
    }

    override val itemViewType: Int
        get() = 2

    /**
     * 返回一级节点布局
     */
    override val layoutId: Int
        get() = R.layout.item_lesson2

    override fun convert(helper: BaseViewHolder, item: BaseNode) {
        Log.i(TAG, "position:${helper.adapterPosition}")
        val mSecondNode = item as LessonFirstNode.SecondeNodeJ
        //helper.setText(R.id.tvLocation, mSecondNode.title)
        /*
        helper.getView<Button>(R.id.btnLugq).setOnClickListener {
            Log.i(TAG, "点击的position${helper.adapterPosition}")
            getAdapter()?.remove(helper.adapterPosition)
        }*/
    }

}