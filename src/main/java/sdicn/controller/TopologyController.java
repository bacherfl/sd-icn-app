package sdicn.controller;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sdicn.model.NetworkGraph;
import sdicn.model.OFLink;
import sdicn.model.OFNode;
import sdicn.model.OFSwitch;
import sdicn.service.TopologyService;

import java.util.List;

/**
 * Created by florian on 16/06/15.
 */
@Api(name = "Topology services", description = "Methods for gathering topology info")
@RestController
public class TopologyController {

    @Autowired
    TopologyService topologyService;

    @ApiMethod
    @RequestMapping(value = "/topology/nodes", method = RequestMethod.GET)
    public @ApiResponseObject
    @ResponseBody
    List<OFNode> getTopologyNodes() {
        NetworkGraph topologyInfo = topologyService.getTopologyInfo();
        return topologyInfo.getNodes();
    }

    @ApiMethod
    @RequestMapping(value = "/topology/links", method = RequestMethod.GET)
    public @ApiResponseObject
    @ResponseBody
    List<OFLink> getTopologyLinks() {
        NetworkGraph topologyInfo = topologyService.getTopologyInfo();
        return topologyInfo.getLinks();
    }

    @ApiMethod
    @RequestMapping(value = "/topology/switches", method = RequestMethod.GET)
    public @ApiResponseObject
    @ResponseBody
    List<OFSwitch> getTopologySwitches() {
        NetworkGraph topologyInfo = topologyService.getTopologyInfo();
        return topologyInfo.getSwitches();
    }

    @ApiMethod
    @RequestMapping(value = "/topology", method = RequestMethod.GET)
    public @ApiResponseObject
    @ResponseBody
    NetworkGraph getTopology() {
        NetworkGraph topologyInfo = topologyService.getTopologyInfo();
        return topologyInfo;
    }
}
