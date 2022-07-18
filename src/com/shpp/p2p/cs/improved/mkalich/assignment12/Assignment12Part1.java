package com.shpp.p2p.cs.improved.mkalich.assignment12;
/*
 * Если альфа канал - ориентироваться по прозрачности.
 * Все цвета = 0
 *
 */

public class Assignment12Part1 {

    private static final boolean inDepthSearch = true;

    private static final String FILE_PATH_BY_DEFAULT = "assets\\assignmetn12\\test2.png";

    private GraphTop[][] image;

    public static void main(String[] args) {

        FindSilhouettes thisClass = new FindSilhouettes(FILE_PATH_BY_DEFAULT, inDepthSearch);
        if (thisClass.initializeImageSuccess()) {
            thisClass.taggedOllObjectsOnScreen();
            thisClass.printObject();
        }
    }


}
