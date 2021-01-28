package org.jobsl.cgames.net.msg;

import org.jobsl.cgames.net.utils.Base64Utils;
import org.jobsl.cgames.net.utils.MD5Utils;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class MessageSign {
    public static String sign(Message message) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return MD5Utils.getEncryptedPwdNoSalt(Base64Utils.encode(message.toString().getBytes()));
    }
}
