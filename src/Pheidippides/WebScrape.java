package Pheidippides;
//Φειδιππήδης

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebScrape {

    public static void webScrape() throws IOException {

        Document doc = null;
        ArrayList<String> l;
        l = new ArrayList<String>();
        doc = Jsoup.connect("https://www.hmu.gr/merimna/news").get();
        Elements links = doc.select("a[href]");
        for (Element link : links) {
            if (link.attr("abs:href").startsWith("https://www.hmu.gr/merimna/el/%CE%BD%CE%AD%CE%B1-%CE%B1%CE%BD%CE%B1%CE%BA%CE%BF%CE%B9%CE%BD%CF%8E%CF%83%CE%B5%CE%B9%CF%82/")) {
                l.add(link.toString());
            }
        }
        
        sw.print2(l);
        sw.print2T(l);
        doc = null;
        l = null;
        System.gc();
    }

    public static void webScrapePost(){
        try {
            ArrayList<String> links = sw.read("DataWeb.txt");
            Document doc = null;
            File file = new File("DataWebTPosts.txt");
            // Create file if not already existent.
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(WebScrape.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
            try (BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)) {
                for (String link : links) {
                    try{
                        doc = Jsoup.connect(link).get();
                        Elements m = doc.select("div.field-item");
                        String s = m.html();
                        s = s.replace("\n", "");
                        bufferedWriter.write(s + "\n");
                        }
                    catch(IOException e){
                        final JDialog d = new JOptionPane("<html><font color='red'>No connection!<br />Or the server is down </font></html>").createDialog((JFrame)null, "Connection");
                        d.setLocation(new Point( M.getX() + M.getW()/2 - (int)d.getPreferredSize().getWidth()/2, M.getY() + M.getH()/2- (int)d.getPreferredSize().getHeight()/2));
                        d.setVisible(true);
                    }
                }
            }
            links = null;
            doc = null;
            file = null;
            fileOutputStream = null;
            outputStreamWriter = null;
            System.gc();
        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }//End of webScrapePost
}
