package org.jobsl.cgames.net.msg;

import java.io.Serializable;
import java.util.UUID;

public class Message implements Serializable {
    private static final long serialVersionUID = -1025179367182508480L;

    private String msgId = UUID.randomUUID().toString();

    /**
     * 000000 成功
     * 111111 失败
     * 010101 异常
     */
    private String code;

    private Long time;

    private String sign;

    // 自定义json
    private String msg;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Message{" +
                "msgId='" + msgId + '\'' +
                ", code='" + code + '\'' +
                ", time=" + time +
                ", sign='" + sign + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
