package Pheidippides;
//Φειδιππίδης
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Document;

public class FirstUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private final JPanel pan;
    private static JPanel pUI;
    private static UI ui;
    private Dimension dim;
    private static MerimnaUI merimnaui;
    private static LibraryUI libraryUI;
    public FirstUI(Point loc) {
        
        setTitle("Φειδιππίδης");
        setSize(500, 400);
        setLayout(new BorderLayout());
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(loc);
        setIconImage(Toolkit.getDefaultToolkit().getImage("Φειδιππίδης.png"));
        setFocusable(true);
        
        addKeyListener(new KeyAdapter (){
            @Override
            public void keyPressed(KeyEvent e){     
                if((e.getKeyCode() == KeyEvent.VK_X) && ((e.getModifiers() & KeyEvent.ALT_MASK) != 0)){
                  System.exit(0);
              }
            }
        });
        
        addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1) {
                        FirstUI.this.requestFocus();
                    }}});
        
        panel = new JPanel();
        pan = new JPanel();
        panel.setLayout(new BorderLayout());
        pan.setLayout(new GridLayout(3, 0));
        pUI = new JPanel();
        pUI.add(new JLabel());
        JLabel title = new JLabel("Επέλεξε την ιστοσελίδα");
        pUI.add(title);
        pUI.add(new JLabel());
        panel.add(pUI, BorderLayout.NORTH);
        JButton ece = new JButton("ece.hmu.gr");
        ece.addActionListener((ActionEvent e) -> {
            if (new File("Data.txt").exists()) {
            ui = new UI("https://ece.hmu.gr/wp-json/wp/v2/posts");
            remove(panel);
            add(ui);
            setLocation(FirstUI.this.getX() - 175, FirstUI.this.getY() - 100);
            setSize(850, 600);
            }
            else{
                sw.Ref("https://ece.hmu.gr/wp-json/wp/v2/posts"); 
                if (!new File("Data.txt").exists()) {
                    final JDialog d = new JOptionPane("<html><font color='red'>No connection!<br />Or the server is down </font></html>").createDialog((JFrame)null, "Connection");
                    d.setLocation(new Point( FirstUI.this.getX() + FirstUI.this.getWidth()/2 - (int)d.getPreferredSize().getWidth()/2, FirstUI.this.getY() + FirstUI.this.getHeight()/2- (int)d.getPreferredSize().getHeight()/2));
                    d.setVisible(true);
                }
            }
        });
        ece.setMnemonic('E');
        pan.add(ece);
        JButton merimna = new JButton("merimna");
        merimna.addActionListener((ActionEvent e) -> {
            merimnaui = new MerimnaUI();
            remove(panel);
            add(merimnaui);
            setLocation(FirstUI.this.getX() - 175, FirstUI.this.getY() - 100);
            setSize(850, 600);
        });
        merimna.setMnemonic('M');
        pan.add(merimna);
        JButton lbr = new JButton("Bιβλιοθήκη");
        lbr.addActionListener((ActionEvent e)->{
            libraryUI = new LibraryUI();
            remove(panel);
            add(libraryUI);
            setLocation(FirstUI.this.getX() - 175, FirstUI.this.getY() - 100);
            setSize(850, 600);
        });
        lbr.setMnemonic('B');
        pan.add(lbr);
        panel.add(pan, BorderLayout.CENTER);
        JLabel addImage = new JLabel();
        JPanel j = new JPanel();
        ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage("Φειδιππίδης.png"));
        addImage.setIcon(image);
        j.add(addImage);
        add(j);
        setVisible(true);
        remove(j);
        add(panel);
        revalidate();
    }    

    private class UI extends JPanel {

        private final JButton re;
        private JPanel vv;
        private final JLabel label;
        private JPanel pa;
        private JPanel i;
        private JPanel SB;
        private JButton b;
        private JButton bb;
        private JButton up;
        private JButton down;
        private JTextField Search;
        private JList<String> list;
        private DefaultListModel<String> listModel = new DefaultListModel<String>();
        private DefaultHighlightPainter highlightPainterIndex = new DefaultHighlightPainter(Color.ORANGE);
        private JEditorPane l;
        private ArrayList<int []> SearchIndex;
        private int index = 0;
        private char p = 'd';
        public UI(String URL) {
            
            setLayout(new BorderLayout());
            label = new JLabel("ΝΕΑ - ΕΙΔΟΠΟΙΉΣΕΙΣ");
            i = new JPanel();
            i.add(new JLabel());
            i.add(label);
            i.add(new JLabel());
            add(i,BorderLayout.NORTH);
            pa = new JPanel();
            re = new JButton("Επαναφόρτωση");
            b = new JButton("Πίσω");
            pa.add(b);
            pa.add(re);
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    FirstUI.this.getContentPane().removeAll();
                    FirstUI.this.add(panel);
                    FirstUI.this.setLocation(FirstUI.this.getX() + 175, FirstUI.this.getY() + 100);
                    FirstUI.this.setSize(500, 400);
                    FirstUI.this.invalidate();
                    FirstUI.this.validate();
                    FirstUI.this.repaint();
                }
            });
            b.setMnemonic('C');
            add(pa, BorderLayout.SOUTH);
            re.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (sw.Ref(URL).equals("OK")) {
                        listModel.clear();
                        FirstUI.ui.fixPanel(URL);
                    } else {
                        final JDialog d = new JOptionPane("<html><font color='red'>No connection!<br />Or the server is down </font></html>").createDialog((JFrame)null, "Connection");
                        d.setLocation(new Point( FirstUI.this.getX() + FirstUI.this.getWidth()/2 - (int)d.getPreferredSize().getWidth()/2, FirstUI.this.getY() + FirstUI.this.getHeight()/2- (int)d.getPreferredSize().getHeight()/2));
                    }
                }
            });
            re.setMnemonic('R');
            list = new JList(listModel); //data has type Object[]
            list.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent ke){
              if(ke.getKeyCode() == KeyEvent.VK_DOWN && list.getSelectedIndex() == list.getModel().getSize()-1)
              {
                  ke.consume();
                list.setSelectedIndex(0);
              }
              else if(ke.getKeyCode() == KeyEvent.VK_UP && list.getSelectedIndex() == 0){
                  ke.consume();
                  list.setSelectedIndex(list.getModel().getSize()-1);
              }
              else if((ke.getKeyCode() == KeyEvent.VK_X) && ((ke.getModifiers() & KeyEvent.ALT_MASK) != 0)){
                  System.exit(0);
              }
            }});
            fixPanel(URL);
            list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
            list.setVisibleRowCount(-1);
            list.setFixedCellWidth(835);
            add(list, BorderLayout.CENTER);
            list.setSelectedIndex(0);
        }

        private void fixPanel(String URL) {
            int q=0;

            for(String s : json.getJListT()){
                listModel.addElement( String.format("%2d ", q)+s);
                q++;
            }
            
            list.removeAll();
            list.setModel(listModel);
            list.addMouseListener(
                    new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        fixListener();
            }}}
            );
            
            list.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                        fixListener();       
                }}
            });
            
            SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {

                                list.requestFocus();
                            }
                        });
            
            System.gc();

        }//End of fixPanel
        
    private void fixListener(){
        if(list.getSelectedValue()!= null){
                            l = new JEditorPane();
                            l.setContentType("text/html");
                            l.addHyperlinkListener(new UrlHandler());
                            l.setText("<html>" + json.getJListC().get(json.getJListT().indexOf((String) list.getSelectedValue().substring(3))) + "</html>");
                            l.setEditable(false);
                            l.addMouseListener(
                                new MouseAdapter() {
                                    @Override
                                    public void mouseClicked(MouseEvent e) {
                                        if (e.getClickCount() == 1) {
                                            FirstUI.this.requestFocus();
                                }}});
                            JScrollPane sc = new JScrollPane(l);
                            sc.getVerticalScrollBar().setUnitIncrement(16);
                            FirstUI.this.getContentPane().removeAll();
                            FirstUI.this.add(sc, BorderLayout.CENTER);
                            vv = new JPanel();
                            vv.add(new JLabel());
                            vv.add(new JLabel(list.getSelectedValue()));
                            vv.add(new JLabel());
                            SB = new JPanel();
                            FirstUI.this.add(vv, BorderLayout.NORTH);
                            bb = new JButton("Πίσω");
                            FirstUI.this.add(bb, BorderLayout.SOUTH);
                            bb.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    FirstUI.this.getContentPane().removeAll();
                                    FirstUI.this.add(FirstUI.ui);
                                    FirstUI.this.invalidate();
                                    FirstUI.this.validate();
                                    FirstUI.this.repaint();
                                    list.requestFocus();
                                }
                            });
                            SB.add(bb);
                            bb.setMnemonic('C');

                            Search = new JTextField(20);
                            SB.add(Search);
                            
                            
                            FirstUI.this.addKeyListener(new KeyAdapter(){
                            
                                public void keyReleased(KeyEvent e){
                                    if((e.getKeyCode() == KeyEvent.VK_F) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)){
                                    UI.this.Search.grabFocus();
                                    }
                                }
                            });
                            up = new JButton("<html>&#x25B2;</html>");
                            up.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    if (p == 'd'){
                                        index-=2;
                                    }
                                    else{
                                     index--;   
                                    }

                                    if(index>=0){
                                    try {
                                        l.getHighlighter().removeAllHighlights();
                                        l.getHighlighter().addHighlight(SearchIndex.get(index)[0],SearchIndex.get(index)[1],highlightPainterIndex);
                                        l.setCaretPosition(SearchIndex.get(index)[0]);
                                    } catch (BadLocationException ex) {
                                        Logger.getLogger(FirstUI.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    p = 'u';
                                    }
                                    else{
                                        index = 0;
                                        final JDialog d = new JOptionPane("<html><font color='red'>NO More Occurrences</font></html>").createDialog((JFrame)null, "Search");
                                        d.setLocation(new Point( FirstUI.this.getX() + FirstUI.this.getWidth()/2 - (int)d.getPreferredSize().getWidth()/2, FirstUI.this.getY() + FirstUI.this.getHeight()/2- (int)d.getPreferredSize().getHeight()/2));
                                        d.setVisible(true);
                                    }
                                }});
                            down = new JButton("<html>&#x25BC;</html>");

                            down.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    if (p == 'u'){
                                        index++;
                                    }

                                    if(index<SearchIndex.size()){
                                    try {
                                        l.setCaretPosition(SearchIndex.get(index)[0]);
                                        l.getHighlighter().removeAllHighlights();
                                        l.getHighlighter().addHighlight(SearchIndex.get(index)[0],SearchIndex.get(index)[1],highlightPainterIndex);
                                    } catch (BadLocationException ex) {
                                        Logger.getLogger(FirstUI.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    p = 'd';
                                    }
                                    else{
                                        index = SearchIndex.size()-1;
                                        final JDialog d = new JOptionPane("<html><font color='red'>NO More Occurrences</font></html>").createDialog((JFrame)null, "Search");
                                        d.setLocation(new Point( FirstUI.this.getX() + FirstUI.this.getWidth()/2 - (int)d.getPreferredSize().getWidth()/2, FirstUI.this.getY() + FirstUI.this.getHeight()/2- (int)d.getPreferredSize().getHeight()/2));
                                        d.setVisible(true);
                                    }
                                    index++;
                                }});

                            SB.add(up);
                            SB.add(down);
                            FirstUI.this.add(SB, BorderLayout.SOUTH);

                            Search.addKeyListener(new KeyAdapter() {
                                @Override
                                public void keyReleased(KeyEvent e) {
                                SearchIndex = new ArrayList<int []>();
                                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                                index = 0;
                                p = 'd';
                                l.getHighlighter().removeAllHighlights();
                                Document document = l.getDocument();
                                try {
                                    String find = Search.getText();
                                    if(!find.trim().equals("")){
                                        String newS = l.getText();
                                    for (int index = 0; index + find.length() < document.getLength(); index++) {
                                        String match = document.getText(index, find.length());
                                        if (find.equals(match)) {
                                            DefaultHighlightPainter highlightPainter = new DefaultHighlightPainter(Color.YELLOW);
                                            int [] k = new int[2];
                                            k[0] = index;
                                            k[1] = index + find.length();
                                            SearchIndex.add(k);
                                            l.getHighlighter().addHighlight(index, index + find.length(),highlightPainter);
                                        }
                                    }
                                    }
                                } catch (BadLocationException ex) {
                                    ex.printStackTrace();
                                } 
                                FirstUI.this.requestFocus();
                                }
                                }
                            });
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {

                                    sc.getViewport().setViewPosition(new Point(0, 0));
                                    JScrollBar vertical = sc.getVerticalScrollBar();
                                    vertical.setUnitIncrement(16);
                                    InputMap im = vertical.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
                                    im.put(KeyStroke.getKeyStroke("DOWN"), "positiveUnitIncrement");
                                    im.put(KeyStroke.getKeyStroke("UP"), "negativeUnitIncrement");
                                }
                            });
                            FirstUI.this.invalidate();
                            FirstUI.this.validate();
                            FirstUI.this.repaint();
                    }
    }
    }//End of UI

    private class MerimnaUI extends JPanel {

        private final JLabel label;
        private JPanel vv;
        private JPanel pa;
        private JPanel i;
        private JPanel SB;
        private JButton b;
        private JButton bb;
        private JButton up;
        private JButton down;
        private JTextField Search;
        private final JButton re;
        private JList<String> list;
        private JEditorPane l;
        private DefaultListModel<String> listModel = new DefaultListModel<String>();
        private ArrayList<int []> SearchIndex;
        private int index = 0;
        private char p = 'd';
        private DefaultHighlightPainter highlightPainterIndex = new DefaultHighlightPainter(Color.ORANGE);
        public MerimnaUI() {
            setLayout(new BorderLayout());
            label = new JLabel("ΝΕΑ - ΑΝΑΚΟΙΝΩΣΕΙΣ");
            i = new JPanel();
            i.add(new JLabel());
            i.add(label);
            i.add(new JLabel());
            add(i,BorderLayout.NORTH);
            re = new JButton("Επαναφόρτωση");
            b = new JButton("Πίσω");
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    FirstUI.this.getContentPane().removeAll();
                    FirstUI.this.add(panel);
                    FirstUI.this.setLocation(FirstUI.this.getX() + 175, FirstUI.this.getY() + 100);
                    FirstUI.this.setSize(500, 400);
                    FirstUI.this.invalidate();
                    FirstUI.this.validate();
                    FirstUI.this.repaint();
                    
                }
            });
              b.setMnemonic('C');
            re.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    
                    try {
                        WebScrape.webScrape();
                        WebScrape.webScrapePost();
                    } catch (IOException ex) {
                        final JDialog d = new JOptionPane("<html><font color='red'>No connection!<br />Or the server is down </font></html>").createDialog((JFrame)null, "Connection");
                        d.setLocation(new Point( FirstUI.this.getX() + FirstUI.this.getWidth()/2 - (int)d.getPreferredSize().getWidth()/2, FirstUI.this.getY() + FirstUI.this.getHeight()/2- (int)d.getPreferredSize().getHeight()/2));
                        d.setVisible(true);
                    }
                    listModel.clear();
                    FirstUI.merimnaui.FixPanel2();
                }
            });
            
            re.setMnemonic('R');
            pa = new JPanel();
            pa.add(b);
            pa.add(re);
            add(pa, BorderLayout.SOUTH);
            list = new JList(listModel); //data has type Object[]
            list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
            list.setVisibleRowCount(-1);
            list.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent ke){
              if(ke.getKeyCode() == KeyEvent.VK_DOWN && list.getSelectedIndex() == list.getModel().getSize()-1)
              {
                  ke.consume();
                list.setSelectedIndex(0);
              }
              else if(ke.getKeyCode() == KeyEvent.VK_UP && list.getSelectedIndex() == 0){
                  ke.consume();
                  list.setSelectedIndex(list.getModel().getSize()-1);
              }
              else if((ke.getKeyCode() == KeyEvent.VK_X) && ((ke.getModifiers() & KeyEvent.ALT_MASK) != 0)){
                  System.exit(0);
              }
            }});
            list.setFixedCellWidth(835);
            add(list, BorderLayout.CENTER);
            FixPanel2();
            list.setSelectedIndex(0);
        }

    private void FixPanel2() {
            ArrayList<String> titles = sw.read("DataWebTitles.txt");
            ArrayList<String> posts = sw.read("DataWebTPosts.txt");
            int q=0;
            for (String s : titles) {
                listModel.addElement(String.format("%2d ", q)+s);
                q++;
            }
            list.removeAll();
            list.setModel(listModel);
            
            list.addMouseListener(
                    new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                         fixListener(posts, titles);
                    }
                }
            }
            );
            
            list.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    
                         fixListener(posts,titles);
                    }
                }}
            );
            
            SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {

                                list.requestFocus();
                            }
                        });
            System.gc();
        }//End of FixPanel2
        
    private void fixListener(ArrayList<String> posts, ArrayList<String> titles){
        l = new JEditorPane();
                        l.setContentType("text/html");
                        l.addHyperlinkListener(new UrlHandler());
                        l.setText(posts.get(titles.indexOf((String) list.getSelectedValue().substring(3))));
                        l.setEditable(false);
                        l.addMouseListener(
                            new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    if (e.getClickCount() == 1) {
                                        FirstUI.this.requestFocus();
                            }}});
                        
                        JScrollPane sc = new JScrollPane(l);
                        sc.getVerticalScrollBar().setUnitIncrement(16);
                        sc.setAutoscrolls(true);
                        FirstUI.this.getContentPane().removeAll();
                        FirstUI.this.add(sc, BorderLayout.CENTER);
                        vv = new JPanel();
                        vv.add(new JLabel());
                        vv.add(new JLabel(list.getSelectedValue()));
                        vv.add(new JLabel());
                        FirstUI.this.add(vv, BorderLayout.NORTH);
                        SB = new JPanel();
                        bb = new JButton("Πίσω");
                        SB.add(bb);
                        bb.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                FirstUI.this.getContentPane().removeAll();
                                FirstUI.this.add(FirstUI.merimnaui);
                                FirstUI.this.invalidate();
                                FirstUI.this.validate();
                                FirstUI.this.repaint();
                                list.requestFocus();
                        } });
                        bb.setMnemonic('C');
                        Search = new JTextField(20);
                        SB.add(Search);
                        FirstUI.this.addKeyListener(new KeyAdapter(){
                            
                                public void keyReleased(KeyEvent e){
                                    if((e.getKeyCode() == KeyEvent.VK_F) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)){
                                    MerimnaUI.this.Search.grabFocus();
                                    }
                                }
                            });
                        up = new JButton("<html>&#x25B2;</html>");
                        up.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                if (p == 'd'){
                                    index-=2;
                                }
                                else{
                                 index--;   
                                }
                                
                                if(index>=0){
                                try {
                                    l.getHighlighter().removeAllHighlights();
                                    l.getHighlighter().addHighlight(SearchIndex.get(index)[0],SearchIndex.get(index)[1],highlightPainterIndex);
                                    l.setCaretPosition(SearchIndex.get(index)[0]);
                                } catch (BadLocationException ex) {
                                    Logger.getLogger(FirstUI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                p = 'u';
                                }
                                else{
                                    index = 0;
                                    final JDialog d = new JOptionPane("<html><font color='red'>NO More Occurrences</font></html>").createDialog((JFrame)null, "Search");
                                    d.setLocation(new Point( FirstUI.this.getX() + FirstUI.this.getWidth()/2 - (int)d.getPreferredSize().getWidth()/2, FirstUI.this.getY() + FirstUI.this.getHeight()/2- (int)d.getPreferredSize().getHeight()/2));
                                    d.setVisible(true);
                                }
                            }});
                        down = new JButton("<html>&#x25BC;</html>");
                        
                        down.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                if (p == 'u'){
                                    index++;
                                }
                                
                                if(index<SearchIndex.size()){
                                try {
                                    l.setCaretPosition(SearchIndex.get(index)[0]);
                                    l.getHighlighter().removeAllHighlights();
                                    l.getHighlighter().addHighlight(SearchIndex.get(index)[0],SearchIndex.get(index)[1],highlightPainterIndex);
                                } catch (BadLocationException ex) {
                                    Logger.getLogger(FirstUI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                p = 'd';
                                }
                                else{
                                    index = SearchIndex.size()-1;
                                    final JDialog d = new JOptionPane("<html><font color='red'>NO More Occurrences</font></html>").createDialog((JFrame)null, "Search");
                                    d.setLocation(new Point( FirstUI.this.getX() + FirstUI.this.getWidth()/2 - (int)d.getPreferredSize().getWidth()/2, FirstUI.this.getY() + FirstUI.this.getHeight()/2- (int)d.getPreferredSize().getHeight()/2));
                                    d.setVisible(true);
                                }
                                index++;
                            }});
                        
                        SB.add(up);
                        SB.add(down);
                        FirstUI.this.add(SB, BorderLayout.SOUTH);
                        
                        Search.addKeyListener(new KeyAdapter() {
                            @Override
                            public void keyReleased(KeyEvent e) {
                            SearchIndex = new ArrayList<int []>();
                            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                            index = 0;
                            p = 'd';
                            l.getHighlighter().removeAllHighlights();
                            Document document = l.getDocument();
                            try {
                                String find = Search.getText();
                                if(!find.trim().equals("")){
                                    String newS = l.getText();
                                for (int index = 0; index + find.length() < document.getLength(); index++) {
                                    String match = document.getText(index, find.length());
                                    if (find.equals(match)) {
                                        DefaultHighlightPainter highlightPainter = new DefaultHighlightPainter(Color.YELLOW);
                                        int [] k = new int[2];
                                        k[0] = index;
                                        k[1] = index + find.length();
                                        SearchIndex.add(k);
                                        l.getHighlighter().addHighlight(index, index + find.length(),highlightPainter);
                                    }
                                }
                                }
                            } catch (BadLocationException ex) {
                                ex.printStackTrace();
                            } 
                            FirstUI.this.requestFocus();
                            }}
                        });
                        
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {

                                sc.getViewport().setViewPosition(new Point(0, 0));
                                JScrollBar vertical = sc.getVerticalScrollBar();
                                vertical.setUnitIncrement(16);
                                InputMap im = vertical.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
                                im.put(KeyStroke.getKeyStroke("DOWN"), "positiveUnitIncrement");
                                im.put(KeyStroke.getKeyStroke("UP"), "negativeUnitIncrement");
                            }
                        });
                        FirstUI.this.invalidate();
                        FirstUI.this.validate();
                        FirstUI.this.repaint();
    }//End of fixListener   
    }//End of MerimnaUI
    
    private class LibraryUI extends JPanel{
        
        private final JLabel label;
        private JPanel vv;
        private JPanel pa;
        private JPanel i;
        private JPanel SB;
        private JButton b;
        private JButton bb;
        private JButton up;
        private JButton down;
        private JTextField Search;
        private final JButton re;
        private JList<String> list;
        private JEditorPane l;
        private DefaultListModel<String> listModel = new DefaultListModel<String>();
        private ArrayList<int []> SearchIndex;
        private int index = 0;
        private char p = 'd';
        private DefaultHighlightPainter highlightPainterIndex = new DefaultHighlightPainter(Color.ORANGE);
        public LibraryUI() {
            setLayout(new BorderLayout());
            label = new JLabel("ΝΕΑ - ΑΝΑΚΟΙΝΩΣΕΙΣ");
            i = new JPanel();
            i.add(new JLabel());
            i.add(label);
            i.add(new JLabel());
            add(i,BorderLayout.NORTH);
            re = new JButton("Επαναφόρτωση");
            b = new JButton("Πίσω");
            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    FirstUI.this.getContentPane().removeAll();
                    FirstUI.this.add(panel);
                    FirstUI.this.setLocation(FirstUI.this.getX() + 175, FirstUI.this.getY() + 100);
                    FirstUI.this.setSize(500, 400);
                    FirstUI.this.invalidate();
                    FirstUI.this.validate();
                    FirstUI.this.repaint();
                    
                }
            });
              b.setMnemonic('C');
            re.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                            WebScrape.lbrGetData("https://lib.hmu.gr/%ce%bd%ce%ad%ce%b1/");                                                    //TODO: Yeahhhh get the webscraping working
                    } catch (IOException ex) {
                        final JDialog d = new JOptionPane("<html><font color='red'>No connection!<br />Or the server is down </font></html>").createDialog((JFrame)null, "Connection");
                        d.setLocation(new Point( FirstUI.this.getX() + FirstUI.this.getWidth()/2 - (int)d.getPreferredSize().getWidth()/2, FirstUI.this.getY() + FirstUI.this.getHeight()/2- (int)d.getPreferredSize().getHeight()/2));
                        d.setVisible(true);
                    }
                    listModel.clear();
                    FirstUI.merimnaui.FixPanel2();
                }
            });
            
            re.setMnemonic('R');
            pa = new JPanel();
            pa.add(b);
            pa.add(re);
            add(pa, BorderLayout.SOUTH);
            list = new JList(listModel); //data has type Object[]
            list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
            list.setVisibleRowCount(-1);
            list.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent ke){
              if(ke.getKeyCode() == KeyEvent.VK_DOWN && list.getSelectedIndex() == list.getModel().getSize()-1)
              {
                  ke.consume();
                list.setSelectedIndex(0);
              }
              else if(ke.getKeyCode() == KeyEvent.VK_UP && list.getSelectedIndex() == 0){
                  ke.consume();
                  list.setSelectedIndex(list.getModel().getSize()-1);
              }
              else if((ke.getKeyCode() == KeyEvent.VK_X) && ((ke.getModifiers() & KeyEvent.ALT_MASK) != 0)){
                  System.exit(0);
              }
            }});
            list.setFixedCellWidth(835);
            add(list, BorderLayout.CENTER);
            
            FixPanel3();
            list.setSelectedIndex(0);
    }
        private void FixPanel3() {
            ArrayList<String> titles = sw.read("lbrTitles.txt");
            ArrayList<String> posts = sw.read("lbrPosts.txt");
            int q=0;
            for (String s : titles) {
                listModel.addElement(String.format("%2d ", q)+s);
                q++;
            }
            list.removeAll();
            list.setModel(listModel);
            
            list.addMouseListener(
                    new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                         fixListener(posts, titles);
                    }
                }
            }
            );
            
            list.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    
                         fixListener(posts,titles);
                    }
                }}
            );
            
            SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {

                                list.requestFocus();
                            }
                        });
            System.gc();
        }//End of FixPanel
        private void fixListener(ArrayList<String> posts, ArrayList<String> titles){
        l = new JEditorPane();
                        l.setContentType("text/html");
                        l.addHyperlinkListener(new UrlHandler());
                        l.setText(posts.get(titles.indexOf((String) list.getSelectedValue().substring(3))));
                        l.setEditable(false);
                        l.addMouseListener(
                            new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    if (e.getClickCount() == 1) {
                                        FirstUI.this.requestFocus();
                            }}});
                        
                        JScrollPane sc = new JScrollPane(l);
                        sc.getVerticalScrollBar().setUnitIncrement(16);
                        sc.setAutoscrolls(true);
                        FirstUI.this.getContentPane().removeAll();
                        FirstUI.this.add(sc, BorderLayout.CENTER);
                        vv = new JPanel();
                        vv.add(new JLabel());
                        vv.add(new JLabel(list.getSelectedValue()));
                        vv.add(new JLabel());
                        FirstUI.this.add(vv, BorderLayout.NORTH);
                        SB = new JPanel();
                        bb = new JButton("Πίσω");
                        SB.add(bb);
                        bb.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                FirstUI.this.getContentPane().removeAll();
                                FirstUI.this.add(FirstUI.libraryUI);
                                FirstUI.this.invalidate();
                                FirstUI.this.validate();
                                FirstUI.this.repaint();
                                list.requestFocus();
                        } });
                        bb.setMnemonic('C');
                        Search = new JTextField(20);
                        SB.add(Search);
                        FirstUI.this.addKeyListener(new KeyAdapter(){
                            
                                public void keyReleased(KeyEvent e){
                                    if((e.getKeyCode() == KeyEvent.VK_F) && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)){
                                    LibraryUI.this.Search.grabFocus();
                                    }
                                }
                            });
                        up = new JButton("<html>&#x25B2;</html>");
                        up.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                if (p == 'd'){
                                    index-=2;
                                }
                                else{
                                 index--;   
                                }
                                
                                if(index>=0){
                                try {
                                    l.getHighlighter().removeAllHighlights();
                                    l.getHighlighter().addHighlight(SearchIndex.get(index)[0],SearchIndex.get(index)[1],highlightPainterIndex);
                                    l.setCaretPosition(SearchIndex.get(index)[0]);
                                } catch (BadLocationException ex) {
                                    Logger.getLogger(FirstUI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                p = 'u';
                                }
                                else{
                                    index = 0;
                                    final JDialog d = new JOptionPane("<html><font color='red'>NO More Occurrences</font></html>").createDialog((JFrame)null, "Search");
                                    d.setLocation(new Point( FirstUI.this.getX() + FirstUI.this.getWidth()/2 - (int)d.getPreferredSize().getWidth()/2, FirstUI.this.getY() + FirstUI.this.getHeight()/2- (int)d.getPreferredSize().getHeight()/2));
                                    d.setVisible(true);
                                }
                            }});
                        down = new JButton("<html>&#x25BC;</html>");
                        
                        down.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                if (p == 'u'){
                                    index++;
                                }
                                
                                if(index<SearchIndex.size()){
                                try {
                                    l.setCaretPosition(SearchIndex.get(index)[0]);
                                    l.getHighlighter().removeAllHighlights();
                                    l.getHighlighter().addHighlight(SearchIndex.get(index)[0],SearchIndex.get(index)[1],highlightPainterIndex);
                                } catch (BadLocationException ex) {
                                    Logger.getLogger(FirstUI.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                p = 'd';
                                }
                                else{
                                    index = SearchIndex.size()-1;
                                    final JDialog d = new JOptionPane("<html><font color='red'>NO More Occurrences</font></html>").createDialog((JFrame)null, "Search");
                                    d.setLocation(new Point( FirstUI.this.getX() + FirstUI.this.getWidth()/2 - (int)d.getPreferredSize().getWidth()/2, FirstUI.this.getY() + FirstUI.this.getHeight()/2- (int)d.getPreferredSize().getHeight()/2));
                                    d.setVisible(true);
                                }
                                index++;
                            }});
                        
                        SB.add(up);
                        SB.add(down);
                        FirstUI.this.add(SB, BorderLayout.SOUTH);
                        
                        Search.addKeyListener(new KeyAdapter() {
                            @Override
                            public void keyReleased(KeyEvent e) {
                            SearchIndex = new ArrayList<int []>();
                            if(e.getKeyCode() == KeyEvent.VK_ENTER){
                            index = 0;
                            p = 'd';
                            l.getHighlighter().removeAllHighlights();
                            Document document = l.getDocument();
                            try {
                                String find = Search.getText();
                                if(!find.trim().equals("")){
                                    String newS = l.getText();
                                for (int index = 0; index + find.length() < document.getLength(); index++) {
                                    String match = document.getText(index, find.length());
                                    if (find.equals(match)) {
                                        DefaultHighlightPainter highlightPainter = new DefaultHighlightPainter(Color.YELLOW);
                                        int [] k = new int[2];
                                        k[0] = index;
                                        k[1] = index + find.length();
                                        SearchIndex.add(k);
                                        l.getHighlighter().addHighlight(index, index + find.length(),highlightPainter);
                                    }
                                }
                                }
                            } catch (BadLocationException ex) {
                                ex.printStackTrace();
                            } 
                            FirstUI.this.requestFocus();
                            }}
                        });
                        
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {

                                sc.getViewport().setViewPosition(new Point(0, 0));
                                JScrollBar vertical = sc.getVerticalScrollBar();
                                vertical.setUnitIncrement(16);
                                InputMap im = vertical.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
                                im.put(KeyStroke.getKeyStroke("DOWN"), "positiveUnitIncrement");
                                im.put(KeyStroke.getKeyStroke("UP"), "negativeUnitIncrement");
                            }
                        });
                        FirstUI.this.invalidate();
                        FirstUI.this.validate();
                        FirstUI.this.repaint();
    }//End of fixListener  
        
    }//End of LibraryUI
}//End of FirstUI
