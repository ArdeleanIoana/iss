package controller;

import domain.Bug;
import domain.BugStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.Service;
import service.observer.Observer;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ProgrammerMainController implements Observer {
    private Service serv;


    @FXML
    TableView<Bug> bugTabel;
    @FXML
    TableColumn<Bug,String> columnName;
    @FXML
    TableColumn<Bug, String> columnDescription;
    @FXML
    TableColumn<Bug,String> columnStatus;


    @FXML
    ObservableList<Bug> modelBugs = FXCollections.observableArrayList();

    Stage searchStage;
    public void setService(Service serv, Stage stage)
    {
        this.serv=serv;
        this. searchStage = stage;
        initModelBugs();

    }
    @FXML
    public void initialize(){

        columnName.setCellValueFactory(new PropertyValueFactory<Bug, String>("name"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<Bug, String>("description"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<Bug, String>("status"));

        bugTabel.setItems(modelBugs);


    }

    public void initModelBugs(){

        Iterable<Bug> b = serv.getAllBugs();
        List<Bug> bugs = StreamSupport.stream(b.spliterator(), false)
                .collect(Collectors.toList());

        modelBugs.setAll(bugs);


    }
    public void handleSolve()
    {
        Bug selected = bugTabel.getSelectionModel().getSelectedItem();
        selected.setStatus(BugStatus.SOLVED);
        serv.updateBug(selected);
    }

    public void handlelogoutbutton() throws IOException {

        searchStage.close();
    }

    @Override
    public void update() {
        initModelBugs();
    }
}
