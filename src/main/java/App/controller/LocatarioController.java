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
import java.util.Map;

public class LocatarioController extends Controller{

    ControllerHelper helper = new ControllerHelper();

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
    private Button btn_voltar;


    @FXML
    private TextField search_txt;

    @FXML
    private Button btn_search;

    @FXML
    private ChoiceBox<String> search_opt;

    @FXML
    private CheckBox toggle_disponivel;

    @FXML
    private  Pagination pagination;

    private ObservableList<QuadraEsportiva> quadrasList = FXCollections.observableArrayList();
    private QuadraEsportivaRepository quadraRepository = new QuadraEsportivaRepository();

    @FXML
    public void initialize() {

        initColums();

        disponivel_cell_id.setCellValueFactory(cellData -> {
            int disponivel = cellData.getValue().isDisponivel();
            return new SimpleStringProperty(disponivel == 1 ? "Sim" : "Não");
        });

        // Configure the table to allow editing of the dono_cell_id column (if needed)
        dono_cell_id.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDono().getNome()));
        dono_cell_id.setCellFactory(TextFieldTableCell.forTableColumn());

        // Popular a tabela com os dados
        quadrasList.addAll(quadraRepository.listarTodasAsQuadras());
        loadPage();


        // Filters
        search_opt.getItems().addAll("ID", "Nome", "Tipo", "Dono");
        search_opt.setValue("Nome");
        btn_search.setOnAction(event -> {
            search();
        });


        //Botton pagination
        pagination = new Pagination((quadrasList.size() / 10 + 1), 0);

        btn_voltar.setOnAction(event -> {
            Stage currentStage = (Stage) btn_voltar.getScene().getWindow();
            helper.loadScene("/app.fxml", currentStage);
        });
    }

    @FXML
    public void locar() {
        QuadraEsportiva selectedQuadra = table_quadras.getSelectionModel().getSelectedItem();
        if (selectedQuadra != null) {
            Stage currentStage = (Stage) btn_locar_quadra.getScene().getWindow();
            Map<String, Object> params = new HashMap<>();
            params.put("quadraId", selectedQuadra.getId());
            helper.loadWithParams(currentStage, "/locacao.fxml", params);
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
    }


    @FXML
    public void loadPage() {
        int fromIndex = pagination.getCurrentPageIndex() * 10;
        int toIndex = Math.min(fromIndex + 10, quadrasList.size());
        table_quadras.setItems(FXCollections.observableArrayList(quadrasList.subList(fromIndex, toIndex)));
    }


    @FXML
    public void search(){
        
        String search_txt = this.search_txt.getText();
        String search_opt = this.search_opt.getValue();

        //Check enpty search
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

        ObservableList<QuadraEsportiva> searchResults = FXCollections.observableArrayList();
        switch (search_opt) {
            case "ID":
                searchResults.addAll(quadraRepository.buscarQuadrasPorId(Integer.parseInt(search_txt)));
                break;
            case "Nome":
                searchResults.addAll(quadraRepository.buscarQuadrasPorNome(search_txt));
                break;
            case "Tipo":
                searchResults.addAll(quadraRepository.buscarQuadrasPorTipo(search_txt));
                break;
//            case "Dono":
//                searchResults.addAll(quadraRepository.buscarQuadrasPorDono(search_txt));
//                break;
            default:
                System.out.println("Invalid search option.");
                break;
        }
    }
}
