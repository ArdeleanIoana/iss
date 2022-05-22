package domain;

public class Bug extends Entity<Integer> {
    private String name;
    private String description;
    private BugStatus status;
    public Bug(String name, String Description, BugStatus status)
    {
        this.name = name;
        this.description = Description;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    public BugStatus getStatus()
    {
        return status;
    }
    public void setStatus(BugStatus status)
    {
        this.status = status;
    }
}
