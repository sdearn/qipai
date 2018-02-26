/**
 * Copyright 2015-2016 Xinrui & Co., Ltd.
 */
package com.sdearn.qipai.basic.gameservice;

import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.sdearn.qipai.utils.ClassUtils;


/**
 * @author wangg
 * @version 1.0 , 2017年12月21日
 */
@ServerEndpoint(value = "/websocket/{game}/{roomId}", encoders = {com.sdearn.qipai.basic.socket.SocketEncoder.class})
public class SocketManager {

	//public static HashMap<String, GameRoom> rooms = new HashMap<String, GameRoom> ();

	//concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
	public static CopyOnWriteArraySet<SocketManager> socketSet = new CopyOnWriteArraySet<SocketManager>();

	public static CopyOnWriteArraySet<GameRoom> rooms = new CopyOnWriteArraySet<GameRoom>();

	private GameSession session;

	@OnOpen
	public void onOpen(@PathParam("game") String game, @PathParam("roomId") String roomId, Session session) {

		GameRoom gameRoom = getGameRoom(game, roomId);
		if(gameRoom == null) {
			gameRoom = getRoomClass(game);
			gameRoom.setGameType(game);
			gameRoom.setRoomId(roomId);
			gameRoom.setStatus((byte) 1);

			rooms.add(gameRoom);
		}

		if(!gameRoom.getGameSessions().containsKey(session.getId())) {
			GameHandle handle = getGameClass(game);
			this.session = getSessionClass(game);
			this.session.setGameType(game);
			this.session.setRoomId(roomId);
			this.session.setSession(session);
			this.session.setGameHandle(handle);
			this.session.setStatus((byte) 1);
			this.session.setUserId(session.getId());

			gameRoom.getGameSessions().put(session.getId(), this.session);
		}

		socketSet.add(this);

		this.session.getGameHandle().enter(this.session);
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {

		this.session.getGameHandle().interrupt(this.session);
	    socketSet.remove(this);  //从set中删除
//	    subOnlineCount();           //在线数减1
//	    System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
	}

	/**
	 * 收到客户端消息后调用的方法
	 * @param message 客户端发送过来的消息
	 * @param session 可选的参数
	 */
	@OnMessage
	public void onMessage(String message, Session session) {

		this.session.getGameHandle().excute(message);

	}

	/**
	 * 发生错误时调用
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
	    System.out.println("发生错误");
	    error.printStackTrace();
	}

	public static Session getSocketSession(String sessionId) {

		for(SocketManager socket : socketSet) {
			if(sessionId.equals(socket.session.getSession().getId())) {
				return socket.session.getSession();
			}
		}

		return null;
	}


	public static GameRoom getGameRoom(String game, String roomId) {
//		String key = game + roomId;
//
//		if(rooms.containsKey(key)) {
//			return rooms.get(key);
//		}else {
//			return null;
//		}

		for(GameRoom room : rooms) {
			if(game.equals(room.getGameType()) && roomId.equals(room.getRoomId())) {
				return room;
			}
		}

		return null;
	}

	static GameRoom getRoomClass(String string) {

		if(GameTypeEnum.contains(string)) {
			String name = string.substring(0, 1).toUpperCase() + string.substring(1);
			String classpath = "com.sdearn.qipai.game." + string.toLowerCase() + "." + name + "Room";
			try {
				GameRoom room = (GameRoom) ClassUtils.getInstance(classpath);
				return room;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	static GameSession getSessionClass(String string) {

		if(GameTypeEnum.contains(string)) {
			String name = string.substring(0, 1).toUpperCase() + string.substring(1);
			String classpath = "com.sdearn.qipai.game." + string.toLowerCase() + "." + name + "PlayerSession";
			try {
				GameSession session = (GameSession) ClassUtils.getInstance(classpath);
				return session;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	static GameHandle getGameClass(String string) {

		if(GameTypeEnum.contains(string)) {
			String name = string.substring(0, 1).toUpperCase() + string.substring(1);
			String classpath = "com.sdearn.qipai.game." + string.toLowerCase() + "." + name + "Handle";
			try {
				GameHandle game = (GameHandle) ClassUtils.getInstance(classpath);
				return game;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}
}
