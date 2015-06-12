package tg.popularity;

import tg.content.DummyContentInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by florian on 12/06/15.
 */
public class RandomPopularityDistribution extends PopularityDistribution {

    public RandomPopularityDistribution(List<DummyContentInfo> nrContentItems) {
        super(nrContentItems);
    }

    @Override
    public List<PopularityItem> generatePopularities() {
        List<PopularityItem> popularities = new ArrayList<>();

        double remains = 1.0;

        do {
            double rnd = Math.random() * remains;
            PopularityItem popularity = new PopularityItem("content" + popularities.size(), rnd);
            popularities.add(popularity);
            remains -= rnd;
        } while (popularities.size() < contentItems.size());
        return null;
    }
}
