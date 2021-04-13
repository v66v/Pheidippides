package Pheidippides;
//Φειδιππήδης

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class sw {

    public static String savePage(String URL) throws IOException {
        String line = "", all = "";
        URL myUrl = null;
        BufferedReader in = null;
        try {
            myUrl = new URL(URL);
            in = new BufferedReader(new InputStreamReader(myUrl.openStream(), "UTF-8"));
            while ((line = in.readLine()) != null) {
                all += line;
            }
            myUrl = null;
            in = null;
            line = null;
            System.gc();
            
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return (all);
    }


    public static void print1(String content) {
        try {
            File file = new File("Data.txt");

            // Create file if not already existent. 
            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
            try (BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)) {
                content = content.replace("href=\\\"\\/wp-content\\/uploads\\/", "href=\\\"https:\\/\\/ece.hmu.gr/wp-content/uploads/");
                bufferedWriter.write(content);
                bufferedWriter.close();
            }
            file = null;
            fileOutputStream.close();
            outputStreamWriter.close();
            System.gc();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void print2(ArrayList<String> content) {
        try {
            File file = new File("DataWeb.txt");

            // Create file if not already existent. 
            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
            try (BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)) {
                for (String i : content) {
                    if (i.indexOf("href=\"https://www.hmu.gr/merimna/el/") != -1) {
                        i = "";
                    } else {
                        i = i.replace("href=\"/merimna/el/", "href=\"https://www.hmu.gr/merimna/el/");
                        i = i.substring(9);
                        i = i.substring(0, i.indexOf("\""));
                        bufferedWriter.write(i + "\n");
                    }

                }
            }
            file = null;
            fileOutputStream.close();
            outputStreamWriter.close();
            System.gc();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void print2T(ArrayList<String> content) {
        try {
            File file = new File("DataWebTitles.txt");

            // Create file if not already existent. 
            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
            try (BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)) {
                for (String i : content) {
                    if (i.indexOf("href=\"https://www.hmu.gr/merimna/el/") != -1) {
                        i = "";
                    } else {
                        i = (URLDecoder.decode(i, "UTF-8"));
                        i = i.replace("href=\"/merimna/el/", "");
                        i = i.replace("href=\"https://www.hmu.gr/merimna/el/", "");
                        i = i.substring(20);
                        i = i.replace("-", " ");
                        i = i.toUpperCase();
                        i = i.substring(0, i.indexOf("\""));
                        bufferedWriter.write(i + "\n");
                    }

                }
            }
            file = null;
            fileOutputStream.close();
            outputStreamWriter.close();
            System.gc();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String Ref(String URL) {
        try {
            sw.print1(sw.savePage(URL));
            json.jsonify();
            return ("OK");
        } catch (IOException e) {
            return ("");
        }
    }

    public static ArrayList<String> read(String f) {
        ArrayList<String> readString = new ArrayList<String>();
        String s;
        try {
            BufferedReader r = null;
            try {
                r = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(sw.class.getName()).log(Level.SEVERE, null, ex);
            }

           while((s = r.readLine())!=null){
               readString.add(s);
           }
           r.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(sw.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(sw.class.getName()).log(Level.SEVERE, null, ex);
        }
        s = null;
        System.gc();
        return readString;
    }
}