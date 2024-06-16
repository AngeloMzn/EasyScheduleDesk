package App.controller;

import App.model.Locador.Locador;
import App.model.Locador.LocadorRepository;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class CadastrarQuadraController {

    @FXML
    private Button btn_salvar_quadra;

    @FXML
    private ChoiceBox<String> choice_box_disponivel;

    @FXML
    private ComboBox<String> combo_dono_quadra; // Alterado para armazenar String

    @FXML
    private TextField input_nome_quadra;

    @FXML
    private PasswordField input_preco_quadra;

    @FXML
    private PasswordField input_tipo_quadra;

    @FXML
    private Label login_tittle;

    private LocadorRepository locadorRepository = new LocadorRepository();
    private List<Locador> locadores = locadorRepository.getAllLocadores();;

    @FXML
    void logar(ActionEvent event) {

    }

    @FXML
    public void initialize() {
        preencherChoiceDisponivel();
        preencherComboDono();
    }

    public void preencherChoiceDisponivel() {
        choice_box_disponivel.setItems(FXCollections.observableArrayList("sim", "não"));
        choice_box_disponivel.setValue("sim");
    }

    public void preencherComboDono() {
        combo_dono_quadra.getItems().clear();
        List<String> nomesLocadores = locadores.stream()
                .map(Locador::getNome) // Supondo que o nome esteja acessível via getNome()
                .collect(Collectors.toList());
        combo_dono_quadra.setItems(FXCollections.observableArrayList(nomesLocadores));
        if (!nomesLocadores.isEmpty()) {
            combo_dono_quadra.setValue(nomesLocadores.get(0)); // Define o primeiro nome como selecionado inicialmente
        }
    }

    @FXML
    void salvarQuadra(ActionEvent event) {
        String nomeQuadra = input_nome_quadra.getText();
        String precoQuadra = input_preco_quadra.getText();
        String tipoQuadra = input_tipo_quadra.getText();
        String disponivel = choice_box_disponivel.getValue();

        String nomeLocadorSelecionado = combo_dono_quadra.getValue();

        Locador locadorSelecionado = null;

        // Procura pelo Locador correspondente ao nome selecionado
        for (Locador locador : locadores) {
            if (locador.getNome().equals(nomeLocadorSelecionado)) {
                locadorSelecionado = locador;
                break;
            }
        }
        System.out.println(locadorSelecionado);

        if (locadorSelecionado != null) {
            int idLocador = locadorSelecionado.getId();
            System.out.println("ID do locador selecionado: " + idLocador);

            // Aqui você pode realizar a lógica para salvar a quadra com o ID do locador
            // Por exemplo: quadraRepository.salvarQuadra(nomeQuadra, precoQuadra, tipoQuadra, disponivel, idLocador);
        }
    }

}
