import controllers.LToy;
import intefaces.DBActions;
import intefaces.IDBActions;
import intefaces.IToysManagable;
import intefaces.ToysMagable;
import views.ViewMenu;

public class App {
    public static void main(String[] args) throws Exception {

        /** Путь к файлу, который содержит данные Игрушкам в базе розыгрыша */
        final String dbDrawFile = "src\\storage\\dataBaseDraw.txt";
        
        System.out.println("Привет! Это розыгрыш игрушек \n");
        
        IDBActions idbActions = new DBActions(dbDrawFile);
        IToysManagable iToysManagable = new ToysMagable(idbActions);
        LToy lToy = new LToy(iToysManagable);
        ViewMenu viewMenu = new ViewMenu(lToy);
        viewMenu.run();

    }
}
