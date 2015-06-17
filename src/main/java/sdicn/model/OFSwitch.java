package sdicn.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by florian on 16/06/15.
 */
public class OFSwitch {

    private String dpid;

    private List<OFLink> links = new ArrayList<>();

    public OFSwitch(String dpid) {
        this.dpid = dpid;
    }

    public OFSwitch() {

    }

    public String getDpid() {
        return dpid;
    }

    public void setDpid(String dpid) {
        this.dpid = dpid;
    }

    public List<OFLink> getLinks() {
        return links;
    }

    public void setLinks(List<OFLink> links) {
        this.links = links;
    }
}
