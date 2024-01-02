package homework;

import manager.TaskManager;
import model.EpicTask;
import model.SubTask;
import model.Task;

public class Main {
    public static void main(String[] args) {
        //Всё что находится далее, используется для демонстрации работоспособности
        TaskManager taskManager = new TaskManager();
        Task newTask = new Task("Задача1", "Test", ++taskManager.count);
        Task newTask1 = new Task("Задача2", "Test", ++taskManager.count);
        EpicTask newEpicTask = new EpicTask("Эпик1", "Test", ++taskManager.count);
        SubTask newSubTask = new SubTask("Подзадача1.1", "Test", ++taskManager.count, newEpicTask.getId());
        SubTask newSubTask1 = new SubTask("Подзадача1.2", "Test", ++taskManager.count, newEpicTask.getId());
        EpicTask newEpicTask1 = new EpicTask("Эпик2", "Test", ++taskManager.count);
        SubTask newSubTask3 = new SubTask("Подзадача2", "Test", ++taskManager.count, newEpicTask1.getId());

        taskManager.addOrUpdateTask(newTask);
        taskManager.addOrUpdateTask(newTask1);
        taskManager.addOrUpdateTask(newEpicTask);
        taskManager.addOrUpdateTask(newEpicTask1);
        taskManager.addOrUpdateTask(newSubTask);
        taskManager.addOrUpdateTask(newSubTask1);
        taskManager.addOrUpdateTask(newSubTask3);

        for (Object task : taskManager.getAllTasks().values()) {
            System.out.println(task);
        }

        newTask.setStatus(Progress.DONE);
        newTask1.setStatus(Progress.IN_PROGRESS);
        taskManager.addOrUpdateTask(newTask);
        taskManager.addOrUpdateTask(newTask1);
        newSubTask.setStatus(Progress.IN_PROGRESS);
        newSubTask1.setStatus(Progress.NEW);
        taskManager.addOrUpdateTask(newSubTask);
        taskManager.addOrUpdateTask(newSubTask1);
        newSubTask3.setStatus(Progress.IN_PROGRESS);
        taskManager.addOrUpdateTask(newSubTask3);

        System.out.println("\n");

        for (Object task : taskManager.getAllTasks().values()) {
            System.out.println(task);
        }

        taskManager.removeTask(1);
        taskManager.removeTask(6);
        taskManager.removeTask(4);

        System.out.println("\n");

        for (Object task : taskManager.getAllTasks().values()) {
            System.out.println(task);
        }

        System.out.println("\n");

        System.out.println(taskManager.getTaskForId(2));

        taskManager.removeAllTasks();

        System.out.println("\n");

        System.out.println("Задачи:");

        for (Object task : taskManager.getAllTasks().values()) {
            System.out.println(task);
        }

    }
}
