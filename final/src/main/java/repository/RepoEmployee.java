package repository;

import domain.Bug;
import domain.BugStatus;
import domain.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class RepoEmployee implements IRepository<Integer, Employee>{

    private JdbcUtils dbUtils;
    public RepoEmployee(Properties props)
    {
        dbUtils = new JdbcUtils(props);
    }
    @Override
    public Integer create(Employee entity) {
        int roleID = this.getRoleId(entity.getRole());
        Connection con = dbUtils.getConnection();
        int id=-1;
        try (PreparedStatement preStmt = con.prepareStatement("insert into Employee (name,username,password,role) values (?,?,?,?)")) {
            preStmt.setString(1, entity.getName());
            preStmt.setString(2, entity.getUsername());
            preStmt.setString(3, entity.getPassword());
            preStmt.setInt(4, roleID);
            int ret = preStmt.executeUpdate();

        } catch (SQLException ex) {

            System.err.println("Error Db" + ex);

        }

        return null;
    }
    public boolean exists(String username,String password,String role)
    {

        Connection con = dbUtils.getConnection();
        List<Employee> elems = new ArrayList<>();
        int count = 0;
        try (PreparedStatement preStmt = con.prepareStatement("select * from Employee inner join Role on Employee.role=Role.id where Employee.username=? and Employee.password=? and Role.name=?")) {
            preStmt.setString(1, username);
            preStmt.setString(2, password);
            preStmt.setString(3, role);
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    count = count + 1;

                }
            }
        }catch (SQLException e)
        {

            System.err.println("Error DB"+e);
        }

        return count > 0;
    }

    @Override
    public Iterable<Employee> getAll() {
        Connection con = dbUtils.getConnection();
        List<Employee> elems = new ArrayList<>();
        int count = 0;
        try (PreparedStatement preStmt = con.prepareStatement("select Employee.name as na, Role.name as rol from Employee  INNER JOIN Role  on Employee.role=Role.id")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    String name = result.getString("na");
                    String role = result.getString("rol");

                    Employee elem  = new Employee(name,role);
                    elems.add(elem);
                }
            }
        }catch (SQLException e)
        {

            System.err.println("Error DB"+e);
        }

        return elems;
    }
    private Integer getRoleId(String role)
    {
        Connection con = dbUtils.getConnection();
        int idf=-1;
        try (PreparedStatement preStmt = con.prepareStatement("select * from Role where name=?")) {
            preStmt.setString(1,role);
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    idf = result.getInt("id");

                }
            }
        }catch (SQLException e)
        {

            System.err.println("Error DB"+e);
        }

        return idf;
    }
}
