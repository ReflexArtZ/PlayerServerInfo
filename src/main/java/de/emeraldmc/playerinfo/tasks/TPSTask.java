package de.emeraldmc.playerinfo.tasks;

public class TPSTask implements Runnable {
    private static final int MINUTE = 60;

    private TaskHistory minuteHistory = new TaskHistory(MINUTE);
    private TaskHistory quarterHistory = new TaskHistory(MINUTE*15);
    private TaskHistory halfHistory = new TaskHistory(MINUTE*30);

    private long lastCheck = System.nanoTime();

    public double getMinuteTPS() {
        return minuteHistory.round(minuteHistory.average());
    }
    public double getQuarterTPS() {
        return quarterHistory.round(quarterHistory.average());
    }
    public double getHalfTPS() {
        return halfHistory.round(halfHistory.average());
    }

    @Override
    public void run() {
        long currTime = System.nanoTime();
        long time = currTime-lastCheck;

        lastCheck = currTime;

        double tps = 1 * 20 * 1000D / (time / (1000 * 1000));
        if (tps >= 0D && tps <= 20D) {
            minuteHistory.put(tps);
            quarterHistory.put(tps);
            halfHistory.put(tps);
        }
    }
}
