package tg.client;

import tg.content.DummyContentInfo;
import tg.popularity.PopularityDistribution;
import tg.popularity.PopularityItem;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

/**
 * Created by florian on 12/06/15.
 */
public abstract class ClientState {

    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

    private ScheduledFuture<?> scheduledFuture;

    protected DummyClient context;
    protected List<PopularityItem> popularities;

    public ClientState(DummyClient context) {
        this.context = context;
    }

    public abstract void nextState();

    protected void requestContent() {
        //while (true) {
            String content = selectContent();
            context.sendRequest(
                    context.getContentRepository().stream()
                            .filter(ci -> ci.getContentName().equalsIgnoreCase(content))
                            .findFirst().get()
            );

            //try {
            //    Thread.sleep((long) (Math.random() * 30 * 1000));
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}
        //}
    }

    protected String selectContent() {
        double random = Math.random();
        String contentName = "";
        double tmp = 0.0;
        for (int i = 0; i < popularities.size(); i++) {
            PopularityItem popularityItem = popularities.get(i);
            tmp += popularityItem.getPopularity();
            if (random <= tmp + popularityItem.getPopularity()) {
                contentName = popularityItem.getContentName();
                break;
            }
        }

        return contentName;
    }

    public void requestFinished() {
        requestContent();
    }
}
