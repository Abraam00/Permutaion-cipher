import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main extends JFrame {
    private Screen mScreen;


    public static void main(String[] args)
    {
        EventQueue.invokeLater(
                new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try {
                            new Main();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

    public Main() throws IOException {
        setTitle("Floating Tanks");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setSize();
        setResizable(false);

        mScreen = new Screen();
        add(mScreen);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
