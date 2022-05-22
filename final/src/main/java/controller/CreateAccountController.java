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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.Service;
import service.observer.Observer;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CreateAccountController {
    private Service serv;
    Stage cStage;

    public TextField nameField;
    public TextField usernameField;
    public TextField passwordField;
    public TextField roleField;
    public void setService(Service serv, Stage stage) {
        this.serv = serv;
        this.cStage = stage;

    }
    @FXML
    public void initialize(){


    }

    public void handleCancel() throws IOException {

        cStage.close();
    }
    public void handleCreate()
    {
        String name = nameField.getText();
        String password = passwordField.getText();
        String username = usernameField.getText();
        String role = roleField.getText();
        this.serv.addEmployee(name,username,password,role);
    }


}
