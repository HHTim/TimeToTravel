package com.tibame.timetotravel.common;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotifyBean<T> {

    private int status;
    private List<T> MessageList;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<T> getMessageList() {
        return MessageList;
    }

    public void setMessageList(List<T> messageList) {
        MessageList = messageList;
    }
}
