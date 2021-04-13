package m;
//Φειδιππήδης
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class UrlHandler implements HyperlinkListener {

    @Override
    public void hyperlinkUpdate(HyperlinkEvent event) {
        if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URI(event.getURL().toString()));

                } catch (URISyntaxException | IOException ex) {
                    Logger.getLogger(UrlHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
