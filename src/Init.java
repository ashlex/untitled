import java.awt.*;

/**
 * Created by Alexej on 13.11.2014.
 */
public class Init {
    public static void main(String []args){
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Menu();
            }
        });
    }
}
