package com.maroons.junitdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Controller {
	@GetMapping(value="/hello")
	public String hello() {
		return "Hello World!";
	}
}
