/**
 * Copyright 2015-2016 Xinrui & Co., Ltd.
 */
package com.sdearn.qipai.basic.user.domain;

/**
 * @author wangg
 * @version 1.0 , 2017年12月21日
 */

public class User {

	private Long id;

	private Long uid;

	private String username;

	private String headimg;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
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
}
