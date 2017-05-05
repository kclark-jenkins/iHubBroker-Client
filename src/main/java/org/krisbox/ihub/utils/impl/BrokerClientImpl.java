package org.krisbox.ihub.utils.impl;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.krisbox.ihub.utils.BROKER;
import org.krisbox.ihub.utils.BrokerClient;
import org.krisbox.ihub.utils.LOG_LEVEL;

import java.util.Map;

import static org.krisbox.ihub.utils.LOG_LEVEL.*;

public class BrokerClientImpl implements BrokerClient {
    private final Logger logger = Logger.getLogger(BrokerClient.class);
    private BROKER brokerClient;
    private Map<String, Object> brokerObjects;

    private Object broker;
    private Object connOpts;

    public BrokerClientImpl(BROKER brokerClient, Map<String, Object> properties) {
        switch((String)properties.get("brokerType")) {
            case "paho":
                brokerClient = BROKER.PAHO;
            break;
        }

        switch((LOG_LEVEL)properties.get("logLevel")) {
            case FATAL:
                logger.setLevel(Level.FATAL);
            break;
            case ERROR:
                logger.setLevel(Level.ERROR);
            break;
            case WARNING:
                logger.setLevel(Level.WARN);
            break;
            case DEBUG:
                logger.setLevel(Level.DEBUG);
            break;
            case INFO:
                logger.setLevel(Level.INFO);
            break;
            default:
                logger.setLevel(Level.FATAL);

        }
    }
    
    public void executeReport(Map<String, Object> properties) {
        
    }

    
    public void deleteReport(Map<String, Object> properties) {

    }

    
    public void uploadReport(Map<String, Object> properties) {

    }

    
    public void addUser(Map<String, Object> properties) {

    }

    
    public void addUsers(Map<String, Object> properties) {

    }

    
    public void addGroup(Map<String, Object> properties) {

    }

    
    public void addGroups(Map<String, Object> properties) {

    }

    
    public void deleteGroup(Map<String, Object> properties) {

    }

    
    public void deleteGroups(Map<String, Object> properties) {

    }

    
    public void deleteUser(Map<String, Object> properties) {

    }

    
    public void deleteUsers(Map<String, Object> properties) {

    }

    
    public void addSchedule(Map<String, Object> properties) {

    }

    
    public void deleteSchedule(Map<String, Object> properties) {

    }

    
    public void deleteSchedules(Map<String, Object> properties) {

    }

    
    public Map<String, Object> listReports(Map<String, Object> properties) {
        return null;
    }

    
    public Map<String, Object> listUsers(Map<String, Object> properties) {
        return null;
    }

    
    public Map<String, Object> listGroups(Map<String, Object> properties) {
        return null;
    }

    
    public Map<String, Object> listSchedules(Map<String, Object> properties) {
        return null;
    }

    
    public void brokerConnect(Map<String, Object> properties) {
        brokerObjects.put("connectionOptions", properties.get("MqttConnectOptions"));
        brokerObjects.put("setCleanSession", properties.get("setCleanSession"));

        ((MqttConnectOptions)brokerObjects.get("connectionOptions")).setCleanSession((Boolean)properties.get("setCleanSession"));
        try {
            ((MqttClient)brokerObjects.get("connectionOptions")).connect(((MqttConnectOptions)brokerObjects.get("connectionOptions")));
            logger.info("Connecting ");
        } catch (MqttException e) {
            logger.fatal(e);
        }
    }

    public void createClient(Map<String, Object> properties) {
        if(brokerClient == BROKER.PAHO) {
            try {
                brokerObjects.put("broker", new MqttClient((String)properties.get("brokerURL"),
                        (String)properties.get("clientID"),
                        (MemoryPersistence)properties.get("persistence")));
            } catch (MqttException e) {
                logger.fatal(e);
            }
        }
    }
    
    public void brokerDisconnect(Map<String, Object> properties) {
        try {
            ((MqttClient)brokerObjects.get("connectionOptions")).disconnect();
        } catch (MqttException e) {
            logger.error(e);
        }
    }

    public void sendMessage(Map<String, Object> properties) {
        logger.info("Creating message");
        MqttMessage message = new MqttMessage(((String)properties.get("message")).getBytes());
        message.setQos((Integer)properties.get("qos"));
        try {
            logger.info("Publishing message");
            ((MqttClient)brokerObjects.get("connectionOptions")).publish(((String)properties.get("topic")), message);
            logger.info("Done");
        } catch (MqttException e) {
            logger.warn(e);
        }
    }

    @Override
    public void connectionLost(Throwable cause) {
        logger.warn(cause);
    }

    @Override
    public void messageArrived(String topic, MqttMessage message)
            throws Exception {
        logger.info("Message arrived: " + message);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        logger.info("Delivery complete");
    }
}
