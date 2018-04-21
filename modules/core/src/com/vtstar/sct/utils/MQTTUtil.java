package com.vtstar.sct.utils;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MQTTUtil {
	private String host = "tcp://127.0.0.1:1883";
    private String clientid;
    private MqttClient client;
    private MqttConnectOptions options;
	public void publish(String topic, String jsonString){
		try {
			client = new MqttClient(host, clientid, new MemoryPersistence());
	        // MQTT的连接设置  
	        options = new MqttConnectOptions();
	        // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接  
	        options.setCleanSession(true);
	        MqttMessage mqttMessage = new MqttMessage((jsonString).getBytes());
	        mqttMessage.setRetained(false);
	        client.connect(options);
	        //保存发送记录,避免在sub之前pub过而客户端收不到
	        client.publish(topic, mqttMessage.getPayload(), 1, true);
	        client.disconnect();
	        client.close();
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	public MQTTUtil() {
		super();
	}

	public MQTTUtil(String clientid) {
		this.clientid = clientid;
	}


}
