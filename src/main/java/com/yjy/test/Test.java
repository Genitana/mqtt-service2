package com.yjy.test;

import java.util.HashMap;

public class Test {

	
	public static void main(String[] args) {
        //定时器
//        for(Integer i=0;i<10;i++) {
//        	Timer timer = new Timer();
//	    	TimerTask timerTask =new TimerTask() {
//				
//				@Override
//				public void run() {
//					System.out.println(Thread.currentThread().getName());
//				}
//			};
//			timer.schedule(timerTask, 1000,1000);
//        }

//		System.out.println(Integer.valueOf("G",16));
		System.out.println("".equals(" "));
		System.out.println(Integer.toHexString(190/60));
		System.out.println(Integer.toHexString(190%60));
		HashMap<String,String> map  = new HashMap();
		map.put("qq","11");
		System.out.println(Integer.valueOf("2", 16));
		System.out.println(Integer.valueOf("1e", 16));
	}
}
