package Pheidippides;
//Φειδιππίδης

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
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
                //l.add(link.toString());
                l.add(link.attr("abs:href"));
            }
        }
        sw.print2(l,"DataWeb.txt");
        sw.print2T(l);
        doc = null;
        l = null;
        System.gc();
    }
    
    public static void lbrGetData(String url) throws IOException{
        
         Set<String> uniqueURL = new HashSet<String>();
                Document doc = Jsoup.connect(url).get();
                Elements links = doc.select("div.post-25998 page type-page status-publish hentry, a.panel-title");//doc.select("a");    5c8903btvx
                //System.out.println(links);
                if (links.isEmpty()) {
                   return;
                }
                for (Element link : links) {
            if (link.attr("abs:href").startsWith("https://lib.hmu.gr/")) {
                uniqueURL.add(link.attr("abs:href"));
            }
        }
                //System.out.println(uniqueURL);
                //Document j;
                //Elements j;
                ArrayList<String> titles = new ArrayList<String>();
                ArrayList<String> text = new ArrayList<String>();
                for (String i : uniqueURL){
                    doc = Jsoup.connect(i).get();
                    //System.out.println(doc.select("h1.entry-title").text());
                    titles.add(doc.select("h1.entry-title").text().toUpperCase());
                    text.add(("<h4>"+doc.select("div.et_post_meta_wrapper").html()+doc.select("div.entry-content")+"</h4>").replaceAll("\\n", ""));
                }
                
                sw.print2(titles, "lbrTitles.txt");
                sw.print2(text, "lbrPosts.txt");
/*
                links.stream().map((link) -> link.attr("abs:href")).forEachOrdered((this_url) -> {
                    boolean add = uniqueURL.add(this_url);
                    if (add && this_url.contains("https://lib.hmu.gr/")) {
                        System.out.println(this_url);
                        get_links(this_url);
                    }
                   System.out.println("Well fuck");
                   if (this_url.contains("https://lib.hmu.gr/")&&!this_url.contains("https://lib.hmu.gr/wp-content/")&&!this_url.contains("https://lib.hmu.gr/%ce%ba%ce%b1%ce%bd%ce%bf%ce%bd%ce%b9%cf%83%ce%bc%cf%8c%cf%82/")){
                    System.out.println(this_url);   
                   }
                });*/
    
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
