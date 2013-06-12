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
                context.createAttribute("self://webservices/" + nameWS[i] + "#requestTime", 0);

                //Query query = context.createQuery("select avg(cast(value?,double)) as avgRT from WAttributeEvent(source like 'self://webservices/" + nameWS[i] + "#requestTime').win:time_batch(10sec) as rt");
                  Query query = context.createQuery("select avg(cast(value?,double)) as avgRT from WAttributeEvent(source like 'self://webservices/" + nameWS[i] + "#requestTime').win:time_batch(10sec) as rt having ( avg(cast(value?,double))  > 1000)");
                
                WAction action = new WAction() {
                    @Override
                    public void onEvent() {
                        double workload = (Double) getListener().getNewEvents()[0].get("avgRT");
                        System.out.println("Violation !!!\nAverage request time : " + workload + " ms");
                    }
                };
                context.registerActions(query, action);

            }
        } catch (IOException | ContextException e) {
        }

        return context;
    }

    public static Context getContext() {
        return context;
    }
}
