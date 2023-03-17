package seabattle;

import static seabattle.SeaBattle.SIZE;

//класс для работы с массивом клеток игрового поля
public class Field {

    //массив клеток игрового поля
    private static Cell[][] cells = new Cell[SIZE][SIZE];

    //помещение клетки на поле
    public static void setCell(int i, int j, Cell cell) {
        cells[i][j] = cell;
    }

    //получение клетки с поля
    public static Cell[][] getCells() {
        return cells;
    }

    //закрытый метод для установки корабля на поле. Ставит корабль и меняет статусы клеток
    private static void setShipInCell(Cell cell, Ship ship) {
        cell.setShip(ship); //устанавливаем корабль в клетку
        cell.setStatus(CellStatus.ship); //меняем статус клетки на "корабль"
        int x = cell.getCoordX(); //получаем координаты клетки
        int y = cell.getCoordY();
        //перебор клеток вокруг корабля, чтобы отметить их занятыми
        for (int i = x - 1; i < x + 2; i++) { //перебор от одной клетки влево до одной вправо
            for (int j = y - 1; j < y + 2; j++) { //перебор от одной клетки вверх до одной вниз 
                if ((i >= 0) && (i < 10) && (j >= 0) && (j < 10)) { //проверка что клетка не за пределами игрового поля
                    Cell c = cells[i][j]; //получаем клетку
                    if (c.getShip() == null) { //Если в клетке не находится корабль
                        c.setStatus(CellStatus.occupied); //меняем статус клетки на "занято"
                    }
                }
            }
        }
    }

    //метод проверяет можно ли поместить корабль в указанные координаты, 
    //и если возможно, то размещает его там
    public static boolean placeShip(Ship ship) {
        int x = ship.getX(); //получаем координаты ..
        int y = ship.getY(); //..куда хотим поместить корабль 
        int size = ship.getSize(); //получаем размер корабля
        //проверка корабль можно разместить
        if (cells[x][y].getStatus() != CellStatus.empty) {//если клетка не "пустая", то...
            return false; //выходим из метода и возвращаем false
        }
        int xStep = 0; //шаг приращения координат по OX
        int yStep = 0; //шаг приращения координат по OY
        if (ship.isVertical()) { //если корабль ориентирован вертикально, то...
            yStep = 1; //шаг по оси OY будет равен 1
        } else {
            xStep = 1; //шаг по оси OX будет равен 1
        }
        for (int i = 1; i < size; i++) { //цикл проверки размещения корабля на поле 
            if (cells[x + i * xStep][y + i * yStep].getStatus() != CellStatus.empty) { //если клетка не "пустая", то...
                return false; //выходим из метода и возвращаем false
            }
        }
        //проверка пройдена успешно, размещаем корабль на поле        
        for (int i = 0; i < size; i++) { //кол-во повторов цикла равно длине корабля
            Cell c = cells[x + i * xStep][y + i * yStep]; //получаем клету, где будет корабль
            setShipInCell(c, ship); //устанавливаем в клетку корабль
        }
        return true; //возвращаем результат об успешном размещении корабля
    }

    //метод отмечает точками область вокруг подбитого корабля
    public static void setShipAreaChecked(Ship ship) {
        int shipX = ship.getX(); //получаем координаты ..
        int shipY = ship.getY(); //..куда хотим поместить корабль 
        int size = ship.getSize(); //получаем размер корабля        
        int xStep = 0; //шаг приращения координат по OX
        int yStep = 0; //шаг приращения координат по OY
        if (ship.isVertical()) { //если корабль ориентирован вертикально, то...
            yStep = 1; //шаг по оси OY будет равен 1
        } else {
            xStep = 1; //шаг по оси OX будет равен 1
        }
        for (int l = 0; l < size; l++) { //кол-во повторов цикла равно длине корабля
            Cell cell = cells[shipX + l * xStep][shipY + l * yStep]; //получаем клету, где корабль

            int x = cell.getCoordX(); //получаем координаты клетки
            int y = cell.getCoordY();
            //перебор клеток вокруг корабля, чтобы отметить их точками
            for (int i = x - 1; i < x + 2; i++) { //перебор от одной клетки влево до одной вправо
                for (int j = y - 1; j < y + 2; j++) { //перебор от одной клетки вверх до одной вниз 
                    if ((i >= 0) && (i < 10) && (j >= 0) && (j < 10)) { //проверка что клетка не за пределами игрового поля
                        Cell c = cells[i][j]; //получаем клетку
                        if (c.getShip() == null) { //Если в клетке не находится корабль
                            c.setStatus(CellStatus.dot);
                            c.repaint();
                        }
                    }
                }
            }
        }
    }

}
