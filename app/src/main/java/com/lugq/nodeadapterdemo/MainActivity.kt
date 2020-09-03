package com.lugq.nodeadapterdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.entity.node.BaseNode
import com.lugq.nodeadapterdemo.adapter.NodeTreeAdapter
import com.lugq.nodeadapterdemo.entity.FirstNode
import com.lugq.nodeadapterdemo.entity.SecondNode
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = NodeTreeAdapter()

        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = adapter

        adapter.setList(getEntity())

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
        }
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
