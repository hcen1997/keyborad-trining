import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.*;

public class KeywordTrainer {


    public static final int defaultTrainTime = 10;

    private final TrainContext trainContext = new TrainContext();

    private final ExecutorService es = Executors.newSingleThreadExecutor();


    public void first() {
        trainContext.printKeySet();
        System.out.println("press digit to start, alpha to exit. ");
    }

    @SneakyThrows
    public void train() {
//        info_head();
        int target = trainContext.generateNextType();
        System.out.println(trainContext.printTarget());

//        timer start  and 1 Thread start to get input
        ConsoleInputReadTask inputTask = new ConsoleInputReadTask();
        Future<String> stringFuture = es.submit(inputTask);
        int result = getResult(stringFuture, inputTask);
//        type result
        if (result == target) {
            trainContext.type(true);
            System.out.println("you are a winner");
        } else {
            trainContext.type(false);
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
            // i dont know how to ,
            // bug sleep and cancel flag work just fine
            String s = stringFuture.get(trainContext.getWaitTime(), trainContext.getTimeUnit());
//            System.out.println("s = " + s);
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
        System.out.println(trainContext.toString());
        System.out.println("goodbye~");
    }

    public void run() {
        first();
        do {
            for (int i = 0; i < KeywordTrainer.defaultTrainTime; i++) {
                train();
            }
        } while (isLevelUp());
        end();
    }


    private boolean isLevelUp() {
        System.out.print("level up?(y,n) ");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        boolean y = s.equalsIgnoreCase("y");
        if (y) {
            trainContext.increaseLevel();
        }
        return y;
    }
}