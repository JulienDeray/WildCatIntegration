/**
 * @author julien
 */

package com.emn.fil1.jderay.wildcatintegration.wildcat;

import com.emn.fil1.jderay.wildcatintegration.logs.ServerLogs;
import com.emn.fil1.jderay.wildcatintegration.logs.LogsManager;
import org.ow2.wildcat.Context;
import org.ow2.wildcat.ContextException;
import org.ow2.wildcat.Query;


public class WildCatManager {

    private Context context;

    public WildCatManager(String propertiesFile) {
        ContextManager.createContext(propertiesFile);
        LogsManager logsManager = new LogsManager("172.17.2.138", 6379);
        logsManager.listen(this);
        logsManager.launchMajChecker();

        this.context = ContextManager.getContext();
    }
    
    public void pushLogs(ServerLogs logs) {
        String pathToWebservice = "self://webservice/" + logs.getMachineName();
        try {
            context.setValue(pathToWebservice + "#requestDate", logs.getRequestDate());
            context.setValue(pathToWebservice + "#url", logs.getUrl());
            context.setValue(pathToWebservice + "#returnedCode", logs.getReturnedCode());
            context.setValue(pathToWebservice + "#requestTime", logs.getRequestTime());
        } catch (ContextException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Context updated :");
            System.out.println("\t#requestDate : " + context.getValue(pathToWebservice + "#requestDate"));
            System.out.println("\t#url : " + context.getValue(pathToWebservice + "#url"));
            System.out.println("\t#returnedCode : " + context.getValue(pathToWebservice + "#returnedCode"));
            System.out.println("\t#requestTime : " + context.getValue(pathToWebservice + "#requestTime"));
        } catch (ContextException e) {
            e.printStackTrace();
        }
    }
}
