package repository;

import domain.Bug;
import domain.BugStatus;
import domain.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class RepoBugs implements IRepository<Integer, Bug>{
    private JdbcUtils dbUtils;
    public RepoBugs(Properties props)
    {
        dbUtils = new JdbcUtils(props);
    }
    @Override
    public Integer create(Bug entity) {
        Connection con = dbUtils.getConnection();
        int id=-1;
        try (PreparedStatement preStmt = con.prepareStatement("insert into Bug (name,description,status) values (?,?,?)")) {
            preStmt.setString(1, entity.getName());
            preStmt.setString(2, entity.getDescription());
            if(entity.getStatus()== BugStatus.SOLVED)
                preStmt.setString(3, "SOLVED");
            else
                preStmt.setString(3, "UNSOLVED");
            int ret = preStmt.executeUpdate();

        } catch (SQLException ex) {

            System.err.println("Error Db" + ex);

        }

        return (Integer)id;
    }
    public Bug getOne(Integer id)
    {
        Connection con = dbUtils.getConnection();
        Bug elem = null;
        try (PreparedStatement preStmt = con.prepareStatement("select * from Bug where id=?")) {
            preStmt.setInt(1, id);
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int idf = result.getInt("id");
                    String name = result.getString("name");
                    String description = result.getString("description");
                    String status = result.getString("status");
                    BugStatus stat;
                    if(Objects.equals(status, "SOLVED"))
                        stat = BugStatus.SOLVED;
                    else
                        stat = BugStatus.UNSOLVED;
                    elem  = new Bug(name,description,stat);
                    elem.setId(idf);

                }
            }
        }catch (SQLException e)
        {

            System.err.println("Error DB"+e);
        }

        return elem;
    }

    @Override
    public Iterable<Bug> getAll() {

        Connection con = dbUtils.getConnection();
        List<Bug> elems = new ArrayList<>();
        try (PreparedStatement preStmt = con.prepareStatement("select * from Bug")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    String name = result.getString("name");
                    String description = result.getString("description");
                    String status = result.getString("status");
                    BugStatus stat;
                    if(Objects.equals(status, "SOLVED"))
                        stat = BugStatus.SOLVED;
                    else
                        stat = BugStatus.UNSOLVED;
                    Bug elem  = new Bug(name,description,stat);
                    elem.setId(id);
                    elems.add(elem);
                }
            }
        }catch (SQLException e)
        {

            System.err.println("Error DB"+e);
        }

        return elems;
    }
    public void update(Bug oldBug, Bug newBug)
    {
        Connection con = dbUtils.getConnection();
        int id=-1;
        try (PreparedStatement preStmt = con.prepareStatement("update Bug  set name=?, description=?,status=? where id=?")) {


            preStmt.setString(1, newBug.getName());
            preStmt.setString(2, newBug.getDescription());
            if(newBug.getStatus() == BugStatus.SOLVED)
                preStmt.setString(3, "SOLVED");
            else
                preStmt.setString(3, "UNSOLVED");
            preStmt.setInt(4, oldBug.getId());
            int ret = preStmt.executeUpdate();

        } catch (SQLException ex) {

            System.err.println("Error Db" + ex);

        }

    }




}
