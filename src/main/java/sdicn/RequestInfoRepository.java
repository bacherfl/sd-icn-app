package sdicn;

import org.springframework.data.repository.CrudRepository;
import sdicn.model.RequestInfo;

import java.util.Date;
import java.util.List;

/**
 * Created by florian on 20.05.15.
 */
public interface RequestInfoRepository extends CrudRepository<RequestInfo, Long> {

    List<RequestInfo> findAllByOrderByDateAsc();

    List<RequestInfo> findAllByDateGreaterThanOrderByDateAsc(Date since);

    List<RequestInfo> findAllByDateBetweenOrderByDateAsc(Date from, Date to);
}
