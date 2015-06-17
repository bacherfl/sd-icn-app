package sdicn.model;

import java.util.List;

/**
 * Created by florian on 15/06/15.
 */
public class NetworkGraph {
    private List<OFNode> nodes;
    private List<OFSwitch> switches;
    private List<OFLink> links;

    public List<OFLink> getLinks() {
        return links;
    }

    public void setLinks(List<OFLink> links) {
        this.links = links;
    }

    public List<OFNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<OFNode> nodes) {
        this.nodes = nodes;
    }

    public void setSwitches(List<OFSwitch> switches) {
        this.switches = switches;
    }

    public List<OFSwitch> getSwitches() {
        return switches;
    }
}
