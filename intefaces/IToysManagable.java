package intefaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

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
     * @return int
     * @throws IOException
     */
    int getLastIDToy() throws IOException;

    /**
     * Метод получения списка наименований всех Игрушек в базе розыгрыша
     * @return List<Toy1>
     * @throws IOException
     * @throws FileNotFoundException
     */
    List<Toy1> getAllToys() throws FileNotFoundException, IOException;

    /**
     * Метод для замены значения частоты выпадения Игрушки на новое
     * @param numberForEdit - индекс выбранной Игрушки
     * @param frqDlvrEdit - новое значения частоты выбранной Игрушки
     * @return Toy1
     * @throws IOException
     * @throws FileNotFoundException
     */
    Toy1 changingFrq(int numberForEdit, int frqDlvrEdit) throws FileNotFoundException, IOException;

    /**
     * Метод получения призовой игрушки
     * @return Toy
     * @throws FileNotFoundException
     * @throws IOException
     */
    Toy1 getPrizeToy() throws FileNotFoundException, IOException;

    /**
     * Метод для регистрации результатов розыгрыша призовой Игрушки
     * @param prizeToy призовая игрушка
     * @param nameWinner имя победителя
     * @throws FileNotFoundException
     * @throws IOException
     */
    void saveResultRaffle(Toy1 prizeToy, String nameWinner) throws FileNotFoundException, IOException;

    /**
     * Метод получения из файла списка Игрушек для выдачи победителю
     * @return String
     * @throws FileNotFoundException
     * @throws IOException
     */
    String getListToyDlrv() throws FileNotFoundException, IOException;

    /**
     * Метод получения данных из файла по выдаваемой Игрушке и удалению 
     * из списка выдачи
     * @param numberForDlvr номер запрашивой Игрушки
     * @return массив данных по игрушке
     * @throws IOException
     */
    String[] getArrPresentToy(int numberForDlvr) throws IOException;
    
}
