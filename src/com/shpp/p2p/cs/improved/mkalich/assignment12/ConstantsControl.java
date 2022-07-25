package com.shpp.p2p.cs.improved.mkalich.assignment12;

public interface ConstantsControl {
    /**
     * This coefficient looks at how many gradations a color can be ignored
     * when marking the background
     */
    int RUNNING_COLOR_COEFFICIENT = 30;

    /**
     * This coefficient determines the type of silhouette search
     * true - reverse depth-first search
     * false - wide
     */
    boolean inDepthSearch = false;
    /**
     * Default file path
     */
    String FILE_PATH_BY_DEFAULT = "test.jpg";

    /**
     * Under what number the background is marked
     */
    int theFonMaker = 1;

    /**
     * the percentage of the totality of pixels from the image size that are considered garbage
     */
    double PIXEL_DROPOUT_RATE = 0.05;

}
