package sdicn;

import org.springframework.stereotype.Service;
import sdicn.model.RequestInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by florian on 08.05.15.
 */
@Service
public class StatisticsService {

    List<RequestInfo> requests = new ArrayList<>();

    public void storeRequestInfo(RequestInfo info) {
        requests.add(info);
    }

    public List<RequestInfo> getAllRequests() {
        return requests;
    }
}
