package models;

/** Класс Игрушка - расширение абстрактного класса Игрушка */
public class Toy1 extends CToy {
   
    /**
     * Конструктор класса Игрушка
     * @param idToy - порядковый номер в базе
     * @param nameToy - наименование Игрушки
     * @param countToy - количество Игрушек 
     * @param frqDlvrToy - частота выпадения Игрушек при розыгрыше
     */
    public Toy1(int idToy, String nameToy, int countToy, int frqDlvrToy) {
        super(idToy, nameToy, countToy, frqDlvrToy);
    }

    /**
     * Конструктор класса Игрушка без параметров используется для редактирования 
     */
    public Toy1() {
        super();
    }

    @Override
    public String toString() {
        return String.format("  Идентификатор: %s\n  Название: %s\n  Количество: %s\n  Частота выпадения: %s", idToy, nameToy, countToy, frqDlvrToy);
    }
    


}
