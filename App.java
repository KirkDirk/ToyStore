import controllers.LToy;
import intefaces.IToysManagable;
import intefaces.ToysMagable;
import views.ViewMenu;

public class App {
    public static void main(String[] args) throws Exception {
        
        System.out.println("Привет! Это розыгрыш игрушек \n");
        
        IToysManagable iToysManagable = new ToysMagable();
        LToy lToy = new LToy(iToysManagable);
        ViewMenu viewMenu = new ViewMenu(lToy);
        viewMenu.run();

    }
}
