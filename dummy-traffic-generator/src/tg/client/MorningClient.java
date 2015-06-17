package tg.client;

import tg.popularity.PopularityItem;
import tg.popularity.PopularitySequence;

import java.util.List;

/**
 * Created by florian on 12/06/15.
 */
public class MorningClient extends ClientState {

    public MorningClient(DummyClient context) {
        super(context);
        popularities = PopularitySequence.getInstance()
                .getPopularitiesForPhaseOfDay(PopularitySequence.PhaseOfDay.MORNING);
    }

    @Override
    public void nextState() {
        context.setState(new LunchTimeClient(context));
        context.getState().requestContent();
    }
}
