package seabattle;
//класс представляющий корабль
public class Ship {
    private int x; //координата корабля по горизонтали
    private int y; //координата корабля по вертекали
    private boolean vertical; //равно true, если корабль расположен вертикально
    private int size; //размер
    private int health; //здоровье

    //конструктор
    public Ship(int x, int y, boolean vertical, int size) {
        this.x = x;
        this.y = y;
        this.vertical = vertical;
        this.size = size;
        health = size;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVertical() {
        return vertical;
    }

    public int getSize() {
        return size;
    }

    //метод уменьшает здоровье корабля на 1 и возвращает оставшееся значение
    public int reduceHealth() {
        health--; //уменьшаем здоровье корабля на 1 
        return health;
    }    
    
    
    
}
