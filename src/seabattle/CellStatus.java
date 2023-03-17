/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seabattle;
//список состояний в которых может быть клетка поля
public enum CellStatus {
    empty, //пустая
    ship, //корабль
    cross, //подбитый корабль
    dot, //проверенная выстрелом, но пустая
    occupied //занятая
}
