package manager;

import model.*;
import homework.Progress;
import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private HashMap<Integer, Task> task = new HashMap<>();
    private HashMap<Integer, EpicTask> epicTasks = new HashMap<>();
    private HashMap<Integer, SubTask> subTasks = new HashMap<>();
    private HashMap<Integer, Task> tasks = new HashMap<>();
    public int count = 0;

    public TaskManager() {
    }

    public HashMap<Integer, Task> getAllTasks() {
        return tasks;
    }

    public void removeAllTasks() {
        tasks.clear();
        task.clear();
        epicTasks.clear();
        subTasks.clear();
    }

    public Object getTaskForId(Integer key) {
        for (int id : tasks.keySet()) {
            if (id == key) {
                return tasks.get(id);
            }
        }
        return null;
    }

    public void removeTask(Integer key) {
        Object obj = tasks.get(key);
        if (obj instanceof Task) {
            task.remove(key);
        }
        if (obj instanceof EpicTask) {
            ArrayList<Integer> idSubTask = epicTasks.get(key).getListForIdSubTask();
            for (int id : idSubTask) {
                subTasks.remove(id);
                tasks.remove(id);
            }
            epicTasks.remove(key);
        }
        if (obj instanceof SubTask) {
            SubTask subTask = (SubTask) obj;
            int idForEpic = subTask.getIdForEpic();
            epicTasks.get(idForEpic).deleteIdSubTask(subTask.getId());
            ArrayList<Integer> listForId = epicTasks.get(idForEpic).getListForIdSubTask();
            if (listForId.size() == 0) {
                epicTasks.get(idForEpic).setStatus(Progress.NEW);
            } else {
                epicTasks.get(idForEpic).deleteIdSubTask(subTask.getId());
                updateStatus(subTasks.get(listForId.get(0)));
            }
            subTasks.remove(key);
        }
        tasks.remove(key);
    }

    public void addOrUpdateTask(Object obj) {
        if (obj instanceof Task) {
            Task newTask = (Task) obj;
            task.put(newTask.getId(), newTask);
            tasks.putAll(task);
        }
        if (obj instanceof EpicTask) {
            EpicTask newEpicTask = (EpicTask) obj;
            epicTasks.put(newEpicTask.getId(), newEpicTask);
            tasks.putAll(epicTasks);
        }
        if (obj instanceof SubTask) {
            SubTask newSubTask = (SubTask) obj;
            int idForEpic = newSubTask.getIdForEpic();
            ArrayList<Integer> listForId = epicTasks.get(idForEpic).getListForIdSubTask();
            if (!listForId.contains(newSubTask.getId())) {
                epicTasks.get(idForEpic).setIdSubTask(newSubTask.getId());
            }
            subTasks.put(newSubTask.getId(), newSubTask);
            tasks.putAll(subTasks);
            updateStatus(newSubTask);
        }
    }

    public void updateStatus(SubTask subTask) {
        int idForEpic = subTask.getIdForEpic();
        Progress progress = Progress.IN_PROGRESS;
        ArrayList<Integer> idSubTask = epicTasks.get(idForEpic).getListForIdSubTask();
        int count = 0;

        for (int id: idSubTask) {
            if (subTasks.get(id).getStatus() == Progress.DONE) {
                count++;
                if (count == idSubTask.size()) {
                    progress = Progress.DONE;
                }
            }
        }

        count = 0;

        for (int id: idSubTask) {
            if (subTasks.get(id).getStatus() == Progress.NEW) {
                count++;
                if (count == idSubTask.size()) {
                    progress = Progress.NEW;
                }
            }
        }

        epicTasks.get(idForEpic).setStatus(progress);
    }
}
