package models;
/** Абстрактный класс Игрушка */
public abstract class CToy {
    /** Параметры "продукта */

    /** Идентификатор Игрушки - уникальный */
    protected int idToy;
    /** Текстовое название Игрушки */
    protected String nameToy;
    /** Количество Игрушек в базе розыгрыша */
    protected int countToy;
    /** Частота выпадения Игрушки при розыгрыше */
    protected int frqDlvrToy;
    
    /**
     * Конструктор абстрактного класса Игрушки 
     * @param idToy - Идентификатор Игрушки - уникальный
     * @param nameToy - Текстовое название Игрушки
     * @param countToy - Количество Игрушек в базе розыгрыша
     * @param frqDlvrToy - Частота выпадения Игрушки при розыгрыше
     */
    public CToy(int idToy, String nameToy, int countToy, int frqDlvrToy) {
        this.idToy = idToy;
        this.nameToy = nameToy;
        this.countToy = countToy;
        this.frqDlvrToy = frqDlvrToy;
    }

    public CToy() {
    }

    public int getIdToy() {
        return idToy;
    }

    public void setIdToy(int idToy) {
        this.idToy = idToy;
    }

    public String getNameToy() {
        return nameToy;
    }

    public void setNameToy(String nameToy) {
        this.nameToy = nameToy;
    }

    public int getCountToy() {
        return countToy;
    }

    public void setCountToy(int countToy) {
        this.countToy = countToy;
    }

    public int getFrqDlvrToy() {
        return frqDlvrToy;
    }

    public void setFrqDlvrToy(int frqDlvrToy) {
        this.frqDlvrToy = frqDlvrToy;
    }
    
    

    
}
