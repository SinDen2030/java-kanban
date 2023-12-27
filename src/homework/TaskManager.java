package homework;
import java.util.HashMap;

public class TaskManager {
    HashMap<Integer, Task> task = new HashMap<>();
    HashMap<Integer, EpicTask> epicTasks = new HashMap<>();
    HashMap<Integer, SubTask> subTasks = new HashMap<>();
    HashMap<String, HashMap> tasks = new HashMap<>();
    int count = 0;

    public TaskManager() {
        tasks.put("Задачи",task);
        tasks.put("Эпики", epicTasks);
        tasks.put("Подзадачи", subTasks);
    }

    public HashMap<String, HashMap> getAllTasksFromCategory() {
        return tasks;
    }

    public void removeAllTasksFromCategory(String key) {
        tasks.get(key).clear();
    }

    public Object getTaskForId(String key, Integer id) {
        return tasks.get(key).get(id);
    }
    public void removeTask(String key, Integer id) {
        tasks.get(key).remove(id);
    }
    public void createTask(String key, String name, String discription, int id) {
        count++;
        switch (key) {
            case "Задача":
                Task newTask = new Task(name, discription, count);
                task.put(count, newTask);
                break;
            case "Эпик":
                EpicTask newEpicTask = new EpicTask(name, discription, count);
                epicTasks.put(count, newEpicTask);
                break;
            case "Подзадача":
                SubTask newSubTask = new SubTask(name, discription, count, id);
                subTasks.put(count, newSubTask);
                epicTasks.get(id).setIdSubTask(count);
                break;
            default:
                System.out.println("Такой категории нет.");
        }
    }
}
