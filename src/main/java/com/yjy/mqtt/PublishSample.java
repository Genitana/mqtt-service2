package com.yjy.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.alibaba.fastjson.JSONObject;

/**
* @author yangbo
* <br> 2018-12-20 13:56:31
*/
/**
 *发布端
 */
public class PublishSample {
    public static void main(String[] args) {

    	JSONObject data = new JSONObject();
    	data.put("did", 477);
    	data.put("uuid", "g5Wuzt");
    	data.put("payload", "AA02002E00210F2100010001020000020000020000000113010801010000FA0114000114FA011400011401140114E6");
    	
    	JSONObject data2 = new JSONObject();
    	data2.put("did", 900);
    	data2.put("uuid", "W9oyhL");
    	data2.put("payload", "AA020022002103210000030A0000030A00180103000102060200020000000001000350");
    	
        String topic = "900";
        String content = data2.toJSONString();
        int qos = 2;
        String HOST = "tcp://192.168.1.74:1884";
        String userName = "test";
        String password = "test";
        String clientId = "pubClient";

        try {
            // 创建客户端
            MqttClient sampleClient = new MqttClient(HOST, clientId, new MemoryPersistence());
            // 创建链接参数
            MqttConnectOptions connOpts = new MqttConnectOptions();
            // 在重新启动和重新连接时记住状态
            connOpts.setCleanSession(false);
            // 设置连接的用户名
            connOpts.setUserName(userName);
            connOpts.setPassword(password.toCharArray());
            // 建立连接
            sampleClient.connect(connOpts);
            // 创建消息
            MqttMessage message = new MqttMessage(content.getBytes("UTF-8"));
            // 设置消息的服务质量
            message.setQos(qos);
            // 发布消息
            sampleClient.publish(topic, message);
            System.out.println("publish success!");
            // 断开连接
            sampleClient.disconnect();
            // 关闭客户端
            sampleClient.close();
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
