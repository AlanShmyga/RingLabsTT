package com.ring.test_utils;

import com.drew.imaging.ImageProcessingException;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class ImageComparatorTest {
    @Test
    public void imageComparatorShouldReturnTrue()
            throws MalformedURLException, ImageProcessingException, URISyntaxException {
        URL solPhoto = new URL("http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol" +
                "/01000/opgs/edr/fcam/FLB_486265257EDR_F0481570FHAZ00323M_.JPG");
        URL earthPhoto = new URL("http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol/" +
                "01000/opgs/edr/fcam/FLB_486265257EDR_F0481570FHAZ00323M_.JPG");

        assertTrue(ImageComparator.compareImage(solPhoto, earthPhoto));
    }

    @Test
    public void imageComparatorShouldReturnFalse()
            throws MalformedURLException, ImageProcessingException, URISyntaxException {
        URL solPhoto = new URL("http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol" +
                "/01000/opgs/edr/fcam/FLB_486265257EDR_F0481570FHAZ00323M_.JPG");
        URL earthPhoto = new URL("http://mars.jpl.nasa.gov/msl-raw-images/proj/msl/redops/ods/surface/sol" +
                "/01000/opgs/edr/ncam/NLB_486264973EDR_S0481570NCAM00546M_.JPG");

        assertFalse(ImageComparator.compareImage(solPhoto, earthPhoto));
    }
}
