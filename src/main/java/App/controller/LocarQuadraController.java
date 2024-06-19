package App.controller;

import App.model.LocacaoQuadra.LocacaoQuadra;
import App.model.LocacaoQuadra.LocacaoQuadraRepository;
import App.model.Locatario.Locatario;
import App.model.Locatario.LocatarioRepository;
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

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class LocarQuadraController extends Controller{

    ControllerHelper helper = new ControllerHelper();
    int quadraId, locatarioId;


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
        LocatarioRepository locatarioRepository = new LocatarioRepository();
        Locatario locatario = locatarioRepository.getLocatarioByUserId(this.locatarioId);
        QuadraEsportiva quadra  = quadraEsportivaRepository.buscarQuadraPorId(quadraId);
        System.out.println("id no controller: " + quadra.getId());
        LocacaoQuadra locacao = null;

        LocalDate data = input_data.getValue();
        String inicio = input_hora_inicio.getValue();
        String fim = input_hora_fim.getValue();

        locacao = locacaoQuadraRepository.buscarPorHorario(quadraId, data ,inicio, fim);

        if(locacao == null){
            System.out.println();
            locacao = new LocacaoQuadra(quadra, locatario, data, inicio, fim);
            locacaoQuadraRepository.adicionarLocacao(locacao);
            helper.showAlert("Sucesso", "Quadra locada com sucesso!!");
            Stage currentStage = (Stage) btn_salvar_locacao.getScene().getWindow();
            helper.loadScene("/locatario.fxml", currentStage);
        }else{
            helper.showAlert("erro", "Este horário não está disponível");
        }

    }

    @FXML
    public void voltar(ActionEvent event) {
        Map<String, Object> params = new HashMap<>();
        params.put("locatarioId", this.locatarioId);
        Stage currentStage = (Stage) btn_backto_locatario.getScene().getWindow();
        helper.loadWithParams(currentStage, "/locatario.fxml", params);
    }

    @Override
    public void initData(Map<String, Object> params) {
        this.locatarioId = (int) params.get("locatarioId");
        this.quadraId = (int) params.get("quadraId");
        System.out.println("passou pelo initiData: " + locatarioId);

    }


}
