package org.example;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class HttpStatusChecker {


    private static final String GENERAL_URL = "https://http.cat/%d.jpg";
    public String getStatusImage(int code) throws ImageNotFoundException {
        String url = getSupposedUrl(code);
        int responseCode = 0;
        try {
            URI uri = new URI(url);
            URL url1 = uri.toURL();
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            connection.setRequestMethod("GET");

            responseCode = connection.getResponseCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(responseCode != 0 && responseCode == 404) {
            throw new ImageNotFoundException();
        } else {
            return  url;
        }

    }
    private String getSupposedUrl( int code) {
        return String.format(GENERAL_URL, code);
    }
}

