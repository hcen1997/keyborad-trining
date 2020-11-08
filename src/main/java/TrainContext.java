import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

class TrainContext {

    // level : 0 -> 99
// time : 3000 -> 100
    private static final int totalLevel = 10;

    TrainContext() {
        total = 0;
        tryType = -1;
        score = 0;
        level=0;
    }
    public static final int[] keySet = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
    private int tryType;
    private int total;
    private int score;
    private int level;
    private final static int timeOutLimit_ms = 3000;
    private final static int timeoutMin = 100;

    private void setTotal(int total) {
        this.total = total;
    }

    private void setScore(int score) {
        this.score = score;
    }
    private void setLevel(int score) {
        this.score = score;
    }

    public void type(boolean right) {
        total++;
        score += right ? 1 : 0;
    }

    public void increaseLevel() {
        level++;
    }

    public void printKeySet() {
        System.out.println("current_set=" + Arrays.toString(keySet));
    }

    public int generateNextType() {
        int target = ThreadLocalRandom.current().nextInt(0, keySet.length);
        tryType = keySet[target];
        return tryType;
    }

    @Override
    public String toString() {
        return "TrainContext{" +
                "tryType=" + tryType +
                ", total=" + total +
                ", score=" + score +
                ", level=" + level +
                '}';
    }
    public String printTarget(){
        return "Try to type: "+ tryType;
    }

    public TimeUnit getTimeUnit() {
        return TimeUnit.MILLISECONDS;
    }
    private int getLevelTimeout(int level) {
        int levelUnit = (timeOutLimit_ms - timeoutMin) / totalLevel;
        return timeOutLimit_ms - levelUnit * level;
    }
    public long getWaitTime() {
        int levelTimeout = getLevelTimeout(level);
//        System.out.println("levelTimeout = " + levelTimeout);
        return levelTimeout;
    }
}
