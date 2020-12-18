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

    private boolean yincang = false;

    public boolean isYincang() {
        return yincang;
    }

    public void setYincang(boolean yincang) {
        this.yincang = yincang;
    }

    public FirstNodeJ(String title) {
        this.title = title;

        // 设置默认折叠状态
        setExpanded(false);
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

    public FooterNode mFooterNode = new FooterNode();
    @Nullable
    @Override
    public BaseNode getFooterNode() {
        //return new FooterNode();
        return mFooterNode;
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
        public String id;

        /*************↓↓↓↓↓↓↓↓↓↓***************/
        public String parentTitle;
        public int parentPosition;
        /*************↑↑↑↑↑↑↑↑↑↑***************/

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
                    ", id='" + id + '\'' +
                    ", parentTitle='" + parentTitle + '\'' +
                    ", parentPosition=" + parentPosition +
                    '}';
        }
    }

    public static class FooterNode extends BaseNode {
        public String parentTitle;
        @Nullable
        @Override
        public List<BaseNode> getChildNode() {
            return null;
        }

        @Override
        public String toString() {
            return "FooterNode{" +
                    "parentTitle='" + parentTitle + '\'' +
                    '}';
        }
    }
}

