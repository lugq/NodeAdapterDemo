package com.lugq.nodeadapterdemo.adapter

import android.util.Log
import android.util.SparseBooleanArray
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lugq.nodeadapterdemo.R
import com.lugq.nodeadapterdemo.entity.FirstNodeJ
import com.lugq.nodeadapterdemo.listener.SelectedListener
import org.greenrobot.eventbus.EventBus
import java.util.*


class CommonProvider1 : BaseNodeProvider() {
    companion object {
        const val TAG = "FirstProvider"
    }

    private val mSelectedPositions: SparseBooleanArray = SparseBooleanArray()

    override val itemViewType: Int
        get() = 1

    /**
     * 返回一级节点布局
     */
    override val layoutId: Int
        get() = R.layout.item_common1




    override fun convert(helper: BaseViewHolder, item: BaseNode) {
        val firstNode = item as FirstNodeJ
        if (firstNode.isYincang) {
            val view = helper.getView<LinearLayout>(R.id.llroot)
            view.layoutParams.height = 0
        } else {
            val view = helper.getView<LinearLayout>(R.id.llroot)
            view.layoutParams.height = 100
        }
        helper.setText(R.id.tv_city, firstNode.title)

        //val cb = helper.getView<CheckBox>(R.id.checkbox)

        //cb.isChecked = isItemChecked(helper.adapterPosition)

        /*
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
        }*/

        /*
        helper.getView<Button>(R.id.btnTest).setOnClickListener {
            Log.i(TAG, "item信息：${item.toString()}")
        }*/
    }

    fun isItemChecked(position: Int): Boolean {
        return mSelectedPositions.get(position)
    }

    fun setItemChecked(position: Int, isChecked: Boolean) {
        mSelectedPositions.put(position, isChecked)
    }

    /****/
    override fun onClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {
        super.onClick(helper, view, data, position)
        Log.i(TAG, "点击了$position")
        Log.i(TAG, "点击了位置：${helper.adapterPosition}")
        // 这里使用payload进行增量刷新（避免整个item刷新导致的闪烁，不自然）

        /**
         * 请问FirstNodeProvider中如何展开其中一个node，并且折叠其他所有node？（类似单选框）遍历折叠后再展开position会越界
         * https://github.com/CymChad/BaseRecyclerViewAdapterHelper/issues/3333
         */
        // 需求1：
        //getAdapter()?.expandOrCollapse(position, false, true, 110)

        // 需求2：点击后收起其它的
        val da = data as FirstNodeJ
        if (da.isExpanded) {
            getAdapter()?.collapse(position)
        }else {
            getAdapter()?.expandAndCollapseOther(position)
            EventBus.getDefault().post(data)
        }


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
    override fun onClick(
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