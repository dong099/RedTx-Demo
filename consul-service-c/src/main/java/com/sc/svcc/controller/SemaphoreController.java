package com.sc.svcc.controller;

import java.util.concurrent.Semaphore;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/test")
@Slf4j
public class SemaphoreController {

	Semaphore semaphore = new Semaphore(1); // 定义资源的总数量

	@GetMapping("/sema")
	@ResponseBody
	public String Resquest() {
		int availablePermits = semaphore.availablePermits();// 可用资源数
		if (availablePermits > 0) {
			System.out.println("抢到资源");
		} else {
			System.out.println("资源已被占用，稍后再试");
			return "Resource is busy！";
		}
		try {
			semaphore.acquire(1); // 请求占用一个资源
			System.out.println("资源正在被使用");
			Thread.sleep(30000);// 放大资源占用时间，便于观察
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release(1);// 释放一个资源
		}
		return "Success";
	}

}