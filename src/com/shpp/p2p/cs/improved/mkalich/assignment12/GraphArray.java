package com.shpp.p2p.cs.improved.mkalich.assignment12;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class GraphArray {

    private BufferedImage readFile(String weyToFile) throws IOException {
        File file = new File(weyToFile);
        return ImageIO.read(file);
    }

    public GraphTop[][] getImageArray(String weyToFile) throws IOException {

        BufferedImage image = readFile(weyToFile);
        WritableRaster raster = image.getRaster();
        GraphTop[][] matrixOfPicture = new GraphTop[raster.getHeight()][raster.getWidth()];

        for (int row = 0; row < matrixOfPicture.length; row++) {
            for (int col = 0; col < matrixOfPicture[row].length; col++) {

                int[] pixelColor = raster.getPixel(col, row, new int[4]);
                boolean isAlpha = image.getAlphaRaster() != null;

                matrixOfPicture[row][col] = new GraphTop(pixelColor, isAlpha);
            }
        }
        return matrixOfPicture;
    }
}