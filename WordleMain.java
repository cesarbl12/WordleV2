package Wordlev2;

import javax.swing.SwingUtilities;

public class WordleMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WordleModel model = new WordleModel("Words.txt", 5);
            WordleView view = new WordleView();
            new WordleController(model, view);
        });
    }
}
