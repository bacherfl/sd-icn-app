package sdicn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sdicn.StatisticsService;
import sdicn.model.RequestInfo;

import java.util.List;

/**
 * Created by florian on 08.05.15.
 */
@RestController
public class StatisticsController {
    
    @Autowired
    StatisticsService statisticsService;

    @RequestMapping(value = "/stats/requests", method = RequestMethod.GET)
    public List<RequestInfo> getRequests() {
        return statisticsService.getAllRequests();
    }
}
