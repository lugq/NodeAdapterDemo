package com.lugq.eventbusdemo

import org.greenrobot.eventbus.EventBus

/**
 * 参考：https://juejin.im/post/6844903556391108615#heading-7
 */
object EventBusUtils {

    // 包含了EventBus,如果不需要，则去掉以下3个函数
    private fun isEventBusRegistered(subscribe: Any?): Boolean {
        return EventBus.getDefault().isRegistered(subscribe)
    }

    /**
     * 注册 eventBus
     * @param subscriber 订阅者
     */
    fun registerEventBus(subscriber: Any?) {
        if (!isEventBusRegistered(subscriber)) {
            EventBus.getDefault().register(subscriber)
        }
    }

    /**
     * 注销 eventBus
     * @param subscriber 订阅者
     */
    fun unregisterEventBus(subscriber: Any?) {
        if (isEventBusRegistered(subscriber)) {
            EventBus.getDefault().unregister(subscriber)
        }
    }

    /**
     * 发布订阅事件
     *
     * 使用场景：给已经存在的窗体传递信息，而且订阅者必须要注册且不能被注销了，否则接收不到消息
     */
    fun post(event: Any?) {
        EventBus.getDefault().post(event)
    }

    /**
     * 发送粘性订阅事件
     *
     * 使用场景：给还未创建的窗体传递信息需要用粘性事件
     */
    fun postSticky(event: Any?) {
        EventBus.getDefault().postSticky(event)
    }
}