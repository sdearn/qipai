/**
 * Copyright 2015-2016 Xinrui & Co., Ltd.
 */
package com.sdearn.qipai.basic.gameservice;

import java.lang.reflect.Field;

/**
 * @author wangg
 * @version 1.0 , 2017年12月26日
 */
public enum GameTypeEnum {

	MAJIANG, DOUDIZHU, PAOHUZI21, PAOHUZI15;

	public static boolean contains(String string) {
		Class<? extends GameTypeEnum> cls = GameTypeEnum.class;
		Field[] fields = cls.getFields();
		for (Field field : fields) {
			if(field.getName().equals(string.toUpperCase())) {
				return true;
			}
		}

		return false;
	}

	public static void main(String[] args) {
		System.out.println(GameTypeEnum.contains("MONDAY"));
	}

}
