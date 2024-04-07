package org.example;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import static java.time.temporal.ChronoUnit.SECONDS;

public class HttpStatusImageDownloader {

        private static final String IMAGE_PATH = "src/main/resources/images/status_%d.jpg";
        private static final String DIR_PATH_FOR_IMAGE = "src/main/resources/images";

        public void downloadStatusImage(int code) throws ImageNotFoundException {
            try{
                String url = new HttpStatusChecker().getStatusImage(code);
                Files.createDirectories(Paths.get(DIR_PATH_FOR_IMAGE));
                HttpRequest httpRequest = HttpRequest.newBuilder()
                        .GET()
                        .header("Content-Type", "image/jpg")
                        .timeout(Duration.of(5, SECONDS))
                        .uri(new URI(url))
                        .build();
                HttpClient httpClient = HttpClient.newBuilder()
                        .connectTimeout(Duration.of(5, SECONDS))
                        .followRedirects(HttpClient.Redirect.NORMAL)
                        .build();
                HttpResponse<Path> httpResponse = httpClient.send(httpRequest,
                        HttpResponse.BodyHandlers.ofFile(Paths.get(String.format(IMAGE_PATH, code))));
                if(httpResponse.statusCode()!=200){
                    throw new ImageNotFoundException();
                }
            } catch (URISyntaxException | IOException e){
                e.printStackTrace();
            } catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
}
