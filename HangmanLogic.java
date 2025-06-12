// File: HangmanLogic.java
import java.util.HashSet;
import java.util.Set;

public class HangmanLogic {
    private final String targetWord;
    private final Set<Character> correctGuesses;
    private final Set<Character> wrongGuesses;
    private final int maxAttempts = 6;

    public HangmanLogic(String word) {
        this.targetWord = word.toUpperCase();
        this.correctGuesses = new HashSet<>();
        this.wrongGuesses = new HashSet<>();
    }

    public boolean guess(char letter) {
        letter = Character.toUpperCase(letter);
        if (targetWord.indexOf(letter) >= 0) {
            correctGuesses.add(letter);
            return true;
        } else {
            wrongGuesses.add(letter);
            return false;
        }
    }

    public String getDisplayWord() {
        StringBuilder sb = new StringBuilder();
        for (char c : targetWord.toCharArray()) {
            if (correctGuesses.contains(c)) {
                sb.append(c).append(" ");
            } else {
                sb.append("_ ");
            }
        }
        return sb.toString().trim();
    }

    public Set<Character> getWrongGuesses() {
        return wrongGuesses;
    }

    public boolean isGameWon() {
        for (char c : targetWord.toCharArray()) {
            if (!correctGuesses.contains(c)) {
                return false;
            }
        }
        return true;
    }

    public boolean isGameOver() {
        return wrongGuesses.size() >= maxAttempts;
    }

    public int getWrongGuessCount() {
        return wrongGuesses.size();
    }

    public void reset() {
        correctGuesses.clear();
        wrongGuesses.clear();
    }

    public String getTargetWord() {
        return targetWord;
    }
}
