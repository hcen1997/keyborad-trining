package pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

public @Data
class TrainContext {
    public static final int[] keySet = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
    public static final double timeLimit = 1.5;
    public static final int defaultTrainTime = 10;
    private int tryType;
    private int total;
    private int score;
}
