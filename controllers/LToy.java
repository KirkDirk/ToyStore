package controllers;

import intefaces.IToysManagable;
import models.Toy1;

public class LToy {
    private IToysManagable iToysManagable;

    public LToy(IToysManagable iToysManagable) {
        this.iToysManagable = iToysManagable;
    }

    public Toy1 getNewToy() {
        //viewMenu
        return null;        
    };

    public int getIdToy() {
        
        return 0;        
    }
}
