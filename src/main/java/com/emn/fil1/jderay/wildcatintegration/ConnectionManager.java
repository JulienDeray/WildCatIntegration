/**
 * @author julien
 */

package com.emn.fil1.jderay.wildcatintegration;

import redis.clients.jedis.Jedis;


public class ConnectionManager {

    private String serverIp;
    private int serverPort;
    private Jedis client;

    public ConnectionManager(String serverIp, int serverPort) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
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
}