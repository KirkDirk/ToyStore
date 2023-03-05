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
        return String.format("Идентификатор: %s\nНазвание: %s\nКоличество: %s\nЧастота выпадения: %s", idToy, nameToy, countToy, frqDlvrToy);
    }
    


}
