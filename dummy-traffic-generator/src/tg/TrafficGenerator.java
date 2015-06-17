package tg;

import tg.client.DummyClient;
import tg.content.DummyContentGenerator;
import tg.content.DummyContentInfo;
import tg.popularity.PopularityDistribution;
import tg.popularity.PopularitySequence;
import tg.popularity.ZipfPopularityDistribution;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by florian on 11/06/15.
 */
public class TrafficGenerator {

    public static void main(String[] args) {
        List<DummyContentInfo> dummyContentInfoList = new DummyContentGenerator().generateDummyContent(100);

        PopularitySequence.getInstance().initialize(dummyContentInfoList);

        SimulationSchuedule schedule = new SimulationSchuedule(2);

        List<Thread> clientThreads = new ArrayList<>();

        IntStream.range(0, 30).forEach(index -> {
            DummyClient dummyClient = new DummyClient(dummyContentInfoList, 2000, "client" + index);
            schedule.addObserver(dummyClient);
            clientThreads.add(new Thread(dummyClient));
        });

        Thread scheduleThread = new Thread(schedule);
        scheduleThread.start();


        clientThreads.parallelStream().forEach(client -> client.start());


        clientThreads.parallelStream().forEach(client -> {
            try {
                client.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

}
