package homework;
import java.util.ArrayList;

public class EpicTask extends Task{
    private ArrayList<Integer> listForIdSubTask = new ArrayList<>();

    public EpicTask(String name, String description, int id) {
        super(name, description, id);
    }

    public void setIdSubTask(int id) {
        listForIdSubTask.add(id);
    }
}
