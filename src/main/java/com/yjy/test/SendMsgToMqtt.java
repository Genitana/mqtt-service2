package com.yjy.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.alibaba.fastjson.JSONObject;

public class SendMsgToMqtt {

	
	
	public static void main(String[] args) {
		
		
		String[] steakModel= {"ogN1u7","jVEl1C","g5Wuzt"};
		String[] breadModel = {"POOvcN","POONej","W9oyhL","XBMBJA"};
		List<String> steakModelList = Arrays.asList(steakModel);
		List<String> breadModelList = Arrays.asList(breadModel);
    	JSONObject data = new JSONObject();
//    	data.put("did", 477);
//    	data.put("uuid", "g5Wuzt");
    	data.put("payload", "AA02002E00210F2100010001020000020000020000000113010801010000FA0114000114FA011400011401140114E6");
    	
    	JSONObject data2 = new JSONObject();
//    	data2.put("did", 900);
//    	data2.put("uuid", "W9oyhL");
    	data2.put("payload", "AA020022002103210000030A0000030A00180103000102060200020000000001000350");
    	JSONObject[]  dataArray = {data,data2};
    	ArrayList<List> modelArrayList = new ArrayList<>();
    	modelArrayList.add(steakModelList);
    	modelArrayList.add(breadModelList);

    	
        String HOST = "tcp://192.168.1.74:1884";
        String clientId = "pubClient";

        try {
            // 创建客户端
            MqttClient sampleClient = new MqttClient(HOST, clientId, new MemoryPersistence());
            // 创建链接参数
            MqttConnectOptions connOpts = new MqttConnectOptions();
            // 在重新启动和重新连接时记住状态
            connOpts.setCleanSession(false);
            // 建立连接
            sampleClient.connect(connOpts);

//            // 断开连接
//            sampleClient.disconnect();
//            // 关闭客户端
//            sampleClient.close();
            
            //定时器
            Timer timer = new Timer();
            for(Integer i=0;i<200;i++) {
            	
            	int sort = i%2 ;
            	JSONObject  dataMsg = dataArray[sort];
            	dataMsg.put("did",i.toString());
            	dataMsg.put("uuid",((List<String>)modelArrayList.get(sort)).get(i%3) );
            	TimerTask timerTask =new MyTimerTask( sampleClient,i.toString(),dataMsg.toJSONString()); 
            	timer.schedule(timerTask, 1000,1000);
            }
            
        } catch (MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }catch (Exception e) {
			e.printStackTrace();
		}
	}
}
