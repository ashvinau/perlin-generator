import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args){
        perlinTest();
    }

    public static void perlinTest() {
        JFrame windowFrame = new JFrame("Perlin Test");
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowFrame.setSize(1024,1024);
        Random newSeed = new Random();

        ArrayList<ArrayList<Integer>> testRanges = new ArrayList<>();
        // Each range consists of a perlin value range for the first two values and four
        // RGBA8 values describing the desired mapped color
        testRanges.add(new ArrayList<>(Arrays.asList(0,31,0,0,0,255)));
        testRanges.add(new ArrayList<>(Arrays.asList(32,63,255,0,255,255)));

        testRanges.add(new ArrayList<>(Arrays.asList(64,95,255,0,0,255)));
        testRanges.add(new ArrayList<>(Arrays.asList(96,127,0,255,0,255)));
        testRanges.add(new ArrayList<>(Arrays.asList(128,159,0,0,255,255)));
        testRanges.add(new ArrayList<>(Arrays.asList(160,191,255,255,0,255)));

        testRanges.add(new ArrayList<>(Arrays.asList(192,223,0,255,255,255)));
        testRanges.add(new ArrayList<>(Arrays.asList(224,255,255,255,255,255)));

        windowFrame.add(new PerlinGraph(512,512, 12346, 0.1, 2, 6, 0.4, testRanges));
        windowFrame.setVisible(true);
    }
}