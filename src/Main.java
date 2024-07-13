import javax.swing.*;
import java.util.Random;

public class Main {
    public static void main(String[] args){
        perlinTest();
        //geographyTest();
    }

    public static void geographyTest() {
        GeographyGenerator testGenerator = new GeographyGenerator(12345);
    }

    public static void perlinTest() {
        JFrame windowFrame = new JFrame("Perlin Test");
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowFrame.setSize(1024,1024);
        Random newSeed = new Random();
        windowFrame.add(new PerlinGraph(512,512, 32768, 0.1, 2, 6, 0.4));
        windowFrame.setVisible(true);
    }
}