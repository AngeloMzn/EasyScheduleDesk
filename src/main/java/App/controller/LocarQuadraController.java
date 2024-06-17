package App.controller;

import App.model.LocacaoQuadra.LocacaoQuadraRepository;
import App.model.QuadraEsportiva.QuadraEsportiva;
import App.model.QuadraEsportiva.QuadraEsportivaRepository;
import Core.Util.ControllerHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LocarQuadraController extends Controller{

    ControllerHelper helper = new ControllerHelper();
    int idQuadra;

    @FXML
    private Button btn_backto_locatario;

    @FXML
    private Button btn_salvar_locacao;

    @FXML
    private DatePicker input_data;

    @FXML
    private ComboBox<String> input_hora_fim;

    @FXML
    private ComboBox<String> input_hora_inicio;

    @FXML
    private Label locacao_tittle;

    @FXML
    void locar(ActionEvent event) {
        LocacaoQuadraRepository locacaoQuadraRepository = new LocacaoQuadraRepository();
        QuadraEsportivaRepository quadraEsportivaRepository = new QuadraEsportivaRepository();
        QuadraEsportiva quadra  = quadraEsportivaRepository.buscarQuadraPorId(idQuadra);

    }

    @FXML
    public void voltar(ActionEvent event) {
        Stage currentStage = (Stage) btn_backto_locatario.getScene().getWindow();
        helper.loadScene("/locatario.fxml", currentStage);
    }

    @Override
    public void initData(int idQuadra) {
        this.idQuadra = idQuadra;

    }
}
