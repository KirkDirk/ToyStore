package intefaces;

import models.Toy1;
/** Класс для управления Игрушками в базе розыгрыша */
public class ToysMagable implements IToysManagable {

    private IDBActions idbActions;

    public ToysMagable(IDBActions idbActions) {
        this.idbActions = idbActions;
    }

    @Override
    public void createToy() {
        
        idbActions.getLastIDToy();
        throw new UnsupportedOperationException("Unimplemented method 'createToy'");
    }

    @Override
    public int getLastIDToy() {
        return idbActions.getLastIDToy();
    }
    
}
