package sdicn.controller;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.jsondoc.core.annotation.ApiResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

@Api(name = "Location services", description = "Methods for managing locations")
@RestController
public class ContentLocationController {

    @Autowired
    StatisticsService statisticsService;

    private Map<String, List<String>> contentLocations;
    private Map<String, Long> contentSizes;

    public ContentLocationController() {
        contentLocations = new HashMap<>();
        contentSizes = new HashMap<>();
    }

    @ApiMethod
    @RequestMapping(value = "/location/resolve", method = RequestMethod.GET)
    public @ApiResponseObject
    @ResponseBody LocationInfo resolveContentLocation(
            @ApiQueryParam(name = "contentName") @RequestParam(value = "contentName") String contentName,
            @ApiQueryParam(name = "client") @RequestParam(value = "client") String client) {
        statisticsService.storeRequestInfo(new RequestInfo(client, contentName));
        if (contentLocations.containsKey(contentName)) {
            LocationInfo locationInfo = new LocationInfo();
            locationInfo.setLocations(contentLocations.get(contentName));
            return  locationInfo;
        }
        else return null;
    }

    @ApiMethod
    @RequestMapping(value = "/location/add", method = RequestMethod.POST)
    public void addContentLocation(@ApiQueryParam(name = "contentName") @RequestParam String contentName,
                                   @ApiQueryParam(name = "contentLocation") @RequestParam String contentLocation,
                                   @ApiQueryParam(name= "size") @RequestParam long size) {
        if (!contentLocations.containsKey(contentName)) {
            contentLocations.put(contentName, new ArrayList<String>());
        }
        contentLocations.get(contentName).add(contentLocation);

        if (!contentSizes.containsKey(contentName)) {
            contentSizes.put(contentName, size);
        }
    }
}
