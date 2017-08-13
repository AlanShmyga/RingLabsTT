package com.ring.test_utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestHelper {

    private static final String NASA_PHOTOS_LINK = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?";
    private static final String API_KEY = "DEMO_KEY";

    public static String getMarsPhotosUrlBySolDay(Integer solDay) {
        return NASA_PHOTOS_LINK + "sol=" + solDay + "&api_key=" + API_KEY;
    }

    public static String getMarsPhotosUrlBySolDayByCamera(Integer solDay, String camera) {
        return NASA_PHOTOS_LINK + "sol=" + solDay + "&camera=" + camera + "&api_key=" + API_KEY;
    }

    public static String getMarsPhotosUrlByEarthDate(String earthDate) {
        return NASA_PHOTOS_LINK + "earth_date=" + earthDate + "&api_key=" + API_KEY;
    }

    public static List<String> getImageUrls(String response) {
        Pattern pattern = Pattern.compile("\\\"img_src\\\":\\\"(.*?)\\\"");
        Matcher imgUrlMatcher = pattern.matcher(response);

        List<String> imageUrls = new ArrayList<>();
        int i = 0;

        while (imgUrlMatcher.find() && i < 10) {
            imageUrls.add(imgUrlMatcher.group(1));
            i++;
        }

        return imageUrls;
    }

    public static String getEarthDay(String response) {
        Pattern pattern = Pattern.compile("\\\"earth_date\\\":\\\"(.*?)\\\"");
        Matcher matcher = pattern.matcher(response);

        if (matcher.find()) {
            return matcher.group(1);
        }
        throw new IllegalArgumentException("Response does not contain earth date!");
    }

    public static Integer getNumberOfPhotos(String response) {
        Pattern pattern = Pattern.compile("\\{\"(.*?)\\}\\}");
        Matcher matcher = pattern.matcher(response);
        Integer photoNumber = 0;

        while (matcher.find()) {
            photoNumber++;
        }

        return photoNumber;
    }
}
