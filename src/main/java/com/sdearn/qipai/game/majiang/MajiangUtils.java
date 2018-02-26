/**
 * Copyright 2015-2016 Xinrui & Co., Ltd.
 */
package com.sdearn.qipai.game.majiang;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author wangg
 * @version 1.0 , 2017年12月27日
 */
public class MajiangUtils {

	private final static String[] majiangStr = {
			"1筒", "2筒", "3筒", "4筒", "5筒", "6筒", "7筒", "8筒", "9筒",
			"1条", "2条", "3条", "4条", "5条", "6条", "7条", "8条", "9条",
			"1万", "2万", "3万", "4万", "5万", "6万", "7万", "8万", "9万",
            "东", "南", "西", "北", "中", "发", "白" };

	private static String[] majiang;


	/**
	 * 洗牌
	 * @param majiangNum
	 * @return
	 */
	public static int[] shuffle(int majiangNum) {

		//生成一副牌
    	ArrayList<Integer> arrayList = new ArrayList<Integer> ();
    	for(int i = 0; i < majiangNum; i++) {
    		for(int j = 0; j < 4; j++) {
    			arrayList.add(i);
    		}
    	}

    	//随机打乱牌
    	Collections.shuffle(arrayList);
    	Integer[] array = arrayList.toArray(new Integer[0]);

    	int[] cards = new int[majiangNum*4];
    	for(int i = 0; i < array.length; i++) {
    		cards[i] = array[i];
    	}

    	return cards;
    }
	
	/**
	 * 是否可以碰牌
	 * @param cards
	 * @return
	 */
	public static boolean isPeng(int[] cards, int card) {
		if(cards[card] >= 2) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 是否可以杠牌
	 * @param cards
	 * @param card
	 * @return
	 */
	public static boolean isGang(int[] cards, int card) {
		if(cards[card] >= 3) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 是否可以胡牌
	 * @param cards
	 * @param card
	 * @return
	 */
	public static boolean isHu(int[] cards, int card) {
		if(cards[card] > 0) {
			return true;
		}else {
			return false;
		}
	}

	public static void print_cards(int[] cards) {
		for (int i = 0; i < 9; ++i) {
			System.out.print(cards[i]);
			System.out.print(",");
		}
		System.out.println("");

		for (int i = 9; i < 18; ++i) {
			System.out.print(cards[i]);
			System.out.print(",");
		}
		System.out.println("");
		for (int i = 18; i < 27; ++i) {
			System.out.print(cards[i]);
			System.out.print(",");
		}
		System.out.println("");
		for (int i = 27; i < 34; ++i) {
			System.out.print(cards[i]);
			System.out.print(",");
		}
		System.out.println("\n=========================================");
	}

}
 