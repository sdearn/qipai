/**
 * Copyright 2015-2016 Xinrui & Co., Ltd.
 */
package com.sdearn.qipai.basic.gameservice;

import java.util.HashMap;

/**
 * @author wangg
 * @version 1.0 , 2017年12月26日
 */
public class GameRoom {

	private String roomId;

	private String gameType;

	private Byte status;  //1：等待游戏开始， 2：游戏进行中， 3：游戏结束， 4：房间解散

	private Byte readyNum = 0;

	private HashMap<String, GameSession> gameSessions = new HashMap<String, GameSession> ();

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Byte getReadyNum() {
		return readyNum;
	}

	public void setReadyNum(Byte readyNum) {
		this.readyNum = readyNum;
	}

	public HashMap<String, GameSession> getGameSessions() {
		return gameSessions;
	}

	public void setGameSessions(HashMap<String, GameSession> gameSessions) {
		this.gameSessions = gameSessions;
	}
}
