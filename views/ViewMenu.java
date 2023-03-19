package views;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import controllers.LToy;
import models.Toy1;

public class ViewMenu {
    private LToy lToy;

    /**
     * Инициируем экземпляр контроллера lToy
     * @param lToy
     */
    public ViewMenu(LToy lToy) {
        this.lToy = lToy;
    }

    /**
     * Запуск меню с выбором действий
     */
    public void run() {

        /** Стартовый выбор меню  */
        Command com = Command.NONE;
        /** Предлагаем варианты пользователю */
        while (true) {
            String command = prompt("Доступные команды: \n"
                + "    CREATE - добавить Игрушки для розыгрыша \n"
                + "    EDIT - отредактировать частоту выпадения Игрушек \n"
                + "    GET - разыграть призовую Игрушку \n"
                + "    PRESENT - вручить призовую Игрушку \n"
                + "    EXIT - выйти из программы \n\n"
                + "Введите команду: ");
            /** Возвращаем команду и выполняем действие */
            com = Command.valueOf(command.toUpperCase());
            if (com == Command.EXIT)
                return;
            try {
                switch (com) {
                    case CREATE:
                        Toy1 toy = getToyFromConsole();
                        lToy.getNewToy(toy);
                        System.out.println("---\nНовые игрушки добавлены в базу: ");
                        System.out.println(toy.toString() + "\n");
                        break;
                    case EDIT:
                        List<Toy1> allToys = lToy.getAllToys();
                        System.out.println("---\nСписок всех игрушек-частоты в базе: ");
                        for (Toy1 toy1 : allToys) {
                            System.out.println(String.format("%d. %s - %d", toy1.getIdToy(), toy1.getNameToy(), toy1.getFrqDlvrToy()));
                        }
                        int numberForEdit = Integer.parseInt(prompt("\nВведите номер редактируемой Игрушки: "));
                        int frqDlvrEdit = Integer.parseInt(prompt("Введите новое значение частоты: "));
                        Toy1 toyEdit = lToy.changingFrq(numberForEdit, frqDlvrEdit);
                        System.out.println("Значение частоты изменено: ");
                        System.out.println(toyEdit.toString() + "\n");
                        break;                        
                    case GET:
                        System.out.println("\nВНИМАНИЕ, РОЗЫГРЫШ ИГРУШКИ!\n-------------\n");
                        Toy1 prizeToy = lToy.getPrizeToy();
                        String nameWinner = prompt("Введите имя победителя: ");
                        System.out.println("\n" + nameWinner.toUpperCase() + " ВЫИГРЫВАЕТ " + prizeToy.getNameToy().toUpperCase() + "!!!\n" );
                        lToy.saveResultRaffle(prizeToy, nameWinner);
                        break;
                    case PRESENT:    
                        System.out.println("\n Список игрушек к выдаче: ");
                        System.out.println(lToy.getListToyDlvr());
                        int numberForDlvr = Integer.parseInt(prompt("\nВведите номер выдаваемой Игрушки: "));
                        String[] presentToy = lToy.getArrPresentToy(numberForDlvr);
                        System.out.println("\nИгрушка " + presentToy[1] + " выдана " + presentToy[0]);
                        break;
                }
            } catch (Exception e) {
                System.out.println("Что-то пошло не так: " + e.getMessage());
            }
        }
    }

    /**
     * Метод получения данных с одного ввода с консоли
     * @param message выводимое сообщение
     * @return String
     */
    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    /**
     * Метод получения из консоли данных по добавляемым Игрушкам 
     * @return toy
     * @throws IOException
     */
    public Toy1 getToyFromConsole() throws IOException {
        Toy1 toy = new Toy1();
        /** Устанавливаем значения ID, наименования, количества и частоты выпадения */
        toy.setIdToy(lToy.getLastIdToy() + 1);
        toy.setNameToy(prompt("Введите наименование Игрушки: "));
        toy.setCountToy(Integer.parseInt(prompt("Введите количество Игрушек: ")));
        toy.setFrqDlvrToy(Integer.parseInt(prompt("Введите частоту выпадения Игрушек от 0 до 100: ")));
        return toy;
    }

}
