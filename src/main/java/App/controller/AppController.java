package App.controller;


import Core.Util.ControllerHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class AppController {
    ControllerHelper helper =  new ControllerHelper();

    @FXML
    private Button btn_goto_login;

    @FXML
    private Button btn_goto_register;

    @FXML
    private void initialize() {
        btn_goto_login.setOnAction(event -> {
            Stage currentStage = (Stage) btn_goto_login.getScene().getWindow();
            helper.loadScene("/login.fxml", currentStage);
        });

        btn_goto_register.setOnAction(event -> {
            Stage currentStage = (Stage) btn_goto_register.getScene().getWindow();
            helper.loadScene("/register.fxml", currentStage);
        });
    }

}
