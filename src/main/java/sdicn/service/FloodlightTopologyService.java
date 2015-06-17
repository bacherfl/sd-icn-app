package sdicn.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import sdicn.model.NetworkGraph;

/**
 * Created by florian on 15/06/15.
 */

@Service
@Profile("production")
public class FloodlightTopologyService implements TopologyService {
    @Override
    public NetworkGraph getTopologyInfo() {
        return null;
    }
}
