public class Starter {
    public static void main(String[] args) {
        KeywordTrainer keywordTrainer = new KeywordTrainer();
        keywordTrainer.run();
        keywordTrainer.saveToDatabase();
        System.exit(0);
    }
}
