package tg.client;

import org.springframework.web.client.RestTemplate;
import tg.SimulationSchuedule;
import tg.content.DummyContentInfo;
import tg.popularity.PopularitySequence;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by florian on 12/06/15.
 */
public class DummyClient implements Runnable, Observer {

    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

    private ScheduledFuture<?> scheduledFuture;

    private int bandwidth;

    private ClientState state;

    private List<DummyContentInfo> contentRepository;
    private String id;

    public DummyClient(List<DummyContentInfo> contentRepository, int bandwidth, String id) {
        this.contentRepository = contentRepository;
        this.bandwidth = bandwidth;
        this.id = id;
        this.setState(new MorningClient(this));
    }

    @Override
    public void run() {
        state.requestContent();
    }

    public List<DummyContentInfo> getContentRepository() {
        return contentRepository;
    }

    public ClientState getState() {
        return state;
    }

    public void setState(ClientState state) {
        this.state = state;
    }

    public void sendRequest(DummyContentInfo contentInfo) {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getForObject(
                "http://localhost:8080/location/resolve?contentName=" + contentInfo.getContentName() + "&client=" + id,
                String.class
        );

        scheduledFuture = scheduler.schedule((Runnable) () -> requestFinished(),
                (long) (1000 * ((contentInfo.getSizeInMB() * 8) / bandwidth)),
                TimeUnit.MILLISECONDS);
        /*
        try {
            Thread.sleep((long) (1000 * ((contentInfo.getSizeInMB() * 8) / bandwidth)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
    }

    public void requestFinished() {
        state.requestFinished();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof SimulationSchuedule) {
            scheduledFuture.cancel(true);
            state.nextState();
        }
    }
}
