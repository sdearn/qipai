/**
 * Copyright 2015-2016 Xinrui & Co., Ltd.
 */
package com.sdearn.qipai.game.majiang.responsemsg;

import com.sdearn.qipai.game.majiang.ResponseAction;

/**
 * @author wangg
 * @version 1.0 , 2017年12月28日
 */
public class PlayerPengMessage extends ResponseMessage {

	private int card;

	public PlayerPengMessage() {
		this.setAction(ResponseAction.PLAYERPENG);
	}

	public int getCard() {
		return card;
	}

	public void setCard(int card) {
		this.card = card;
	}
}
