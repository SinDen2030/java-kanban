package homework;

import java.util.ArrayList;

public class EpicTask extends Task {
    private ArrayList<Integer> listForIdSubTask = new ArrayList<>();

    public EpicTask(String name, String description, int id) {
        super(name, description, id);
    }

    public void setIdSubTask(int id) {
        listForIdSubTask.add(id);
    }

    public void deleteIdSubTask(Integer id) {
        listForIdSubTask.remove(id);
    }

    public ArrayList<Integer> getListForIdSubTask() {
        return listForIdSubTask;
    }

    @Override
    public String toString() {
        return "EpicTask{name=" + name + ", description.length=" + description.length() + ", id=" + id +
                ", status=" + status + ", listForIdSubTask=" + listForIdSubTask + "}";
    }
}
