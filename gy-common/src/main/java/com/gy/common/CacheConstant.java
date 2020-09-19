package com.gy.common;

/**
 * redis key
 */
public class CacheConstant {

	// 登录扫码公众号accesstoken
	public static final String GY_TEST_USER = "gy:test:user:";
	public static final int GY_TEST_USER_EXPIRE = 5400;

	public static String getGyTestUserKey(Integer userId) {
		return GY_TEST_USER + userId;
	}



}
