package com.shpp.p2p.cs.improved.mkalich.assignment12;
/*
 * Если альфа канал - ориентироваться по прозрачности.
 * Все цвета = 0
 *
 */

import java.io.IOException;
import java.util.HashMap;

public class Assignment12Part1 {

    private static final boolean inDepthSearch = true;

    private static final String FILE_PATH_BY_DEFAULT = "assets\\assignmetn12\\zero.png";

    private GraphTop[][] image;

    public static void main(String[] args) {

        Assignment12Part1 thisClass = new Assignment12Part1();
        if (thisClass.initializeImageSuccess()) {
            thisClass.markUpTheBackground();
            thisClass.printObject();
//            FindSilhouettes silhouettes = new FindSilhouettes(thisClass.image, inDepthSearch);
//            silhouettes.taggedOllObjectsOnScreen();
//            silhouettes.printObject();

        }
    }

    public boolean initializeImageSuccess() {
        try {
            image = new GraphArray().getImageArray(FILE_PATH_BY_DEFAULT);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void markUpTheBackground() {
        int[] backGrandColor = backGrandColor();
        for (int row = 0; row < image.length; row++) {
            for (int col = 0; col < image[row].length; col++) {
                if (checkColor(row, col, backGrandColor)){
                    image[row][col].isVisited = true;
                    image[row][col].belongToObject = 1;
                }
            }
        }
    }

    public int[] backGrandColor() {

        HashMap<String, Integer> colors = new HashMap<>();

        for (int col = 0; col < image.length; col++) {

            String currentPixel = key(0, col);

            if (colors.containsKey(currentPixel)) colors.put(currentPixel, colors.get(currentPixel) + 1);
            else colors.put(currentPixel, 1);
        }

        int frequentlySeenPixel = 0;
        String colorInString = "";

        for (String key : colors.keySet())
            if (colors.get(key) > frequentlySeenPixel) {
                frequentlySeenPixel = colors.get(key);
                colorInString = key;
            }

        String[] getRGB = colorInString.split(" ");
        int[] backGrandColor = new int[4];

        for (int i = 0; i < backGrandColor.length; i++) {
            backGrandColor[i] = Integer.parseInt(getRGB[i]);
        }
        System.out.println(colors);
        System.out.println(colorInString);
        return backGrandColor;
    }

    public String key(int row, int col) {
        return image[row][col].getRed() + " " +
                image[row][col].getGreen() + " " +
                image[row][col].getBlue() + " " +
                image[row][col].getAlpha();
    }

    private void printObject() {
        for (GraphTop[] graphPoint : image) {
            for (GraphTop point : graphPoint) {
                System.out.print(point.belongToObject + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private boolean checkColor(int row, int col, int[] currentColor) {

        int red = image[row][col].getRed();
        int green = image[row][col].getGreen();
        int blue = image[row][col].getBlue();

        int redOld = currentColor[0];
        int greenOld = currentColor[1];
        int blueOld = currentColor[2];

        return (Math.abs(red - redOld) <= 30 &&
                Math.abs(green - greenOld) <= 30 &&
                Math.abs(blue - blueOld) <= 30);
    }
}
