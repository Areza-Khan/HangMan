// File: HangmanGame.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HangmanGame extends JFrame {
    private final HangmanLogic logic = new HangmanLogic("TURTLE");
    private final HangmanPanel hangmanPanel = new HangmanPanel();
    private final JTextField inputField = new JTextField(1);
    private final JButton guessButton = new JButton("Guess");
    private final JButton resetButton = new JButton("Reset");
    private final JLabel wordLabel = new JLabel();
    private final JLabel missedLabel = new JLabel();

    public HangmanGame() {
        setTitle("🔥 Epic Hangman 🔥");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 550);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(30, 30, 30));

        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(new Color(50, 50, 50));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        controlPanel.add(new JLabel("Enter a letter:"));

        inputField.setFont(new Font("Arial", Font.BOLD, 20));
        controlPanel.add(inputField);

        guessButton.setFont(new Font("Arial", Font.BOLD, 18));
        guessButton.setBackground(new Color(70, 130, 180));
        guessButton.setForeground(Color.WHITE);
        controlPanel.add(guessButton);

        resetButton.setFont(new Font("Arial", Font.BOLD, 18));
        resetButton.setBackground(new Color(220, 20, 60));
        resetButton.setForeground(Color.WHITE);
        controlPanel.add(resetButton);

        wordLabel.setFont(new Font("Monospaced", Font.BOLD, 36));
        wordLabel.setForeground(new Color(255, 215, 0));
        wordLabel.setHorizontalAlignment(SwingConstants.CENTER);

        missedLabel.setFont(new Font("Monospaced", Font.PLAIN, 20));
        missedLabel.setForeground(Color.WHITE);
        missedLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setBackground(new Color(30, 30, 30));
        infoPanel.add(wordLabel);
        infoPanel.add(missedLabel);

        add(hangmanPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
        add(infoPanel, BorderLayout.NORTH);

        guessButton.addActionListener(new GuessHandler());
        resetButton.addActionListener(e -> resetGame());

        updateUI();
        setVisible(true);
    }

    private class GuessHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = inputField.getText().trim().toUpperCase();
            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                JOptionPane.showMessageDialog(HangmanGame.this, "Please enter a single letter.");
                inputField.setText("");
                return;
            }

            char guess = input.charAt(0);
            inputField.setText("");

            logic.guess(guess);
            updateUI();

            if (logic.isGameWon()) {
                JOptionPane.showMessageDialog(HangmanGame.this, "🎉 You won! Word was: " + logic.getTargetWord());
                guessButton.setEnabled(false);
            } else if (logic.isGameOver()) {
                JOptionPane.showMessageDialog(HangmanGame.this, "💀 You lost! Word was: " + logic.getTargetWord());
                guessButton.setEnabled(false);
            }
        }
    }

    private void updateUI() {
        wordLabel.setText(logic.getDisplayWord());
        missedLabel.setText("Missed: " + logic.getWrongGuesses());
        hangmanPanel.setWrongGuessCount(logic.getWrongGuessCount());
    }

    private void resetGame() {
        logic.reset();
        guessButton.setEnabled(true);
        updateUI();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HangmanGame::new);
    }
}



