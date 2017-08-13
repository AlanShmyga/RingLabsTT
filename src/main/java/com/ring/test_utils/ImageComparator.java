package com.ring.test_utils;

import com.drew.imaging.ImageProcessingException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class ImageComparator {
    // This API will compare two image file //
    // return true if both image files are equal else return false//**
    public static boolean compareImage(URL imageA, URL imageB) throws ImageProcessingException, URISyntaxException {
        try {
            // take buffer data from botm image files //
            BufferedImage biA = ImageIO.read(imageA);
            DataBuffer dbA = biA.getData().getDataBuffer();

            int sizeA = dbA.getSize();
            BufferedImage biB = ImageIO.read(imageB);
            DataBuffer dbB = biB.getData().getDataBuffer();

            int sizeB = dbB.getSize();
            // compare data-buffer objects //

            if (sizeA == sizeB) {
                for (int i = 0; i < sizeA; i++) {
                    if (dbA.getElem(i) != dbB.getElem(i)) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            System.out.println("URL A: " + imageA);
            System.out.println("URL B: " + imageB);
            System.out.println("Failed to compare image files ...\n" + e.getMessage());
            return false;
        }
    }
}
