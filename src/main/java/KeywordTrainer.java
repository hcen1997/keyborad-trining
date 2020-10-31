import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;

public class KeywordTrainer {
    private final TrainContext trainContext = new TrainContext();
    private final byte[] inputBuffer = new byte[10];
    private final Timer timer = new Timer();

    //    field for multithreading notice
    public void abortInput() {

    }


    // field for work
    public void first() {
        System.out.println("current_set=" + Arrays.toString(TrainContext.keySet));
        System.out.println("press digit to start, alpha to exit. ");
    }

    @SneakyThrows
    public void train() {
//        info_head();
        int target = ThreadLocalRandom.current().nextInt(0, TrainContext.keySet.length);
        trainContext.setTryType(TrainContext.keySet[target]);
        System.out.println(trainContext);
//        timer start  and 1 Thread start to get input
        timer.schedule(new CountDown(), TrainContext.timeLimit_ms);
        this.wait();
        this.notify();
//        type_body()
        System.in.read(inputBuffer);
        if (inputBuffer[0] - '0' == trainContext.getTryType()) {
            System.out.println("you are a winner");
        } else {
            System.out.println("sucker~");
        }
    }
}