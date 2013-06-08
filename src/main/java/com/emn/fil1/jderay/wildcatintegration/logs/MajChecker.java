/**
 * @author julien
 */

package com.emn.fil1.jderay.wildcatintegration.logs;


public class MajChecker implements Runnable {

    private int sequence;

    private LogsManager logsManager;

    public MajChecker(LogsManager logsManager) {
        this.logsManager = logsManager;
        sequence = Integer.parseInt( logsManager.getSequence() );
    }
    
    public void run() {
        System.out.println("--- Start listening Redis ---");
        while (true) {
            checkSequence();
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("--- Stop listening Redis ---");
                break;
            }
        }
    }

    private void checkSequence() {
        int newSequence = Integer.parseInt( logsManager.getSequence() );
        
        if ( newSequence > sequence ) {
            while ( sequence < newSequence ) {
                sequence++;
                logsManager.fireLogs( sequence );
            }
        }
    }
    

}
