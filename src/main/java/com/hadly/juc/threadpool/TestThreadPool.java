/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hadly.juc.threadpool;


import utils.MethodUtil;

/**
 *
 * @author lizhinian
 */
public class TestThreadPool {

    public void helloWorld(final String name) {
	System.out.println("before, name=" + name);
	ThreadPool.getCachedPool().execute(new Runnable() {

	    @Override
	    public void run() {
		System.out.println("--execute thread, name=" + name);
		MethodUtil.sleepNMilliseconds(5 * 1000);
		System.out.println("==execute finished, name=" + name);
	    }
	});
	System.out.println("after, name=" + name);
    }

    public static void main(String[] args) {
	//core size 1
	//max size 4
	//queue size 3

	//第1个线程很快执行
	//第2 3 4放到队列
	//第5 6 7会直接执行
	//第8个以上会被拒绝
	TestThreadPool test = new TestThreadPool();
	test.helloWorld("name1");
	test.helloWorld("name2");
	test.helloWorld("name3");
	test.helloWorld("name4");
	test.helloWorld("name5");
	test.helloWorld("name6");
	test.helloWorld("name7");
	try {
	    test.helloWorld("name8");
	    test.helloWorld("name9");
	    test.helloWorld("name10");
	} catch (Exception e) {
	    System.out.println("5-8 rejected");
	    e.printStackTrace();
	}

	while (true) {
	    System.out.println("threadpool details=" + ThreadPool.getCachedPool().toString());
	    MethodUtil.sleepNMilliseconds(1000);
	}
	//
	//
    }
}
