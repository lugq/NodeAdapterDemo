package com.lugq.nodeadapterdemo.adapter

import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.provider.BaseNodeProvider
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lugq.nodeadapterdemo.R

class RootFooterNodeProvider : BaseNodeProvider() {

    override val itemViewType: Int
        get() = 3

    override val layoutId: Int
        get() = R.layout.node_footer

    override fun convert(helper: BaseViewHolder, item: BaseNode) {

    }


}