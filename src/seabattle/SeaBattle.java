package seabattle;

import java.awt.Container;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.JFrame;
import static seabattle.CellStatus.*;

//главный класс программы "Морской бой"
public class SeaBattle {
    
    public static final int SIZE = 10; //размер поля
    
    private static Random random = new Random();  //генератор случайных чисел  

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame(); //создаём окно
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //при закрытии окна завершается работа программы
        frame.setBounds(100, 100, 600, 600); //задаём размеры окна
        frame.setTitle("Односторонний морской бой"); //заголовок окна
        
        SeaPanel seaPanel = new SeaPanel(); //создаётся объект панель
        GridLayout layout = new GridLayout(SIZE, SIZE, 2, 2); //задаём правило размещения элементов в окне
        seaPanel.setLayout(layout);
        Container contentPane = frame.getContentPane(); //получаем контейнер
        
        
        Cell[][] cells = Field.getCells(); //получаем массв клеток поля

        for (int i = 0; i < SIZE; i++) { //цикл по строкам
            for (int j = 0; j < SIZE; j++) { //цикл по столбцам
                Cell c = new Cell(i, j, empty); //создаются объекты "Клетка"
                seaPanel.add(c); // выводятся на окно
                cells[i][j] = c; //заносятся в массив
            }
        }
        
        //создаём корабли и размещаем на поле
        int x;
        int y;
        boolean vertical;
        Ship ship;
        
        //четырёхпалубный        
        do { //цикл попыток создания и размещения корабля
            x = random.nextInt(SIZE - 3); //случайная координата по оси OX
            y = random.nextInt(SIZE - 3); //случайная координата по оси OY
            vertical = random.nextBoolean(); //случайная ориентация корабля в пространстве
            ship = new Ship(x, y, vertical, 4); //создаём 4х палубный корабль
        } while (!Field.placeShip(ship)); //пробуем разместить такой корабль на поле, если получилось, то выходим из цикла
        
        //трёхпалубные
        for (int i = 0; i < 2; i++)
        do { //цикл попыток создания и размещения корабля
            x = random.nextInt(SIZE - 2); //случайная координата по оси OX
            y = random.nextInt(SIZE - 2); //случайная координата по оси OY
            vertical = random.nextBoolean(); //случайная ориентация корабля в пространстве
            ship = new Ship(x, y, vertical, 3); //создаём 3х палубный корабль
        } while (!Field.placeShip(ship)); //пробуем разместить такой корабль на поле, если получилось, то выходим из цикла
        
        
        //двухпалубный        
        for (int i = 0; i < 3; i++)
        do { //цикл попыток создания и размещения корабля
            x = random.nextInt(SIZE - 1); //случайная координата по оси OX
            y = random.nextInt(SIZE - 1); //случайная координата по оси OY
            vertical = random.nextBoolean(); //случайная ориентация корабля в пространстве
            ship = new Ship(x, y, vertical, 2); //создаём 2х палубный корабль
        } while (!Field.placeShip(ship)); //пробуем разместить такой корабль на поле, если получилось, то выходим из цикла
        
        //однопалубный       
        for (int i = 0; i < 4; i++)
        do { //цикл попыток создания и размещения корабля
            x = random.nextInt(SIZE); //случайная координата по оси OX
            y = random.nextInt(SIZE); //случайная координата по оси OY
            vertical = random.nextBoolean(); //случайная ориентация корабля в пространстве
            ship = new Ship(x, y, vertical, 1); //создаём однопалубный корабль
        } while (!Field.placeShip(ship)); //пробуем разместить такой корабль на поле, если получилось, то выходим из цикла        

        contentPane.add(seaPanel); //помещаем панель в контейнер

        frame.setVisible(true); //делаем окно видимым
    }

}
