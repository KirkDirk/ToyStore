package intefaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.Toy1;

/** Класс для управления Игрушками в базе розыгрыша */
public class ToysMagable implements IToysManagable {

    /** Экземпляр интерфейса работы с базами данных */
    private IDBActions idbActions;

    /**
     * Интерфейс управления моделями "Игрушки"
     * @param idbActions интерфейс работы с БД
     */
    public ToysMagable(IDBActions idbActions) {
        this.idbActions = idbActions;
    }

    @Override
    public void createToy(Toy1 toy) throws FileNotFoundException, IOException {
        idbActions.saveNewToyToStorage(toy);
    }

    @Override
    public int getLastIDToy() throws IOException {
        return idbActions.getLastIDToy();
    }

    @Override
    public List<Toy1> getAllToys() throws FileNotFoundException, IOException {
        return idbActions.getAllToys();
    }

    @Override
    public Toy1 changingFrq(int numberForEdit, int frqDlvrEdit) throws FileNotFoundException, IOException {
        List<Toy1> allToys = idbActions.getAllToys();
        for (Toy1 toy1 : allToys) {
            if (toy1.getIdToy() == numberForEdit) {
                toy1.setFrqDlvrToy(frqDlvrEdit);
            }
        }
        idbActions.saveAllToys(allToys);
        Toy1 toy = allToys.get(numberForEdit - 1);
        return toy;
    }

    @Override
    public Toy1 getPrizeToy() throws FileNotFoundException, IOException {
        /**
         * Схема розыгрыша: получаем диапазон состоящий из суммы величин
         * по всем игрушкам = кол-во * частоту выпадения игрушки, расположенных
         * один за другим (типа диапазон интервалов) и рэндомно берем из него
         * точку. От того в какой интервал попадает рэндом, определяем индекс
         * игрушки. Таким образом, чем больше игрушка представлена количеством и
         * чем больше частота ее выпадения, тем шире будет интервал и вероятнее
         * выпадение
         */
        List<Toy1> allToys = idbActions.getAllToys(); // получаем список всех игрушек в базе
        List<Integer> arrLimits = new ArrayList<>(); // массив для получения точек диапазона для работы Рэндом
        int edgeLimits = 0; // - конец диапазона для работы Рэндом
        /**
         * формируем диапазон с точками для работы РЭндом и определения индекса призовой
         * игрушки 
        */
        for (Toy1 toy : allToys) {
            edgeLimits += toy.getCountToy() * toy.getFrqDlvrToy();
            arrLimits.add(edgeLimits);
        }
        /** получаем точку Рэндом для призовой игрушки */
        double prizePoint = Math.random() * (edgeLimits + 1);
        /** находим индекс призовой игрушки */
        boolean bPrizeToy = false;
        int idPrizeToy = 0;
        for (Integer eInt : arrLimits) {
            if (bPrizeToy == false && eInt > prizePoint) {
                idPrizeToy = arrLimits.indexOf(eInt);
                bPrizeToy = true;
            }
        }
        return allToys.get(idPrizeToy);
    }

    @Override
    public void saveResultRaffle(Toy1 prizeToy, String nameWinner) throws FileNotFoundException, IOException {
        List<Toy1> allToys = idbActions.getAllToys(); // получаем список всех игрушек в базе
        int countToy = prizeToy.getCountToy()-1; // уменьшаем количество игрушек на одну розыгранную
        /** Если игрушка розыгранного типа закончилась, вымарываем ее из базы, иначе перезаписываем
         * в базу с уменьшенным количеством 
        */
        if (countToy == 0) {
            idbActions.deleteToy(prizeToy.getIdToy());
        }
        else {
            prizeToy.setCountToy(countToy);
            allToys.set(prizeToy.getIdToy()-1, prizeToy);
            idbActions.saveAllToys(allToys);
        }
        /** Пополняем список победителей и добавляем запись в лист выдачи игрушек */
        idbActions.addWinner(prizeToy, nameWinner);
        idbActions.addToyForDelivery(prizeToy.getNameToy(), nameWinner);
    }

    @Override
    public String getListToyDlrv() throws FileNotFoundException, IOException {
        return idbActions.getAllPrizeToys();
        //throw new UnsupportedOperationException("Unimplemented method 'getToyDlrv'");
    }

    @Override
    public String[] getArrPresentToy(int numberForDlvr) throws IOException {
        return idbActions.getPrizeToys(numberForDlvr);
        //throw new UnsupportedOperationException("Unimplemented method 'getArrPresentToy'");
    }

}
