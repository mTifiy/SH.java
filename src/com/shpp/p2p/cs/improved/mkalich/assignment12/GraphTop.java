package com.shpp.p2p.cs.improved.mkalich.assignment12;

public class GraphTop {
    /**
     * Has the pixel been visited
     */
    public boolean isVisited = false;
    /**
     * which object a pixel belongs to
     */
    public int belongToObject = 0;
    /**
     * pixel color
     */
    private final int[] color;


    /**
     * A class constructor that takes and sets the pixel color as an argument
     */
    public GraphTop(int[] color) {
        this.color = color;
    }

    /**
     * @return the pixel color
     */
    public int[] getColor() {
        return color;
    }

    /**
     * @return alpha channel of a pixel
     */
    public int getAlpha() {
        return color[3];
    }

    /**
     * @return pixel red channel value
     */
    public int getRed() {
        return color[0];
    }

    /**
     * @return pixel green channel value
     */
    public int getGreen() {
        return color[1];
    }

    /**
     * @return pixel blue channel value
     */
    public int getBlue() {
        return color[2];
    }

}
