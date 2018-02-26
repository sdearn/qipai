/**
 * Copyright 2015-2016 Xinrui & Co., Ltd.
 */
package com.sdearn.qipai.game.majiang.responsemsg;

import com.sdearn.qipai.game.majiang.ResponseAction;

/**
 * @author wangg
 * @version 1.0 , 2017年12月28日
 */
public class PlayerWaitMessage extends ResponseMessage {

	private String userId;

	public PlayerWaitMessage() {
		this.setAction(ResponseAction.PLAYERWAIT);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
