package com.ring.nasa_api_tests;

import com.drew.imaging.ImageProcessingException;
import com.ring.test_utils.ImageComparator;
import com.ring.test_utils.RequestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static com.ring.test_utils.RequestHelper.getEarthDay;
import static com.ring.test_utils.RequestHelper.getImageUrls;
import static io.restassured.RestAssured.get;
import static junit.framework.TestCase.assertTrue;

@RunWith(JUnit4.class)
public class PhotoComparisonTest {

    @Test
    public void shouldReturnTheSamePictureSetInDifferentTimeFormats()
            throws IOException, ImageProcessingException, URISyntaxException {
        String solDayResponse = get(RequestHelper.getMarsPhotosUrlBySolDay(1000)).asString();
        String earthDay = getEarthDay(solDayResponse);
        String earthDayResponse = get(RequestHelper.getMarsPhotosUrlByEarthDate(earthDay)).asString();

        List<String> solDayPhotos = getImageUrls(solDayResponse);
        List<String> earthDayPhotos = getImageUrls(earthDayResponse);

        for (int i = 0; i < solDayPhotos.size(); i++) {
            URL solDayPhoto = new URL(solDayPhotos.get(i));
            URL earthDayPhoto = new URL(earthDayPhotos.get(i));
            String errorMessage = "Error comparing image #" + i + "\nImage by solar day: " + solDayPhoto
                    + "\nImage by earth day: " + earthDayPhoto;

            assertTrue(errorMessage, ImageComparator.compareImage(solDayPhoto, earthDayPhoto));
        }
    }
}
