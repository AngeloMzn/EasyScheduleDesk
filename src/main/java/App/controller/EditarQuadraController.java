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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;

public class EditarQuadraController {

    ControllerHelper helper = new ControllerHelper();
    private int idQuadra;

    private QuadraEsportiva quadra;

    @FXML
    private Button btn_editar_quadra;

    @FXML
    private Button btn_backto_locador;

    @FXML
    private ChoiceBox<String> choice_box_disponivel;

    @FXML
    private ComboBox<String> combo_dono_quadra;

    @FXML
    private TextField input_nome_quadra;

    @FXML
    private Label login_tittle;

    @FXML
    private TextField preco_input;

    @FXML
    private TextField tipo_input;
    private LocadorRepository locadorRepository = new LocadorRepository();
    private List<Locador> locadores = locadorRepository.getAllLocadores();
    @FXML
    void salvarQuadra(ActionEvent event) {
        String nome = input_nome_quadra.getText().trim();
        int disponivel = choice_box_disponivel.getValue()== "sim" ? 1 : 0;
        String tipo = tipo_input.getText().trim();
        double preco = Double.parseDouble(preco_input.getText().trim());
        String nomeLocador = combo_dono_quadra.getValue();
        Locador dono = locadores.stream()
                .filter(locador -> locador.getNome().equals(nomeLocador))
                .findFirst()
                .orElse(null);
        quadra.setNome(nome);
        quadra.setDisponivel(disponivel);
        quadra.setTipo(tipo);
        quadra.setPrecoPorHora(preco);
        quadra.setDono(dono);
        System.out.println(quadra);
        QuadraEsportivaRepository quadraEsportivaRepository = new QuadraEsportivaRepository();
        quadraEsportivaRepository.atualizarQuadra(quadra);
        Stage currentStage = (Stage) btn_editar_quadra.getScene().getWindow();
        helper.loadScene("/locador.fxml", currentStage);
    }


    @FXML
    public void initialize() {
        preencherChoiceDisponivel();
        preencherComboDono();
    }

    public void preencherChoiceDisponivel() {
        choice_box_disponivel.setItems(FXCollections.observableArrayList("sim", "não"));
    }

    public void preencherComboDono() {
        combo_dono_quadra.getItems().clear();
        List<String> nomesLocadores = locadores.stream()
                .map(Locador::getNome)
                .collect(Collectors.toList());
        combo_dono_quadra.setItems(FXCollections.observableArrayList(nomesLocadores));
        if (!nomesLocadores.isEmpty()) {
            combo_dono_quadra.setValue(nomesLocadores.get(0));
        }
    }

    public void initData(int idQuadra) {
        this.idQuadra = idQuadra;
        QuadraEsportivaRepository quadraEsportivaRepository = new QuadraEsportivaRepository();
        quadra = quadraEsportivaRepository.buscarQuadraPorId(idQuadra);

        // Set initial values for input fields
        input_nome_quadra.setText(quadra.getNome());
        choice_box_disponivel.setValue(quadra.isDisponivel() == 1 ? "sim" : "não");
        tipo_input.setText(quadra.getTipo());
        preco_input.setText(String.valueOf(quadra.getPrecoPorHora()));

        // Pre-select the owner's name in the combo box
        Locador dono = quadra.getDono();
        if (dono != null) {
            combo_dono_quadra.getSelectionModel().select(dono.getNome());
        }
    }

    @FXML
    public void voltar(){
        Stage currentStage = (Stage) btn_backto_locador.getScene().getWindow();
        helper.loadScene("/locador.fxml", currentStage);
    }

}
