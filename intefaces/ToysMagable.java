package intefaces;

import java.io.FileNotFoundException;
import java.io.IOException;

import models.Toy1;
/** Класс для управления Игрушками в базе розыгрыша */
public class ToysMagable implements IToysManagable {

    private IDBActions idbActions;

    public ToysMagable(IDBActions idbActions) {
        this.idbActions = idbActions;
    }

    @Override
    public void createToy(Toy1 toy) throws FileNotFoundException, IOException {
        idbActions.saveNewToyToStorage(toy);
        //throw new UnsupportedOperationException("Unimplemented method 'createToy'");
    }

    @Override
    public int getLastIDToy() throws IOException {
        return idbActions.getLastIDToy();
    }
    
}
