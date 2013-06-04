/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emn.fil1.jderay.wildcatintegration;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import junit.framework.TestCase;

/**
 *
 * @author julien
 */
public class RedisTest extends TestCase {
    
    public RedisTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testConnection() {
        LogsManager manager = new LogsManager("172.17.2.138", 6379);
        // on insère une valeur pour établir la connexion avec Redis
        manager.insert("connection", "yes");
        assertTrue( manager.isConnected() );
    }
    
    public void testGet() {
        LogsManager manager = new LogsManager("172.17.2.138", 6379);
        manager.insert("test", "OK");
        assertEquals( manager.get("test"), "OK" );
    }
    
}
