import pojo.TrainContext;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class KeywordTrainer {
    public static final TrainContext trainContext = new TrainContext();
    public static void main(String[] args) {
        first();
        for (int i = 0; i < TrainContext.defaultTrainTime; i++) {
            train();
        }
    }

    private static void first() {
        System.out.println("current_set=" + Arrays.toString(TrainContext.keySet));
        System.out.println("press digit to start, alpha to exit. ");
    }

    private static void train() {
        info_head()
        t = Process(target = check)
        t.start()
        type_body()
        clear(t)
        print(current_typing)
    }

    private static void info_head() {
        int target = ThreadLocalRandom.current().nextInt(0, TrainContext.keySet.length);
        trainContext.setTryType(TrainContext.keySet[target]);
        System.out.println(trainContext);
    }


}
