package com.ring.test_utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RequestHelperTest {

    @Test
    public void shouldReturnAValidLink() {
        String actualLink = RequestHelper.getMarsPhotosUrlByEarthDate("2015-05-30");
        String expectedLink = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?" +
                "earth_date=2015-05-30&api_key=1xPyihUqjk9Lovkh7B7Ax2FnqS9O7eVxaHWaxAGV";

        assertEquals(expectedLink, actualLink);
    }

    @Test
    public void shouldReturnAValidUrls() {
        String response = "\"img_src\":\"http://mars.jpl\" http: \"img_src\":";
        List<String> actualUrlList = RequestHelper.getImageUrls(response);
        List<String> expectedUrlList = new ArrayList<>();
        expectedUrlList.add("http://mars.jpl");

        assertEquals(expectedUrlList, actualUrlList);
    }

    @Test
    public void shouldReturnAValidEarthDay() {
        String response = "\"earth_date\":\"2015-05-30\" \"earth_date\":\"2015.05.30 qeqasf wqer-qw-er";
        String actualEarthDay = RequestHelper.getEarthDay(response);
        String expectedEarthDay = "2015-05-30";

        assertEquals(expectedEarthDay, actualEarthDay);
    }
}
