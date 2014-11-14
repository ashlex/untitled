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
    JFrame frame=this;

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
                String pathFile1 ="data/tutor.exe";
                try {
                    f=File.createTempFile("temp", ".exe");
                    InputStream resourceFile = Init.class.getResourceAsStream(pathFile1);
                    Menu.write(resourceFile, new FileOutputStream(f));

                    Process p=null;
                    p=Runtime.getRuntime().exec(f.getPath());
                    frame.setVisible(false);
                    p.waitFor();
                    frame.setVisible(true);
                }  catch (InterruptedException e) {
                    JOptionPane.showMessageDialog(null, e.getStackTrace().toString(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(null, "Файл " + pathFile1 + " не найден.\nПроверьте целостность пакета.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getStackTrace().toString(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                }catch (NullPointerException e) {
                    JOptionPane.showMessageDialog(null, "Файл " + pathFile1 + " не найден.\nПроверьте целостность пакета.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        main_panel.add(b);
        main_panel.add(l);
        this.add(main_panel);
        this.add(l);

    }
    public static void write(InputStream in, OutputStream out) throws IOException,NullPointerException {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) >= 0)
            out.write(buffer, 0, len);
        in.close();
        out.close();
    }
}
