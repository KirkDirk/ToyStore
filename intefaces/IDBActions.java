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
     * @return List<Toy1>
     * @throws IOException
     * @throws FileNotFoundException
     */
    List<Toy1> getAllToys() throws FileNotFoundException, IOException;

    /**
     * Перезаписываем список всех Игрушек в базу розыгрыша
     * @param allToys список всех Игрушек
     * @throws FileNotFoundException
     * @throws IOException
     */
    void saveAllToys(List<Toy1> allToys) throws FileNotFoundException, IOException;

    /**
     * Удаляем игрушку из базы по индексу, обновляем индексы в базе
     * @param idRemovedToy - номер удаляемой игрушки
     * @throws FileNotFoundException
     * @throws IOException
     */
    void deleteToy(int idRemovedToy) throws FileNotFoundException, IOException;

    /**
     * Добавляем статистику по победителю розыгрыша
     * @param prizeToy выигранная игрушка
     * @param nameWinner имя победителя
     * @throws FileNotFoundException
     * @throws IOException
     */
    void addWinner(Toy1 prizeToy, String nameWinner) throws FileNotFoundException, IOException;

    /**
     * Добавляем запись в файл выдачи призовых игрушек
     * @param nameToy название игрушки
     * @param nameWinner имя победителя
     * @throws FileNotFoundException
     * @throws IOException
     */
    void addToyForDelivery(String nameToy, String nameWinner) throws FileNotFoundException, IOException;

    /**
     * Записываем преобразованные в строку данные в указанный файл. Используем для добавления
     * новых игрушек, для перезаписи все базы разыгрываемых игрушек, для записи победителей
     * розыгрыша, для добавления в список выдачи призовых игрушек
     * @param stringData преобразованные данные
     * @param fileName имя (относительный путь) файла
     * @param appendMode режим записи: тру - добавляем, фолс - перезаписываем
     * @throws FileNotFoundException
     * @throws IOException
     */
    void saveStringToFile(String stringData, String fileName, boolean appendMode) throws FileNotFoundException, IOException;

    /**
     * Получаем из файла список всех Игрушек к выдаче, преобразованный в строку
     * @return - String
     * @throws FileNotFoundException
     * @throws IOException
     */
    String getAllPrizeToys() throws FileNotFoundException, IOException;

    /**
     * 
     * @param numberForDlvr
     * @return
     * @throws IOException
     */
	String[] getPrizeToys(int numberForDlvr) throws IOException;

}
