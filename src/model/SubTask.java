package model;

public class SubTask extends Task {
    private final int idForEpic;

    public SubTask(String name, String description, int id, int idForEpic) {
        super(name, description, id);
        this.idForEpic = idForEpic;
    }

    public int getIdForEpic() {
        return idForEpic;
    }

    @Override
    public String toString() {
        return "SubTask{name=" + name + ", description.length=" + description.length() + ", id=" + id +
                ", status=" + status + ", idForEpic=" + idForEpic + "}";
    }
}
