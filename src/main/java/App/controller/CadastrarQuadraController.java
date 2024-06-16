package App.controller;

import App.model.Locador.Locador;
import App.model.Locador.LocadorRepository;

import App.model.QuadraEsportiva.QuadraEsportiva;
import App.model.QuadraEsportiva.QuadraEsportivaRepository;
import Core.Util.ControllerHelper;
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

public class CadastrarQuadraController extends Controller {

    ControllerHelper helper = new ControllerHelper();

    @FXML
    private Button btn_salvar_quadra;

    @FXML
    private Button btn_backto_locador;

    @FXML
    private ChoiceBox<String> choice_box_disponivel;

    @FXML
    private ComboBox<String> combo_dono_quadra; // Alterado para armazenar String

    @FXML
    private TextField input_nome_quadra;

    @FXML
    private TextField preco_input;

    @FXML
    private TextField tipo_input;

    @FXML
    private Label login_tittle;

    private LocadorRepository locadorRepository = new LocadorRepository();
    private List<Locador> locadores = locadorRepository.getAllLocadores();

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
        Double precoQuadra = Double.parseDouble(preco_input.getText());
        String tipoQuadra = tipo_input.getText();
        int disponivel = choice_box_disponivel.getValue() == "sim" ? 1 : 0;
        String nomeLocadorSelecionado = combo_dono_quadra.getValue();
        Locador locadorSelecionado = null;
        for (Locador locador : locadores) {
            if (locador.getNome().equals(nomeLocadorSelecionado)) {
                locadorSelecionado = locador;
                break;
            }
        }
        QuadraEsportiva quadraEsportiva = new QuadraEsportiva(nomeQuadra,tipoQuadra ,precoQuadra, disponivel, locadorSelecionado);
        QuadraEsportivaRepository quadraEsportivaRepository = new QuadraEsportivaRepository();
        quadraEsportivaRepository.adicionarQuadra(quadraEsportiva);
        Stage currentStage = (Stage) btn_salvar_quadra.getScene().getWindow();
        helper.loadScene("/locador.fxml", currentStage);
    }

    @FXML
    public void voltar(){
        Stage currentStage = (Stage) btn_backto_locador.getScene().getWindow();
        helper.loadScene("/locador.fxml", currentStage);
    }

}
