/**
 * @author julien
 */

package com.emn.fil1.jderay.wildcatintegration.logs;


public class ServerLogs {
    private int sequence;
    
    private String requestDate;
    private String url;
    private String returnedCode;
    private String machineName;
    private String requestTime;

    public ServerLogs(int sequence, String requestDate, String url, String returnedCode, String machineName, String requestTime) {
        this.sequence = sequence;
        this.requestDate = requestDate;
        this.url = url;
        this.returnedCode = returnedCode;
        this.machineName = machineName;
        this.requestTime = requestTime;
    }

    public int getSequence() {
        return sequence;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public String getUrl() {
        return url;
    }

    public String getReturnedCode() {
        return returnedCode;
    }

    public String getMachineName() {
        return machineName;
    }

    public int getRequestTime() {
            return (int) (Double.parseDouble(requestTime) * 1000);
    }

    @Override
    public String toString() {
        return "Sequence : " + sequence + "\n"
                + "requestDate : " + requestDate + "\n"
                + "url : " + url + "\n"
                + "returndCode : " + returnedCode + "\n"
                + "machineName : " + machineName + "\n"
                + "requestTime : " + requestTime;
    }
    
}
