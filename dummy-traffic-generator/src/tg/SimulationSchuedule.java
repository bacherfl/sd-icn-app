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
            waitForHours(3);
            phaseOfDay = PopularitySequence.PhaseOfDay.LUNCHTIME;
        } else if (phaseOfDay == PopularitySequence.PhaseOfDay.LUNCHTIME) {
            waitForHours(2);
            phaseOfDay = PopularitySequence.PhaseOfDay.AFTERNOON;
        } else if (phaseOfDay == PopularitySequence.PhaseOfDay.AFTERNOON) {
            waitForHours(7);
            phaseOfDay = PopularitySequence.PhaseOfDay.PRIMETIME;
        } else if(phaseOfDay == PopularitySequence.PhaseOfDay.PRIMETIME) {
            waitForHours(3);
            phaseOfDay = PopularitySequence.PhaseOfDay.NIGHT;
        } else if (phaseOfDay == PopularitySequence.PhaseOfDay.NIGHT) {
            waitForHours(9);
            phaseOfDay = PopularitySequence.PhaseOfDay.MORNING;
            dayNr++;
        }
        setChanged();
        notifyObservers();
    }

    private void waitForHours(int hours) {
        scheduler.schedule((Runnable) () -> nextPhase(), 1000 * 10 * hours, TimeUnit.MILLISECONDS);
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
