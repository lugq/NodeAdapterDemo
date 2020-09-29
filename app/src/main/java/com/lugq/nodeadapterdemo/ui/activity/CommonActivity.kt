package com.lugq.nodeadapterdemo.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.entity.node.BaseNode
import com.lugq.nodeadapterdemo.R
import com.lugq.nodeadapterdemo.adapter.CommonAdapter
import com.lugq.nodeadapterdemo.adapter.CommonProvider2
import com.lugq.nodeadapterdemo.entity.FirstNodeJ
import kotlinx.android.synthetic.main.activity_check_box.*

/**
 * 点击监听返回父节点的思路？
 * 思路1：设置数据的时候将父节点的字段保存进入子节点
 */
class CommonActivity : AppCompatActivity() {

    companion object {
        const val TAG = "CommonActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_common)
        val mCommonAdapter = CommonAdapter()

        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mCommonAdapter

        //adapter.setList(getEntity())
        mCommonAdapter.setList(setInfo(getEntity()))
        mCommonAdapter.firstRefresh(setInfo(getEntity()))

        mCommonAdapter.addChildClickViewIds(R.id.btnTest, R.id.btnLugq)
        mCommonAdapter.setOnItemChildClickListener { adapter, view, position ->
            when (view.id) {
                R.id.btnLugq -> {
                    val entity = adapter.data[position] as FirstNodeJ.SecondeNodeJ
                    Toast.makeText(this@CommonActivity, "点击了click${entity}", Toast.LENGTH_SHORT)
                        .show()
                }
                R.id.btnTest -> {

                }
            }
            val data = adapter.data[position]
            if (data is FirstNodeJ.SecondeNodeJ) {
                val secondNode = data as FirstNodeJ.SecondeNodeJ
                //adapter.remove(secondNode)

                val subItems = search(secondNode.id, mCommonAdapter.listData)
                if (subItems != null) {
                    for (subItem in subItems) {
                        Log.i(TAG, subItem.toString())
                    }
                }

            }
            /*
            if (data is FirstNode) {

            }
             */
            /**
             * 引发的问题：最里层的点击事件获取外层的Entity
             *
             * 解决方案：在子的Entity中设置父的Entity的一些字段
             */
            /**
             * 引发的问题：最里层的点击事件获取外层的Entity
             *
             * 解决方案：在子的Entity中设置父的Entity的一些字段
             */
            //Log.i(TAG, "触发点击:${data.toString()} -- position:${position}")
        }
    }

    /**
     * 将子item赋值一个父的Entity
     *
     * 目的：点击子item可以打印出父节点中的数据
     */
    private fun setInfo(dataList: List<FirstNodeJ>?): List<FirstNodeJ>? {
        if (dataList != null) {
            for (index in dataList.indices) {
                val item = dataList[index]
                // 给footView设置数据
                item.mFooterNode.parentTitle = item.title

                val childItem = item.childNode as List<FirstNodeJ.SecondeNodeJ>
                if (childItem != null) {
                    //向2级的subItem 添加数据
                    for (child in childItem) {
                        child.parentTitle = item.title
                        child.parentPosition = index
                    }
                }
            }
        }
        return dataList
    }

    private fun getEntity(): List<FirstNodeJ>? {
        val dataList = ArrayList<FirstNodeJ>()
        // 循环添加数据
        for (i in 1..10) {
            val firstNode = FirstNodeJ("标题${i}")
            val secondList = ArrayList<FirstNodeJ.SecondeNodeJ>()
            for (j in 1..5) {
                val secondNodeList: MutableList<BaseNode> = ArrayList()
                val secondEntity = FirstNodeJ.SecondeNodeJ(secondNodeList, "标题${(0..100).random()}")
                secondEntity.id = "${i}_${j}"
                secondList.add(secondEntity)
            }
            firstNode.mSubItems = secondList
            dataList.add(firstNode)
        }
        return dataList
    }

    /**
     * 根据ID遍历对应的一组父娃
     */
    private fun search(id: String, dataList: List<FirstNodeJ>?): List<FirstNodeJ.SecondeNodeJ>? {
        if (dataList.isNullOrEmpty()) return null
        for (index in dataList.indices) {
            val data = dataList[index]
            val subItems = data.mSubItems
            for (item in subItems) {
                if (id == item.id)
                    return subItems
            }
        }
        return null
    }
}
