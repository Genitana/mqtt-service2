package com.yjy.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @author yangbo
* <br> 2018-12-20 13:55:12<br>
* 订阅端
 */
public class SubscribeSample {

    private static Logger LOG  = LoggerFactory.getLogger(SubscribeSample.class);
    public static void main(String[] args) throws MqttException {   
        String HOST = "tcp://192.168.1.74:1884";
        String TOPIC = "900";
        int qos = 1;
//        String userName = "test";
//        String passWord = "test";
        String clientid = "subClient1";
        try {
            // host为主机名，test为clientid即连接MQTT的客户端ID，一般以客户端唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
            MqttClient client = new MqttClient(HOST, clientid, null);
            // MQTT的连接设置
            MqttConnectOptions options = new MqttConnectOptions();
            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(false);
            //重连
            options.setAutomaticReconnect(true);
//            // 设置连接的用户名
//            options.setUserName(userName);
//            // 设置连接的密码
//            options.setPassword(passWord.toCharArray());
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(10);
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(20);
            //连接
            client.connect(options);
            LOG.info("connect:{}",client.isConnected());

            // 设置回调函数
            client.setCallback(new MqttCallbackExtended() {
                @Override
                public void connectComplete(boolean reconnect, String serverURI) {
                        LOG.info("reconnected sucess ");
                    try {
                        client.subscribe(TOPIC, qos);
                        LOG.info("重新订阅成功");
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void connectionLost(Throwable cause) {
                    LOG.error("connect Lost...");
                }
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    System.out.println("topic:"+topic);
                    System.out.println("Qos:"+message.getQos());
                    System.out.println("message content:"+new String(message.getPayload(),"UTF-8"));
                    
                }
                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.out.println("deliveryComplete---------"+ token.isComplete());
                }
            });


            //订阅消息
            client.subscribe(TOPIC, qos);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

