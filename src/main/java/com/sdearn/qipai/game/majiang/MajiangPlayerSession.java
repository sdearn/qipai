/**
 * Copyright 2015-2016 Xinrui & Co., Ltd.
 */
package com.sdearn.qipai.game.majiang;

import com.sdearn.qipai.basic.gameservice.GameSession;

/**
 * @author wangg
 * @version 1.0 , 2017年12月27日
 */
public class MajiangPlayerSession extends GameSession {

	private int[] cards = new int[34];

	private int[] showCards;

	private int[] pengCards = new int[34];

	private int[] mGangCards = new int[34];

	private int[] aGangCards = new int[34];

	private int[] dingCards = new int[34];

	public int[] getCards() {
		return cards;
	}

	public void setCards(int[] cards) {
		this.cards = cards;
	}

	public int[] getShowCards() {
		return showCards;
	}

	public void setShowCards(int[] showCards) {
		this.showCards = showCards;
	}

	public int[] getPengCards() {
		return pengCards;
	}

	public void setPengCards(int[] pengCards) {
		this.pengCards = pengCards;
	}

	public int[] getmGangCards() {
		return mGangCards;
	}

	public void setmGangCards(int[] mGangCards) {
		this.mGangCards = mGangCards;
	}

	public int[] getaGangCards() {
		return aGangCards;
	}

	public void setaGangCards(int[] aGangCards) {
		this.aGangCards = aGangCards;
	}

	public int[] getDingCards() {
		return dingCards;
	}

	public void setDingCards(int[] dingCards) {
		this.dingCards = dingCards;
	}
}
