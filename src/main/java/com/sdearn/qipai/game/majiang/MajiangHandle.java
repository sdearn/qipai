/**
 * Copyright 2015-2016 Xinrui & Co., Ltd.
 */
package com.sdearn.qipai.game.majiang;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.EncodeException;

import com.sdearn.qipai.basic.gameservice.GameHandle;
import com.sdearn.qipai.basic.gameservice.GameSession;
import com.sdearn.qipai.basic.gameservice.GameUtils;
import com.sdearn.qipai.basic.gameservice.SocketManager;
import com.sdearn.qipai.game.majiang.domain.PlayerInfo;
import com.sdearn.qipai.game.majiang.responsemsg.GameStartMessage;
import com.sdearn.qipai.game.majiang.responsemsg.OwnActionMessage;
import com.sdearn.qipai.game.majiang.responsemsg.PlayerEnterMessage;
import com.sdearn.qipai.game.majiang.responsemsg.PlayerPengMessage;
import com.sdearn.qipai.game.majiang.responsemsg.PlayerReadyMessage;
import com.sdearn.qipai.game.majiang.responsemsg.PlayerWaitMessage;

import net.sf.json.JSONObject;

/**
 * @author wangg
 * @version 1.0 , 2017年12月26日
 */
public class MajiangHandle extends GameHandle {

