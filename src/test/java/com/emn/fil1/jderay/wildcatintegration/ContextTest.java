package com.emn.fil1.jderay.wildcatintegration;

import com.emn.fil1.jderay.wildcatintegration.wildcat.ContextManager;
import junit.framework.TestCase;
import org.ow2.wildcat.ContextException;
import org.ow2.wildcat.Context;

import java.util.Set;

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
        Context ctx = ContextManager.createContext("WSListTest.properties");
        try {
            Set<String> resources = ctx.list("self://webservice/");
            assertTrue(resources.contains("machine1") && resources.contains("machine2"));

            Set<String> attributes = ctx.list("self://webservice/machine1");
            assertTrue(attributes.contains("#requestTime") &&
                    attributes.contains("#requestDate") &&
                    attributes.contains("#returnedCode") &&
                    attributes.contains("#url"));
        } catch (ContextException e) {
            fail();
            e.printStackTrace();
        }
    }

}
