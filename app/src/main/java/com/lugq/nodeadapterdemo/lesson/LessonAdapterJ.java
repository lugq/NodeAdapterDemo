package com.lugq.nodeadapterdemo.lesson;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.lugq.nodeadapterdemo.adapter.RootFooterNodeProvider;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @Description
 * @Author Lu Guoqiang
 * @Time 12/18/20 7:12 PM
 */
public class LessonAdapterJ extends BaseNodeAdapter {

    LessonAdapterJ() {
        addFullSpanNodeProvider(new LessonProvider1());
        addFullSpanNodeProvider(new LessonProvider2());
        addFullSpanNodeProvider(new RootFooterNodeProvider());
    }

    @Override
    protected int getItemType(@NotNull List<? extends BaseNode> data, int position) {
        Object entity = data.get(position);
        if (entity instanceof LessonFirstNode) {
            return 1;
        } else if (entity instanceof LessonFirstNode.SecondeNodeJ) {
            return 2;
        } else {
            return 3;
        }
    }
}
