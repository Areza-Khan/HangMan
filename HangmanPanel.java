// File: HangmanPanel.java
import javax.swing.*;
import java.awt.*;

public class HangmanPanel extends JPanel {
    private int wrongGuessCount;

    public void setWrongGuessCount(int count) {
        this.wrongGuessCount = count;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));

        // Base
        g2.drawLine(50, 400, 200, 400);
        // Pole
        g2.drawLine(125, 400, 125, 50);
        g2.drawLine(125, 50, 275, 50);
        g2.drawLine(275, 50, 275, 100);

        if (wrongGuessCount >= 1) g2.drawOval(250, 100, 50, 50); // Head
        if (wrongGuessCount >= 2) g2.drawLine(275, 150, 275, 250); // Body
        if (wrongGuessCount >= 3) g2.drawLine(275, 170, 240, 210); // Left Arm
        if (wrongGuessCount >= 4) g2.drawLine(275, 170, 310, 210); // Right Arm
        if (wrongGuessCount >= 5) g2.drawLine(275, 250, 240, 300); // Left Leg
        if (wrongGuessCount >= 6) g2.drawLine(275, 250, 310, 300); // Right Leg
    }
}

