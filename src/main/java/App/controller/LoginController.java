package App.controller;

import Core.Util.ControllerHelper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    ControllerHelper helper = new ControllerHelper();

    @FXML
    private Button btn_backto_app;

    @FXML
    private Button btn_goto_register;

    @FXML
    private Button btn_registrar;

    @FXML
    private TextField input_email_login;

    @FXML
    private PasswordField input_senha_login;

    @FXML
    private Label login_tittle;


    @FXML
    private void initialize() {
        btn_backto_app.setOnAction(event -> {
            Stage currentStage = (Stage) btn_backto_app.getScene().getWindow();
            helper.loadScene("/app.fxml", currentStage);
        });

        btn_goto_register.setOnAction(event -> {
            Stage currentStage = (Stage) btn_goto_register.getScene().getWindow();
            helper.loadScene("/register.fxml", currentStage);
        });
    }

}