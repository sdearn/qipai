/**
 * Copyright 2015-2016 Xinrui & Co., Ltd.
 */
package com.sdearn.qipai.game.majiang.responsemsg;

import com.sdearn.qipai.game.majiang.ResponseAction;
import com.sdearn.qipai.game.majiang.domain.PlayerInfo;

/**
 * @author wangg
 * @version 1.0 , 2017年12月28日
 */
public class PlayerEnterMessage extends ResponseMessage {

	private PlayerInfo[] players;

	public PlayerEnterMessage() {
		this.setAction(ResponseAction.PLAYERENTER);
	}

	public PlayerInfo[] getPlayers() {
		return players;
	}

	public void setPlayers(PlayerInfo[] players) {
		this.players = players;
	}
}
