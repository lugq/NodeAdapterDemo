package com.lugq.nodeadapterdemo.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.entity.node.BaseNode
import com.chad.library.adapter.base.listener.OnItemChildClickListener
import com.lugq.nodeadapterdemo.R
import com.lugq.nodeadapterdemo.adapter.CommonAdapter
import com.lugq.nodeadapterdemo.adapter.HasCheckBoxAdapter
import com.lugq.nodeadapterdemo.entity.FirstNode
import com.lugq.nodeadapterdemo.entity.SecondNode
import kotlinx.android.synthetic.main.activity_check_box.*
import java.util.*

/**
 * 点击监听返回父节点的思路？
 * 思路1：设置数据的时候将父节点的字段保存进入子节点
 */
class CommonActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)
        val adapter = CommonAdapter()

        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = adapter

        adapter.setList(getEntity())

        adapter.addChildClickViewIds(R.id.btnTest, R.id.btnLugq)
        adapter.setOnItemChildClickListener(object : OnItemChildClickListener {
            override fun onItemChildClick(
                adapter: BaseQuickAdapter<*, *>,
                view: View,
                position: Int
            ) {
                val data = adapter.data[position]
                if (data is SecondNode) {

                }
                if (data is FirstNode) {

                }
                /**
                 * 引发的问题：最里层的点击事件获取外层的Entity
                 */
                Log.i(TAG, "data.toString():${data.toString()}")

            }

        })

        /*
        btnOk.setOnClickListener {
            val firstNodes = adapter.getFirstNodeSelectedItems()
            for (item in firstNodes) {
                Log.i(TAG, "已选中的1级节点：${item.toString()}")
            }
            Log.i(TAG, "----------------------------------------------")
            val secondNodes = adapter.getSecondNodeSelectedItems()
            for (item in secondNodes) {
                Log.i(TAG, "已选中的2级节点：${item.toString()}")
            }
        }*/
    }

    private fun getEntity(): List<BaseNode>? {
        // 一级节点容器
        val list: MutableList<BaseNode> =
            ArrayList()
        for (i in 0..7) {
            // 二级节点容器
            val secondNodeList: MutableList<BaseNode> = ArrayList()

            // 二级节点的
            for (a in 0..4) {
                secondNodeList.add(SecondNode(null, "地址$i -- $a"))
            }

            val entity = FirstNode(secondNodeList, "First Node $i")
            // 模拟 默认第0个是展开的
            //entity.setExpanded(i == 0)
            list.add(entity)
        }
        return list
    }
}
