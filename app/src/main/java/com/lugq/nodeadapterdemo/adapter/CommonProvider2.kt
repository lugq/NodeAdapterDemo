package com.lugq.nodeadapterdemo.adapter

import android.util.Log
import android.util.SparseBooleanArray
import android.view.View
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lugq.nodeadapterdemo.R
import com.lugq.nodeadapterdemo.entity.FirstNodeJ
import com.lugq.nodeadapterdemo.listener.SelectedListener
import java.util.*

class CommonProvider2 : BaseNodeProvider() {

    val TAG = CommonProvider2::class.java.simpleName

    private val mSelectedPositions: SparseBooleanArray = SparseBooleanArray()

    override val itemViewType: Int
        get() = 2

    /**
     * 返回一级节点布局
     */
    override val layoutId: Int
        get() = R.layout.item_common2

    override fun convert(helper: BaseViewHolder, item: BaseNode) {
        Log.i(TAG, "position:${helper.adapterPosition}")
        val mSecondNode = item as FirstNodeJ.SecondeNodeJ
        helper.setText(R.id.tvLocation, mSecondNode.title)

        //val cb = helper.getView<CheckBox>(R.id.checkbox)

        //cb.isChecked = isItemChecked(helper.adapterPosition)



    }

    fun isItemChecked(position: Int): Boolean {
        return mSelectedPositions.get(position)
    }

    fun setItemChecked(position: Int, isChecked: Boolean) {
        if (isChecked)
            Log.i(TAG, "选中了：$position isChecked $isChecked")
        mSelectedPositions.put(position, isChecked)
    }

    override fun onChildClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {
        Log.i(TAG, "position:$position")
        super.onChildClick(helper, view, data, position)
    }

    override fun onClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {
        Log.i(TAG, "position:$position")
        super.onClick(helper, view, data, position)
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
    fun getSelectedItems(): MutableList<BaseNode> {
        //val hash = HashMap<Int, MutableList<BaseNode>>()
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

}