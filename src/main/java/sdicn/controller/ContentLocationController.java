package sdicn.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by florian on 27.04.2015.
 */
@RestController
public class ContentLocationController {

    private Map<String, List<String>> contentLocations;

    public ContentLocationController() {
        contentLocations = new HashMap<>();
    }

    @RequestMapping(value = "/location/resolve", method = RequestMethod.GET)
    public List<String> resolveContentLocation(@RequestParam(value = "contentName") String contentName) {
        if (contentLocations.containsKey(contentName))
            return contentLocations.get(contentName);
        else return null;
    }

    @RequestMapping(value = "/location/add", method = RequestMethod.POST)
    public void addContentLocation(@RequestParam String contentName, @RequestParam String contentLocation) {
        if (!contentLocations.containsKey(contentName)) {
            contentLocations.put(contentName, new ArrayList<>());
        }
        contentLocations.get(contentName).add(contentLocation);
    }
}
