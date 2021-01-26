package org.jobsl.cgames.net.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class MD5Utils {
    private static final String HEX_NUMS_STR = "0123456789ABCDEF";
    private static final Integer SALT_LENGTH = 6;

    /**
     * 获得加密后的16进制形式口令
     *
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String getEncryptedPwd(String password, String salt)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 声明加密后的口令数组变量
        byte[] pwd = null;
        // 声明消息摘要对象
        MessageDigest md = null;
        // 创建消息摘要
        md = MessageDigest.getInstance("MD5");
        // 将盐数据传入消息摘要对象
        md.update(hexStringToByte(salt));
        // 将口令的数据传给消息摘要对象
        md.update(password.getBytes("UTF-8"));
        // 获得消息摘要的字节数组
        byte[] digest = md.digest();
        return byteToHexString(digest);
    }

    /**
     * 获得加密后的16进制形式口令，不加Salt
     *
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String getEncryptedPwdNoSalt(String password)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        // 声明加密后的口令数组变量
        byte[] pwd = null;
        // 声明消息摘要对象
        MessageDigest md = null;
        // 创建消息摘要
        md = MessageDigest.getInstance("MD5");
        // 将口令的数据传给消息摘要对象
        md.update(password.getBytes("UTF-8"));
        // 获得消息摘要的字节数组
        byte[] digest = md.digest();
        return byteToHexString(digest);
    }

    /**
     * 生成一个随机Salt
     */
    public static String getSalt() {
        // 随机数生成器
        SecureRandom random = new SecureRandom();
        // 声明盐数组变量
        byte[] salt = new byte[SALT_LENGTH];
        // 将随机数放入盐变量中
        random.nextBytes(salt);
        return byteToHexString(salt);
    }

    /**
     * 将16进制字符串转换成字节数组
     *
     * @param hex
     * @return
     */
    private static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2);
        byte[] result = new byte[len];
        char[] hexChars = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (HEX_NUMS_STR.indexOf(hexChars[pos]) << 4 | HEX_NUMS_STR.indexOf(hexChars[pos + 1]));
        }
        return result;
    }

    /**
     * 将指定byte数组转换成16进制字符串
     *
     * @param b
     * @return
     */
    private static String byteToHexString(byte[] b) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            hexString.append(hex.toUpperCase());
        }
        return hexString.toString();
    }
}
