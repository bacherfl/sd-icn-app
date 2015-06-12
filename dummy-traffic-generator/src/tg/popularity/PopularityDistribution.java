package tg.popularity;

import tg.content.DummyContentInfo;

import java.util.List;

/**
 * Created by florian on 12/06/15.
 */
public abstract class PopularityDistribution {

    protected List<DummyContentInfo> contentItems;

    public PopularityDistribution(List<DummyContentInfo> contentItems) {
        this.contentItems = contentItems;
    }

    public abstract List<PopularityItem> generatePopularities();
}
