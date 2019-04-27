package com.task.test;

import java.io.IOException;

import com.task.util.LedSendServer;

public class ThreadTest {

	public static void main(String[] args) throws IOException {
	}

	class MyThread extends Thread {
		@Override
		public void run() {
			System.out.println("进入线程" + Thread.currentThread().getName());
			try {
				Thread.currentThread().sleep(5000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			System.out
					.println("线程" + Thread.currentThread().getName() + "执行完毕");
		}
	}
}
