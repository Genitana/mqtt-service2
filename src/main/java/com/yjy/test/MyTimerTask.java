package com.yjy.test;

import java.io.UnsupportedEncodingException;
import java.util.TimerTask;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

public class MyTimerTask extends TimerTask {

	private   MqttClient sampleClient ;
	private String topic ;
	private String msg;
	
	public MyTimerTask(MqttClient sampleClient,String topic,String msg) {
		this.sampleClient = sampleClient;
		this.topic=topic;
		this.msg=msg;
	}
	@Override
	public void run() {
        try {
        	Thread.currentThread().setName("topic-"+topic);
        	// 创建消息
        	MqttMessage message = new MqttMessage(msg.getBytes("UTF-8"));
        	// 设置消息的服务质量
        	message.setQos(0);
        	// 发布消息
			sampleClient.publish(topic, message);
			System.out.println(Thread.currentThread().getName()+",publish success!topic:"+topic+",msg:"+msg);
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MqttPersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
