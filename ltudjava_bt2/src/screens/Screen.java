package screens;

import configs.AppConfig;

import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame {
    public Screen(){
        super(AppConfig.title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(800,400));
        setMinimumSize(new Dimension(800,400));
//        setVisible(true);
    }

    public void changeScreen(Screen screen){
        screen.setVisible(true);
        this.dispose();
    }

}
