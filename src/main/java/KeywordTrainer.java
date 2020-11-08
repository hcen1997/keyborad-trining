import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.concurrent.*;

public class KeywordTrainer {
    public static final int[] keySet = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
    public static final int timeOutLimit_ms = 3000;

    public static final int defaultTrainTime = 10;

    private final TrainContext trainContext = new TrainContext();

    private final ExecutorService es = Executors.newSingleThreadExecutor();
    //    field for multithreading notice


    // field for work
    public void first() {
        System.out.println("current_set=" + Arrays.toString(keySet));
        System.out.println("press digit to start, alpha to exit. ");
    }

    @SneakyThrows
    public void train() {
//        info_head();
        int target = ThreadLocalRandom.current().nextInt(0, keySet.length);
        trainContext.setTryType(keySet[target]);
        System.out.println(trainContext);

//        timer start  and 1 Thread start to get input
        ConsoleInputReadTask inputTask = new ConsoleInputReadTask();
        Future<String> stringFuture = es.submit(inputTask);
        int result = getResult(stringFuture,inputTask);
//        type result
        if (result == trainContext.getTryType()) {
            System.out.println("you are a winner");
        } else {
            System.out.println("sucker~");
        }
    }

    private int getResult(Future<String> stringFuture, ConsoleInputReadTask inputTask) throws InterruptedException, ExecutionException {
        try {
            // there is a bug, and i cannot solve it
            // if i use buffered input, it can read after last training is timeout
            // but if i  use scanner, it just cannot recover.
            // bug fix: remove previous task
            // how to not use while true?
            // even people cannot feel while true: sleep(200ms)
            String s = stringFuture.get(timeOutLimit_ms, TimeUnit.MILLISECONDS);
            System.out.println("s = " + s);
            return Integer.parseInt(s);
        } catch (TimeoutException e) {
            // remove the task
            inputTask.cancel();
            return -1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void end() {
        System.out.println("total = " + trainContext.getTotal());
        int score = trainContext.getScore();
        System.out.println("score = " + score);
        System.out.println("goodbye~");
    }
}