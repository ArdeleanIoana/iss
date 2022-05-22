package service;

import domain.Bug;
import domain.BugStatus;
import domain.Employee;
import repository.IRepository;
import repository.RepoBugs;
import repository.RepoEmployee;
import service.observer.Observable;
import service.observer.Observer;

import java.util.ArrayList;

public class Service implements Observable {
    RepoBugs repoBug;
    RepoEmployee repoEmployee;
    Employee employee;
    public ArrayList<Observer> obs;
    public Service(RepoBugs repoBug, RepoEmployee repoEmployee)
    {
        this.repoBug=repoBug;
        this.repoEmployee = repoEmployee;
        obs = new ArrayList<>();
    }
    public Integer addBug(String name, String description)
    {
        Bug bug = new Bug(name,description, BugStatus.UNSOLVED);
        Integer id = repoBug.create(bug);
        notifyObservers();
        return id;
    }
    public void addEmployee(String name, String username, String password, String role)
    {
        Employee emp = new Employee(name,username,password,role);
        this.repoEmployee.create(emp);
        notifyObservers();
    }
    public void updateBug(Bug bug)
    {
        Bug old = repoBug.getOne(bug.getId());
        repoBug.update(old,bug);
        notifyObservers();
    }
    public Iterable<Bug> getAllBugs()
    {
        return repoBug.getAll();
    }
    public Iterable<Employee> getAllEmployees(){
        return repoEmployee.getAll();
    }
    public boolean LoginCheck(String username, String password,String role)
    {

        return repoEmployee.exists(username,password,role);

    }

    @Override
    public void addObserver(Observer e) {
        obs.add(e);
    }

    @Override
    public void removeObserver(Observer e) {

    }

    @Override
    public void notifyObservers() {
        for(Observer ob: obs)
        {
            ob.update();
        }
    }
}
