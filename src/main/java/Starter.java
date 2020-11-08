public class Starter {
    public static void main(String[] args) {
        KeywordTrainer keywordTrainer = new KeywordTrainer();
        keywordTrainer.first();
        for (int i = 0; i < KeywordTrainer.defaultTrainTime; i++) {
            keywordTrainer.train();
        }
        keywordTrainer.end();
    }
}
