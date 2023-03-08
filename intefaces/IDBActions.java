package intefaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

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

    /**
     * Получаем список всех Игрушек в базе розыгрыша
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     */
    List<Toy1> getAllToys() throws FileNotFoundException, IOException;

    /**
     * Перезаписываем списко всех Игрушек в базу розыгрыша
     * @param allToys список всех Игрушек
     * @throws FileNotFoundException
     * @throws IOException
     */
    void saveAllToys(List<Toy1> allToys) throws FileNotFoundException, IOException;

}
