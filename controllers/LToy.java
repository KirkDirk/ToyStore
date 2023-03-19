package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import intefaces.IToysManagable;
import models.Toy1;

public class LToy {
    /**
     * интерфейс управления моделями
     */
    private IToysManagable iToysManagable;

    /**
     * Конструктор контроллера
     * @param iToysManagable интерфейс управления моделями
     */
    public LToy(IToysManagable iToysManagable) {
        this.iToysManagable = iToysManagable;
    }

    /**
     * Создаем и записываем в базу новую игрушку
     * @param toy экземпляр класса Toy1
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void getNewToy(Toy1 toy) throws FileNotFoundException, IOException {
        iToysManagable.createToy(toy);                
    };

    /**
     * Получаем ID последней Игрушки в базе розыгрыша
     * @return int
     * @throws IOException
     */
    public int getLastIdToy() throws IOException {
        return iToysManagable.getLastIDToy();        
    }

    /**
     * Получаем список всех Игрушек из базы данных в формате списка моделей
     * @return List-Toy1
     * @throws FileNotFoundException
     * @throws IOException
     */
    public List<Toy1> getAllToys() throws FileNotFoundException, IOException {
        return iToysManagable.getAllToys();
    }

    /**
     * Заменяем значение частоты выпадения для выбранной Игрушки
     * @param numberForEdit номер выбранной Игрушки
     * @param frqDlvrEdit новое значения частоты выпадения
     * @return Toy1
     * @throws FileNotFoundException
     * @throws IOException
     */
    public Toy1 changingFrq(int numberForEdit, int frqDlvrEdit) throws FileNotFoundException, IOException {
        return iToysManagable.changingFrq(numberForEdit, frqDlvrEdit);
    }

    /**
     * Разыгрываем призовую игрушку через рандом. Результат розыгрыша выводим на экран
     * @return Toy1
     * @throws FileNotFoundException
     * @throws IOException
     */
    public Toy1 getPrizeToy() throws FileNotFoundException, IOException {
        return iToysManagable.getPrizeToy();
    }

    /**
     * Записываем результаты розыгрыша игрушки. Запись осуществляем в формате: 
     * Имя победителя; наименование Игрушки; дата-время. Пополняются два файла: 
     * исторический файл всех победителей и список игрушек к выдаче.
     * @param prizeToy экземпляр класса Toy1, определенный в getPrizeToy
     * @param nameWinner имя победителя
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void saveResultRaffle(Toy1 prizeToy, String nameWinner) throws FileNotFoundException, IOException {
        iToysManagable.saveResultRaffle(prizeToy, nameWinner);
    }

    /**
     * Получаем список Игрушек к выдаче победителям, конвертированный в строку.
     * Список нужен для вывода на экран, чтобы выбрать ту Игрушку, которую выдать. 
     * Предполагается, что Игрушки забирают быстро и список небольшой 
     * @return String
     * @throws FileNotFoundException
     * @throws IOException
     */
    public String getListToyDlvr() throws FileNotFoundException, IOException {
        return iToysManagable.getListToyDlrv();
    }

    /**
     * Получаем данные из списка игрушек для выдачи по конкретному указателю. 
     * Конвертируем в массив для удобства вывода на экран
     * @param numberForDlvr - номер выбранной Игрушки из списка
     * @return
     * @throws IOException
     */
    public String[] getArrPresentToy(int numberForDlvr) throws IOException {
        return iToysManagable.getArrPresentToy(numberForDlvr);
    }

}
