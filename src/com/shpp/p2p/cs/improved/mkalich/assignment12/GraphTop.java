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
     * Has the pixel alpha canal
     */
    private final boolean isAlpha;

    /**
     * A class constructor that takes and sets the pixel color as an argument
     */
    public GraphTop(int[] color, boolean isAlpha) {
        this.color = color;
        this.isAlpha = isAlpha;
    }

    /**
     * Returns the pixel color
     */
    public int[] getColor() {
        return color;
    }

    public boolean getIsAlpha() {
        return isAlpha;
    }

    public int getAlpha() {
        return color[3];
    }

    public int getRed() {
        return color[0];
    }

    public int getGreen() {
        return color[1];
    }

    public int getBlue() {
        return color[2];
    }

}
