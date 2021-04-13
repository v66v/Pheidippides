package Pheidippides;

//Φειδιππήδης
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class M {
private static FirstUI f = null;
private static JFrame F;
    public static void main(String[] args) {

        F = new JFrame();
        F.setTitle("Φειδιππήδης");
        F.setSize(500, 400);
        F.setDefaultCloseOperation(EXIT_ON_CLOSE);
        F.setLayout(new BorderLayout());
        F.setResizable(false);
        Dimension dim;
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        Point p = new Point(dim.width / 2 - F.getSize().width / 2, dim.height / 2 - F.getSize().height / 2);
        F.setLocation(p);
        F.setIconImage(Toolkit.getDefaultToolkit().getImage("Φειδιππίδης.png"));
        JLabel addImage = new JLabel();
        JPanel j = new JPanel();
        ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage("Φειδιππίδης.png"));
        addImage.setIcon(image);
        j.add(addImage);
        F.add(j);
        F.setVisible(true);
          sw.Ref("https://ece.hmu.gr/wp-json/wp/v2/posts");        // for ece.hmu.gr
        json.jsonify();// for https://www.hmu.gr/merimna/el/
        try {
            WebScrape.webScrape();
            WebScrape.webScrapePost();
        } catch (IOException ex) {
                    final JDialog d = new JOptionPane("<html><font color='red'>No connection!<br />Or the server is down </font></html>").createDialog((JFrame)null, "Connection");
                    d.setLocation(new Point( F.getX() + F.getWidth()/2 - (int)d.getPreferredSize().getWidth()/2, F.getY() + F.getHeight()/2- (int)d.getPreferredSize().getHeight()/2));
                    d.setVisible(true);
        }
        if (F.getLocation() != p) {
            f = new FirstUI(new Point(F.getLocation().x, F.getLocation().y));
            F.dispose();
        } else {
            f = new FirstUI(p);
            F.setVisible(false);
            F.dispose();
        }
    }//End of main
    
    public static int getX(){
        if (f !=null){
            return f.getX();
        }
        return F.getX();
    }
    public static int getY(){
        if (f !=null){
            return f.getY();
        }
        return F.getY();
    }
    public static int getW(){
        if (f !=null){
            return f.getWidth();
        }
        return F.getWidth();
    }
    public static int getH(){
        if (f !=null){
            return f.getHeight();
        }
        return F.getHeight();
    }
}
