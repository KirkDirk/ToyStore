package controllers;

import intefaces.IToysManagable;
import models.Toy1;

public class LToy {
    private IToysManagable iToysManagable;

    public LToy(IToysManagable iToysManagable) {
        this.iToysManagable = iToysManagable;
    }

    public Toy1 getNewToy() {
        Toy1 toy = new Toy1();
        toy.setIdToy(getLastIdToy());
        
        iToysManagable.createToy();
        return null;        
    };

    public int getLastIdToy() {
        return iToysManagable.getLastIDToy();        
    }
}
