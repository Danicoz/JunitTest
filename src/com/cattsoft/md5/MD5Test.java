package com.cattsoft.md5;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cattsoft.utility.Md5;



public class MD5Test {

	@Test
	public void test() {
		String str = "1234567890";
		String md5Str = Md5.getMD5(str);
		System.out.println("cattsoft.MD5:" + md5Str);
		String md5Util = MD5Util.getMD5String(str);
		System.out.println(md5Util);
	}

}
