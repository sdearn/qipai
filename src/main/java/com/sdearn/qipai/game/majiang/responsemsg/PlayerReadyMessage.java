/**
 * Copyright 2015-2016 Xinrui & Co., Ltd.
 */
package com.sdearn.qipai.game.majiang.responsemsg;

import com.sdearn.qipai.game.majiang.ResponseAction;

/**
 * @author wangg
 * @version 1.0 , 2017年12月28日
 */
public class PlayerReadyMessage extends ResponseMessage {

	private String userId;

	private Byte position;

	public PlayerReadyMessage() {
		this.setAction(ResponseAction.PLAYERREADY);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Byte getPosition() {
		return position;
	}

	public void setPosition(Byte position) {
		this.position = position;
	}
}
