package tg.client;

import tg.popularity.PopularitySequence;

/**
 * Created by florian on 12/06/15.
 */
public class PrimeTimeClient extends ClientState {
    public PrimeTimeClient(DummyClient context) {
        super(context);
        popularities = PopularitySequence.getInstance()
                .getPopularitiesForPhaseOfDay(PopularitySequence.PhaseOfDay.PRIMETIME);
    }

    @Override
    public void nextState() {
        context.setState(new NightTimeClient(context));
        context.getState().requestContent();
    }
}
