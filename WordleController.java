package Wordlev2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WordleController {
    private WordleModel model;
    private WordleView view;

    public WordleController(WordleModel model, WordleView view) {
        this.model = model;
        this.view = view;
        initializeController();
    }

    private void initializeController() {
        view.getEnterButton().addActionListener(e -> processAttempt());
        view.getDeleteButton().addActionListener(e -> deleteCharacter());
        setupKeyboardListeners();
    }

    private void setupKeyboardListeners() {
        Component[] components = view.getKeyboardPanel().getComponents();
        for (Component component : components) {
            if (component instanceof JButton button) {
                if (!button.getText().equals("Enter") && !button.getText().equals("Del")) {
                    button.addActionListener(e -> {
                        String currentText = view.getDisplayLabel().getText();
                        if (currentText.length() <= 5) {
                            view.getDisplayLabel().setText(currentText + button.getText());
                        }
                    });
                }
            }
        }
    }

    private void deleteCharacter() {
        String currentText = view.getDisplayLabel().getText();
        if (!currentText.isEmpty()) {
            view.getDisplayLabel().setText(currentText.substring(0, currentText.length() - 1));
        }
    }

    private void processAttempt() {
        if (model.isGameOver()) {
            JOptionPane.showMessageDialog(view, "Has agotado todos tus intentos.");
            return;
        }

        String userWord = view.getDisplayLabel().getText().trim();
        if (userWord.length() != 5) {
            JOptionPane.showMessageDialog(view, "Debes ingresar una palabra de 5 letras.");
            return;
        }

        ArrayList<JPanel> gridPanels = view.getGridPanels();
        for (int i = 0; i < 5; i++) {
            char userChar = userWord.charAt(i);
            JPanel panel = gridPanels.get(model.getCurrentAttempt() * 5 + i);
            panel.setLayout(new BorderLayout());
            JLabel label = new JLabel(String.valueOf(userChar), SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 20));
            panel.add(label);

            if (model.getTargetWord().charAt(i) == userChar) {
                panel.setBackground(Color.GREEN);
            } else if (model.getTargetWord().contains(String.valueOf(userChar))) {
                panel.setBackground(Color.YELLOW);
            } else {
                panel.setBackground(Color.RED);
            }
            panel.revalidate();
            panel.repaint();
        }

        // Pintar teclas procesadas
        updateKeyboardColors(userWord);

        if (model.isCorrectWord(userWord)) {
            JOptionPane.showMessageDialog(view, "¡Felicidades! Adivinaste la palabra.");
            System.exit(0);
        }

        model.incrementAttempt();
        if (model.isGameOver()) {
            JOptionPane.showMessageDialog(view, "Lo siento, la palabra era: " + model.getTargetWord());
            System.exit(0);
        }

        view.getDisplayLabel().setText("");
    }

    private void updateKeyboardColors(String userWord) {
        Component[] components = view.getKeyboardPanel().getComponents();
        for (Component component : components) {
            if (component instanceof JButton button) {
                String buttonText = button.getText();
                if (userWord.contains(buttonText)) {
                    button.setBackground(Color.YELLOW); // Cambiar el fondo a amarillo
                }
            }
        }
    }
}
