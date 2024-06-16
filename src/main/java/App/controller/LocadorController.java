package App.controller;

import App.model.QuadraEsportiva.QuadraEsportiva;
import App.model.QuadraEsportiva.QuadraEsportivaRepository;
import Core.Util.ControllerHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class LocadorController {

    private ControllerHelper helper = new ControllerHelper();
    private int idQuadraSelecionada;

    @FXML
    private Button btn_add_quadra;

    @FXML
    private Button btn_delete_quadra;

    @FXML
    private Button btn_edit_quadra;

    @FXML
    private Button btn_visualizar;

    @FXML
    private Label login_tittle;

    @FXML
    private TextField search_id;

    @FXML
    private TextField search_nome;

    @FXML
    private TextField search_tipo;

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
    private TableColumn<QuadraEsportiva, Boolean> disponivel_cell_id;

    @FXML
    private TableColumn<QuadraEsportiva, String> dono_cell_id;

    @FXML
    private ToggleButton toggle_disponivel;

    private ObservableList<QuadraEsportiva> quadrasList = FXCollections.observableArrayList();
    private QuadraEsportivaRepository quadraRepository = new QuadraEsportivaRepository();

    @FXML
    public void initialize() {
        // Inicializar as colunas da tabela
        quadra_cell_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nome_cell_id.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tipo_cell_id.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        preco_cell_id.setCellValueFactory(new PropertyValueFactory<>("preco"));
        disponivel_cell_id.setCellValueFactory(new PropertyValueFactory<>("disponivel"));
        dono_cell_id.setCellValueFactory(new PropertyValueFactory<>("dono"));

        // Popular a tabela com os dados
        quadrasList.addAll(quadraRepository.listarTodasAsQuadras());
        table_quadras.setItems(quadrasList);

    }

    @FXML
    public void deletarQuadra() {
        QuadraEsportiva quadraSelecionada = table_quadras.getSelectionModel().getSelectedItem();
        if (quadraSelecionada != null) {
            int idQuadraSelecionada = quadraSelecionada.getId();
            quadraRepository.deletarQuadra(idQuadraSelecionada);
            quadrasList.remove(quadraSelecionada);
        }
    }

    @FXML
    public void editarQuadra() {
        QuadraEsportiva quadraSelecionada = table_quadras.getSelectionModel().getSelectedItem();
        int idQuadraSelecionada = quadraSelecionada.getId();
        if (idQuadraSelecionada != 0) {
            Stage currentStage = (Stage) btn_edit_quadra.getScene().getWindow();
            helper.openEditarQuadra(idQuadraSelecionada, currentStage);
        }
    }

    @FXML
    public void novaQuadra() {
        Stage currentStage = (Stage) btn_add_quadra.getScene().getWindow();
        helper.loadScene("/cadastrar_quadra.fxml", currentStage);
    }
}
