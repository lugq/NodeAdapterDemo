package com.lugq.nodeadapterdemo.entity

import com.chad.library.adapter.base.entity.node.BaseNode

class KotlinEntity(
    val chs: MutableList<BaseNode>,
    override val childNode: MutableList<BaseNode>? = chs
) : BaseNode() {

}