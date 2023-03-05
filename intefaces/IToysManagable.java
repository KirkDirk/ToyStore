package intefaces;

import models.Toy1;

/** Интерфейс управления Игрушками в программе */
public interface IToysManagable {
    /**
     * Метод добавления Игрушки в базу розыгрыша
     * @param toy - класс Игрушки
     */
    void createToy();

    int getLastIDToy();
}
