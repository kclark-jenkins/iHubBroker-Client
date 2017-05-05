package org.krisbox.ihub.utils;

import org.eclipse.paho.client.mqttv3.MqttCallback;

import java.util.Map;

public interface BrokerClient extends iHubClient, MqttCallback {
    public void   createClient(     Map<String, Object> properties);
    public void   brokerConnect(    Map<String, Object> properties);
    public void   brokerDisconnect( Map<String, Object> properties);
    public void   sendMessage(      Map<String, Object> properties);
}
