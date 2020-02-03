
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Joy
 */
public class Main {
    static boolean lock=false;

    public static void main(String[] args) {
        String urlAddress = "http://rss.cnn.com/rss/edition.rss";
        String path = "d:\\a.rss";
        Thread thread1 = new Thread(new Thread1(urlAddress, path));
        Thread thread2 = new Thread(new Thread2(path));
        thread1.start();
        thread2.start();
        

    }

  
}
