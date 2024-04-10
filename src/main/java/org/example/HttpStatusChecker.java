package org.example;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class HttpStatusChecker {


    private static final String GENERAL_URL = "https://http.cat/%d.jpg";
    public String getStatusImage(int code) throws ImageNotFoundException {
        String SupposedUrl = getSupposedUrl(code);
        int responseCode = 0;
        try {
            URL url = new URI(SupposedUrl).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            responseCode = connection.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(responseCode != 0 && responseCode == 404) {
            throw new ImageNotFoundException("Image not found");
        } else {
            return  SupposedUrl;
        }

    }
    private String getSupposedUrl( int code) {
        return String.format(GENERAL_URL, code);
    }
}

