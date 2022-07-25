package com.shpp.p2p.cs.improved.mkalich.assignment12;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

/**
 * This class reads an image by pixels and writes it to an array of GraphTop objects.
 * The GraphTop constructor is passed the pixel color as an argument
 */
public class GraphArray {

    /**
     * This method reads a file and returns a BufferedImage
     * @param weyToFile image path
     * @return BufferedImage images
     * @throws IOException File access error
     */
    private BufferedImage readFile(String weyToFile) throws IOException {
        File file = new File(weyToFile);
        return ImageIO.read(file);
    }

    /**
     * The method accesses the image, gets the raster of the image,
     * reads the color of each pixel and passes it to the GraphTop array.
     * @param weyToFile Image path
     * @return a two-dimensional array of GraphTop class objects that creates a picture matrix
     * @throws IOException file access error
     */
    public GraphTop[][] getImageArray(String weyToFile) throws IOException {

        BufferedImage image = readFile(weyToFile);
        WritableRaster raster = image.getRaster();
        GraphTop[][] matrixOfPicture = new GraphTop[raster.getHeight()][raster.getWidth()];

        for (int row = 0; row < matrixOfPicture.length; row++) {
            for (int col = 0; col < matrixOfPicture[row].length; col++) {

                int[] pixelColor = raster.getPixel(col, row, new int[4]);

                matrixOfPicture[row][col] = new GraphTop(pixelColor);
            }
        }
        return matrixOfPicture;
    }
}