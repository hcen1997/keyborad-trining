import java.util.TimerTask;

public class CountDown extends TimerTask {
    @Override
    public void run() {
        System.out.println("you lose!");
    }
}
