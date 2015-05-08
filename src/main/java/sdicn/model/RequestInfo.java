package sdicn.model;

import java.util.Date;

/**
 * Created by florian on 08.05.15.
 */
public class RequestInfo {

    private String client;
    private String name;
    private Date date;

    public RequestInfo() {
    }

    public RequestInfo(String client, String name) {
        this.client = client;
        this.name = name;
        this.date = new Date();
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
