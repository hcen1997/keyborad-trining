import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
class TrainContext {
    TrainContext() {
        total = 0;
        tryType = -1;
        score = 0;
    }

    @Getter
    @Setter
    private int tryType;
    @Getter
    private int total;
    @Getter
    private int score;

    public void type(boolean right) {
        total++;
        score += right ? 1 : 0;
    }
}
