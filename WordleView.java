package Wordlev2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WordleView extends JFrame {
    private ArrayList<JPanel> gridPanels;
    private JLabel displayLabel;
    private JButton enterButton;
    private JButton deleteButton;
    private JPanel keyboardPanel;

    public WordleView() {
        setTitle("Wordle Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 700);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(30, 30, 30));

        // Crear el grid 5x5
        JPanel gridPanel = new JPanel(new GridLayout(5, 5, 5, 5));
        gridPanel.setBackground(new Color(50, 50, 50));
        gridPanels = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            JPanel panel = new JPanel();
            panel.setBackground(Color.WHITE);
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            gridPanels.add(panel);
            gridPanel.add(panel);
        }

        // Panel de texto
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayLabel = new JLabel(" ");
        displayLabel.setFont(new Font("Arial", Font.BOLD, 24));
        displayLabel.setForeground(Color.WHITE);
        displayPanel.setBackground(new Color(60, 60, 60));
        displayPanel.add(displayLabel, BorderLayout.CENTER);

        // Teclado
        keyboardPanel = new JPanel(new GridLayout(4, 7, 5, 5));
        keyboardPanel.setBackground(new Color(50, 50, 50));
        setupKeyboard();

        add(gridPanel, BorderLayout.CENTER);
        add(displayPanel, BorderLayout.NORTH);
        add(keyboardPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void setupKeyboard() {
        for (char c = 'A'; c <= 'Z'; c++) {
            JButton button = new JButton(String.valueOf(c));
            button.setFont(new Font("Arial", Font.BOLD, 16));
            button.setBackground(new Color(90, 90, 90)); // Fondo gris
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false); // Quitar borde de enfoque
            button.setBorder(BorderFactory.createBevelBorder(0)); // Borde elegante
            button.setOpaque(true);
            keyboardPanel.add(button);
        }

        // Botón para borrar
        deleteButton = new JButton("Del");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 16));
        deleteButton.setBackground(new Color(120, 50, 50)); // Fondo rojo oscuro
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.setBorder(BorderFactory.createBevelBorder(0));
        deleteButton.setOpaque(true);
        keyboardPanel.add(deleteButton);

        // Botón para Enter
        enterButton = new JButton("Enter");
        enterButton.setFont(new Font("Arial", Font.BOLD, 16));
        enterButton.setBackground(new Color(50, 120, 50)); // Fondo verde oscuro
        enterButton.setForeground(Color.WHITE);
        enterButton.setFocusPainted(false);
        enterButton.setBorder(BorderFactory.createBevelBorder(0));
        enterButton.setOpaque(true);
        keyboardPanel.add(enterButton);
    }

    public void updateGridPanel(int index, char letter, Color color) {
        JPanel panel = gridPanels.get(index);
        panel.removeAll();
        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel(String.valueOf(letter), SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(label);
        panel.setBackground(color);
        panel.revalidate();
        panel.repaint();
    }

    public void resetDisplayLabel() {
        displayLabel.setText("");
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public ArrayList<JPanel> getGridPanels() {
        return gridPanels;
    }

    public JLabel getDisplayLabel() {
        return displayLabel;
    }

    public JButton getEnterButton() {
        return enterButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JPanel getKeyboardPanel() {
        return keyboardPanel;
    }
}
