package com.lugq.nodeadapterdemo.lesson

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.entity.node.BaseNode
import com.lugq.nodeadapterdemo.R
import kotlinx.android.synthetic.main.activity_check_box.*
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * 点击监听返回父节点的思路？
 * 思路1：设置数据的时候将父节点的字段保存进入子节点
 */
class LessonActivity : AppCompatActivity() {

    companion object {
        const val TAG = "CommonActivity"
    }

    private lateinit var mCommonAdapter: LessonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test3)
        mCommonAdapter = LessonAdapter()

        mRecyclerView.layoutManager =
            LinearLayoutManager(this).apply { orientation = LinearLayoutManager.HORIZONTAL }

        mRecyclerView.adapter = mCommonAdapter

        mCommonAdapter.setList(setInfo(getEntity()))
        mCommonAdapter.firstRefresh(setInfo(getEntity()))

        mCommonAdapter.addChildClickViewIds(R.id.btnTest, R.id.btnLugq)
    }

    /**
     * 将子item赋值一个父的Entity
     *
     * 目的：点击子item可以打印出父节点中的数据
     */
    private fun setInfo(dataList: List<LessonFirstNode>?): List<LessonFirstNode>? {
        if (dataList != null) {
            for (index in dataList.indices) {
                val item = dataList[index]
                // 给footView设置数据
                //item.mFooterNode.parentTitle = item.title

                val childItem = item.childNode as List<LessonFirstNode.SecondeNodeJ>
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

    private fun getEntity(): List<LessonFirstNode>? {
        val dataList = ArrayList<LessonFirstNode>()
        // 循环添加数据
        for (i in 1..3) {
            val firstNode =
                LessonFirstNode("标题${i}")
            val secondList = ArrayList<LessonFirstNode.SecondeNodeJ>()
            for (j in 1..5) {
                val secondNodeList: MutableList<BaseNode> = ArrayList()
                val secondEntity =
                    LessonFirstNode.SecondeNodeJ(secondNodeList, "标题${(0..100).random()}")
                secondEntity.id = "${i}_${j}"
                secondList.add(secondEntity)
            }
            firstNode.mSubItems = secondList
            dataList.add(firstNode)
        }
        return dataList
    }
}
