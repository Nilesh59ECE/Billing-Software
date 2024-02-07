package com.example.billingsoftware;

import Service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeLogin implements Initializable {
    public TextField usernameText;
    public PasswordField passwordText;
    public Button loginButton;
    public Button homeDashboardButton;
    public AnchorPane employeePane;
    UserService us;
    static String username;
    public void loginButtonListner(ActionEvent actionEvent) throws IOException {
        String res =us.loginEmployee(usernameText.getText(),passwordText.getText());
        if(res.equals("true")){
            username=usernameText.getText();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("BillingDashboard.fxml"));
            employeePane.getChildren().setAll(pane);
        }
        else{
            Alert al = new Alert(Alert.AlertType.WARNING);
            al.setHeaderText("CREDENTIAL MISMATCH");
            al.setContentText("Either username or passowrd is wrong");
            al.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DatabaseConnection.startDatabaseConnection();
            us=new UserService();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void homeDashboardButtonListner(ActionEvent actionEvent) throws IOException, SQLException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
        employeePane.getChildren().setAll(pane);
        DatabaseConnection.closeConnection();
        System.out.println("Database Connection CLOSED");
    }
}
