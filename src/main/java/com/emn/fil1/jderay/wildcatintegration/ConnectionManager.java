/**
 * @author julien
 */

package com.emn.fil1.jderay.wildcatintegration;

import redis.clients.jedis.Jedis;


public class ConnectionManager {

    private Jedis client;

    public ConnectionManager(String serverIp, int serverPort) {
        client = new Jedis(serverIp, serverPort);
    }

    public boolean isConnected() {
        return client.isConnected();
    }
    
    public void insert(String key, String value) {
        client.set(key, value);
    }
    
    public String get(String key) {
        return client.get(key);
    }
    
    public boolean sequenceIsPresent() {
        return !get("sequence").isEmpty() ? true : false;
    }
    
    public ServerLogs getLogs(int sequence) {
        return sequenceIsPresent() ? 
                new ServerLogs(
                    sequence,
                    get(sequence + ":requestDate"),
                    get(sequence + ":url"),
                    get(sequence + ":returnedCode"),
                    get(sequence + ":machineName"),
                    get(sequence + ":requestTime"))
           : null;
    }
}