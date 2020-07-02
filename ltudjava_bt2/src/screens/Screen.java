package screens;

import javax.swing.*;

public class Screen extends JPanel {
    JFrame parent;
    public Screen(JFrame parent){
        super();
        this.parent = parent;
    }

    public void changeScreen(Screen screen){
        parent.remove(this);
        parent.add(screen);
        parent.pack();
    }

}
