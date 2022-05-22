package controller;

import domain.Bug;
import domain.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.Service;
import service.observer.Observer;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AdminMainController implements Observer {
    private Service serv;
    Stage searchStage;

    @FXML
    TableView<Employee> EmployeeTabel;
    @FXML
    TableColumn<Employee, String> columnName;
    @FXML
    TableColumn<Employee, String> columnRole;

    @FXML
    ObservableList<Employee> modelEmployee = FXCollections.observableArrayList();

    public void setService(Service serv, Stage stage) {
        this.serv = serv;
        this.searchStage = stage;
        initModelEmployee();
    }
    @FXML
    public void initialize(){

        columnName.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
        columnRole.setCellValueFactory(new PropertyValueFactory<Employee, String>("role"));

        EmployeeTabel.setItems(modelEmployee);


    }
    void initModelEmployee()
    {
        Iterable<Employee> b = serv.getAllEmployees();
        List<Employee> emps = StreamSupport.stream(b.spliterator(), false)
                .collect(Collectors.toList());

        modelEmployee.setAll(emps);

    }
    public void handlelogoutbutton() throws IOException {

        searchStage.close();
    }

    public void handleCreateAccount()
    {
        FXMLLoader searchLoader = new FXMLLoader();
        searchLoader.setLocation(getClass().getResource("/service/createAccount.fxml"));
        AnchorPane root = null;
        try {
            root = searchLoader.load();
        CreateAccountController ca = searchLoader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("CreateAccount");
        stage.show();
        ca.setService(serv,stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void update() {
        initModelEmployee();
    }
}
