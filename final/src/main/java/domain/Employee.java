package domain;

public class Employee extends Entity<Integer> {
    protected String username;
    protected String password;
    protected String role;
    protected String name;
    public Employee(String name, String username,String password,String role)
    {
        this.name = name;
        this.username= username;
        this.password = password;
        this.role = role;
    }

    public Employee(String name, String role) {
        this.name = name;
        this.username= "";
        this.password = "";
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword()
    {
        return password;
    }
}
