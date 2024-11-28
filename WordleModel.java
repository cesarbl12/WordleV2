package Wordlev2;

import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordleModel {
    private String targetWord;
    private int maxAttempts;
    private int currentAttempt;
    
    public WordleModel(String wordFile, int maxAttempts) {
        this.maxAttempts = maxAttempts;
        this.currentAttempt = 0;
        ArrayList<String> words = loadWordsFromFile(wordFile);
        if (!words.isEmpty()) {
            this.targetWord = getRandomWord(words);
        } else {
            throw new RuntimeException("El archivo Words.txt está vacío o no se encuentra.");
        }
    }

    private ArrayList<String> loadWordsFromFile(String fileName) {
        ArrayList<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() == 5) { // Solo palabras de 5 caracteres
                    words.add(line.toUpperCase());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    private String getRandomWord(ArrayList<String> words) {
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }

    public String getTargetWord() {
        return targetWord;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public int getCurrentAttempt() {
        return currentAttempt;
    }

    public void incrementAttempt() {
        currentAttempt++;
    }

    public boolean isCorrectWord(String word) {
        return word.equalsIgnoreCase(targetWord);
    }

    public boolean isGameOver() {
        return currentAttempt >= maxAttempts;
    }
}
