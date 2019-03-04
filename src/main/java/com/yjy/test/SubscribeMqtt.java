package com.yjy.test;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class SubscribeMqtt {

	
	public static void main(String[] args) {
		int num =0 ;
		int num2 = 0 ;
		for(Integer i=0;i<200;i++) {
			boolean result = Post(i.toString());
			if(result) {
				num=num+1 ;
			}else {
				num2=num2+1 ;
			}
		}
		System.out.println("成功次数:"+num+",失败次数:"+num2);
	}
	
	public static boolean Post(String param) {
		try {
			String token = "e531ca4f-d3f3-4ae6-be3b-3f1655951a79";
			HttpClient httpClient = new HttpClient();
			PostMethod postMethod = new PostMethod("http://192.168.1.74:8765/api/cooking-service/mqtt/topic/add?token="+token+"&topic="+param);
			int code = httpClient.executeMethod(postMethod);
			if(code != HttpStatus.SC_OK) {
				System.out.println("失败："+param+",code:"+code);
				return false ;
			}
			return true ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false ;
		}	
		}
}
