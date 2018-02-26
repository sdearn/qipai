/**
 * Copyright 2015-2016 Xinrui & Co., Ltd.
 */
package com.sdearn.qipai.basic.gameservice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.EncodeException;
import javax.websocket.Session;

import com.sdearn.qipai.game.majiang.responsemsg.ResponseMessage;

/**
 * @author wangg
 * @version 1.0 , 2018年1月2日
 */
public class GameUtils {

	/**
	 * 给某一个玩家推送消息
	 * @param session
	 * @param message
	 * @throws EncodeException
	 * @throws IOException
	 */
	public static void sendMessage(GameSession session, ResponseMessage message) throws IOException, EncodeException {

		Session socketSession = SocketManager.getSocketSession(session.getSession().getId());

		if(session != null) {
			socketSession.getBasicRemote().sendObject(message);
		}
	}

	/**
	 * 给所有玩家推送消息
	 * @param gameSessions
	 * @param message
	 * @throws EncodeException
	 * @throws IOException
	 */
	public static void sendMessage(HashMap<String, GameSession> gameSessions, ResponseMessage message) throws IOException, EncodeException {

		Session session = null;
		for (Map.Entry<String, GameSession> gameSession : gameSessions.entrySet()) {

			session = SocketManager.getSocketSession(gameSession.getValue().getSession().getId());
			if(session != null) {
				session.getBasicRemote().sendObject(message);
			}

		}
	}

	/**
	 * 给除了某一玩家之外的其余玩家推送消息
	 * @param gameSessions
	 * @param message
	 * @param userId
	 * @throws EncodeException
	 * @throws IOException
	 */
	public static void sendMessage(HashMap<String, GameSession> gameSessions, ResponseMessage message, String userId) throws IOException, EncodeException {

		Session session = null;
		for (Map.Entry<String, GameSession> gameSession : gameSessions.entrySet()) {

			if(gameSession.getValue().getUserId().equals(userId)) {
				continue;
			}

			session = SocketManager.getSocketSession(gameSession.getValue().getSession().getId());
			if(session != null) {
				session.getBasicRemote().sendObject(message);
			}
		}
	}
}
