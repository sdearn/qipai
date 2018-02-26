/**
 * Copyright 2015-2016 Xinrui & Co., Ltd.
 */
package com.sdearn.qipai.game.majiang;

/**
 * @author wangg
 * @version 1.0 , 2017年12月28日
 */
public class ResponseAction {

	/**
	 * 玩家进入
	 */
	public static String PLAYERENTER = "playerEnter";

	/**
	 * 玩家退出
	 */
	public static String PLAYERQUIT = "playerQuit";

	/**
	 * 玩家坐下
	 */
	public static String PLAYERREADY = "playerReady";

	/**
	 * 玩家起身
	 */
	public static String PLAYERSTAND = "playerStand";

	/**
	 * 玩家暂离
	 */
	public static String PLAYERSTOP = "playerStop";

	/**
	 * 游戏开始
	 */
	public static String GAMESTART = "gameStart";

	/**
	 * 等待玩家操作
	 */
	public static String PLAYERWAIT = "playerWait";

	/**
	 * 玩家出牌
	 */
	public static String PLAYERDOCARD = "playerDoCard";

	/**
	 * 玩家碰牌
	 */
	public static String PLAYERPENG = "playerPeng";

	/**
	 * 玩家杠牌
	 */
	public static String PLAYERGANG = "playerGang";

	/**
	 * 玩家胡牌
	 */
	public static String PLAYERHU = "playerHu";

	/**
	 * 玩家中断
	 */
	public static String PLAYERINTERRUPT = "playerInterrupt";

	/**
	 * 自己摸牌
	 */
	public static String OWNDRAWCARD = "ownDrawCard";

	/**
	 * 自己碰、杠、胡
	 */
	public static String OWNACTION = "ownAction";

	/**
	 * 游戏结束
	 */
	public static String GAMEOVER = "gameOver";
}
