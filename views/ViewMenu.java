package views;

import java.util.Scanner;

import controllers.LToy;
import models.Toy1;

public class ViewMenu {
    private LToy lToy;

    public ViewMenu(LToy lToy) {
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
            /** Возвращаем команду и выполняет действие */
            com = Command.valueOf(command.toUpperCase());
            if (com == Command.EXIT)
                return;
            try {
                switch (com) {
                    case CREATE:
                        Toy1 toy = this.getToyFromConsole();
                        lToy.getNewToy();
                        System.out.println("Новые игрушки добавлены в базу: ");
                        System.out.println(toy.toString() + "\n");
                    case EDIT:
                    case GET:
                    case PRESENT:                    
                }
            } catch (Exception e) {
                System.out.println("Что-то пошло не так: " + e.getMessage());
            }
        }
    }

    /**
     * Получение данных с одного ввода с консоли
     * 
     * @param message - выводимое сообщение
     * @return String
     */
    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

    /**
     * Метод получения из консоли данных по добавляемым Игрушкам 
     * @return
     */
    public Toy1 getToyFromConsole() {
        Toy1 toy = new Toy1();
        /** Устанавливаем значения ID, наименования, количества и частоты выпадения */
        toy.setIdToy(lToy.getLastIdToy() + 1);
        toy.setNameToy(prompt("Введите наименование Игрушки: "));
        toy.setCountToy(Integer.parseInt(prompt("Введител количество Игрушек: ")));
        toy.setFrqDlvrToy(Integer.parseInt(prompt("Введите частоту выпадения Игрушек от 0 до 100: ")));
        return toy;
    }

}
