package com.lugq.nodeadapterdemo.lesson

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
    override val itemViewType: Int
        get() = 1

    override val layoutId: Int
        get() = R.layout.item_lesson1

    override fun convert(helper: BaseViewHolder, item: BaseNode) {
        val firstNode = item as LessonFirstNode
        // 关键代码：做分组头部Item显示和隐藏操作
        if (firstNode.isYincang) {
            val view = helper.getView<ConstraintLayout>(R.id.llroot)
            view.layoutParams.width = 0
        } else {
            val view = helper.getView<ConstraintLayout>(R.id.llroot)
            view.layoutParams.width = ConvertUtils.dp2px(205f)
        }
    }

    override fun onClick(helper: BaseViewHolder, view: View, data: BaseNode, position: Int) {
        super.onClick(helper, view, data, position)
        /**
         * 请问FirstNodeProvider中如何展开其中一个node，并且折叠其他所有node？（类似单选框）遍历折叠后再展开position会越界
         * https://github.com/CymChad/BaseRecyclerViewAdapterHelper/issues/3333
         */
        // 点击后收起其它的
        val currEntity = data as LessonFirstNode
        if (currEntity.isExpanded) {
            getAdapter()?.collapse(position)
        } else {
            /**
             * 两步操作：
             * 第一步：还原UI
             * 将列表的所有集合中的Item中isYincang字段置为 false
             * 第二步：隐藏当前头部的item视图
             */
            val dataList = getAdapter()?.data
            dataList?.apply {
                for (index in dataList.indices) {
                    val entity = dataList[index]
                    if (entity is LessonFirstNode) {
                        entity.isYincang = false
                    }
                }
            }
            currEntity.isYincang = true

            getAdapter()?.expandAndCollapseOther(position)
        }
    }

}