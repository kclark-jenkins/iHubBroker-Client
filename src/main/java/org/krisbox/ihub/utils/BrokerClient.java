package org.krisbox.ihub.utils;

import java.util.Map;

public interface BrokerClient extends iHubClient {
    public void   brokerConnect(    Map<String, Object> properties);
    public void   brokerDisconnect( Map<String, Object> properties);
    public Object onMessageRecieved(Map<String, Object> properties);
    public Object onError(          Map<String, Object> properties);
}
