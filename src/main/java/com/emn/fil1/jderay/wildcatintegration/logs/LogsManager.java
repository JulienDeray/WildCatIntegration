/**
 * @author julien
 */

package com.emn.fil1.jderay.wildcatintegration.logs;

import com.emn.fil1.jderay.wildcatintegration.wildcat.WildCatManager;
import java.util.ArrayList;
import java.util.List;
import redis.clients.jedis.Jedis;


public class LogsManager {

    private Jedis client;
    private List<WildCatManager> listeners;
    
    public LogsManager(String serverIp, int serverPort) {
        client = new Jedis(serverIp, serverPort);
        listeners = new ArrayList<>();
    }

    public void listen( WildCatManager listener ) {
        System.out.println("--- One listener added ---");
        this.listeners.add( listener );
    }
    
    public boolean isConnected() {
        return client.isConnected();
    }

    public String getSequence() {
        return client.get("sequence");
    }
    
    public boolean sequenceIsPresent() {
        return !getSequence().isEmpty() ? true : false;
    }
    
    void fireLogs( int sequence ) {
        if ( parasite( sequence ) ) {
            for ( WildCatManager listener : listeners ) {
                listener.pushLogs( makeLogs( sequence ) );
            }
        }
    }
    
    private boolean parasite( int sequence ) {
        if ( client.get(sequence + ":url").equals("-") )
            return false;
        else
            return true;
    }
    
    private ServerLogs makeLogs( int sequence ) {
        return sequenceIsPresent() ? 
                new ServerLogs(
                    sequence,
                    client.get(sequence + ":requestDate"),
                    client.get(sequence + ":url"),
                    client.get(sequence + ":returnedCode"),
                    client.get(sequence + ":machineName"),
                    client.get(sequence + ":requestTime"))
           : null;
    }
    
    public void launchMajChecker() {
        Thread t = new Thread( new MajChecker( this ) );
        t.start();
    }
}