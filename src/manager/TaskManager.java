package manager;

import model.*;
import homework.Progress;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private HashMap<Integer, Task> task = new HashMap<>();
    private HashMap<Integer, EpicTask> epicTasks = new HashMap<>();
    private HashMap<Integer, SubTask> subTasks = new HashMap<>();
    public int count = 0;

    public HashMap<Integer, Task> getAllTasks() {
        HashMap<Integer, Task> allTasks = new HashMap<>();
        allTasks.putAll(task);
        allTasks.putAll(epicTasks);
        allTasks.putAll(subTasks);
        return allTasks;
    }

    public void removeAllTasks() {
        task.clear();
        epicTasks.clear();
        subTasks.clear();
    }

    public Object getTaskForId(Integer key) {
        HashMap<Integer, Task> allTasks = getAllTasks();

        for (int id : allTasks.keySet()) {
            if (id == key) {
                return allTasks.get(id);
            }
        }
        return null;
    }

    public void removeTask(Integer key) {
        HashMap<Integer, Task> allTasks = getAllTasks();
        Object obj = allTasks.get(key);

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
            return;
        }
        if (obj instanceof EpicTask) {
            ArrayList<Integer> idSubTask = epicTasks.get(key).getListForIdSubTask();
            for (int id : idSubTask) {
                subTasks.remove(id);
                allTasks.remove(id);
            }
            epicTasks.remove(key);
            return;

        }
        if (obj instanceof Task) {
            task.remove(key);
        }
    }

    public void addOrUpdateTask(Object obj) {
        if (obj instanceof SubTask) {
            SubTask newSubTask = (SubTask) obj;
            int idForEpic = newSubTask.getIdForEpic();
            ArrayList<Integer> listForId = epicTasks.get(idForEpic).getListForIdSubTask();
            if (!listForId.contains(newSubTask.getId())) {
                epicTasks.get(idForEpic).setIdSubTask(newSubTask.getId());
            }
            subTasks.put(newSubTask.getId(), newSubTask);
            updateStatus(newSubTask);
            return;
        }
        if (obj instanceof EpicTask) {
            EpicTask newEpicTask = (EpicTask) obj;
            epicTasks.put(newEpicTask.getId(), newEpicTask);
            return;
        }
        if (obj instanceof Task) {
            Task newTask = (Task) obj;
            task.put(newTask.getId(), newTask);
        }
    }

    public void updateStatus(SubTask subTask) {
        int idForEpic = subTask.getIdForEpic();
        Progress progress = Progress.IN_PROGRESS;
        ArrayList<Integer> idSubTask = epicTasks.get(idForEpic).getListForIdSubTask();
        int count = 0;

        for (int id : idSubTask) {
            if (subTasks.get(id).getStatus() == Progress.DONE) {
                count++;
                if (count == idSubTask.size()) {
                    progress = Progress.DONE;
                }
            }
        }

        count = 0;

        for (int id : idSubTask) {
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