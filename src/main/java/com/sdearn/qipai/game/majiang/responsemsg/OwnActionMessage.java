/**
 * Copyright 2015-2016 Xinrui & Co., Ltd.
 */
package com.sdearn.qipai.game.majiang.responsemsg;

import com.sdearn.qipai.game.majiang.ResponseAction;

/**
 * @author wangg
 * @version 1.0 , 2017年12月28日
 */
public class OwnActionMessage extends ResponseMessage {

	private Boolean isPeng;

	private Boolean isGang;

	private Boolean isHu;

	public OwnActionMessage() {
		this.setAction(ResponseAction.OWNACTION);
	}

	public Boolean getIsPeng() {
		return isPeng;
	}

	public void setIsPeng(Boolean isPeng) {
		this.isPeng = isPeng;
	}

	public Boolean getIsGang() {
		return isGang;
	}

	public void setIsGang(Boolean isGang) {
		this.isGang = isGang;
	}

	public Boolean getIsHu() {
		return isHu;
	}

	public void setIsHu(Boolean isHu) {
		this.isHu = isHu;
	}
}
