package com.emn.fil1.jderay.wildcatintegration.wildcat;

import org.ow2.wildcat.Context;
import org.ow2.wildcat.ContextException;
import org.ow2.wildcat.ContextFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * User: jonathan
 * Date: 04/06/13
 * Time: 13:31
 * To change this template use File | Settings | File Templates.
 */
public class WSContext {

    public Context getCtx() {
        return ctx;
    }

    Context ctx;

    public WSContext(String propertyFileName){
    /*
		 * Creating a context through the ContextFactory
		 */
    ctx = ContextFactory.getDefaultFactory().createContext("WSContext");
    try {
        try {
            InputStream in =
                    getClass().getClassLoader().getResourceAsStream(propertyFileName);
            Properties p = new Properties();
            p.load(in);
            String[] nameWS = p.getProperty("WSNames").split(";");
            for(int i =0; i<nameWS.length;i++){
                /*
                * Creating some resource
                 */
                ctx.createResource("self://webservice/"+nameWS[i]);
                /*
			    * Creating and initializing attributes
			    */
                ctx.createAttribute("self://webservice/"+nameWS[i]+"#requestDate","");
                ctx.createAttribute("self://webservice/"+nameWS[i]+"#url", "");
                ctx.createAttribute("self://webservice/"+nameWS[i]+"#returnedCode", "");
                ctx.createAttribute("self://webservice/"+nameWS[i]+"#machineName", "");
                ctx.createAttribute("self://webservice/"+nameWS[i]+"#requestTime", "");
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    } catch (ContextException e) {
        e.printStackTrace();
    }
    }
}
