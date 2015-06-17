package sdicn.model;

/**
 * Created by florian on 15/06/15.
 */
public class OFLink {

    private int bandwidth;
    private double reliability;

    private String srcSwitch;
    private String dstSwitch;

    private int srcPort;
    private int dstPort;

    public OFLink(int bandwidth, double reliability, String srcSwitch, String dstSwitch, int srcPort, int dstPort) {
        this.bandwidth = bandwidth;
        this.reliability = reliability;
        this.srcSwitch = srcSwitch;
        this.dstSwitch = dstSwitch;
        this.srcPort = srcPort;
        this.dstPort = dstPort;
    }

    public OFLink(String srcSwitch, String dstSwitch, int srcPort, int dstPort) {
        this.srcSwitch = srcSwitch;
        this.dstSwitch = dstSwitch;
        this.srcPort = srcPort;
        this.dstPort = dstPort;
    }

    public int getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(int bandwidth) {
        this.bandwidth = bandwidth;
    }

    public double getReliability() {
        return reliability;
    }

    public void setReliability(double reliability) {
        this.reliability = reliability;
    }

    public String getSrcSwitch() {
        return srcSwitch;
    }

    public void setSrcSwitch(String srcSwitch) {
        this.srcSwitch = srcSwitch;
    }

    public String getDstSwitch() {
        return dstSwitch;
    }

    public void setDstSwitch(String dstSwitch) {
        this.dstSwitch = dstSwitch;
    }

    public int getSrcPort() {
        return srcPort;
    }

    public void setSrcPort(int srcPort) {
        this.srcPort = srcPort;
    }

    public int getDstPort() {
        return dstPort;
    }

    public void setDstPort(int dstPort) {
        this.dstPort = dstPort;
    }
}
