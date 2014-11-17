import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Menu extends JFrame{
    JPanel bgraund,mainPanel;
    JButton button1 = new JButton("ПРЕДСТАВЛЕНИЕ ЧИСЕЛ В КОМПЬЮТЕРЕ");
    JButton button2 = new JButton("ЧИСЛА В ФОРМАТЕ С ПЛАВАЮЩЕЙ ТОЧКОЙ");

    public Menu() {
		bgraund=new JPanel(){
			@Override
			public void paintComponent(Graphics g){
				Image im = null;
				try {
					im = ImageIO.read(new File("resources\\fon.jpg"));
				} catch (IOException e) {}
				g.drawImage(im, 0, 0,getWidth(), getHeight(), null);
			}

		};

		this.setContentPane(bgraund);
		mainPanel=new JPanel();
		bgraund.setLayout(new GridLayout(1,1));
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
                    File f=File.createTempFile("temp", ".exe");
                    InputStream resourceFile = Init.class.getResourceAsStream(pathFile1);
                    Menu.write(resourceFile, new FileOutputStream(f));
					exec(f.getPath());

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
				String pathFile = "resources\\lab3.jar";
				String cmd = "java -jar " + pathFile;
				System.out.println(cmd);
				exec(cmd);
			}
		});
		mainPanel.setLayout(new BorderLayout());
		mainPanel.setOpaque(false);
		JPanel pan=new JPanel();
		pan.setLayout(new FlowLayout());
		pan.setOpaque(false);
        pan.add(button1);
        pan.add(button2);
		JPanel namePanel=new JPanel();
		namePanel.setLayout(new FlowLayout());
		namePanel.setOpaque(false);
		JPanel bottomPanel=new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		bottomPanel.setOpaque(false);
		JLabel name=new JLabel("<html><center>ПРЕДСТАВЛЕНИЕ ИНФОРМАЦИИ<br>В КОМПЬЮТЕРЕ</html>");
		JLabel bottom=new JLabel("<html><div style=\"color:black\">МГУПС (МИИТ)</div></html>");
		name.setFont(new Font("Tahoma",Font.BOLD,20));
		bottom.setFont(new Font("Tahoma",Font.BOLD,20));
		namePanel.add(name);
		bottomPanel.add(bottom);
		mainPanel.add(namePanel, BorderLayout.NORTH);
		mainPanel.add(pan,BorderLayout.CENTER);
		mainPanel.add(bottomPanel,BorderLayout.SOUTH);
		bgraund.add(mainPanel);
        this.add(bgraund);
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
	public void exec(String pathFile){
		Process p=null;
		try {
			p=Runtime.getRuntime().exec(pathFile);

		this.setVisible(false);
		p.waitFor();
		this.setVisible(true);
		}  catch (InterruptedException e) {
			JOptionPane.showMessageDialog(null, e.getStackTrace().toString(), "Ошибка", JOptionPane.ERROR_MESSAGE);
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Файл " + pathFile + " не найден.\nПроверьте целостность пакета.", "Ошибка", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getStackTrace().toString(), "Ошибка", JOptionPane.ERROR_MESSAGE);
		}catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Файл " + pathFile + " не найден.\nПроверьте целостность пакета.", "Ошибка", JOptionPane.ERROR_MESSAGE);
		}
	}
}
