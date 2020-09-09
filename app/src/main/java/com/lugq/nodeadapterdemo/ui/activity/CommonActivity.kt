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
import com.lugq.nodeadapterdemo.entity.FirstNode
import com.lugq.nodeadapterdemo.entity.FirstNodeJ
import com.lugq.nodeadapterdemo.entity.SecondNode
import kotlinx.android.synthetic.main.activity_check_box.*

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

        //adapter.setList(getEntity())
        adapter.setList(test(getEntity()))

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
                 *
                 * 解决方案：在子的Entity中设置父的Entity的一些字段
                 */
                Log.i(TAG, "触发点击:${data.toString()}")
            }
        })
    }

    /**
     * 将子item赋值一个父的Entity
     *
     * 目的：点击子item可以打印出父节点中的数据
     */
    private fun test(data: List<FirstNodeJ>?): List<FirstNodeJ>? {
        if (data != null) {
            for (item in data) {
                // 给footView设置数据
                item.mFooterNode.parentTitle = item.title
                //val footItem = item.footerNode as FirstNodeJ.FooterNode
                //footItem.parentTitle = item.title

                val childItem = item.childNode as List<FirstNodeJ.SecondeNodeJ>
                if (childItem != null) {
                    //向2级的subItem 添加数据
                    for (child in childItem) {
                        child.parentTitle = item.title
                    }
                }
            }
        }
        return data
    }

    private fun getEntity(): List<FirstNodeJ>? {
        val dataList = ArrayList<FirstNodeJ>()
        // 循环添加数据
        for (i in 1..10) {
            val firstNode = FirstNodeJ("标题${i}")

            val secondList = ArrayList<FirstNodeJ.SecondeNodeJ>()
            for (j in 1..5) {
                val secondNodeList: MutableList<BaseNode> = ArrayList()
                val secondEntity = FirstNodeJ.SecondeNodeJ(secondNodeList, "标题")
                secondList.add(secondEntity)
            }
            firstNode.mSubItems = secondList
            dataList.add(firstNode)
        }
        return dataList
    }
}
