package intefaces;
/** Интерфейс взаимодейстия с файлами хранилища */
public interface IDBActions {
    
    /**
     * Получаем ID Игрушек из базы розыгрыша
     * @return - int
     */
    int getLastIDToy();
}
