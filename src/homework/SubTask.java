package homework;

public class SubTask extends Task{
    private final int idForEpic;
    public SubTask(String name, String description, int id, int idForEpic) {
        super(name, description, id);
        this.idForEpic = idForEpic;
    }

    public int getIdForEpic() {
        return idForEpic;
    }
}
