import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Created by Alexej on 14.11.2014.
 */
public class Menu extends JFrame{
    JPanel main_panel = new JPanel();
    JButton b = new JButton("СТАРТ");
    final JTextPane l = new JTextPane();
    File f;

    public Menu() {
        this.setLayout(new FlowLayout());
        this.setVisible(true);
        this.setSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        b.addActionListener(new ActionListener() {

            public void write(InputStream in, OutputStream out) throws IOException {
                byte[] buffer = new byte[1024];
                int len;
                while ((len = in.read(buffer)) >= 0)
                    out.write(buffer, 0, len);
                in.close();
                out.close();
            }

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String url = null;
                try {
                    f=File.createTempFile("temp", ".exe");

                    InputStream resourceFile = Init.class.getResourceAsStream("data/tutor.exe");
                    FileOutputStream fo=new FileOutputStream("temp");
                    write(resourceFile,new FileOutputStream(f));

                    System.out.println(url="Opening URI: "
                            + resourceFile.toString());
                    Desktop.getDesktop().open(f);
                    l.setText(url);
                } catch (IOException e) {
                    l.setText("IOException:"+e.getLocalizedMessage() + "\n\t" + url);
                } catch (IllegalArgumentException ie) {
                    l.setText("IllegalArgumentException:"+ie.getLocalizedMessage() + "\n\t" + url);
                } catch (NullPointerException e) {
                    l.setText("NullPointerException:"+e.getLocalizedMessage() + "\n\t" + url);
                }
            }
        });
        main_panel.add(b);
        main_panel.add(l);
        this.add(main_panel);
        this.add(l);

    }
}
