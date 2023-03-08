package intefaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

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
            if (toy1.getIdToy() == numberForEdit){
                toy1.setFrqDlvrToy(frqDlvrEdit);
            }
        }
        idbActions.saveAllToys(allToys);
        Toy1 toy = allToys.get(numberForEdit-1);
        return toy;        
    }
    
}
