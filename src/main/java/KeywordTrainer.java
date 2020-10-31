import lombok.SneakyThrows;
import pojo.TrainContext;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class KeywordTrainer {
    public static final TrainContext trainContext = new TrainContext();
    private static final byte[] inputBuffer = new byte[10];

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

    @SneakyThrows
    private static void train() {
        info_head();
//        timer start
//        type_body()
        // why show '10'? because sout('\n')=='10'
        System.in.read(inputBuffer);
        if(inputBuffer[0]-'0' == trainContext.getTryType()){
            System.out.println("you are a winner");
        }else {
            System.out.println("sucker~");
        }
    }

    private static void info_head() {
        int target = ThreadLocalRandom.current().nextInt(0, TrainContext.keySet.length);
        trainContext.setTryType(TrainContext.keySet[target]);
        System.out.println(trainContext);
    }


}
