package com.lugq.nodeadapterdemo.adapter

import android.util.SparseBooleanArray
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lugq.nodeadapterdemo.R
import java.util.ArrayList

class SecondProvider : BaseNodeProvider() {

    val mSelectedPositions: SparseBooleanArray = SparseBooleanArray()

    override val itemViewType: Int
        get() = 2

    /**
     * 返回一级节点布局
     */
    override val layoutId: Int
        get() = R.layout.item_node_second

    var parentNodePosition: Int? = 0

    fun getParentNodePosition() {

    }

    override fun convert(helper: BaseViewHolder, item: BaseNode) {
        parentNodePosition = getAdapter()?.findParentNode(item)

        val cb = helper.getView<CheckBox>(R.id.checkbox)

        cb.isChecked = isItemChecked(helper.adapterPosition)

        val position = helper.adapterPosition
        if (isItemChecked(position)) {
            setItemChecked(position, true)
        } else {
            setItemChecked(position, false)
        }

        helper.getView<LinearLayout>(R.id.rootView).setOnClickListener {
            if (isItemChecked(helper.adapterPosition)) {
                setItemChecked(helper.adapterPosition, false)
            } else {
                setItemChecked(helper.adapterPosition, true)
            }

            mSelectedListener?.getSelectedItems(0)
            //notifyDataSetChanged()
        }


    }

    fun isItemChecked(position: Int): Boolean {
        return mSelectedPositions.get(position)
    }

    fun setItemChecked(position: Int, isChecked: Boolean) {
        mSelectedPositions.put(position, isChecked)
    }

    override fun onChildClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {
        super.onChildClick(helper, view, data, position)
    }

    private var mSelectedListener: SelectedListener? = null
    fun setSelectedListener(listener: SelectedListener) {
        mSelectedListener = listener
    }


    // 获得选中条目的结果
    /**
     * 返回父节点的位置
     * 返回每个位置对应的二级已选项目
     */
    fun getSelectedItems(): HashMap<Int, MutableList<BaseNode>> {
        val hash = HashMap<Int, MutableList<BaseNode>>()
        val data = this.getAdapter()?.data

        val selectedList: MutableList<BaseNode> = ArrayList()

        if (!data.isNullOrEmpty()) {
            for (item in data.iterator()) {
                val position = getAdapter()?.getItemPosition(item)
                if (null != position) {
                    if (isItemChecked(position)) {
                        // 所有子节点
                        selectedList.add(item)
                        val parentNodePosition = this.getAdapter()?.findParentNode(item)
                        if (parentNodePosition != null) {
                            hash[parentNodePosition] = selectedList
                        }
                    }
                }
            }
        }
        return hash
    }

    interface SelectedListener {
        // 返回已选中的个数
        fun getSelectedItems(num: Int)
    }
}