package com.lugq.nodeadapterdemo.adapter

import android.util.Log
import android.util.SparseBooleanArray
import android.view.View
import android.widget.CheckBox
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lugq.nodeadapterdemo.R
import com.lugq.nodeadapterdemo.entity.FirstNode
import com.lugq.nodeadapterdemo.listener.SelectedListener
import java.util.ArrayList

class FirstProvider : BaseNodeProvider() {
    val TAG = FirstProvider::class.java.simpleName

    private val mSelectedPositions: SparseBooleanArray = SparseBooleanArray()

    override val itemViewType: Int
        get() = 1

    /**
     * 返回一级节点布局
     */
    override val layoutId: Int
        get() = R.layout.item_node_first


    override fun convert(helper: BaseViewHolder, item: BaseNode) {
        val firstNode = item as FirstNode

        helper.setText(R.id.tv_city, firstNode.title)

        val cb = helper.getView<CheckBox>(R.id.checkbox)

        cb.isChecked = isItemChecked(helper.adapterPosition)

        val position = helper.adapterPosition
        if (isItemChecked(position)) {
            setItemChecked(position, true)
        } else {
            setItemChecked(position, false)
        }

        helper.getView<FrameLayout>(R.id.rootView).setOnClickListener {
            if (isItemChecked(helper.adapterPosition)) {
                setItemChecked(helper.adapterPosition, false)
            } else {
                setItemChecked(helper.adapterPosition, true)
            }
            //notifyDataSetChanged()
            mSelectedListener?.getSelectedItems(0)
        }
    }

    fun isItemChecked(position: Int): Boolean {
        return mSelectedPositions.get(position)
    }

    fun setItemChecked(position: Int, isChecked: Boolean) {
        mSelectedPositions.put(position, isChecked)
    }

    override fun onClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {
        super.onClick(helper, view, data, position)
        Log.i(TAG, "点击了$position")
        getAdapter()?.expandOrCollapse(position, false, true, 110)
    }

    fun getSelectedItems(): MutableList<BaseNode> {
        val data = this.getAdapter()?.data

        val selectedList: MutableList<BaseNode> = ArrayList()

        if (!data.isNullOrEmpty()) {
            for (item in data.iterator()) {
                val position = getAdapter()?.getItemPosition(item)
                if (null != position) {
                    if (isItemChecked(position)) {
                        // 所有子节点
                        selectedList.add(item)
                    }
                }
            }
        }
        return selectedList
    }

    /*
    fun onClick(
        @NotNull helper: BaseViewHolder?, @NotNull view: View?, data: BaseNode?,
        position: Int
    ) {
        getAdapter()!!.expandOrCollapse(position)
    }*/

    private var mSelectedListener: SelectedListener? = null
    fun setSelectedListener(listener: SelectedListener) {
        mSelectedListener = listener
    }
}