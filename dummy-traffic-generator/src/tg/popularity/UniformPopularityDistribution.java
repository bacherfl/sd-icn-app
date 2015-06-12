package tg.popularity;

import tg.content.DummyContentInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by florian on 12/06/15.
 */
public class UniformPopularityDistribution extends PopularityDistribution {

    public UniformPopularityDistribution(List<DummyContentInfo> nrContentItems) {
        super(nrContentItems);
    }

    @Override
    public List<PopularityItem> generatePopularities() {
        List<PopularityItem> popularities = new ArrayList<>();
        for (int i = 0; i < contentItems.size(); i++) {
            PopularityItem popularity = new PopularityItem("content" + i, 1.0 / contentItems.size());
            popularities.add(popularity);
        }

        return popularities;
    }
}
