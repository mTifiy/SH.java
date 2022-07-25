package com.shpp.p2p.cs.improved.mkalich.assignment12;

import java.io.IOException;
import java.util.HashMap;

/**
 * This class counts the number of silhouettes in a picture.
 * First, an array is created with the size of the length and width of the image.
 * This array includes the color of each pixel in the picture, information about the visit to the pixel
 * when bypassing the graph and marking the background, and to which object (background or silhouette) it belongs.
 * <p>
 * Then comes the background markup in the array. The background color is the most commonly used color.
 * at the top of the figure.
 * <p>
 * Next, each silhouette is labeled with a new number by breadth-first search or depth-recursion.
 * After that, the number of silhouettes is displayed on the screen.
 */
public class Assignment12Part1 implements ConstantsControl {
    /**
     * This variable stores an array of pixel colors, information about the pixel's ownership
     * to the object (silhouette or background) and whether this pixel has been visited.
     */
    private GraphTop[][] image;

    /**
     * This method is the entry point to the program, and runs all the necessary methods and calculations
     *
     * @param args image path
     */
    public static void main(String[] args) {
        /* create a class object to use the methods of this class */
        Assignment12Part1 thisClass = new Assignment12Part1();
        /* If nothing comes to the input of the main arguments, the path to the file is assigned by default */
        String filePath = args.length == 1 ? args[0] : FILE_PATH_BY_DEFAULT;
        /* If the path to the image is correct, the image array is initialized and the program continues to work */
        if (thisClass.initializeImageSuccess(filePath)) {
            /* Mark the background of the image in the array */
            thisClass.markUpTheBackground();
            /* Create a class object that counts the number of silhouettes by passing it to the class constructor
             * image array with already marked background */
            FindSilhouettes silhouettes = new FindSilhouettes(thisClass.image);
            /* Display the method that returns the number of silhouettes */
            System.out.println(silhouettes.calculateTheSilhouettes());

        }
    }

    /**
     * This method initializes the image array by reading the color information of each pixel.
     * The GraphArray class method is responsible for this.
     *
     * @param filePath File path
     * @return Whether the initialization was successful.
     */
    public boolean initializeImageSuccess(String filePath) {
        try {
            image = new GraphArray().getImageArray(filePath);
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * This method marks the background in the image array
     * First, get the background color using the backGrandColor method.
     * then all pixels of the given color are marked as visited, and
     * belonging to the object with marker 1
     */
    public void markUpTheBackground() {
        int[] backGrandColor = backGrandColor();
        for (int row = 0; row < image.length; row++) {
            for (int col = 0; col < image[row].length; col++) {
                if (checkColor(row, col, backGrandColor)) {
                    image[row][col].isVisited = true;
                    image[row][col].belongToObject = theFonMaker;
                }
            }
        }
    }

    /**
     * This method looks for the most common pixel color in the top left of the image
     * In HashMap, color is written as a key in string format, as a value, the number is written by key
     * similar pixels.
     * <p>
     * By the largest value, we get the key, and translate it back into an int array in RGB format
     * and return it.
     *
     * @return background color
     */
    public int[] backGrandColor() {

        HashMap<String, Integer> colorsAndQuantity = RetainsColorAndQuantity();

        GetFrequentlyOccurringColor(colorsAndQuantity);

        String colorInString = GetFrequentlyOccurringColor(colorsAndQuantity);

        return getColor(colorInString);
    }

    /**
     * This method takes a color encoded by four numbers from a string and translates it into
     * int array
     *
     * @param colorInString The color encoded in the string
     * @return array encoded int color
     */
    private int[] getColor(String colorInString) {
        String[] getRGB = colorInString.split(" ");
        int[] backGrandColor = new int[4];

        for (int i = 0; i < backGrandColor.length; i++) {
            backGrandColor[i] = Integer.parseInt(getRGB[i]);
        }
        return backGrandColor;
    }

    /**
     * This method looks up the largest value in the HashMap and returns the key of that value
     *
     * @param colorsAndQuantity HashMap with encoded color, and number of similar pixels
     *                          this color
     * @return Color coded string
     */
    private String GetFrequentlyOccurringColor(HashMap<String, Integer> colorsAndQuantity) {

        int frequentlySeenPixel = 0;
        String colorInString = "";

        for (String key : colorsAndQuantity.keySet())
            if (colorsAndQuantity.get(key) > frequentlySeenPixel) {
                frequentlySeenPixel = colorsAndQuantity.get(key);
                colorInString = key;
            }
        return colorInString;
    }


    /**
     * This method is to go through the top pixels of the image.
     * The pixel color is translated into a string and put into the HashMap as a key.
     * The next pixel is also translated into a string, and if the HashMap already contains
     * by key such a string, the value by key increases, otherwise
     * a new key and a new value for the number of pixels are entered into the HashMap.
     *
     * @return HashMap with keys as color and values as number of pixels encountered
     * with the same color.
     */
    private HashMap<String, Integer> RetainsColorAndQuantity() {
        HashMap<String, Integer> colors = new HashMap<>();
        for (int col = 0; col < image.length; col++) {
            String currentPixelColor = key(col, 0);

            if (colors.containsKey(currentPixelColor)) colors.put(currentPixelColor, colors.get(currentPixelColor) + 1);
            else colors.put(currentPixelColor, 1);
        }
        return colors;
    }

    /**
     * This method converts the encoded int[] array to a string
     *
     * @param row pixel y position
     * @param col pixel x position
     * @return Color-encoded string of four numbers.
     */
    public String key(int row, int col) {
        return image[row][col].getRed() + " " +
                image[row][col].getGreen() + " " +
                image[row][col].getBlue() + " " +
                image[row][col].getAlpha();
    }

    /**
     * this method returns the result of comparing pixels by color
     *
     * @param row coordinates of the pixel being checked along the y-axis
     * @param col x-coordinates of the pixel being checked
     * @return is the pixel in the array eligible to start the recursion
     */
    private boolean checkColor(int row, int col, int[] currentColor) {

        int red = image[row][col].getRed();
        int green = image[row][col].getGreen();
        int blue = image[row][col].getBlue();
        int alpha = image[row][col].getAlpha();

        int redOld = currentColor[0];
        int greenOld = currentColor[1];
        int blueOld = currentColor[2];
        int alphaAld = currentColor[3];
        return (Math.abs(red - redOld) <= RUNNING_COLOR_COEFFICIENT &&
                Math.abs(green - greenOld) <= RUNNING_COLOR_COEFFICIENT &&
                Math.abs(blue - blueOld) <= RUNNING_COLOR_COEFFICIENT &&
                Math.abs(alpha - alphaAld) <= RUNNING_COLOR_COEFFICIENT);
    }
}
