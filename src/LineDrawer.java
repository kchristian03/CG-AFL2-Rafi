import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

public class LineDrawer extends JPanel {

    public static Double[] x1 = {10.0, 20.0, 30.0, 30.0, 40.0, 40.0, 30.0, 20.0, 20.0, 10.0};
    public static Double[] y1 = {10.0, 10.0, 30.0, 10.0, 10.0, 50.0, 50.0, 30.0, 50.0, 50.0};
    public static Double[] x2 = {20.0, 30.0, 30.0, 40.0, 40.0, 30.0, 20.0, 20.0, 10.0, 10.0};
    public static Double[] y2 = {10.0, 30.0, 10.0, 10.0, 50.0, 50.0, 30.0, 50.0, 50.0, 10.0};

    public static Double[] x1_dilate = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    public static Double[] y1_dilate = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    public static Double[] x2_dilate = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    public static Double[] y2_dilate = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

    public static Double[] x1_rotate = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    public static Double[] y1_rotate = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    public static Double[] x2_rotate = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    public static Double[] y2_rotate = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

    public static Double degr = 0.0;
    public static Double dil = 0.0;
    public static int w = 0;
    public static int h = 0;


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));


        //draw normal line
        for (int i = 0; i < x1.length; i++) {
            Line2D line = new Line2D.Double(x1[i], y1[i], x2[i], y2[i]);
            g2.draw(line);
        }

        g2.setColor(Color.BLUE);
        g2.setTransform(AffineTransform.getScaleInstance(dil, dil));

        //draw dilated line
        for (int i = 0; i < x1.length; i++) {
            Line2D line = new Line2D.Double(x1[i], y1[i], x2[i], y2[i]);
            g2.draw(line);
            x1_dilate[i] = g2.getTransform().getScaleX() * x1[i];
            y1_dilate[i] = g2.getTransform().getScaleY() * y1[i];
            x2_dilate[i] = g2.getTransform().getScaleX() * x2[i];
            y2_dilate[i] = g2.getTransform().getScaleY() * y2[i];
        }

        g2.setColor(Color.RED);
        g2.setTransform(AffineTransform.getRotateInstance(Math.toRadians(degr), w * 0.5, h * 0.5));

        //draw rotated line
        for (int i = 0; i < x1.length; i++) {
            Line2D line = new Line2D.Double(x1_dilate[i], y1_dilate[i], x2_dilate[i], y2_dilate[i]);
            g2.draw(line);
            x1_rotate[i] = x1_dilate[i];
            y1_rotate[i] = y1_dilate[i];
            x2_rotate[i] = x2_dilate[i];
            y2_rotate[i] = y2_dilate[i];
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Line Drawer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new LineDrawer());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setResizable(false);
        w = frame.getWidth();
        h = frame.getHeight();

        System.out.println(w + " " + h);


        for (int i = 0; i < x1.length; i++) {
            x1[i] = x1[i] * 2 + (w * 0.5);
            y1[i] = y1[i] * 2 + (h * 0.5);
            x2[i] = x2[i] * 2 + (w * 0.5);
            y2[i] = y2[i] * 2 + (h * 0.5);
        }


        frame.setVisible(true);

        //make input dialog for dilatation
        String dilatation_input = JOptionPane.showInputDialog("Masukkan nilai untuk dilatasi");
        Double dilatation_k = Double.parseDouble(dilatation_input);
        dilatation(dilatation_k);
        frame.repaint();

        //make input dialog for rotation
        String rotation_input = JOptionPane.showInputDialog("Masukkan nilai untuk rotasi");
        Double rotation_deg = Double.parseDouble(rotation_input);
        rotation(rotation_deg);
        frame.repaint();


    }

    //make function to calculate dilatation from user input
    public static void dilatation(Double k) {
        dil = k;
    }

    //make function to calculate rotation from user input
    public static void rotation(Double deg) {
        degr = deg;
    }
}
