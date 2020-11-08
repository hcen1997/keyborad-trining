import java.io.*;
import java.util.concurrent.Callable;

public class ConsoleInputReadTask implements Callable<String> {
    private boolean cancel = false;
    public void cancel(){
        this.cancel = true;
    }
    ConsoleInputReadTask(){
    }
    public String call() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(System.in));
        String input = "";
        System.out.print("======> ");
        do {
            try {
                while (!bufferedReader.ready()) {
                    if(cancel){
                        return "-1";
                    }
                    Thread.sleep(200);
                }
                input = bufferedReader.readLine();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (input.equals(""));
        return input;
    }
}