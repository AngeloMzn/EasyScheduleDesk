package App.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class RegisterController {

    @FXML
    private Label app_tittle;

    @FXML
    private Button btn_registrar;

    @FXML
    private ChoiceBox<String> choice_tipoUsuario;

    @FXML
    private TextField input_email;

    @FXML
    private TextField input_nome;

    @FXML
    private PasswordField input_senha;

    @FXML
    private Label label_cpf_cnpj;

    @FXML
    private TextField input_cpf_cnpj;

    @FXML
    void registrarUsuario(ActionEvent event) {
        // Lógica para registrar o usuário
        String nome = input_nome.getText();
        String email = input_email.getText();
        String senha = input_senha.getText();
        String tipoUsuario = choice_tipoUsuario.getValue();
        String cpfCnpj = input_cpf_cnpj.getText();

        // Adicione a lógica para processar os dados do registro aqui
        System.out.println("Registrando usuário: " + nome + ", " + email + ", " + senha + ", " + tipoUsuario + ", " + cpfCnpj);
    }

    @FXML
    void tipoUsuarioSelecionado(ActionEvent event) {
        String tipoSelecionado = choice_tipoUsuario.getValue();

        if ("locatário".equals(tipoSelecionado)) {
            label_cpf_cnpj.setText("CPF:");
            input_cpf_cnpj.setVisible(true);
        } else if ("locador".equals(tipoSelecionado)) {
            label_cpf_cnpj.setText("CNPJ:");
            input_cpf_cnpj.setVisible(true);
        } else {
            // Caso não seja selecionado nenhum tipo, oculta o campo
            label_cpf_cnpj.setText("");
            input_cpf_cnpj.setVisible(false);
        }
    }
}
