/**
 * Copyright 2015-2016 Xinrui & Co., Ltd.
 */
package com.sdearn.qipai.game.majiang.responsemsg;

import com.sdearn.qipai.game.majiang.ResponseAction;

/**
 * @author wangg
 * @version 1.0 , 2017年12月28日
 */
public class GameStartMessage extends ResponseMessage {

	private int[] cards;

	public GameStartMessage() {
		this.setAction(ResponseAction.GAMESTART);
	}

	public int[] getCards() {
		return cards;
	}

	public void setCards(int[] cards) {
		this.cards = cards;
	}
}
