package com.yjy.thread;

import java.util.concurrent.TimeUnit;

public class Interrupted {

	public static void main(String[] args) throws InterruptedException  {
		
		Thread sleepThread = new Thread(new SleepRunner(), "sleepThread");
		sleepThread.setDaemon(true);
		Thread busyThread = new Thread(new BusyRunner(), "busyThread");
		busyThread.setDaemon(false);
		sleepThread.start();
		busyThread.start();

		TimeUnit.SECONDS.sleep(1);

		sleepThread.interrupt(); //睡眠中的sleep 中断会报错 InterruptedException
		busyThread.interrupt(); 
		System.out.println("sleepthread interrupted is " + sleepThread.isInterrupted());
		System.out.println("busythread interrupted is " + busyThread.isInterrupted());
		
		TimeUnit.SECONDS.sleep(2);
	}
	
	static class SleepRunner implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
			  SleepUtils.second(10);
			}
		}
	}
	
	static class BusyRunner implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
				
			}
		}
		
	}
}
