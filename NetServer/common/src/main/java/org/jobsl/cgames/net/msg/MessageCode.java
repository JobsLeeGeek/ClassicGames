package org.jobsl.cgames.net.msg;

public enum MessageCode {
    SUCCESS("000000"), FAIL("111111"), EXCEPTION("010101");

    String code;
    MessageCode(String code) {
        this.code = code;
    }
}
