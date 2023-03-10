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
     * @return
     * @throws IOException
     */
    int getLastIDToy() throws IOException;

    /**
     * Метод получения списка наименований Игрушек в базе розыгрыша
     * @return 
     * @throws IOException
     * @throws FileNotFoundException
     */
    List<Toy1> getAllToys() throws FileNotFoundException, IOException;

    /**
     * Метод для замены значения частоты выпадения Игрушки на новое
     * @param numberForEdit индекс выбранной Игрушки
     * @param frqDlvrEdit новое значения частоты выбранной Игрушки
     * @return Toy1
     * @throws IOException
     * @throws FileNotFoundException
     */
    Toy1 changingFrq(int numberForEdit, int frqDlvrEdit) throws FileNotFoundException, IOException;
    
}
