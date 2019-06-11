package com.mycompany.todoapp.util;

import org.apache.commons.codec.digest.DigestUtils;

public class HashUtil {
    public String md5(String str) {
        String md5Str = DigestUtils.md5Hex(str);
        return md5Str;
    }
}