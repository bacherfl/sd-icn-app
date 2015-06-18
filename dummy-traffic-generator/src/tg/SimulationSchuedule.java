package tg;

import tg.popularity.PopularitySequence;

import java.util.Observable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by florian on 12/06/15.
 */
public class SimulationSchuedule extends Observable implements Runnable {

    public static final double SPEEDUP_FACTOR = 180.0;

    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

    private PopularitySequence.PhaseOfDay phaseOfDay;
    int dayNr;
    int maxDays;

    public SimulationSchuedule(int maxDays) {
        this.maxDays = maxDays;
    }

    @Override
    public void run() {
        initialize();
        do {
            nextPhase();
        } while (dayNr <= maxDays);
    }

    private void initialize() {
        dayNr = 0;
        phaseOfDay = PopularitySequence.PhaseOfDay.MORNING;
    }

    private void nextPhase() {
        if (phaseOfDay == PopularitySequence.PhaseOfDay.MORNING) {
            scheduler.schedule((Runnable) () -> {
                        phaseOfDay = PopularitySequence.PhaseOfDay.LUNCHTIME;
                        nextPhase();
                    },
                    (long) (1000 * 3600 * 3 / SPEEDUP_FACTOR),
                    TimeUnit.MILLISECONDS);
            //waitForHours(3);
            //phaseOfDay = PopularitySequence.PhaseOfDay.LUNCHTIME;
        } else if (phaseOfDay == PopularitySequence.PhaseOfDay.LUNCHTIME) {
            scheduler.schedule((Runnable) () -> {
                        phaseOfDay = PopularitySequence.PhaseOfDay.AFTERNOON;
                        nextPhase();
                    },
                    (long) (1000 * 3600 * 2 / SPEEDUP_FACTOR),
                    TimeUnit.MILLISECONDS);
        } else if (phaseOfDay == PopularitySequence.PhaseOfDay.AFTERNOON) {
            scheduler.schedule((Runnable) () -> {
                        phaseOfDay = PopularitySequence.PhaseOfDay.PRIMETIME;
                        nextPhase();
                    },
                    (long) (1000 * 3600 * 7 / SPEEDUP_FACTOR),
                    TimeUnit.MILLISECONDS);
        } else if(phaseOfDay == PopularitySequence.PhaseOfDay.PRIMETIME) {
            scheduler.schedule((Runnable) () -> {
                        phaseOfDay = PopularitySequence.PhaseOfDay.NIGHT;
                        nextPhase();
                    },
                    (long) (1000 * 3600 * 3 / SPEEDUP_FACTOR),
                    TimeUnit.MILLISECONDS);
        } else if (phaseOfDay == PopularitySequence.PhaseOfDay.NIGHT) {
            scheduler.schedule((Runnable) () -> {
                        phaseOfDay = PopularitySequence.PhaseOfDay.MORNING;
                        nextPhase();
                    },
                    (long) (1000 * 3600 * 9 / SPEEDUP_FACTOR),
                    TimeUnit.MILLISECONDS);
            dayNr++;
        }
        setChanged();
        notifyObservers();
    }

    private void waitForHours(int hours) {
        scheduler.schedule((Runnable) () -> nextPhase(),
                (long) (1000 * 3600 * hours / SPEEDUP_FACTOR),
                TimeUnit.MILLISECONDS);
        /*
        try {
            Thread.sleep(1000 * 10 * hours);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
    }

    public PopularitySequence.PhaseOfDay getPhaseOfDay() {
        return phaseOfDay;
    }
}
