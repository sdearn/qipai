/**
 * Copyright 2015-2016 Xinrui & Co., Ltd.
 */
package com.sdearn.qipai.basic.socket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.sdearn.qipai.game.majiang.responsemsg.ResponseMessage;

import net.sf.json.JSONSerializer;

/**
 * @author wangg
 * @version 1.0 , 2018年1月3日
 */
public class SocketEncoder implements Encoder.Text<ResponseMessage> {

	@Override
	public void init(EndpointConfig config) {
		// TODO Auto-generated method stub

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public String encode(ResponseMessage object) throws EncodeException {
		String string = JSONSerializer.toJSON(object).toString();
		System.out.println(string);
		return string;
	}

}
