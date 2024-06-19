package App.controller;


import App.model.Locatario.Locatario;
import App.model.Locatario.LocatarioRepository;
import App.model.QuadraEsportiva.QuadraEsportiva;
import App.model.QuadraEsportiva.QuadraEsportivaRepository;
import Core.Util.ControllerHelper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class LocatarioController extends Controller{

    ControllerHelper helper = new ControllerHelper();
    int locatarioId;
    Locatario locatario;

    @FXML
    private Button btn_locar_quadra;

    @FXML
    private Button btn_visualizar;

    @FXML
    private Button btn_logout;

    @FXML
    private TableView<QuadraEsportiva> table_quadras;

    @FXML
    private TableColumn<QuadraEsportiva, Integer> quadra_cell_id;

    @FXML
    private TableColumn<QuadraEsportiva, String> nome_cell_id;

    @FXML
    private TableColumn<QuadraEsportiva, String> tipo_cell_id;

    @FXML
    private TableColumn<QuadraEsportiva, Double> preco_cell_id;

    @FXML
    private TableColumn<QuadraEsportiva, String> disponivel_cell_id;

    @FXML
    private TableColumn<QuadraEsportiva, String> dono_cell_id;


    @FXML
    private Label login_tittle;


    @FXML
    private TextField search_id;

    @FXML
    private TextField search_nome;

    @FXML
    private TextField search_tipo;

    @FXML
    private ToggleButton toggle_disponivel;

    private ObservableList<QuadraEsportiva> quadrasList = FXCollections.observableArrayList();
    private QuadraEsportivaRepository quadraRepository = new QuadraEsportivaRepository();
    @FXML
    public void initialize() {
        quadra_cell_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nome_cell_id.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tipo_cell_id.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        preco_cell_id.setCellValueFactory(new PropertyValueFactory<>("precoPorHora"));

        disponivel_cell_id.setCellValueFactory(cellData -> {
            int disponivel = cellData.getValue().isDisponivel();
            StringProperty value = new SimpleStringProperty(disponivel == 1 ? "Sim" : "Não");
            return value;
        });

        // Configure the table to allow editing of the dono_cell_id column (if needed)
        dono_cell_id.setCellValueFactory(cellData -> {
            StringProperty value = new SimpleStringProperty(cellData.getValue().getDono().getNome());
            return value;
        });
        dono_cell_id.setCellFactory(TextFieldTableCell.forTableColumn());

        // Popular a tabela com os dados
        quadrasList.addAll(quadraRepository.listarTodasAsQuadras());
        table_quadras.setItems(quadrasList);

        this.locatario = getLocatario();
    }

    @FXML
    public void logout(){
        Stage currentStage = (Stage) btn_logout.getScene().getWindow();
        helper.loadScene("/app.fxml", currentStage);
    }

    @FXML
    public void locar() {
        QuadraEsportiva selectedQuadra = table_quadras.getSelectionModel().getSelectedItem();
        if (selectedQuadra != null) {
            int quadraId = selectedQuadra.getId();
            HashMap<String, Object> params = new HashMap<>();
            params.put("quadraId", quadraId);
            params.put("locatarioId", this.locatarioId);
            Stage currentStage = (Stage) btn_locar_quadra.getScene().getWindow();
            helper.loadWithParams(currentStage, "/locar_quadra.fxml", params);
        } else {
            System.out.println("No Quadra selected.");
        }
    }

    @FXML
    public void visualizar(){
        QuadraEsportiva selectedQuadra = table_quadras.getSelectionModel().getSelectedItem();
        HashMap<String, Object> params = new HashMap<>();
        params.put("locatarioId", this.locatarioId);
        Stage currentStage = (Stage) btn_visualizar.getScene().getWindow();
        helper.loadWithParams(currentStage, "/locacaoQuadra.fxml", params);
    }
    Locatario getLocatario(){
        LocatarioRepository rep = new LocatarioRepository();
        return rep.getLocatarioByUserId(this.locatarioId);
    }

    @Override
    public void initData(Map<String, Object> params) {
        this.locatarioId = (int) params.get("locatarioId");
    }
}
