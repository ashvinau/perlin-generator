import javax.swing.*;
import java.awt.*;

public class PerlinGraph extends JPanel {
    private final Integer[][] perlinMatrix;

    PerlinGraph(int width, int height, int randSeed, double depth, double scale, int octaves, double persistence) {
        PerlinNoise graphNoise = new PerlinNoise(4,256,2, randSeed);
        perlinMatrix = new Integer[width][height];

        for (int y = 0; y < width; y++) {
            for (int x = 0; x < height; x++) {
                double px = intToDouble(x);
                double py = intToDouble(y);

                //double perlinVal = graphNoise.perlinHalf(px * scale, py * scale, depth * scale); // Single layer of perlin noise
                double perlinVal = graphNoise.octavePerlinHalf(px * scale, py * scale, depth * scale, octaves, persistence);
                int matrixVal = doubleToInt(perlinVal);

                // Clamps

                if (matrixVal > 0 && matrixVal < 120)
                    matrixVal = 255;
                else if (matrixVal >= 120 && matrixVal < 150)
                    matrixVal = 128;
                else if (matrixVal > 190 && matrixVal < 255)
                    matrixVal = 200;
                //else if (matrixVal > 127 && matrixVal < 137)
                //    matrixVal = 255;
                else
                    matrixVal = 0;

                perlinMatrix[x][y] = matrixVal;
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

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int pWidth = perlinMatrix[0].length;
        int pHeight = perlinMatrix.length;
        int wWidth = this.getWidth();
        int wHeight = this.getHeight();

        System.out.println("Window - width: " + wWidth + " height: " + wHeight);

        for (int yOffset = 0; yOffset < wHeight; yOffset += pHeight) {
            for (int xOffset = 0; xOffset < wWidth; xOffset += pWidth) {

                for (int y = 0; y < pHeight; y++) {
                    for (int x = 0; x < pWidth; x++) {
                        int curColor = perlinMatrix[x][y];
                        g2d.setColor(new Color(curColor,curColor,curColor));
                        g2d.drawLine(x+xOffset,y+yOffset,x+xOffset,y+yOffset);
                    }
                }

            }
        }

    }
}

