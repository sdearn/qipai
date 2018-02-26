/**
 * Copyright 2015-2016 Xinrui & Co., Ltd.
 */
package com.sdearn.qipai.basic.gameservice;

/**
 * @author wangg
 * @version 1.0 , 2017年12月26日
 */
public interface Game {

	public void enter(GameSession session);

	public void excute(String message);

	public void interrupt(GameSession session);

}
