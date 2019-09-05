package com.cattsoft.md5;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

	static String byteHEX(byte ib)
	  {
	    char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	    char[] ob = new char[2];
	    ob[0] = Digit[(ib >>> 4 & 0xF)];
	    ob[1] = Digit[(ib & 0xF)];
	    String s = new String(ob);
	    return s;
	  }


	public static String getMD5String(String original) {
	 byte[] originalByte = original.getBytes();

	    byte[] toDigest = null;

	    String digestHexStr = "";
	    try
	    {
	      MessageDigest md = MessageDigest.getInstance("MD5");

	      md.update(originalByte);
	      toDigest = md.digest();

	      for (int i = 0; i < 16; i++) {
	        digestHexStr = digestHexStr + byteHEX(toDigest[i]);
	      }

	      return digestHexStr;
	    }
	    catch (NoSuchAlgorithmException nsae)
	    {
	      nsae.toString();
	    }
	    return digestHexStr;
	  }
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(System.getProperty("file.encoding")); 
	}
}