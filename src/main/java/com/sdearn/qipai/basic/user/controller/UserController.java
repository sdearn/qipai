package com.sdearn.qipai.basic.user.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

	@RequestMapping(value = "/test")
	public String test(Map<String, Object> model, HttpServletRequest request) {
		System.out.println("54390850");
		return "test";
	}
}
