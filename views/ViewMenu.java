package views;

import java.util.Scanner;

public class ViewMenu {
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

}
