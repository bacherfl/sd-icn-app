package sdicn.model;

import java.util.List;

/**
 * Created by florian on 15/06/15.
 */
public class OFNode {

    private String macAddress;
    private String ipAddress;
    private OFAttachmentPoint attachmentPoint;

    public OFNode(String macAddress, String ipAddress) {
        this.macAddress = macAddress;
        this.ipAddress = ipAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public OFAttachmentPoint getAttachmentPoint() {
        return attachmentPoint;
    }

    public void setAttachmentPoint(OFAttachmentPoint attachmentPoint) {
        this.attachmentPoint = attachmentPoint;
    }
}
