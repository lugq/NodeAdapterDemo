package com.lugq.nodeadapterdemo.lesson

import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.blankj.utilcode.util.ConvertUtils
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lugq.nodeadapterdemo.R
import org.greenrobot.eventbus.EventBus

/**
 * 横向列表父级Item视图
 */
class LessonProvider1 : BaseNodeProvider() {
    companion object {
        const val TAG = "FirstProvider"
    }

    override val itemViewType: Int
        get() = 1

    /**
     * 返回一级节点布局
     */
    override val layoutId: Int
        get() = R.layout.item_lesson1

    override fun convert(helper: BaseViewHolder, item: BaseNode) {
        val firstNode = item as LessonFirstNode
        if (firstNode.isYincang) {
            val view = helper.getView<ConstraintLayout>(R.id.llroot)
            view.layoutParams.width = 0
        } else {
            val view = helper.getView<ConstraintLayout>(R.id.llroot)
            view.layoutParams.width = ConvertUtils.dp2px(205f)
        }
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
        val da = data as LessonFirstNode
        if (da.isExpanded) {
            // 每次

            getAdapter()?.collapse(position)
        } else {
            EventBus.getDefault().post(data)

            getAdapter()?.expandAndCollapseOther(position)

        }
    }

}