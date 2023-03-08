package intefaces;

import java.io.FileNotFoundException;
import java.io.IOException;

import models.Toy1;

/** Интерфейс управления Игрушками в программе */
public interface IToysManagable {
    /**
     * Метод добавления Игрушки в базу розыгрыша
     * @param toy - класс Игрушки
     * @throws IOException
     * @throws FileNotFoundException
     */
    void createToy(Toy1 toy) throws FileNotFoundException, IOException;

    /**
     * Метод получения ID последней Игрушки в базе розыгрыша
     * @return
     * @throws IOException
     */
    int getLastIDToy() throws IOException;
}
