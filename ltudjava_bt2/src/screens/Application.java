package screens;

import javax.swing.*;
import java.awt.*;

public class Application{
    private final JFrame window = new JFrame("18120509");
    public void start(){
        window.add(new LoginScreen(window));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setPreferredSize(new Dimension(800,400));
        window.setMinimumSize(new Dimension(800,400));
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
