package com.emn.fil1.jderay.wildcatintegration;

import com.emn.fil1.jderay.wildcatintegration.wildcat.ContextManager;
import com.emn.fil1.jderay.wildcatintegration.wildcat.WildCatManager;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        ContextManager.createContext("WSList.properties");
        WildCatManager wildCatManager = new WildCatManager();
        
    }
}
