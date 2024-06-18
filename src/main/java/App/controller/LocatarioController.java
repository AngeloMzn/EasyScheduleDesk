package App.controller;


import App.model.QuadraEsportiva.QuadraEsportiva;
import App.model.QuadraEsportiva.QuadraEsportivaRepository;
import Core.Util.ControllerHelper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocatarioController extends Controller{

    ControllerHelper helper = new ControllerHelper();

    @FXML
    private Button btn_locar_quadra;

    private int locatarioId;

    @FXML
    private Button btn_visualizar;

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
    private Button btn_voltar;


    @FXML
    private TextField search_txt;

    @FXML
    private Button btn_search;

    @FXML
    private ChoiceBox<String> search_opt;

    @FXML
    private CheckBox toggle_disponivel;


    private ObservableList<QuadraEsportiva> quadrasList = FXCollections.observableArrayList();
    private QuadraEsportivaRepository quadraRepository = new QuadraEsportivaRepository();

    @FXML
    public void initialize() {

        initColums();

//        disponivel_cell_id.setCellValueFactory(cellData -> {
//            int disponivel = cellData.getValue().isDisponivel();
//            return new SimpleStringProperty(disponivel == 1 ? "Sim" : "Não");
//        });


        // Popular a tabela com os dados
        quadrasList.addAll(quadraRepository.listarTodasAsQuadras());
        loadPage();


        // Filters
//        search_opt.getItems().addAll("ID", "Nome", "Tipo", "Dono");
        search_opt.getItems().addAll("ID", "Nome", "Tipo");
        search_opt.setValue("Nome");
        btn_search.setOnAction(event -> {
            search();
        });
        search_txt.setOnAction(event -> {
            search();
        });

        btn_voltar.setOnAction(event -> {
            Stage currentStage = (Stage) btn_voltar.getScene().getWindow();
            helper.loadScene("/app.fxml", currentStage);
        });
    }

    @Override
    public void initData(Map<String, Object> params) {
        this.locatarioId = (int) params.get("locatarioId");

        //TODO: Implementar tela com historico de locacoes
//        btn_visualizar.setOnAction(event -> {
//            Stage currentStage = (Stage) btn_visualizar.getScene().getWindow();
//            helper.loadScene("/visualizar_locacoes.fxml", currentStage);
//        });

        btn_locar_quadra.setOnAction(event -> {
            locar();
        });


    }

    @FXML
    public void locar() {
        QuadraEsportiva selectedQuadra = table_quadras.getSelectionModel().getSelectedItem();
        int idQuadraSelecionada = selectedQuadra.getId();

        System.out.println("idQuadraSelecionada: " + idQuadraSelecionada);

        if (idQuadraSelecionada != 0) {
            Stage currentStage = (Stage) btn_locar_quadra.getScene().getWindow();
            Map<String, Object> params = new HashMap<>();
            params.put("quadraId", idQuadraSelecionada);
            params.put("locatarioId", locatarioId);
//            helper.loadWithParams(currentStage, "/locar_quadra.fxml", params);
        } else {
            System.out.println("No Quadra selected.");
        }
    }

    @FXML
    public void initColums(){
        quadra_cell_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nome_cell_id.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tipo_cell_id.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        preco_cell_id.setCellValueFactory(new PropertyValueFactory<>("precoPorHora"));
        dono_cell_id.setCellValueFactory(new PropertyValueFactory<>("dono"));
        disponivel_cell_id.setCellValueFactory(cellData -> {
            int disponivel = cellData.getValue().isDisponivel();
            return new SimpleStringProperty(disponivel == 1 ? "Sim" : "Não");
        });
    }


    @FXML
    public void loadPage() {
        table_quadras.setItems(FXCollections.observableArrayList(quadrasList));
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
                    quadrasList.addAll(quadraRepository.buscarQuadrasPorId(id));
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
