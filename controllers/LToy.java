package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;

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

}
