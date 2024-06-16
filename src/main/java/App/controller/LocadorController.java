package App.controller;

import App.model.QuadraEsportiva.QuadraEsportiva;
import App.model.QuadraEsportiva.QuadraEsportivaRepository;
import Core.Util.ControllerHelper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class LocadorController extends Controller{

    private ControllerHelper helper = new ControllerHelper();
    private int idQuadraSelecionada;

    @FXML
    private Button btn_add_quadra;

    @FXML
    private Button btn_delete_quadra;

    @FXML
    private Button btn_logout;

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
    private TableColumn<QuadraEsportiva, String> disponivel_cell_id;

    @FXML
    private TableColumn<QuadraEsportiva, String> dono_cell_id;

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

        // Define disponivel_cell_id column with a custom cell value factory
        disponivel_cell_id.setCellValueFactory(cellData -> {
            int disponivel = cellData.getValue().isDisponivel(); // Assuming isDisponivel() returns a boolean
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
            helper.loadWithParams(idQuadraSelecionada, currentStage, "/editar_quadra.fxml");
        }
    }

    @FXML
    public void logout(){
        Stage currentStage = (Stage) btn_logout.getScene().getWindow();
        helper.loadScene("/app.fxml", currentStage);
    }
    @FXML
    public void novaQuadra() {
        Stage currentStage = (Stage) btn_add_quadra.getScene().getWindow();
        helper.loadScene("/cadastrar_quadra.fxml", currentStage);
    }
}
