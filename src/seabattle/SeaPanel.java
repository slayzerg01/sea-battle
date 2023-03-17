package seabattle;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class SeaPanel extends JPanel {

    public void paintComponent(Graphics gr) {
        gr.setColor(Color.cyan);  //установка цвет моря
        gr.fillRect(0, 0, getSize().width, getSize().height);//рисуем закрашенный прямоугольник        
    }

}
