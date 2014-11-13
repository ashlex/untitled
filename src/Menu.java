import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Alexej on 14.11.2014.
 */
public class Menu extends JFrame{
    JPanel main_panel = new JPanel();
    JButton b = new JButton("СТАРТ");
    final JTextPane l = new JTextPane();

    public Menu() {
        this.setLayout(new FlowLayout());
        this.setVisible(true);
        this.setSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String url = null;
                try {
                    URI resourceFile = Init.class.getResource("/tutor.exe").toURI();
                    System.out.println(url="Opening URI: "
                            + resourceFile.toString());
                    Desktop.getDesktop().open(new File(resourceFile));
                    l.setText(url);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    l.setText("IOException:"+e.getLocalizedMessage() + "\n\t" + url);
                } catch (IllegalArgumentException ie) {
                    l.setText("IllegalArgumentException:"+ie.getLocalizedMessage() + "\n\t" + url);
                } catch (NullPointerException e) {
                    l.setText("NullPointerException:"+e.getLocalizedMessage() + "\n\t" + url);
                }
                catch (URISyntaxException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        main_panel.add(b);
        main_panel.add(l);
        this.add(main_panel);
        this.add(l);

    }
}
