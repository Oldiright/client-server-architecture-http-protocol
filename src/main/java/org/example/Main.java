package org.example;

public class Main {
    public static void main(String[] args)  {
       HttpStatusImageDownloader httpStatusImageDownloader = new HttpStatusImageDownloader();
        try {
            httpStatusImageDownloader.downloadStatusImage(70000);
        } catch (ImageNotFoundException e) {
            System.out.println("invalid code");
        }


    }

}