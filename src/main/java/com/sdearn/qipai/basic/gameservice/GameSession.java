/**
 * Copyright 2015-2016 Xinrui & Co., Ltd.
 */
package com.sdearn.qipai.basic.gameservice;

import javax.websocket.Session;

import com.sdearn.qipai.basic.user.domain.User;

/**
 * @author wangg
 * @version 1.0 , 2017年12月21日
 */
public class GameSession {

	private User user;

	private String userId;

	private String username;

	private String headimg;

	private Byte sex;

	private Byte status; //1：进入， 2：准备好， 3，游戏中， 4：等待开始， 5：暂离

	private Byte position = null;

	private Integer socre = 0;

	private String gameType;

	private String roomId;

	private Session session;

	private GameHandle gameHandle;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public Byte getSex() {
		return sex;
	}

	public void setSex(Byte sex) {
		this.sex = sex;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Byte getPosition() {
		return position;
	}

	public void setPosition(Byte position) {
		this.position = position;
	}

	public Integer getSocre() {
		return socre;
	}

	public void setSocre(Integer socre) {
		this.socre = socre;
	}

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public GameHandle getGameHandle() {
		return gameHandle;
	}

	public void setGameHandle(GameHandle gameHandle) {
		this.gameHandle = gameHandle;
	}
}