	/**
	 * 玩家进入房间
	 */
	@Override
	public void enter(GameSession session) {

		this.session = session;

		MajiangRoom room = (MajiangRoom) SocketManager.getGameRoom(this.session.getGameType(), this.session.getRoomId());

		HashMap<String, GameSession> gameSessions = room.getGameSessions();

		PlayerEnterMessage msg = new PlayerEnterMessage();
		PlayerInfo[] players = new PlayerInfo[gameSessions.size()];
		int i = 0;
		for (Map.Entry<String, GameSession> gameSession : gameSessions.entrySet()) {
			GameSession value = gameSession.getValue();
			PlayerInfo player = new PlayerInfo();

			player.setUserId(value.getUserId());
			player.setUsername(value.getUsername());
			player.setHeadimg(value.getHeadimg());
			player.setSex(value.getSex());
			player.setPosition(value.getPosition());
			player.setStatus(value.getStatus());

			players[i] = player;
			i++;
		}

		msg.setPlayers(players);

		System.out.println("推送消息");
		try {
			GameUtils.sendMessage(gameSessions, msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void excute(String message) {
		// TODO Auto-generated method stub

		RequestMessage messageBean = getMessageBean(message);
		Class<? extends MajiangHandle> clazz = this.getClass();
		try {
			Method method = clazz.getDeclaredMethod(messageBean.getAction(), RequestMessage.class);
			try {
				method.invoke(this, messageBean);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 玩家游戏中断
	 */
	@Override
	public void interrupt(GameSession session) {

	}

	RequestMessage getMessageBean(String message) {
		JSONObject object = JSONObject.fromObject(message);
		RequestMessage bean = (RequestMessage) JSONObject.toBean(object, RequestMessage.class);
		return bean;
	}


	/**
	 * 玩家坐下，准备开始游戏
	 * @param message
	 */
	public void ready(RequestMessage message) {

		//改变玩家状态
		this.session.setStatus((byte) 2);

		MajiangRoom room = (MajiangRoom) SocketManager.getGameRoom(this.session.getGameType(), this.session.getRoomId());
		room.setReadyNum((byte) (room.getReadyNum() + 1));

		message.setPosition((byte) (room.getReadyNum() -1));
		if(message.getPosition() < 4) {
			if(room.getGamePlayer()[message.getPosition()] != null) {

				//房间该位置以有人
			}

			//玩家坐下该位置
			this.session.setPosition(message.getPosition());
			room.getGamePlayer()[message.getPosition()] = this.session;
		}

		PlayerReadyMessage msg = new PlayerReadyMessage();
		msg.setPosition(message.getPosition());

		HashMap<String, GameSession> gameSessions = room.getGameSessions();

		try {
			GameUtils.sendMessage(gameSessions, msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//当人数满四人，游戏开始
		if(room.getReadyNum() == 4) {

			this.shuffle();
		}
	}

	/**
	 * 给玩家发牌
	 */
	private void shuffle() {
		MajiangRoom room = (MajiangRoom) SocketManager.getGameRoom(this.session.getGameType(), this.session.getRoomId());

		room.setCards(MajiangUtils.shuffle(27));

		//开始发牌
		for(int i = 0; i < 4; i++) {
			MajiangPlayerSession gameSession = (MajiangPlayerSession) room.getGamePlayer()[i];
			int[] handCards = this.getHandCards(room);
			gameSession.setCards(handCards);
			room.getGamePlayer()[i] = gameSession;

			int[] serializeCards = handCardsSerialize(handCards);
			GameStartMessage msg = new GameStartMessage();
			msg.setCards(serializeCards);
			try {
				gameSession.getSession().getBasicRemote().sendObject(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (EncodeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 取消准备
	 * @param message
	 */
	public void stand(RequestMessage message) {

		this.session.setStatus((byte) 1);

		MajiangRoom room = (MajiangRoom) SocketManager.getGameRoom(this.session.getGameType(), this.session.getRoomId());
		room.setReadyNum((byte) (room.getReadyNum() - 1));
		room.getGamePlayer()[this.session.getPosition()] = null;
		this.session.setPosition(null);

	}

	/**
	 * 离开游戏
	 * @param message
	 */
	public void leave(RequestMessage message) {

		MajiangRoom room = (MajiangRoom) SocketManager.getGameRoom(this.session.getGameType(), this.session.getRoomId());
		if(this.session.getStatus() - 1 == 0) {
			if(room.getGameSessions().containsKey(this.session.getSession().getId())) {
				room.getGameSessions().remove(this.session.getSession().getId());
			}
		}else {
			this.session.setStatus((byte) 5);
		}
	}

	/**
	 * 出牌
	 * @param message
	 */
	public void playCard(RequestMessage message) {

		int card = message.getCard();
		MajiangPlayerSession gameSession = (MajiangPlayerSession) this.session;
		if(card > 0 && gameSession.getCards()[card] > 0) {
			gameSession.getCards()[card] --;
		}

		MajiangRoom room = (MajiangRoom) SocketManager.getGameRoom(this.session.getGameType(), this.session.getRoomId());
		for(int i = 0; i < 4; i++) {
			if(i - gameSession.getPosition() == 0) {
				continue;
			}

			MajiangPlayerSession player =(MajiangPlayerSession) room.getGamePlayer()[i];
			OwnActionMessage msg = new OwnActionMessage();
			boolean sendMsg = false;

			if(MajiangUtils.isGang(player.getCards(), card)) {
				sendMsg = true;
				msg.setIsGang(true);
				msg.setIsPeng(true);
			}else if(MajiangUtils.isPeng(player.getCards(), card)) {
				sendMsg = true;
				msg.setIsPeng(true);
			}

			if(MajiangUtils.isHu(player.getCards(), card)) {
				sendMsg = true;
				msg.setIsHu(true);
			}

			if(sendMsg) {
				room.getWaitPlayers().add(player.getUserId());

				try {
					GameUtils.sendMessage(player, msg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (EncodeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		if(room.getWaitPlayers().size() > 0) {
			PlayerWaitMessage waitMsg = new PlayerWaitMessage();
			waitMsg.setUserId(room.getWaitPlayers().get(0));

			for(int i = 0; i < 4; i++) {
				GameSession player = room.getGamePlayer()[i];
				for(int j = 0; j < room.getWaitPlayers().size(); j++) {
					if(!player.getUserId().equals(room.getWaitPlayers().get(j))) {
						try {
							GameUtils.sendMessage(player, waitMsg);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (EncodeException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	/**
	 * 碰牌
	 * @param message
	 */
	public void peng(RequestMessage message) {

		int card = message.getCard();
		MajiangPlayerSession gameSession = (MajiangPlayerSession) this.session;
		MajiangRoom room = (MajiangRoom) SocketManager.getGameRoom(this.session.getGameType(), this.session.getRoomId());
		if(MajiangUtils.isPeng(gameSession.getCards(), card)) {
			int[] cards = gameSession.getCards();
			cards[card] = cards[card] -2;
			gameSession.setCards(cards);

			PlayerPengMessage pengMsg = new PlayerPengMessage();
			pengMsg.setCard(card);

			try {
				GameUtils.sendMessage(room.getGameSessions(), pengMsg, gameSession.getUserId());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (EncodeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 杠牌
	 * @param message
	 */
	public void gang(RequestMessage message) {

		int card = message.getCard();
		MajiangPlayerSession gameSession = (MajiangPlayerSession) this.session;
		if(MajiangUtils.isGang(gameSession.getCards(), card)) {
			int[] cards = gameSession.getCards();
			cards[card] = cards[card] -3;
			gameSession.setCards(cards);
		}
	}

	/**
	 * 胡牌
	 * @param message
	 */
	public void hu(RequestMessage message) {

	}

	/**
	 * 过牌
	 * @param message
	 */
	public void guo(RequestMessage message) {

	}

	/**
	 * 获取手牌
	 * @param room
	 * @return
	 */
	private int[] getHandCards(MajiangRoom room) {
		int[] cards = room.getCards();
		int[] handCards = new int[34];
		for(int i = 0; i < 13; i++) {
			if(handCards[cards[i]] > 0) {
				handCards[cards[i]] ++;
			}else {
				handCards[cards[i]] = 1;
			}
		}

		cards = Arrays.copyOfRange(cards, 14, cards.length);
		room.setCards(cards);

		return handCards;
	}

	/**
	 * 摸牌
	 * @param room
	 * @return
	 */
	private int drawCard(MajiangRoom room) {
		int[] cards = room.getCards();
		int card = cards[0];

		cards = Arrays.copyOfRange(cards, 1, cards.length);
		room.setCards(cards);

		return card;
	}

	/**
	 * 开杠摸牌
	 * @param room
	 * @return
	 */
	private int gangDrawCard(MajiangRoom room) {
		int[] cards = room.getCards();
		int card = cards[cards.length - 1];

		cards = Arrays.copyOfRange(cards, 0, cards.length - 1);
		room.setCards(cards);

		return card;
	}

	/**
	 * 手牌序列化
	 * @param cards
	 * @return
	 */
	static int[] handCardsSerialize(int[] cards) {
		ArrayList<Integer> arrayList = new ArrayList<Integer> ();

		for(int i = 0; i < cards.length; i++) {
			for(int j = 0; j < cards[i]; j++) {
				arrayList.add(i);
			}
		}

		Integer[] array = arrayList.toArray(new Integer[0]);

		int[] handCards = new int[array.length];
    	for(int i = 0; i < array.length; i++) {
    		handCards[i] = array[i];
    	}

    	return handCards;
	}
}
