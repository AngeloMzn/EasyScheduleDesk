package App.controller;

import App.model.Locador.Locador;
import App.model.Locador.LocadorRepository;
import App.model.Locatario.Locatario;
import App.model.Locatario.LocatarioRepository;

import Core.Util.ControllerHelper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class RegisterController extends Controller{

    ControllerHelper helper = new ControllerHelper();


    @FXML
    private Label app_tittle;

    @FXML
    private Button btn_registrar;

    @FXML
    private Button btn_backto_app;

    @FXML
    private Button btn_goto_login;

    @FXML
    private ChoiceBox<String> choice_tipoUsuario;

    @FXML
    private TextField input_email;

    @FXML
    private TextField input_nome;

    @FXML
    private PasswordField input_senha;

    @FXML
    private Label label_doc1;

    @FXML
    private TextField input_doc1;

    @FXML
    public void initialize() {
        // Configura o ChoiceBox
        choice_tipoUsuario.setItems(FXCollections.observableArrayList("locador", "locatário"));
        choice_tipoUsuario.setValue("locatário");

        btn_goto_login.setOnAction(event -> {
            Stage currentStage = (Stage) btn_goto_login.getScene().getWindow();
            helper.loadScene("/login.fxml", currentStage);
        });

        btn_backto_app.setOnAction(event -> {
            Stage currentStage = (Stage) btn_backto_app.getScene().getWindow();
            helper.loadScene("/app.fxml", currentStage);
        });
    }

    @FXML
    void registrarUsuario(ActionEvent event) {
        // Lógica para registrar o usuário
        String nome = input_nome.getText();
        String email = input_email.getText();
        String senha = input_senha.getText();
        String tipoUsuario = choice_tipoUsuario.getValue();
        String doc1 = input_doc1.getText();
        if (nome == null || email == null || senha == null || tipoUsuario == null || doc1 == null) {
            System.out.println("Todos os campos aqui são obrigatórios...");
        } else if(tipoUsuario.equals("locador")) {
            Locador locador = new Locador(nome, email, senha, tipoUsuario, doc1);
            LocadorRepository repository = new LocadorRepository();
            repository.addLocador(locador);
            Stage currentStage = (Stage) btn_registrar.getScene().getWindow();
            helper.loadScene("/login.fxml", currentStage);
        }else if(tipoUsuario.equals("locatário")){
            Locatario locatario = new Locatario(nome, email, senha, tipoUsuario, doc1);
            LocatarioRepository repository = new LocatarioRepository();
            repository.addLocatario(locatario);
            Stage currentStage = (Stage) btn_registrar.getScene().getWindow();
            helper.loadScene("/login.fxml", currentStage);
        }

        // Adicione a lógica para processar os dados do registro aqui
        System.out.println("Registrando usuário: " + nome + ", " + email + ", " + senha + ", " + tipoUsuario + ", " + doc1);
    }

    @FXML
    void tipoUsuarioSelecionado(ActionEvent event) {
        String tipoSelecionado = choice_tipoUsuario.getValue();

        if ("locatário".equals(tipoSelecionado)) {
            label_doc1.setText("CPF:");
            label_doc1.setVisible(true);
            input_doc1.setVisible(true);
        } else if ("locador".equals(tipoSelecionado)) {
            label_doc1.setText("CNPJ:");
            label_doc1.setVisible(true);
            input_doc1.setVisible(true);
        } else {
            // Caso não seja selecionado nenhum tipo, oculta o campo
            label_doc1.setText("");
            input_doc1.setVisible(false);
        }
    }
}
