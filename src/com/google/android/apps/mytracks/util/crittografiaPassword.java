/*
 * Copyright 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.android.apps.mytracks.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Utitlites for sending pageviews to Google Analytics.
 * 
 * @author Jimmy Shih
 */
public class crittografiaPassword {

  public static SecretKey generateKey(String password) {
    
    if(password.length()<16)
    {
      int passLenght=password.length();
      for(int i=0;i<(16-passLenght);i++)
      {
        password+="x";
      }
    }
 //   SecretKey sk =new SecretKeySpec
    try {
      byte[] key=password.getBytes("UTF-8");
      int a=key.length;
      
      return new SecretKeySpec(key, "AES");
    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return null;
}

public static byte[] encryptMsg(String message, SecretKey secret) {
/* Encrypt the message. */
    Cipher cipher = null;
    try {
      cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
    } catch (NoSuchAlgorithmException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    try {
      cipher.init(Cipher.ENCRYPT_MODE, secret);
    } catch (InvalidKeyException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    byte[] cipherText=null;
    try {
      cipherText = cipher.doFinal(message.getBytes("UTF-8"));
    } catch (IllegalBlockSizeException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (BadPaddingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return cipherText;
}

public static String decryptMsg(byte[] cipherText, SecretKey secret) {

    /* Decrypt the message, given derived encContentValues and initialization vector. */
    Cipher cipher = null;
    try {
      cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
    } catch (NoSuchAlgorithmException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
   try {
    cipher.init(Cipher.DECRYPT_MODE, secret);
  } catch (InvalidKeyException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
    String decryptString=null;
    try {
      decryptString = new String(cipher.doFinal(cipherText), "UTF-8");
    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IllegalBlockSizeException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (BadPaddingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return decryptString;
}
}