package controller;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.Service;
import service.observer.Observer;


import java.io.IOException;

public class LoginController
{
    private Service serv;
    @FXML
    private Button logInButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField enterPasswordField;
    @FXML
    private RadioButton programmerButton;
    @FXML
    private RadioButton checkerButton;
    @FXML
    private RadioButton adminButton;
    @FXML
    Button cancelButton;
    public void setService(Service serv)
    {
        this.serv = serv;
    }
    @FXML
    public void loginButtonOnAction(){
        String username = usernameTextField.getText();
        String password = enterPasswordField.getText();
        String buttonName="";
        if (programmerButton.isSelected())
            buttonName = "programmer";
        else if(checkerButton.isSelected())
            buttonName="checker";
        else if(adminButton.isSelected())
            buttonName="admin";
        if(serv.LoginCheck(username,password,buttonName)) {
            try {
                if (programmerButton.isSelected()) {
                    FXMLLoader searchLoader = new FXMLLoader();
                    searchLoader.setLocation(getClass().getResource("/service/programmerMainView.fxml"));
                    AnchorPane root = searchLoader.load();

                    ProgrammerMainController programmer = searchLoader.getController();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("MainViewProgrammer");
                    stage.show();
                    programmer.setService(serv,stage);
                    serv.addObserver(programmer);
                }
                else if(checkerButton.isSelected())
                {
                    FXMLLoader searchLoader = new FXMLLoader();
                    searchLoader.setLocation(getClass().getResource("/service/checkerMainView.fxml"));
                    AnchorPane root = searchLoader.load();


                    CheckerMainController checker = searchLoader.getController();


                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("MainViewChecker");
                    stage.show();
                    checker.setService(serv,stage);
                    serv.addObserver(checker);
                }
                else if(adminButton.isSelected())
                {
                    FXMLLoader searchLoader = new FXMLLoader();
                    searchLoader.setLocation(getClass().getResource("/service/adminMainView.fxml"));
                    AnchorPane root = searchLoader.load();


                    AdminMainController admin = searchLoader.getController();


                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.setTitle("MainViewAdmin");
                    stage.show();
                    admin.setService(serv,stage);
                    serv.addObserver(admin);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            loginMessageLabel.setText("invalid user");

        }
    }
    public void cancelButtonOnAction(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
