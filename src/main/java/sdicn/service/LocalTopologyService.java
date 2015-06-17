package sdicn.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import sdicn.model.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by florian on 15/06/15.
 */

@Service
@Profile("dev")
public class LocalTopologyService implements TopologyService {
    public LocalTopologyService() {
    }

    @Override
    public NetworkGraph getTopologyInfo() {
        NetworkGraph nwg = new NetworkGraph();
        List<OFNode> nodes = new ArrayList<>();
        List<OFSwitch> switches = new ArrayList<>();
        List<OFLink> ofLinks = new ArrayList<>();

        try {
            String devices = new String(Files.readAllBytes(Paths.get("device.json")));
            String links = new String(Files.readAllBytes(Paths.get("links.json")));

            //Reader devicesReader = new InputStreamReader(new FileInputStream("device.json"), StandardCharsets.UTF_8);
            //Reader linksReader = new InputStreamReader(new FileInputStream("device.json"), StandardCharsets.UTF_8);

            if (devices.charAt(0) != '[') {
                devices = devices.substring(1);
            }
            JSONArray deviceJson = new JSONArray(devices);
            JSONArray linksJson = new JSONArray(links);

            for (int i = 0; i < linksJson.length(); i++) {
                JSONObject link = linksJson.getJSONObject(i);
                String srcDpid = link.getString("src-switch");
                String dstDpid = link.getString("dst-switch");
                int srcPort = link.getInt("src-port");
                int dstPort = link.getInt("dst-port");

                OFSwitch srcSwitch;
                OFSwitch dstSwitch;

                Optional<OFSwitch> srcSwitchOpt = switches.stream().filter(
                        sw -> sw.getDpid().equalsIgnoreCase(srcDpid)).findFirst();

                if (!srcSwitchOpt.isPresent()) {
                    srcSwitch = new OFSwitch(srcDpid);
                    switches.add(srcSwitch);
                } else {
                    srcSwitch = srcSwitchOpt.get();
                }

                Optional<OFSwitch> dstSwitchOpt = switches.stream().filter(
                        sw -> sw.getDpid().equalsIgnoreCase(dstDpid)).findFirst();

                if (!dstSwitchOpt.isPresent()) {
                    dstSwitch = new OFSwitch(dstDpid);
                    switches.add(dstSwitch);
                } else {
                    dstSwitch = dstSwitchOpt.get();
                }

                OFLink linkSrc2Dst = new OFLink(srcSwitch.getDpid(), dstSwitch.getDpid(), srcPort, dstPort);
                ofLinks.add(linkSrc2Dst);
                //OFLink linkDst2Src = new OFLink(dstSwitch, srcSwitch, dstPort, srcPort);

                //srcSwitch.getLinks().add(linkSrc2Dst);
                //dstSwitch.getLinks().add(linkDst2Src);

            }

            for (int i = 0; i < deviceJson.length(); i++) {
                JSONObject device = deviceJson.getJSONObject(i);
                if (device.getJSONArray("mac").length() == 0
                        || device.getJSONArray("ipv4").length() == 0
                        || device.getJSONArray("attachmentPoint").length() == 0) {
                    continue;
                }
                String mac = device.getJSONArray("mac").get(0).toString();
                String ip = device.getJSONArray("ipv4").get(0).toString();

                JSONArray attachmentPoint = device.getJSONArray("attachmentPoint");
                String apDpid = attachmentPoint.getJSONObject(0).getString("switchDPID");
                int apPort = attachmentPoint.getJSONObject(0).getInt("port");

                OFNode ofNode = new OFNode(mac, ip);
                Optional<OFSwitch> apSwitch = switches
                        .stream()
                        .filter(sw -> sw.getDpid().equalsIgnoreCase(apDpid))
                        .findFirst();

                ofNode.setAttachmentPoint(new OFAttachmentPoint(apSwitch.get(), apPort));
                nodes.add(ofNode);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        nwg.setNodes(nodes);
        nwg.setSwitches(switches);
        nwg.setLinks(ofLinks);
        return nwg;
    }
}
