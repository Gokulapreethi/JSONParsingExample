package com.example.preethi.jsonparsingexample;

/**
 * Created by Preethi on 3/29/2018.
 */

public class jsonBean implements Cloneable {
    public  String clientName;
    public String ClientMail;
    public String  ClientNo;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientMail() {
        return ClientMail;
    }

    public void setClientMail(String clientMail) {
        ClientMail = clientMail;
    }

    public String getClientNo() {
        return ClientNo;
    }

    public void setClientNo(String clientNo) {
        ClientNo = clientNo;
    }
}
