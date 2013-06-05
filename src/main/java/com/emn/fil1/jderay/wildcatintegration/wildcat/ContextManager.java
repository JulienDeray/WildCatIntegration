package com.emn.fil1.jderay.wildcatintegration.wildcat;

import org.ow2.wildcat.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ContextManager {

    static Context context;

    public static Context createContext(String propertyFileName) {
        /*
         * Creating a context through the ContextFactory
         */
        context = ContextFactory.getDefaultFactory().createContext("ContextManager");
        try {
            InputStream in =
                    ContextManager.class.getClassLoader().getResourceAsStream(propertyFileName);
            Properties p = new Properties();
            p.load(in);
            String[] nameWS = p.getProperty("WSNames").split(";");
            for (int i = 0; i < nameWS.length; i++) {
                /*
                 * Creating some resource
                 */
                context.createResource("self://webservices/" + nameWS[i]);
                /*
                 * Creating and initializing attributes
                 */
                context.createAttribute("self://webservices/" + nameWS[i] + "#requestDate", "");
                context.createAttribute("self://webservices/" + nameWS[i] + "#url", "");
                context.createAttribute("self://webservices/" + nameWS[i] + "#returnedCode", "");
                context.createAttribute("self://webservices/" + nameWS[i] + "#requestTime", "");

                Query query = context.createQuery("select avg(cast(rt.value?,int)) as avgRT from WAttributeEvent(source like 'self://webservices/" + nameWS[i] + "#responseTime').win:time_batch(1min) as rt having ( avg(cast(rt.value?,int)) > 10)");
                WAction action = new WAction() {
                    public void onEvent() {
                        System.out.println("Violation !!!");
                    }
                };
                context.registerActions(query, action);

            }
        } catch (IOException | ContextException e) {
            e.printStackTrace();
        }

        return context;
    }

    public static Context getContext() {
        return context;
    }
}
