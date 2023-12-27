package homework;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner reader = new Scanner(System.in);

        printMenu();
        String command = reader.next();

        switch (command) {
            case "1":
                System.out.println(taskManager.getAllTasksFromCategory());
        }
    }

    static void printMenu(){
        System.out.println("""
                              Выберите одну из команд:
                              1-Посмотреть все текущие задачи
                              2-Удалить все текущие задачи
                              3-Посмотреть определённую задачу
                              4-Создать задачу
                              5-Обновить задачу
                              6-Удалить задачу""");
    }

}
