package com.example.mock.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    private long timestamp;

    public long getTimestamp() {
        return timestamp;
    }

    @JsonProperty("msg_id")
    private Integer msg_id;

    public Integer getMsg_Id() {
        return msg_id;
    }

    public void setMsgId(Integer msgId) {
    }
}
