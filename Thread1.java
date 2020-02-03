
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Joy
 * 
 * 
 * 
 *first thread updates d:\a.rss in every 15 seconds with the content fetched from http://rss.cnn.com/rss/edition.rss .
 */
public class Thread1 implements Runnable {
    String urlAddress;
    String path;
  

    public Thread1(String urlAddress, String path) {
        this.urlAddress = urlAddress;
        this.path = path;
      
    }

    

   
    
    
    public void run() {
        while (true) {
            String contentFromUrl=fetchDataFromUrl(urlAddress);
            Main.lock=true;
            boolean status=writeToFile(path, contentFromUrl);
            Main.lock=false;
            if (status){
                System.out.println("Updation of "+path+" Successfull");
            }
            else {
                System.out.println("Updation of "+path+" Failed");
            }
            
            try {
                Thread.sleep(15000); // Waiting for 15 Seconds
            } catch (InterruptedException ex) {
            }
            
        }
        
        
    }
     private static String fetchDataFromUrl(String urlAddress) {
        URL url = null;
        HttpURLConnection conn = null;
        BufferedReader in = null;
        String page = new String("");
        try {
            url = new URL(urlAddress);

        } catch (MalformedURLException ex) {
            System.out.println("Can Not Find " + urlAddress);
        }
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;

            while (((line = in.readLine())) != null) {
                line = line + '\n';
                page += line;

            }
            in.close();
        } catch (Exception IoException) {
            System.out.println("Io Exception Occured.");
        }

        return page;
    }

    private static boolean writeToFile(String path, String content) {
        File file = new File(path);
        FileWriter fw;
        try {
            fw = new FileWriter(file);
            fw.write(content);
            fw.close();
        } catch (IOException ex) {
            return false;
        }

        return true;
    }
    
}
