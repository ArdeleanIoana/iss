package domain;

public class Entity<ID> {
    protected ID id;
    public ID getId(){
        return id;
    }
    public void setId(ID id)
    {
        this.id = id;
    }
}
