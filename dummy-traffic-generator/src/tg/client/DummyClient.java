package tg.client;

import org.springframework.web.client.RestTemplate;
import tg.content.DummyContentInfo;

import java.util.List;

/**
 * Created by florian on 12/06/15.
 */
public class DummyClient implements Runnable {

    private int bandwidth;

    private ClientState state;

    private List<DummyContentInfo> contentRepository;

    public DummyClient(List<DummyContentInfo> contentRepository, int bandwidth) {
        this.contentRepository = contentRepository;
        this.setState(new MorningClient());
        this.bandwidth = bandwidth;
    }

    @Override
    public void run() {

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
        //TODO send request
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getForObject(
                "http://localhost:8080/location/resolve?contentName=" + contentInfo.getContentName(),
                String.class
        );

        try {
            Thread.sleep((long) (1000 * ((contentInfo.getSizeInMB() * 8) / bandwidth)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
