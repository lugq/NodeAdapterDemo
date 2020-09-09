package com.lugq.nodeadapterdemo.entity;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.entity.node.NodeFooterImp;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * 写法参考：https://github.com/CymChad/BaseRecyclerViewAdapterHelper/issues/3023
 * <p>
 * 遗留的？不知道怎么转换成Kotlin
 */
public class FirstNodeJ extends BaseExpandNode implements NodeFooterImp  {
    // 这两个字段是Json解析中对应的字段
    private String title;
    public List<SecondeNodeJ> mSubItems;

    // 这个字段是子节点需要返回给
    private List<BaseNode> childNode;

    public FirstNodeJ(String title) {
        this.title = title;

        setExpanded(true);
    }

    public String getTitle() {
        return title;
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        // 重点代码
        if (childNode == null) {
            childNode = new ArrayList(mSubItems);
        }
        return childNode;
    }

    @Nullable
    @Override
    public BaseNode getFooterNode() {
        return new FooterNode();
    }

    @Override
    public String toString() {
        return "FirstNode{" +
                "childNode=" + childNode +
                ", title='" + title + '\'' +
                '}';
    }

    public static class SecondeNodeJ extends BaseExpandNode {
        private List<BaseNode> childNode;
        private String title;

        public SecondeNodeJ(List<BaseNode> childNode, String title) {
            this.childNode = childNode;
            this.title = title;

            setExpanded(false);
        }

        public String getTitle() {
            return title;
        }

        @Nullable
        @Override
        public List<BaseNode> getChildNode() {
            return childNode;
        }

        @Override
        public String toString() {
            return "SecondeNodeJ{" +
                    "childNode=" + childNode +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    public static class FooterNode extends BaseNode {

        @Nullable
        @Override
        public List<BaseNode> getChildNode() {
            return null;
        }
    }
}

