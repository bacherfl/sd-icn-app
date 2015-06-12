package tg;

import tg.content.DummyContentGenerator;
import tg.content.DummyContentInfo;
import tg.popularity.PopularityDistribution;
import tg.popularity.ZipfPopularityDistribution;

import java.util.List;

/**
 * Created by florian on 11/06/15.
 */
public class TrafficGenerator {

    public static void main(String[] args) {
        List<DummyContentInfo> dummyContentInfoList = new DummyContentGenerator().generateDummyContent(100);

        PopularityDistribution d1 = new ZipfPopularityDistribution(dummyContentInfoList, 1.8);

        d1.generatePopularities().forEach(popularityItem -> System.out.println("requesting " +
                popularityItem.getContentName() +
                ", popularity = "
                + popularityItem.getPopularity())
        );
    }

}
