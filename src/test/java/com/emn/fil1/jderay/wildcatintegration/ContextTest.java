package com.emn.fil1.jderay.wildcatintegration;

import junit.framework.TestCase;
import org.ow2.wildcat.ContextException;

import java.util.Set;

/**
 * User: jonathan
 * Date: 04/06/13
 * Time: 14:06
 * To change this template use File | Settings | File Templates.
 */
public class ContextTest extends TestCase {

    public ContextTest(String testName){
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

    public void testCreateContext(){
        WSContext wsc = new WSContext("WSListTest.properties");
        try {
            Set<String> resources = wsc.getCtx().list("self://webservice/");
            assertTrue(resources.contains("machine1") && resources.contains("machine2"));

            Set<String> attributes = (wsc.getCtx().list("self://webservice/machine1"));
            assertTrue(attributes.contains("#requestTime") &&
                    attributes.contains("#machineName") &&
                    attributes.contains("#requestDate") &&
                    attributes.contains("#returnedCode") &&
                    attributes.contains("#url"));
        } catch (ContextException e) {
            fail();
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

}
