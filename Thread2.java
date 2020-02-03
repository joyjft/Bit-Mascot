
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Joy
 */
public class Thread2 implements Runnable {

    String path;

    public Thread2(String path) {
        this.path = path;
    }

    @Override
    public void run() {
        while (true) {
            if (Main.lock == false) {
                String content = readFromFile(path);
                String tokens[] = content.split(" ");
                int counter = 0;
                for (String token : tokens) {
                    if (Pattern.compile(".jpg\"$").matcher(token).find() && Pattern.compile("^url=").matcher(token).find())//check if token matched with url=....jpg"
                    {
                        System.out.println("Reference-" + (++counter) + " : " + token);
                    }
                }
            }
            try {
                Thread.sleep(20000); // Waiting for 15 Seconds
            } catch (InterruptedException ex) {
            }
        }
}
    private static String readFromFile(String path) {
        BufferedReader bufferedReader = null;
        String page = new String("");
        try {
            File file = new File(path);
            bufferedReader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                page += line;
            }
            bufferedReader.close();
        } catch (Exception ex) {
            System.out.println("Can Not read from " + path);
        }
        return page;   
    }
}
