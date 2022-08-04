package com.shpp.p2p.cs.improved.mkalich.assignment13;

import java.util.LinkedList;

public class FindSilhouettes implements ConstantsControl {

    /**
     * An array that creates an image matrix including the color of a pixel,
     * whether the pixel was processed, and whether the pixel belongs to the object (background or silhouette)
     */
    private final GraphTop[][] image;

    /**
     * Class constructor
     *
     * @param image An array that creates an image matrix including the color of a pixel,
     *              whether the pixel was processed, and whether the pixel belongs to the object (background or silhouette)
     */
    FindSilhouettes(GraphTop[][] image) {
        this.image = image;
    }

    /**
     * Which object (silhouette) the pixel belongs to
     */
    private int belongToObject = 1;

    /**
     * This variable help to calculate the garbage pixels
     */
    private int garbagePixel = 1;

    /**
     * The max number of pixels in the group
     */
    private int NUMBER_OF_GARBAGE_PIXELS_FOR_DEL;

    /**
     * This method calculate return the number of silhouettes
     *
     * @return number of silhouettes
     */
    public int calculateTheSilhouettes() {
        taggedOllObjectsOnScreen();
        return belongToObject - theFonMaker;
    }

    /**
     * This method marks the  silhouettes.
     * Method increases the pixel marking by 1 for the new silhouette
     * If the number of pixels in the silhouette is less than the minimum value,
     * we refer this group to the background
     */
    private void taggedOllObjectsOnScreen() {
        for (int row = 0; row < image.length; row++) {
            for (int col = 0; col < image[row].length; col++) {
                if (!image[row][col].isVisited) {
                    belongToObject++;
                    garbagePixel = 0;
                    if (inDepthSearch) taggedTheObjectDepth(row, col);
                    else taggedTheObjectWide(row, col);
                    ifObjectIsGarbed();
                }
            }
        }
    }

    /**
     * this method searches for objects by deep recursion
     * first, the path that the method will take is checked.
     * if the pixel matches the requirements - the method is called on the new pixel
     * if not, go to the next check
     *
     * @param row pixel y-coordinates
     * @param col x-coordinates of a pixel
     */
    private void taggedTheObjectDepth(int row, int col) {

        image[row][col].isVisited = true;
        image[row][col].belongToObject = belongToObject;

        int rowUp = row - 1;
        int rowDn = row + 1;
        int colLeft = col - 1;
        int colRight = col + 1;

        if (checkTheWey(rowUp, col, row, col))
            taggedTheObjectDepth(rowUp, col);
        if (checkTheWey(rowDn, col, row, col))
            taggedTheObjectDepth(rowDn, col);
        if (checkTheWey(row, colLeft, row, col))
            taggedTheObjectDepth(row, colLeft);
        if (checkTheWey(row, colRight, row, col))
            taggedTheObjectDepth(row, colRight);

        garbagePixel++;
    }

    /**
     * This method implements a width search.
     * To the created LinkedList the coordinates of the next pixel in the matrix are entered
     * Each visited pixel is assigned a value of object affiliation and attendance
     *
     * @param row Y position of pixel in the matrix
     * @param col X position of pixel in the matrix
     */
    private void taggedTheObjectWide(int row, int col) {

        image[row][col].isVisited = true;
        image[row][col].belongToObject = belongToObject;

        LinkedList<Integer> queue = new LinkedList<>();
        queue.addFirst(row);
        queue.addFirst(col);

        while (!queue.isEmpty()) {
            garbagePixel++;
            int currentRow = queue.pollLast();
            int currentCol = queue.pollLast();

            int rowUp = currentRow - 1;
            int rowDn = currentRow + 1;
            int colLeft = currentCol - 1;
            int colRight = currentCol + 1;

            if (checkTheWey(rowUp, currentCol, currentRow, currentCol))
                taggedNewPixel(rowUp, currentCol, queue);
            if (checkTheWey(rowDn, currentCol, currentRow, currentCol))
                taggedNewPixel(rowDn, currentCol, queue);
            if (checkTheWey(currentRow, colLeft, currentRow, currentCol))
                taggedNewPixel(currentRow, colLeft, queue);
            if (checkTheWey(currentRow, colRight, currentRow, currentCol))
                taggedNewPixel(currentRow, colRight, queue);
        }
    }

    /**
     * This method checks if the number of pixels found is the minimum allowed,
     * if not, then the pixels are assigned to the background
     */
    private void ifObjectIsGarbed() {
        countNumberOfGarbagePixels();
        if (garbagePixel <= NUMBER_OF_GARBAGE_PIXELS_FOR_DEL) {
            for (GraphTop[] row : image) {
                for (GraphTop col : row) {
                    if (col.belongToObject == belongToObject)
                        col.belongToObject = theFonMaker;
                }
            }
            belongToObject--;
        }
    }

    /**
     * This methode calculates % garbage pixels
     * the sum of all pixels divided by 100 and multiplied by the PIXEL_DROPOUT_RATE factor
     */
    private void countNumberOfGarbagePixels() {
        NUMBER_OF_GARBAGE_PIXELS_FOR_DEL =
                (int) (((image.length * image[0].length) / 100) *
                        PIXEL_DROPOUT_RATE);
    }

    /**
     * This method marks a pixel as visited and
     * adds it to the queue to check its neighbors
     *
     * @param row   Y position of pixel in the matrix
     * @param col   X position of pixel in the matrix
     * @param queue pixel check queue
     */
    private void taggedNewPixel(int row, int col, LinkedList<Integer> queue) {
        queue.addFirst(row);
        queue.addFirst(col);
        image[row][col].isVisited = true;
        image[row][col].belongToObject = belongToObject;
    }

    /**
     * This method checks if a pixel matches the launch requirement
     * new branch of recursion.
     * For background search and for object search, they are slightly different
     *
     * @param row        coordinates of the pixel being checked along the y-axis
     * @param col        x-coordinates of the pixel being checked
     * @param currentRow coordinates of the compared pixel along the Y-axis
     * @param currentCol x-coordinates of the compared pixel
     * @return is the pixel in the array eligible to start the recursion ?
     */
    private boolean checkTheWey(int row, int col, int currentRow, int currentCol) {
        if (pixelOutOfTheScreen(row, col)) return false;
        return !(image[row][col].isVisited);
    }

    /**
     * This method checks if the requested pixel is within the matrix
     *
     * @param row Y position of pixel in the matrix
     * @param col X position of pixel in the matrix
     * @return true ore false
     */
    private boolean pixelOutOfTheScreen(int row, int col) {
        return ((row >= image.length || row < 0) || (col >= image[row].length || col < 0));
    }

}
