import java.util.Scanner;

//Single Responsibility Principle
//Класс выполняет только функцию показа меню из массива и считывание выбранного значения

//DRY - Все меню в программе отображаются при помощи этого класса
//Так же любой список, из которого надо выбрать один элемент, и который может быть представлен в виде меню,
//тоже может показан при помощи данного класса

public class Menu {
    private final String[] menuItems;
    //Avoid Magic Numbers - цифровой код для выхода не хардкодится, а задаётся в конструкторе меню
    private final int exitCode;
    private final String header;

    public Menu(String[] menuItems, String header, int exitCode) {
        this.menuItems = menuItems;
        this.exitCode = exitCode;
        this.header = header;
    }

    public int readMenuItem() {
        System.out.printf(header, exitCode);
        for (int i = 0; i < menuItems.length; i++) {
            System.out.printf("%d. %s\n", (i+1), menuItems[i]);
        }
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                int input = Integer.parseInt(scanner.nextLine());
                if (((input < 1) || (input > menuItems.length)) && (input != exitCode)) {
                    System.out.println("Некорректное значение. Попробуйте еще раз");
                } else {
                    return input;
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорректное значение. Попробуйте еще раз");
            }
        }
    }
}
