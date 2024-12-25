package test;

import javax.swing.JPanel;

import java.awt.*;
import java.util.ArrayList;

public class DiagramRenderer extends JPanel {
    private ArrayList<CircuitElement> elements = new ArrayList<>();
    private String circuitType = "Nối tiếp";
    private String sourceType = "DC";
    private boolean showDiagram = false;

    public void setElements(ArrayList<CircuitElement> elements, String circuitType, String sourceType) {
        this.elements = elements;
        this.circuitType = circuitType;
        this.sourceType = sourceType;
        showDiagram = true;
        repaint();
    }

    public void resetDiagram() {
        elements.clear();
        showDiagram = false;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g2.setFont(new Font("Arial", Font.PLAIN, 12));
        int x = 50, y = 100;

        if (circuitType.equals("Nối tiếp")) {
            g2.drawLine(x, y + 100, x, y);
            for (CircuitElement element : elements) {
                g2.drawLine(x, y, x + 70, y);
                x += 70;
                drawSeriesElement(g2, element, x, y);
                x += 100;
            }
            g2.drawLine(x, y, x + 70, y);
            x += 70;
            g2.drawLine(x, y + 100, x, y);
            drawSeriesSource(g2, sourceType, x, y + 100);
        } else {
            drawParallelSource(g2, sourceType, x, y);
            g2.drawLine(x, y, x + 100, y);
            g2.drawLine(x, y + 100, x + 100, y + 100);
            x += 100;

            for (int i = 0; i < elements.size(); i++) {
                CircuitElement element = elements.get(i);
                drawParallelElement(g2, element, x, y);
                if (i < elements.size() - 1) {
                    g2.drawLine(x, y, x + 100, y);
                    g2.drawLine(x, y + 100, x + 100, y + 100);
                }

                x += 100;
            }
        }
    }

    private void drawParallelSource(Graphics2D g2, String sourceType, int x, int y) {
        if (sourceType.equals("DC")) {
            g2.drawLine(x, y, x, y + 30);
            g2.drawOval(x - 20, y + 30, 40, 40);
            g2.drawLine(x, y + 37, x, y + 47);
            g2.drawLine(x - 5, y + 42, x + 5, y + 42);
            g2.drawLine(x, y + 53, x, y + 63);
            g2.drawLine(x, y + 70, x, y + 100);
        }
        if (sourceType.equals("AC")) {
            g2.drawLine(x, y, x, y + 30);
            g2.drawOval(x - 20, y + 30, 40, 40);
            g2.drawArc(x - 5, y + 40, 10, 10, 90, 180);
            g2.drawArc(x - 5, y + 50, 10, 10, 90, -180);
            g2.drawLine(x, y + 70, x, y + 100);
        }
    }

    private void drawParallelElement(Graphics2D g2, CircuitElement element, int x, int y) {
        switch (element.type) {
            case "R":
                g2.drawLine(x, y, x, y + 20);
                g2.drawLine(x, y + 20, x - 10, y + 30);
                g2.drawLine(x - 10, y + 30, x + 10, y + 40);
                g2.drawLine(x + 10, y + 40, x - 10, y + 50);
                g2.drawLine(x - 10, y + 50, x + 10, y + 60);
                g2.drawLine(x + 10, y + 60, x - 10, y + 70);
                g2.drawLine(x - 10, y + 70, x, y + 80);
                g2.drawLine(x, y + 80, x, y + 100);
                g2.drawString(element.name + " " + element.value + " \u03A9", x + 15, y + 30);
                break;
            case "C":
                g2.drawLine(x, y, x, y + 45);
                g2.drawLine(x - 15, y + 45, x + 15, y + 45);
                g2.drawLine(x - 15, y + 55, x + 15, y + 55);
                g2.drawLine(x, y + 55, x, y + 100);
                g2.drawString(element.name + " " + element.value + " F", x + 15, y + 30);
                break;
            case "L":
                g2.drawLine(x, y, x, y + 20);
                for (int i = 0; i < 3; i++) {
                    g2.drawArc(x - 10, y + 20 + i * 20, 20, 20, 270, 180);
                }
                g2.drawLine(x, y + 80, x, y + 100);
                g2.drawString(element.name + " " + element.value + " H", x + 15, y + 30);
                break;
        }
    }

    private void drawSeriesSource(Graphics2D g2, String sourceType, int x, int y) {
        if (sourceType.equals("DC")) {
            g2.drawLine(50, y, (x / 2), y);
            g2.drawOval((x / 2), y - 20, 40, 40);
            g2.drawLine((x / 2) + 7, y, (x / 2) + 17, y);
            g2.drawLine((x / 2) + 12, y - 5, (x / 2) + 12, y + 5);
            g2.drawLine((x / 2) + 23, y, (x / 2) + 33, y);
            g2.drawLine((x / 2) + 40, y, x, y);
        }
        if (sourceType.equals("AC")) {
            g2.drawLine(50, y, (x / 2), y);
            g2.drawOval((x / 2), y - 20, 40, 40);
            g2.drawArc((x / 2) + 10, y - 5, 10, 10, 0, 180);
            g2.drawArc((x / 2) + 20, y - 5, 10, 10, 0, -180);
            g2.drawLine((x / 2) + 40, y, x, y);
        }
    }

    private void drawSeriesElement(Graphics2D g2, CircuitElement element, int x, int y) {
        switch (element.type) {
            case "R":
                g2.drawLine(x, y, x + 20, y);
                g2.drawLine(x + 20, y, x + 30, y - 10);
                g2.drawLine(x + 30, y - 10, x + 50, y + 10);
                g2.drawLine(x + 50, y + 10, x + 70, y - 10);
                g2.drawLine(x + 70, y - 10, x + 80, y);
                g2.drawLine(x + 80, y, x + 100, y);
                g2.drawString(element.name + " " + element.value + " \u03A9", x + 30, y - 10);
                break;
            case "C":
                g2.drawLine(x, y, x + 40, y);
                g2.drawLine(x + 40, y - 15, x + 40, y + 15);
                g2.drawLine(x + 60, y - 15, x + 60, y + 15);
                g2.drawLine(x + 60, y, x + 100, y);
                g2.drawString(element.name + " " + element.value + " F", x + 45, y - 20);
                break;
            case "L":
                g2.drawLine(x, y, x + 10, y);
                for (int i = 0; i < 3; i++) {
                    g2.drawArc(x + 10 + i * 20, y - 10, 20, 20, 0, -180);
                }
                g2.drawLine(x + 70, y, x + 100, y);
                g2.drawString(element.name + " " + element.value + " H", x + 30, y - 10);
                break;
        }
    }
}
