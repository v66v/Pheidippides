package Pheidippides;
//Φειδιππήδης

import java.io.FileInputStream;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import org.apache.commons.lang3.StringEscapeUtils;

public class json {

    private static ArrayList<String> jlistT;
    private static ArrayList<String> jlistC;

    public static ArrayList<String> getJListT() {
        return (jlistT);
    }

    public static ArrayList<String> getJListC() {
        return (jlistC);
    }

    public static void jsonify() {
        JSONParser parser = new JSONParser();
        try {

            Reader r = new InputStreamReader(new FileInputStream("Data.txt"), StandardCharsets.UTF_8);
            Object obj = parser.parse(r);
            JSONArray json = (JSONArray) obj;
            JSONObject temp;
            jlistT = new ArrayList<String>();
            jlistC = new ArrayList<String>();
            for (int i = 0; i < json.size(); i++) {
                temp = (JSONObject) json.get(i);
                temp = (JSONObject) temp.get("title");
                jlistT.add(StringEscapeUtils.unescapeHtml4(temp.get("rendered").toString().replace("ΗΛΕΚΤΡΟΛΟΓΩΝ ΜΗΧΑΝΙΚΩΝ ΚΑΙ ΜΗΧΑΝΙΚΩΝ ΥΠΟΛΟΓΙΣΤΩΝ", "ΗΜΜΥ")));   //For Title simplicity
            }
            for (int i = 0; i < json.size(); i++) {
                temp = (JSONObject) json.get(i);
                temp = (JSONObject) temp.get("content");
                jlistC.add(temp.get("rendered").toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
