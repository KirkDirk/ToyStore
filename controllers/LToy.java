package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import intefaces.IToysManagable;
import models.Toy1;

public class LToy {
    private IToysManagable iToysManagable;

    public LToy(IToysManagable iToysManagable) {
        this.iToysManagable = iToysManagable;
    }

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
     * Получаем список всех Игрушек
     * @return List-Toy1
     * @throws FileNotFoundException
     * @throws IOException
     */
    public List<Toy1> getAllToys() throws FileNotFoundException, IOException {
        return iToysManagable.getAllToys();
    }

    /**
     * Заменяем значение частоты выпадения для выбранной Игрушки
     * @param numberForEdit
     * @param frqDlvrEdit
     * @return Toy1
     * @throws FileNotFoundException
     * @throws IOException
     */
    public Toy1 changingFrq(int numberForEdit, int frqDlvrEdit) throws FileNotFoundException, IOException {
        return iToysManagable.changingFrq(numberForEdit, frqDlvrEdit);
    }

    /**
     * Разыгрываем призовую игрушку
     * @return Toy1
     * @throws FileNotFoundException
     * @throws IOException
     */
    public Toy1 getPrizeToy() throws FileNotFoundException, IOException {
        return iToysManagable.getPrizeToy();
    }

    /**
     * Записываем результаты розыгрыша игрушки
     * @param prizeToy экземпляр класса Toy1, определенныq в getPrizeToy
     * @param nameWinner имя победителя
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void saveResultRaffle(Toy1 prizeToy, String nameWinner) throws FileNotFoundException, IOException {
        iToysManagable.saveResultRaffle(prizeToy, nameWinner);
    }

}
