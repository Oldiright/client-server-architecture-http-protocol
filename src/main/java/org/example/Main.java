package org.example;

public class Main {
    public static void main(String[] args)  {
       HttpStatusChecker httpStatusChecker = new HttpStatusChecker();
        try {
            httpStatusChecker.getStatusImage(119);
        } catch (ImageNotFoundException e) {
            e.printStackTrace();
        }


    }

}