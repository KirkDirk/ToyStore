package models;

public class Toy1 extends CToy {
   

    public Toy1(int idToy, String nameToy, int countToy, int frqDlvrToy) {
        super(idToy, nameToy, countToy, frqDlvrToy);
    }

    public Toy1() {
        super();
    }

    @Override
    public String toString() {
        return String.format("  Идентификатор: %s\n  Название: %s\n  Количество: %s\n  Частота выпадения: %s", idToy, nameToy, countToy, frqDlvrToy);
    }
    


}
