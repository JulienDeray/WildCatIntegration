/**
 * @author julien
 */

package com.emn.fil1.jderay.wildcatintegration.wildcat;

import com.emn.fil1.jderay.wildcatintegration.logs.ServerLogs;
import com.emn.fil1.jderay.wildcatintegration.logs.LogsManager;


public class WildCatManager {

    public WildCatManager() {
        LogsManager logsManager = new LogsManager("172.17.2.138", 6379);
        logsManager.listen(this);
        logsManager.launchMajChecker();
    }
    
    public void pushLogs(ServerLogs logs) {
        System.out.println( logs );
    }
}
