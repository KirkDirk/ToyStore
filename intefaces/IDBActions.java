package intefaces;

import java.io.FileNotFoundException;
import java.io.IOException;

import models.Toy1;

/** Интерфейс взаимодейстия с файлами хранилища */
public interface IDBActions {
    
    /**
     * Получаем последний ID Игрушек из базы розыгрыша
     * @return int
     * @throws IOException
     */
    int getLastIDToy() throws IOException;

    /**
     * Записываем в базу розыгрыша новую Игрушку
     * @param toy записываемый экземпляр класса
     * @throws IOException
     * @throws FileNotFoundException
     */
    void saveNewToyToStorage(Toy1 toy) throws FileNotFoundException, IOException;
}
