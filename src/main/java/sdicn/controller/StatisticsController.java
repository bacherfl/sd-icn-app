package sdicn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sdicn.StatisticsService;
import sdicn.model.ContentPopularity;
import sdicn.model.RequestInfo;

import java.util.Date;
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

    @RequestMapping(value= "/stats/popularities/all", method = RequestMethod.GET)
    public List<ContentPopularity> getPopularities() {
        return statisticsService.getContentPopularities();
    }

    @RequestMapping(value= "/stats/popularities/since/{tstamp}", method = RequestMethod.GET)
    public List<ContentPopularity> getPopularitiesSince(@PathVariable(value = "tstamp") Long tstamp) {
        Date date = new Date(tstamp);
        return statisticsService.getContentPopularitiesSince(date);
    }

    @RequestMapping(value= "/stats/popularities/between/{from}/{to}", method = RequestMethod.GET)
    public List<ContentPopularity> getPopularitiesBetween(@PathVariable(value = "from") Long tsfrom,
                                                        @PathVariable(value = "to") Long tsto) {
        Date from = new Date(tsfrom);
        Date to = new Date(tsto);
        return statisticsService.getContentPopularitiesBetween(from, to);
    }
}
