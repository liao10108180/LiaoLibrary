package com.liao.common.event;

/**
 * @ProjectName: LiaoLibraryDemo
 * @Package: com.liao.common.event
 * @ClassName: MessageEvent
 * @Description: EventBus消息传递载体
 * @Author: CHN_Liao
 * @CreateDate: 2021/10/26 9:32
 */
public class MessageEvent {
    private String key;
    private Object object;
    private Object object2;
    private Object object3;

    public MessageEvent(String key) {
        this.key = key;
    }

    public MessageEvent(String key, Object object) {
        this.key = key;
        this.object = object;
    }

    public MessageEvent(String key, Object object, Object object2) {
        this.key = key;
        this.object = object;
        this.object2 = object2;
    }

    public MessageEvent(String key, Object object, Object object2, Object object3) {
        this.key = key;
        this.object = object;
        this.object2 = object2;
        this.object3 = object3;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Object getObject2() {
        return object2;
    }

    public void setObject2(Object object2) {
        this.object2 = object2;
    }

    public Object getObject3() {
        return object3;
    }

    public void setObject3(Object object3) {
        this.object3 = object3;
    }
}
