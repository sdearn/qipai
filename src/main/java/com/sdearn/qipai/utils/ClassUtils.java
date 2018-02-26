/**
 * Copyright 2015-2016 Xinrui & Co., Ltd.
 */
package com.sdearn.qipai.utils;

/**
 * @author wangg
 * @version 1.0 , 2017年12月26日
 */
public class ClassUtils {

	public static ClassLoader getClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}

	public static Object getInstance (String string) throws Exception {
		Class<?> forName = Class.forName(string, true, getClassLoader());
		return forName.newInstance();
	}
}
