package seabattle;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//Класс представляющий клетку игрового поля
public class Cell extends JPanel { //расширяет класс JPanel

    private static int clickCounter = 0; //счётчик щелчков мыши
    private static int shipCounter = 10; //счётчик кораблей

    private int x; //координата по горизонтали
    private int y; //координата по вертикали
    private CellStatus status; //состояние клетки
    private Ship ship; //ссылка на корабль. Равно null, если корабля нет
    
    private Cell getThisCell(){
        return this;
    }

    //внутренний класс для обработки нажатий мыши
    private class onClick implements MouseListener {

        //метод вызывается при щелчке по клетке
        @Override
        public void mouseClicked(MouseEvent me) {
            clickCounter++; //увеличиваем счётчик щелчков мыши
            Graphics2D gr = (Graphics2D) getGraphics();
            int w = getSize().width;
            int h = getSize().height;

            if (status == CellStatus.ship) {
                status = CellStatus.cross;//меняем статус клетки
                if (ship.reduceHealth() <= 0) { //если у корабля подбиты все палубы
                    shipCounter--; //уменьшаем счётчик кораблей                    
                    Field.setShipAreaChecked(ship); //отмечаем клетки вокруг него проверенными                    
                    if (shipCounter <= 0) {
                        repaint();
                        JOptionPane.showMessageDialog(getThisCell(), "Игра закончена за " + clickCounter + " шагов!");                        
                    }
                }
            } else { //иначе 
                if (status != CellStatus.cross) { //если там не крестик
                    status = CellStatus.dot; //рисуем точку
                }
            }
            repaint();
        }

        @Override
        public void mousePressed(MouseEvent me) {
        }

        @Override
        public void mouseReleased(MouseEvent me) {
        }

        @Override
        public void mouseEntered(MouseEvent me) {
        }

        @Override
        public void mouseExited(MouseEvent me) {
        }

    }

    //конструктор
    public Cell(int x, int y, CellStatus status) {
        this.x = x;
        this.y = y;
        this.status = status;
        this.addMouseListener(new onClick()); //добавляем клетке обработку нажатия мыши        
    }

    public CellStatus getStatus() {
        return status;
    }

    public int getCoordX() {
        return x;
    }

    public int getCoordY() {
        return y;
    }

    public void setStatus(CellStatus status) {
        this.status = status;
    }

    //метод выполняищий отрисовку клетки
    public void paintComponent(Graphics grr) {
        Graphics2D gr = (Graphics2D) grr;
        gr.setColor(Color.BLUE);//установка цвета моря
        int w = getSize().width;
        int h = getSize().height;
        gr.fillRect(0, 0, w, h);//рисуем закрашенный прямоугольник
        //gr.setColor(Color.BLACK);//установка цвета 
        switch (status) {
            case dot:
                drawDot(gr);
                break;
            case cross:
                drawCross(gr);
                break;

        }

        //   gr.drawString(x + " " + y, 10, 10);
    }

    void setShip(Ship ship) {
        this.ship = ship;
    }

    public Ship getShip() {
        return ship;
    }

    //метод для отрисовки точки
    public void drawDot(Graphics gr) {
        int w = getSize().width; //получаем ширину клетки
        int h = getSize().height;//получаем высоту клетки              
        gr.setColor(Color.BLACK);//установка цвета точки
        int d = h / 3; //размер точки
        gr.fillOval((w - d) / 2, (h - d) / 2, d, d); //вывод точки
    }

    private void drawCross(Graphics2D gr) {
        int w = getSize().width;
        int h = getSize().height;
        gr.setColor(Color.GREEN);//установка цвета моря
        gr.fillRect(0, 0, w, h);//рисуем закрашенный прямоугольник
        gr.setColor(Color.BLACK);  //установка цвета 
        gr.setStroke(new BasicStroke(10f)); //устанавливаем ширину линии
        gr.drawLine(0, 0, w, h); //рисуем крестик
        gr.drawLine(w, 0, 0, h);
    }

}
