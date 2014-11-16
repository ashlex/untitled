import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Menu extends JFrame{
    JPanel main_panel = new JPanel();
    JButton button1 = new JButton("ПРЕДСТАВЛЕНИЕ ЧИСЕЛ В КОМПЬЮТЕРЕ");
    JButton button2 = new JButton("ЧИСЛА В ФОРМАТЕ С ПЛАВАЮЩЕЙ ТОЧКОЙ");
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
        button1.addActionListener(new ActionListener() {
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
		button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
				String pathFile ="resources\\lab3.jar";
				f=new File(pathFile);
				Runtime r = Runtime.getRuntime();
				Process p = null;
				String cmd= "java -jar "+pathFile;
				System.out.println(cmd);
				l.setText(cmd);
				try {
					p=r.exec(cmd);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
        });
		main_panel.setLayout(new FlowLayout());
        main_panel.add(button1);
        main_panel.add(button2);
        main_panel.add(l);
        this.add(main_panel);
//        this.add(l);

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
