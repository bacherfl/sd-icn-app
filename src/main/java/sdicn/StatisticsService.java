package sdicn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdicn.model.ContentPopularity;
import sdicn.model.RequestInfo;

import java.util.*;

/**
 * Created by florian on 08.05.15.
 */
@Service
public class StatisticsService {

    @Autowired
    RequestInfoRepository requestInfoRepository;

    List<RequestInfo> requests = new ArrayList<>();

    public void storeRequestInfo(RequestInfo info) {
        requestInfoRepository.save(info);
    }

    public List<RequestInfo> getAllRequests() {
        return requestInfoRepository.findAllByOrderByDateAsc();
    }

    public List<RequestInfo> getRequestsSince(Date since) {
        return requestInfoRepository.findAllByDateGreaterThanOrderByDateAsc(since);
    }

    public List<RequestInfo> getRequestsBetween(Date from, Date to) {
        return requestInfoRepository.findAllByDateBetweenOrderByDateAsc(from, to);
    }

    public List<ContentPopularity> getContentPopularities() {

        List<RequestInfo> requests = getAllRequests();

        List<ContentPopularity> popularities = computePopularities(requests);

        return popularities;
    }

    public List<ContentPopularity> getContentPopularitiesSince(Date since) {
        List<RequestInfo> requests = getRequestsSince(since);

        List<ContentPopularity> popularities = computePopularities(requests);

        return popularities;
    }

    public List<ContentPopularity> getContentPopularitiesBetween(Date from, Date to) {
        List<RequestInfo> requests = getRequestsBetween(from, to);
        List<ContentPopularity> popularities = computePopularities(requests);
        return popularities;
    }

    public Map<Long, List<ContentPopularity>> getContentPopularitiesInPeriods(Date since, Long intervalInSeconds) {
        Map<Long, List<ContentPopularity>> map = new HashMap<>();
        Long idx = 0L;
        Date tmpTo;
        Date currentDate = new Date();
        do {
            tmpTo = new Date(since.getTime() + intervalInSeconds * 1000);
            List<RequestInfo> requests = requestInfoRepository.findAllByDateBetweenOrderByDateAsc(since, tmpTo);
            List<ContentPopularity> popularities = computePopularities(requests);
            map.put(idx++, popularities);
            since = tmpTo;
        } while (tmpTo.before(currentDate));
        return map;
    }

    private List<ContentPopularity> computePopularities(List<RequestInfo> requests) {
        List<ContentPopularity> popularities = new ArrayList<>();
        final int[] total = {0};
        requests.stream().forEach(request -> {
            Optional<ContentPopularity> popularity = popularities.stream()
                    .filter(p -> p.getContentName().equals(request.getName()))
                    .findFirst();

            if (popularity.isPresent()) {
                popularity.get().incrementCount();
            } else {
                ContentPopularity p = new ContentPopularity();
                p.setContentName(request.getName());
                p.setCount(1);
                popularities.add(p);
            }
            total[0]++;
        });

        popularities.forEach(p -> p.setPopularity((p.getCount() + 0.0) / total[0]));

        return popularities;
    }

}
