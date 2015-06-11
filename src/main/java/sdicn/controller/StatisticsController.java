package sdicn.controller;

import org.jsondoc.core.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sdicn.StatisticsService;
import sdicn.model.ContentPopularity;
import sdicn.model.RequestInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by florian on 08.05.15.
 */
@Api(name = "Statistics services", description = "Methods for managing statistics")
@RestController
public class StatisticsController {
    
    @Autowired
    StatisticsService statisticsService;

    @ApiMethod
    @RequestMapping(value = "/stats/requests", method = RequestMethod.GET)
    public @ApiResponseObject @ResponseBody List<RequestInfo> getRequests() {
        return statisticsService.getAllRequests();
    }

    @ApiMethod
    @RequestMapping(value= "/stats/popularities/all", method = RequestMethod.GET)
    public @ApiResponseObject @ResponseBody  List<ContentPopularity> getPopularities() {
        return statisticsService.getContentPopularities();
    }

    @ApiMethod
    @RequestMapping(value= "/stats/popularities/since/{tstamp}", method = RequestMethod.GET)
    public @ApiResponseObject @ResponseBody List<ContentPopularity> getPopularitiesSince(
            @ApiPathParam @PathVariable(value = "tstamp") Long tstamp) {
        Date date = new Date(tstamp);
        return statisticsService.getContentPopularitiesSince(date);
    }

    @ApiMethod
    @RequestMapping(value= "/stats/popularities/between/{from}/{to}", method = RequestMethod.GET)
    public @ApiResponseObject @ResponseBody List<ContentPopularity> getPopularitiesBetween(
            @ApiPathParam @PathVariable(value = "from") Long tsfrom,
            @ApiPathParam @PathVariable(value = "to") Long tsto) {
        Date from = new Date(tsfrom);
        Date to = new Date(tsto);
        return statisticsService.getContentPopularitiesBetween(from, to);
    }

    @ApiMethod
    @RequestMapping(value = "/stats/popularities/periods/{since}/{interval}", method = RequestMethod.GET)
    public @ApiResponseObject @ResponseBody Map<Long, List<ContentPopularity>> getContentPopularitiesInPeriods(
            @ApiPathParam @PathVariable(value = "since") Long tsFrom,
            @ApiPathParam @PathVariable(value = "interval") Long interval) {
        Date from = new Date(tsFrom);
        return statisticsService.getContentPopularitiesInPeriods(from, interval);
    }

}
