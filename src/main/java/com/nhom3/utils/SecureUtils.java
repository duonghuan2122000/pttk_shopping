/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nhom3.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author duong
 */
public class SecureUtils {

    public static String md5(String plainText) {
        String encrypt = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(plainText.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                sb.append(Integer.toString((messageDigest[i] & 0xff) + 0x100, 16).substring(1));
            }
            encrypt = sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return encrypt;
    }
}
