package sdicn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import model.LocationInfo;
import sdicn.StatisticsService;
import sdicn.model.RequestInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by florian on 27.04.2015.
 */
@RestController
public class ContentLocationController {

    @Autowired
    StatisticsService statisticsService;

    private Map<String, List<String>> contentLocations;

    public ContentLocationController() {
        contentLocations = new HashMap<>();
    }

    @RequestMapping(value = "/location/resolve", method = RequestMethod.GET)
    public LocationInfo resolveContentLocation(@RequestParam(value = "contentName") String contentName,
                                               @RequestParam(value = "client") String client) {
        statisticsService.storeRequestInfo(new RequestInfo(client, contentName));
        if (contentLocations.containsKey(contentName)) {
            LocationInfo locationInfo = new LocationInfo();
            locationInfo.setLocations(contentLocations.get(contentName));
            return  locationInfo;
        }
        else return null;
    }

    @RequestMapping(value = "/location/add", method = RequestMethod.POST)
    public void addContentLocation(@RequestParam String contentName, @RequestParam String contentLocation) {
        if (!contentLocations.containsKey(contentName)) {
            contentLocations.put(contentName, new ArrayList<String>());
        }
        contentLocations.get(contentName).add(contentLocation);
    }
}
