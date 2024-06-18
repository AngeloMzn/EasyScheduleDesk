package App.controller;

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
    private TextField search_txt;

    @FXML
    private Button btn_search;

    @FXML
    private ChoiceBox<String> search_opt;

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

        // Popular a tabela com os dados
        initColums();
        quadrasList.addAll(quadraRepository.listarTodasAsQuadras());
        loadPage();

        search_opt.getItems().addAll("ID", "Nome", "Tipo");
        search_opt.setValue("Nome");
        btn_search.setOnAction(event -> {
            search();
        });
        search_txt.setOnAction(event -> {
            search();
        });


        // Popular a tabela com os dados
//        quadrasList.addAll(quadraRepository.listarTodasAsQuadras());
//        table_quadras.setItems(quadrasList);
    }

    @FXML
    public void initColums(){
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
            Map<String, Object> params = new HashMap<>();
            params.put("idQuadra", idQuadraSelecionada);
            helper.loadWithParams(currentStage, "/editar_quadra.fxml", params);
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

    @FXML
    public void loadPage() {
        table_quadras.setItems(quadrasList);
        table_quadras.refresh();
    }

    @FXML
    public void search() {
        String search_txt = this.search_txt.getText();
        String search_opt = this.search_opt.getValue();

        // Check for empty search
        if (search_txt.isEmpty()) {
            quadrasList.setAll(quadraRepository.listarTodasAsQuadras());
            return;
        }

        // Sanity check SQL injection on search
        if (search_txt.contains(";") || search_txt.contains("DROP") || search_txt.contains("DELETE") ||
                search_txt.contains("UPDATE") || search_txt.contains("INSERT")) {
            System.out.println("Invalid search query.");
            return;
        }

        // Check if search_opt is null
        if (search_opt == null) {
            System.out.println("No search option selected.");
            return;
        }

        try {
            switch (search_opt) {
                case "ID":
                    quadrasList.clear();
                    int id = Integer.parseInt(search_txt);
                    quadrasList.addAll(quadraRepository.buscarQuadraPorId(id));
                    break;
                case "Nome":
                    quadrasList.clear();
                    quadrasList.addAll(quadraRepository.buscarQuadrasPorNome(search_txt));
                    break;
                case "Tipo":
                    quadrasList.clear();
                    quadrasList.addAll(quadraRepository.buscarQuadrasPorTipo(search_txt));
                    break;
//            case "Dono":
//                quadrasList.clear();
//                quadrasList.addAll(quadraRepository.buscarQuadrasPorDono(search_txt));
//                break;
                default:
                    System.out.println("Invalid search option.");
                    quadrasList.clear();
                    break;
            }

            // Check if the search returned any results
            if (quadrasList.isEmpty()) {
                System.out.println("No results found.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
            quadrasList.clear();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            quadrasList.clear();
        }

        loadPage();
    }
}
