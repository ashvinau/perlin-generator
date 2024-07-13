import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PerlinGraph extends JPanel {
    private final Integer[][] perlinMatrix;
    private final Color[][] graphMatrix;

    PerlinGraph(int width, int height, int randSeed, double depth, double scale, int octaves, double persistence,
                ArrayList<ArrayList<Integer>> colorRanges) {
        PerlinNoise graphNoise = new PerlinNoise(4,256,2, randSeed);
        perlinMatrix = new Integer[width][height];
        graphMatrix = new Color[width][height];

        for (int y = 0; y < width; y++) {
            for (int x = 0; x < height; x++) {
                double px = intToDouble(x);
                double py = intToDouble(y);

                //double perlinVal = graphNoise.perlinHalf(px * scale, py * scale, depth * scale); // Single layer of perlin noise
                double perlinVal = graphNoise.octavePerlinHalf(px * scale, py * scale, depth * scale, octaves, persistence);
                int matrixVal = doubleToInt(perlinVal);
                perlinMatrix[x][y] = matrixVal;

                // Clamps

                for (ArrayList<Integer> curRange : colorRanges) {
                    if (matrixVal > curRange.get(0) && matrixVal < curRange.get(1)) {
                        graphMatrix[x][y] = new Color(curRange.get(2),
                                curRange.get(3),
                                curRange.get(4),
                                curRange.get(5));
                    }
                }
            }
        }
    }

    // Converts an integer from 0-255 to a float from 0 to 1
    public static double intToDouble(int value) {
        return (double) value / 255;
    }

    // Converts a float from 0 to 1 to an integer from 0-255
    public static int doubleToInt(double value) {
        return (int) Math.round(value * 255);
    }

    public Integer[][] getPerlinMatrix() {
        return perlinMatrix;
    }

    public Color[][] getGraphMatrix() {
        return graphMatrix;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int pWidth = graphMatrix[0].length;
        int pHeight = graphMatrix.length;
        int wWidth = this.getWidth();
        int wHeight = this.getHeight();

        System.out.println("Window - width: " + wWidth + " height: " + wHeight);

        for (int yOffset = 0; yOffset < wHeight; yOffset += pHeight) {
            for (int xOffset = 0; xOffset < wWidth; xOffset += pWidth) {

                for (int y = 0; y < pHeight; y++) {
                    for (int x = 0; x < pWidth; x++) {
                        Color curColor = graphMatrix[x][y];
                        g2d.setColor(curColor);
                        g2d.drawLine(x+xOffset,y+yOffset,x+xOffset,y+yOffset);
                    }
                }
            }
        }
    }
}

