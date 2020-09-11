package com.lugq.nodeadapterdemo

import android.util.Log
import com.chad.library.adapter.base.entity.node.BaseNode
import com.lugq.nodeadapterdemo.entity.FirstNodeJ
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() {
        val dataList = getEntity()
        // 查找ID = 1_2 的一组数据

        val subItems = search("1_2", dataList)
        if (subItems != null) {
            for (subItem in subItems) {
                println(subItem.toString())
            }
        }
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

    private fun getEntity(): List<FirstNodeJ>? {
        val dataList = ArrayList<FirstNodeJ>()
        // 循环添加数据
        for (i in 1..10) {
            val firstNode = FirstNodeJ("标题${i}")

            val secondList = ArrayList<FirstNodeJ.SecondeNodeJ>()
            for (j in 1..5) {
                val secondNodeList: MutableList<BaseNode> = ArrayList()
                val secondEntity = FirstNodeJ.SecondeNodeJ(secondNodeList, "标题")
                secondEntity.id = "${i}_${j}"
                secondList.add(secondEntity)
            }
            firstNode.mSubItems = secondList
            dataList.add(firstNode)
        }
        return dataList
    }
}
