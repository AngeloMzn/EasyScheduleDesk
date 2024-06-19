package App.controller;

import App.model.Usuario.Usuario;
import App.model.Usuario.UsuarioRepository;
import Core.Util.ControllerHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginController extends Controller{

    ControllerHelper helper = new ControllerHelper();

    @FXML
    private Button btn_backto_app;

    @FXML
    private Button btn_goto_register;

    @FXML
    private Button btn_login;

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

    @FXML
    void logar(ActionEvent event) {
        String email = input_email_login.getText();
        String senha = input_senha_login.getText();
        UsuarioRepository repository = new UsuarioRepository();
        Usuario user = repository.buscarUsuarioPorEmail(email);

        if (user != null && senha.equals(user.getPassword())) {
            System.out.println("Logado !!");
            Stage currentStage = (Stage) btn_login.getScene().getWindow();
            if (user.getTipoUsuario().equals("locatário")) {
                Map<String, Object> params = new HashMap<>();
                params.put("locatarioId", user.getUserId());
                helper.loadWithParams(currentStage,"/locatario.fxml", params);
            } else {
                helper.loadScene("/locador.fxml", currentStage);
            }
        } else {
            helper.showAlert("Erro:","Credenciais inválidas!");
        }
    }


}