package com.emn.fil1.jderay.wildcatintegration.wildcat;

import org.ow2.wildcat.Context;
import org.ow2.wildcat.ContextException;
import org.ow2.wildcat.ContextFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ContextManager {

    static Context ctx;

    public static Context createContext(String propertyFileName) {
         /*
         * Creating a context through the ContextFactory
		 */
        ctx = ContextFactory.getDefaultFactory().createContext("ContextManager");
        try {
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
                    ctx.createResource("self://webservice/" + nameWS[i]);
                /*
			    * Creating and initializing attributes
			    */
                    ctx.createAttribute("self://webservice/" + nameWS[i] + "#requestDate", "");
                    ctx.createAttribute("self://webservice/" + nameWS[i] + "#url", "");
                    ctx.createAttribute("self://webservice/" + nameWS[i] + "#returnedCode", "");
                    ctx.createAttribute("self://webservice/" + nameWS[i] + "#requestTime", "");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ContextException e) {
            e.printStackTrace();
        }

        return ctx;
    }

    public static Context getCtx() {
        return ctx;
    }
}
