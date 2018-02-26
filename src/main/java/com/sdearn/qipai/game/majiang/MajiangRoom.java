/**
 * Copyright 2015-2016 Xinrui & Co., Ltd.
 */
package com.sdearn.qipai.game.majiang;

import java.util.ArrayList;
import java.util.List;

import com.sdearn.qipai.basic.gameservice.GameRoom;
import com.sdearn.qipai.basic.gameservice.GameSession;

/**
 * @author wangg
 * @version 1.0 , 2017年12月27日
 */
public class MajiangRoom extends GameRoom {

	//玩家席位
	private GameSession[] gamePlayer = new GameSession[4];

	//公牌
	private int[] cards;

	//当前操作玩家
	private int currPlayer;

	//当前牌
	private int currCard;

	//庄家
	private int dealer;

	//需要等待的玩家
	private List<String> waitPlayers = new ArrayList<String> ();

	public GameSession[] getGamePlayer() {
		return gamePlayer;
	}

	public void setGamePlayer(GameSession[] gamePlayer) {
		this.gamePlayer = gamePlayer;
	}

	public int[] getCards() {
		return cards;
	}

	public void setCards(int[] cards) {
		this.cards = cards;
	}

	public int getCurrPlayer() {
		return currPlayer;
	}

	public void setCurrPlayer(int currPlayer) {
		this.currPlayer = currPlayer;
	}

	public int getCurrCard() {
		return currCard;
	}

	public void setCurrCard(int currCard) {
		this.currCard = currCard;
	}

	public int getDealer() {
		return dealer;
	}

	public void setDealer(int dealer) {
		this.dealer = dealer;
	}

	public List<String> getWaitPlayers() {
		return waitPlayers;
	}

	public void setWaitPlayers(List<String> waitPlayers) {
		this.waitPlayers = waitPlayers;
	}
}
