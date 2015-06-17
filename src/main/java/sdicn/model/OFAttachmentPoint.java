package sdicn.model;

/**
 * Created by florian on 16/06/15.
 */
public class OFAttachmentPoint {

    private OFSwitch ofSwitch;
    private int port;

    public OFAttachmentPoint(OFSwitch ofSwitch, int port) {
        this.ofSwitch = ofSwitch;
        this.port = port;
    }

    public OFSwitch getOfSwitch() {
        return ofSwitch;
    }

    public void setOfSwitch(OFSwitch ofSwitch) {
        this.ofSwitch = ofSwitch;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
