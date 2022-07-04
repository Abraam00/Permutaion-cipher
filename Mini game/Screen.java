import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Screen extends JPanel implements Runnable {
    public static float ScreenWidth = 1024;
    public static float ScreenHeight = 650;
    private static JLabel playerName1, playerName2, score1, score2, angle1,
            angle2, velocity1, velocity2;
    final BufferedImage tankImage1 = ImageIO.read(new File("C:\\Users\\abraa\\Desktop\\CSCI 142\\csci142-proj-Abraam00\\res\\tankResized1.jpg"));
    final BufferedImage tankImage2 = ImageIO.read(new File("C:\\Users\\abraa\\Desktop\\CSCI 142\\csci142-proj-Abraam00\\res\\tankResized2.jpg"));

    private static Thread mGameLoop;
    private static boolean mGameOn, shot, shot1, hit, shot2;
    private static JButton fire1, fire2, left1, left2, right1, right2, up1, down1, up2, down2;
    private static JTextField tVelocity1, tVelocity2, tAngle1, tAngle2;
    private static Tank mTank1;
    private static Tank mTank2;
    private static bigShot bigshot;
    private static Skipper skipper;
    private static Sniper sniper;
    private static JButton[] buttons1, buttons2;
    private static JTextField[] textFields1, textFields2;
    private static JComboBox<Object> bulletList1, bulletList2;
    private static Rectangle bullet1, bullet2;
    private static List<Object> bullets = new ArrayList<>();
    private static float t;
    private static boolean test = false;
    private static boolean test2 = false;
    private static boolean test3 = false;
    private static Thread t1;
    private static Thread t2;
    private static Thread t3;

    public Screen() throws IOException {
        setPreferredSize(new Dimension((int) ScreenWidth, (int) ScreenHeight));
        setBackground(Color.white);
        setDoubleBuffered(true);
        setLayout(null);


        bigshot = new bigShot();
        skipper = new Skipper();
        sniper = new Sniper();
        bullets.add(skipper);
        bullets.add(bigshot);
        bullets.add(sniper);
        bullets.add(skipper);
        bullets.add(bigshot);
        bullets.add(sniper);
        bulletList1 = new JComboBox<>(bullets.toArray());
        bulletList1.setBounds(350, 120, 140, 20);
        bulletList1.add(new CustomActionListener());

        bulletList2 = new JComboBox<>(bullets.toArray());
        bulletList2.setBounds(550, 120, 140, 20);
        bulletList2.setEnabled(false);
        bulletList2.add(new CustomActionListener());

        mTank1 = new Tank(200, 580);
        mTank2 = new Tank(900, 580);
        setDoubleBuffered(true);
        buttons1 = new JButton[5];
        buttons2 = new JButton[5];
        textFields1 = new JTextField[2];
        textFields2 = new JTextField[2];

        playerName1 = new JLabel("Player 1:");
        playerName1.setFont(new Font("PLAYER 1", Font.PLAIN, 24));
        playerName1.setSize(100, 50);
        playerName1.setLocation(10, 5);

        score1 = new JLabel("0");
        score1.setFont(new Font("score", Font.PLAIN, 24));
        score1.setSize(50, 50);
        score1.setLocation(10, 40);

        angle1 = new JLabel("Angle:");
        angle1.setFont(new Font("angle1", Font.PLAIN, 20));
        angle1.setSize(70, 50);
        angle1.setLocation(10, 100);

        velocity1 = new JLabel("Velocity:");
        velocity1.setFont(new Font("velocity1", Font.PLAIN, 20));
        velocity1.setSize(80, 50);
        velocity1.setLocation(10, 140);

        playerName2 = new JLabel("Player 2:");
        playerName2.setFont(new Font("PLAYER 2:", Font.PLAIN, 24));
        playerName2.setSize(100, 50);
        playerName2.setLocation(920, 5);

        score2 = new JLabel("0");
        score2.setFont(new Font("score", Font.PLAIN, 24));
        score2.setSize(50, 50);
        score2.setLocation(980, 40);

        angle2 = new JLabel("Angle:");
        angle2.setFont(new Font("angle2", Font.PLAIN, 20));
        angle2.setSize(70, 50);
        angle2.setLocation(900, 100);

        velocity2 = new JLabel("Velocity:");
        velocity2.setFont(new Font("velocity", Font.PLAIN, 20));
        velocity2.setSize(80, 50);
        velocity2.setLocation(900, 140);

        tAngle1 = new JTextField();
        tAngle1.setSize(40, 20);
        tAngle1.setLocation(80, 120);
        textFields1[0] = tAngle1;

        tVelocity1 = new JTextField();
        tVelocity1.setSize(40, 20);
        tVelocity1.setLocation(90, 160);
        textFields1[1] = tVelocity1;

        tAngle2 = new JTextField();
        tAngle2.setSize(40, 20);
        tAngle2.setLocation(970, 120);
        tAngle2.setEnabled(false);
        textFields2[0] = tAngle2;

        tVelocity2 = new JTextField();
        tVelocity2.setSize(40, 20);
        tVelocity2.setLocation(980, 160);
        tVelocity2.setEnabled(false);
        textFields2[1] = tVelocity2;

        fire1 = new JButton("FIRE");
        fire1.setBackground(Color.red);
        fire1.setSize(80, 40);
        fire1.setLocation(230, 60);
        fire1.setFocusable(false);
        fire1.setFont(new Font("fire", Font.PLAIN, 20));
        fire1.addActionListener(new CustomActionListener());
        buttons1[0] = fire1;

        up1 = new JButton("\uD83E\uDC69");
        up1.setBackground(Color.BLUE);
        up1.setSize(80, 40);
        up1.setLocation(230, 20);
        up1.setFocusable(false);
        up1.setFont(new Font("up", Font.PLAIN, 42));
        up1.addActionListener(new CustomActionListener());
        buttons1[1] = up1;

        down1 = new JButton("\uD83E\uDC6B");
        down1.setBackground(Color.BLUE);
        down1.setSize(80, 40);
        down1.setLocation(230, 100);
        down1.setFocusable(false);
        down1.setFont(new Font("down", Font.PLAIN, 42));
        down1.addActionListener(new CustomActionListener());
        buttons1[2] = down1;

        right1 = new JButton("\uD83E\uDC6A");
        right1.setBackground(Color.BLUE);
        right1.setSize(80, 40);
        right1.setLocation(310, 60);
        right1.setFocusable(false);
        right1.setFont(new Font("right", Font.PLAIN, 42));
        right1.addActionListener(new CustomActionListener());
        buttons1[3] = right1;

        left1 = new JButton("\uD83E\uDC68");
        left1.setBackground(Color.BLUE);
        left1.setSize(80, 40);
        left1.setLocation(150, 60);
        left1.setFocusable(false);
        left1.setFont(new Font("left", Font.PLAIN, 42));
        left1.addActionListener(new CustomActionListener());
        buttons1[4] = left1;

        fire2 = new JButton("FIRE");
        fire2.setBackground(Color.red);
        fire2.setSize(80, 40);
        fire2.setLocation(730, 60);
        fire2.setFocusable(false);
        fire2.setFont(new Font("fire", Font.PLAIN, 20));
        fire2.addActionListener(new CustomActionListener());
        fire2.setEnabled(false);
        buttons2[0] = fire2;

        up2 = new JButton("\uD83E\uDC69");
        up2.setBackground(Color.BLUE);
        up2.setSize(80, 40);
        up2.setLocation(730, 20);
        up2.setFocusable(false);
        up2.setFont(new Font("up", Font.PLAIN, 42));
        up2.addActionListener(new CustomActionListener());
        up2.setEnabled(false);
        buttons2[1] = up2;

        down2 = new JButton("\uD83E\uDC6B");
        down2.setBackground(Color.BLUE);
        down2.setSize(80, 40);
        down2.setLocation(730, 100);
        down2.setFocusable(false);
        down2.setFont(new Font("down", Font.PLAIN, 42));
        down2.addActionListener(new CustomActionListener());
        down2.setEnabled(false);
        buttons2[2] = down2;

        right2 = new JButton("\uD83E\uDC6A");
        right2.setBackground(Color.BLUE);
        right2.setSize(80, 40);
        right2.setLocation(810, 60);
        right2.setFocusable(false);
        right2.setFont(new Font("left", Font.PLAIN, 42));
        right2.addActionListener(new CustomActionListener());
        right2.setEnabled(false);
        buttons2[3] = right2;

        left2 = new JButton("\uD83E\uDC68");
        left2.setBackground(Color.BLUE);
        left2.setSize(80, 40);
        left2.setLocation(650, 60);
        left2.setFocusable(false);
        left2.setFont(new Font("left", Font.PLAIN, 42));
        left2.addActionListener(new CustomActionListener());
        left2.setEnabled(false);
        buttons2[4] = left2;

        this.add(playerName1);
        this.add(playerName2);
        this.add(score1);
        this.add(score2);
        this.add(fire1);
        this.add(fire2);
        this.add(right1);
        this.add(left1);
        this.add(left2);
        this.add(right2);
        this.add(up1);
        this.add(down1);
        this.add(up2);
        this.add(down2);
        this.add(angle1);
        this.add(velocity1);
        this.add(tAngle1);
        this.add(tVelocity1);
        this.add(velocity2);
        this.add(angle2);
        this.add(tVelocity2);
        this.add(tAngle2);
        this.add(bulletList1);
        this.add(bulletList2);
        mGameOn = true;

        mGameLoop = new Thread(this);
        mGameLoop.start();
    }

    @Override
    public void run() {
        while (mGameOn) {
            update();
            repaint();
        }
    }

    public void update() {
        if (mTank1.getX() + 100 >= ScreenWidth / 2)
            mTank1.setX(2);
        else if (mTank1.getX() <= 0)
            mTank1.setX(411);
        else if (mTank1.getY() <= 200)
            mTank1.setY(580);
        else if (mTank1.getY() + 59 >= 648)
            mTank1.setY(201);
        else if (mTank2.getX() <= ScreenWidth / 2)
            mTank2.setX(ScreenWidth - 101);
        else if (mTank2.getX() + 100 >= ScreenWidth)
            mTank2.setX(513);
        else if (mTank2.getY() + 59 >= 648)
            mTank2.setY(201);
        else if (mTank2.getY() <= 200)
            mTank2.setY(580);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D Vline = (Graphics2D) g;
        Graphics2D Hline = (Graphics2D) g;
        g.drawImage(tankImage1, (int) mTank1.getX(), (int) mTank1.getY(), this);
        g.drawImage(tankImage2, (int) mTank2.getX(), (int) mTank2.getY(), null);

        Line2D Horizontal = new Line2D.Float(0, 200, 1024, 200);
        Hline.draw(Horizontal);
        Line2D line = new Line2D.Float((float) 1024 / 2, 10, (float) 1024 / 2, 740);
        Vline.draw(line);
        if (shot) {
            if (shot1) {
                g.setColor(Color.BLACK);
                g.fillRect(bullet1.x, bullet1.y, bullet1.width, bullet1.height);
            }
            if (shot2) {
                g.setColor(Color.blue);
                g.fillOval(bullet2.x, bullet2.y, bullet2.width, bullet2.height);
            }
            t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    projectileMotion1();
                }
            });
            t3 = new Thread(new Runnable() {
                @Override
                public void run() {
                    projectileMotion2();
                }
            });
            if (test == true) {
                t1.start();
                try {
                    t1.sleep(24);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (test3 == true) {
                t3.start();
                try {
                    t3.sleep(24);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        bulletAnimation1(g);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            if (test2 == true && !t1.isAlive() && !t3.isAlive()) {
                t2.start();
            }
        }

    }


    public static void bulletAnimation1(Graphics g) throws IOException, InterruptedException {
        final BufferedImage bigShotImage = ImageIO.read(new File("C:\\Users\\abraa\\Desktop\\CSCI 142\\csci142-proj-Abraam00\\res\\bigshot.png"));
        final BufferedImage sniperImage = ImageIO.read(new File("C:\\Users\\abraa\\Desktop\\CSCI 142\\csci142-proj-Abraam00\\res\\sniper.png"));
        final BufferedImage skipperImage = ImageIO.read(new File("C:\\Users\\abraa\\Desktop\\CSCI 142\\csci142-proj-Abraam00\\res\\skipper.png"));
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                if (hit && (bulletList1.getSelectedItem() == skipper)) {
                    g.drawImage(skipperImage, bullet1.x, bullet1.y - 10, null);
                    g.drawImage(skipperImage, bullet1.x, bullet1.y - 10, null);
                }
                if (hit && (bulletList1.getSelectedItem() == bigshot)) {
                    g.drawImage(bigShotImage, bullet1.x, bullet1.y - 10, null);
                    g.drawImage(bigShotImage, bullet1.x, bullet1.y - 10, null);
                }
                if (hit && (bulletList1.getSelectedItem() == sniper)) {
                    g.drawImage(sniperImage, bullet1.x, bullet1.y - 10, null);
                    g.drawImage(sniperImage, bullet1.x, bullet1.y - 10, null);
                }
            }
        };
        Timer timer = new Timer(100, taskPerformer);
        timer.setRepeats(false);
        timer.start();
        Thread.sleep(5000);

    }

    public static void projectileMotion1() {

        if (bullet1.x < ScreenWidth - 10 && bullet1.y < ScreenHeight - 10 && bullet1.y > 245 && !bullet1.intersects(mTank2.getX(), mTank2.getY(), 90, 55)) {
            try {
                t += .1;
                bullet1.y -= (int) ((Float.valueOf(tVelocity1.getText()) / 2 * Math.sin(Math.toRadians(Double.valueOf(tAngle1.getText()))) * t) - (0.5 * 9.8 * t * t));
                bullet1.x += (int) ((Float.valueOf(tVelocity1.getText()) / 2 * Math.cos(Math.toRadians(Double.valueOf(tAngle1.getText())))) * t);
            } catch (Exception ex) {
            }
            if (bullet1.intersects(mTank2.getX(), mTank2.getY(), 90, 55)) {
                hit = true;
                bullet1.setLocation(bullet1.x, bullet1.y);
                if (bulletList1.getSelectedItem() == skipper) {
                    score1.setText(String.valueOf(Integer.valueOf(score1.getText()) + skipper.getDamage()));
                }
                if (bulletList1.getSelectedItem() == bigshot) {
                    score1.setText(String.valueOf(Integer.valueOf(score1.getText()) + bigshot.getDamage()));
                }

                if (bulletList1.getSelectedItem() == sniper) {
                    score1.setText(String.valueOf(Integer.valueOf(score1.getText()) + sniper.getDamage()));
                }
                bulletList1.removeItem(bulletList1.getSelectedItem());
                t1.interrupt();
                test = false;
            }
            if (bullet1.x >= ScreenWidth - 10 || bullet1.y >= ScreenHeight - 10 || bullet1.y <= 245) {
                bulletList1.removeItem(bulletList1.getSelectedItem());
            }

        }

    }

    public static void projectileMotion2() {

        if (bullet2.x > 10 && bullet2.y < ScreenHeight - 10 && bullet2.y > 245 && !bullet2.intersects(mTank1.getX(), mTank1.getY(), 90, 55)) {
            try {
                t += .1;
                bullet2.y -= (int) ((Float.valueOf(tVelocity2.getText()) / 2 * Math.sin(Math.toRadians(Double.valueOf(tAngle2.getText()))) * t) - (0.5 * 9.8 * t * t));
                bullet2.x -= (int) ((Float.valueOf(tVelocity2.getText()) / 2 * Math.cos(Math.toRadians(Double.valueOf(tAngle2.getText())))) * t);
            } catch (Exception ex) {
            }
            if (bullet2.intersects(mTank1.getX(), mTank1.getY(), 90, 55)) {
                hit = true;
                bullet2.setLocation(bullet2.x, bullet2.y);
                if (bulletList2.getSelectedItem() == skipper) {
                    score2.setText(String.valueOf(Integer.valueOf(score2.getText()) + skipper.getDamage()));
                }
                if (bulletList2.getSelectedItem() == bigshot) {
                    score2.setText(String.valueOf(Integer.valueOf(score2.getText()) + bigshot.getDamage()));
                }

                if (bulletList2.getSelectedItem() == sniper) {
                    score2.setText(String.valueOf(Integer.valueOf(score2.getText()) + sniper.getDamage()));
                }
                bulletList2.removeItem(bulletList2.getSelectedItem());
                t3.interrupt();
                test3 = false;
            }
            if (bullet2.x <= 10 || bullet2.y >= ScreenHeight - 10 || bullet2.y <= 245) {
                bulletList2.removeItem(bulletList2.getSelectedItem());
            }
            if (bulletList2.getSelectedItem() == null) {
                int s1 = Integer.valueOf(score1.getText());
                int s2 = Integer.valueOf(score2.getText());
                try {
                    File file = new File("records.txt");
                    FileWriter fw = new FileWriter(file, true);
                    PrintWriter pw = new PrintWriter(fw);
                    pw.print("Player1: " + score1.getText() + "    ___    Player2: " + score2.getText() + "\n");
                    pw.close();
                } catch (IOException ex) {
                }
                if (s1 > s2) {
                    Object[] options = {"OK"};
                    int input = JOptionPane.showOptionDialog(null, " The winner is Player1! ", "Winner Winner Chicken Dinner", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    if (input == 0)
                        System.exit(0);
                } else if (s2 > s1) {
                    Object[] options = {"OK"};
                    int input = JOptionPane.showOptionDialog(null, " The winner is Player2! ", "Winner Winner Chicken Dinner", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    if (input == 0)
                        System.exit(0);
                } else {
                    Object[] options = {"OK"};
                    int input = JOptionPane.showOptionDialog(null, " It's a tie! ", "Tie Tie No Dinner", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                    if (input == 0)
                        System.exit(0);
                }
            }
        }
    }

    public static void disableButtons(JButton[] b) {
        for (JButton button : b)
            button.setEnabled(false);
    }

    public static void enableButtons(JButton[] b) {
        for (JButton button : b)
            button.setEnabled(true);
    }

    public static void disableText(JTextField[] t) {
        for (JTextField text : t)
            text.setEnabled(false);
    }

    public static void enableText(JTextField[] t) {
        for (JTextField text : t)
            text.setEnabled(true);
    }

    static class CustomActionListener extends Component implements
            ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == right1)
                mTank1.updatePositiveX();
            else if (e.getSource() == up1)
                mTank1.updateNegativeY();
            else if (e.getSource() == down1)
                mTank1.updatePositiveY();
            else if (e.getSource() == left1)
                mTank1.updateNegativeX();
            else if (e.getSource() == up2)
                mTank2.updateNegativeY();
            else if (e.getSource() == down2)
                mTank2.updatePositiveY();
            else if (e.getSource() == right2)
                mTank2.updatePositiveX();
            else if (e.getSource() == left2)
                mTank2.updateNegativeX();

            if (e.getSource() == fire1) {
                int bx = (int) (mTank1.getX() + 100);
                int by = (int) (mTank1.getY());
                bullet1 = new Rectangle(bx, by, 10, 8);
                shot = true;
                shot1 = true;
                t = 0.0F;
                disableButtons(buttons1);
                disableText(textFields1);
                bulletList1.setEnabled(false);
                enableButtons(buttons2);
                enableText(textFields2);
                bulletList2.setEnabled(true);
                test = true;
                test2 = true;

            }
            if (e.getSource() == fire2) {
                int bx = (int) (mTank2.getX());
                int by = (int) (mTank2.getY());
                bullet2 = new Rectangle(bx, by, 10, 8);
                shot = true;
                shot2 = true;
                t = 0.0F;
                enableButtons(buttons1);
                enableText(textFields1);
                bulletList1.setEnabled(true);
                disableButtons(buttons2);
                disableText(textFields2);
                bulletList2.setEnabled(false);
                test3 = true;
                test2 = true;
            }
        }
    }
}