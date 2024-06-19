package App.controller;

import App.model.LocacaoQuadra.LocacaoQuadra;
import App.model.LocacaoQuadra.LocacaoQuadraRepository;
import Core.Util.ControllerHelper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class LocacaoQuadraLocadorController extends Controller{
    ControllerHelper helper = new ControllerHelper();
    LocacaoQuadraRepository locacaoQuadraRepository = new LocacaoQuadraRepository();
    int locatarioId, quadraId;


    @FXML
    private Button btn_backto_locatario;

    @FXML
    private Button btn_cancelar_locacao;

    @FXML
    private TableColumn<LocacaoQuadra, String> cell_data;

    @FXML
    private TableColumn<LocacaoQuadra, String> cell_hora_fim;

    @FXML
    private TableColumn<LocacaoQuadra, String> cell_hora_inicio;

    @FXML
    private TableColumn<LocacaoQuadra, String> cell_id;

    @FXML
    private TableColumn<LocacaoQuadra, String> cell_nome;

    @FXML
    private TableColumn<LocacaoQuadra, String> cell_quadra;

    @FXML
    private Label login_tittle;

    @FXML
    private TableView<LocacaoQuadra> table_quadras;

    @FXML
    void cancelarLocacao(ActionEvent event) {
        LocacaoQuadra selectedLocacao = table_quadras.getSelectionModel().getSelectedItem();
        String response = locacaoQuadraRepository.deletarLocacao(selectedLocacao.getId());
        helper.showAlert("Erro", response);
        Stage currentStage = (Stage) btn_cancelar_locacao.getScene().getWindow();
        helper.loadScene("/locatario.fxml", currentStage);
    }

    @FXML
    void voltar(){
        Stage currentStage = (Stage) btn_backto_locatario.getScene().getWindow();
        helper.loadScene("/locatario.fxml", currentStage);
    }

    @FXML
    void initialize() {
        cell_hora_inicio.setCellValueFactory(new PropertyValueFactory<>("horaInicio"));
        cell_hora_fim.setCellValueFactory(new PropertyValueFactory<>("horaFim"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        cell_data.setCellValueFactory(cellData -> {
            LocalDate data = cellData.getValue().getData();
            StringProperty value = new SimpleStringProperty(data.format(formatter));
            return value;
        });
        cell_id.setCellValueFactory(cellData -> {
            int id = cellData.getValue().getId();
            StringProperty value = new SimpleStringProperty(String.valueOf(id));
            return value;
        });
        cell_quadra.setCellValueFactory(cellData -> {
            String quadra = cellData.getValue().getQuadra().getNome();
            StringProperty value = new SimpleStringProperty(quadra);
            return value;
        });
        cell_nome.setCellValueFactory(cellData -> {
            String locatario = cellData.getValue().getLocatario().getNome();
            StringProperty value = new SimpleStringProperty(locatario);
            return value;
        });


        carregarLocacoes();
    }

    @Override
    public void initData(Map<String, Object> params) {
        this.locatarioId = (int) params.get("locatarioId");
        System.out.println("locatario no init: " + locatarioId);
    }

    public void carregarLocacoes(){
        List<LocacaoQuadra> locacoes = locacaoQuadraRepository.listarTodasAsLocacoes();

        table_quadras.setItems(FXCollections.observableArrayList(locacoes));
    }

}
