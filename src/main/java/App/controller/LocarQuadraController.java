package App.controller;

import App.model.LocacaoQuadra.LocacaoQuadra;
import App.model.LocacaoQuadra.LocacaoQuadraRepository;
import App.model.Locatario.LocatarioRepository;
import App.model.Locatario.Locatario;
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
import java.util.Map;

public class LocarQuadraController extends Controller{

    public Label login_tittle;
    ControllerHelper helper = new ControllerHelper();
    private int locatarioId;
    private int idQuadra;
    private QuadraEsportiva quadra;

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

    QuadraEsportivaRepository quadraEsportivaRepository = new QuadraEsportivaRepository();
    LocatarioRepository locatarioRepository = new LocatarioRepository();
    LocacaoQuadraRepository locacaoQuadraRepository = new LocacaoQuadraRepository();

    @FXML
    public void initialize() {
        System.out.println("LocarQuadraController initialize");
//        input_hora_inicio.getItems().addAll("08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00",
//                "15:00", "16:00", "17:00", "18:00", "19:00", "20:00");
//        input_hora_fim.getItems().addAll("08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00",
//                "15:00", "16:00", "17:00", "18:00", "19:00", "20:00");
//
//        input_data.addEventHandler(ActionEvent.ACTION, event -> {
//            System.out.println("Data selecionada: " + input_data.getValue());
//        });
//
//        input_hora_inicio.addEventHandler(ActionEvent.ACTION, event -> {
//            System.out.println("Hora de inicio selecionada: " + input_hora_inicio.getValue());
//        });
//
//        input_hora_fim.addEventHandler(ActionEvent.ACTION, event -> {
//            System.out.println("Hora de fim selecionada: " + input_hora_fim.getValue());
//        });
    }

    @Override
    public void initData(Map<String, Object> params) {
        this.idQuadra = (int) params.get("quadraId");
        this.locatarioId = (int) params.get("locatarioId");

        quadra = quadraEsportivaRepository.buscarQuadraPorId(idQuadra);
        System.out.println("Quadra: " + quadra.getNome());

        // Set the title of the locacao_tittle label
//        locacao_tittle.setText("Locar quadra: " + quadra.getNome());

        // Pre-populate the input_data, input_hora_inicio, and input_hora_fim fields with default values
        // For example:
         input_data.setValue(LocalDate.now());
         input_hora_inicio.setValue("08:00");
         input_hora_fim.setValue("10:00");

        // Enable or disable the btn_salvar_locacao button based on whether the quadra is available for rental
        // For example:
        // btn_salvar_locacao.setDisable(!quadra.isAvailable());

        System.out.println("initData locar quadra ");
        System.out.println("Locatario ID: " + locatarioId);
        System.out.println("Quadra ID: " + idQuadra);
    }

    @FXML
    void locar(ActionEvent event) {
        System.out.println("LocarQuadraController locar");
//        Locatario locatario = locatarioRepository.getLocatarioByUserId(locatarioId);
//
//        LocacaoQuadra locacaoQuadra = new LocacaoQuadra(
//                quadra,
//                locatario,
//                input_data.getValue(),
//                input_hora_inicio.getValue(),
//                input_hora_fim.getValue()
//        );
//
//        System.out.println(locacaoQuadra);
//
//        if (locacaoQuadraRepository.adicionarLocacao(locacaoQuadra)) {
//            System.out.println("Locação salva com sucesso!");
//        } else {
//            System.out.println("Erro ao salvar locação.");
//            //Pop-up de erro
//            Stage currentStage = (Stage) btn_salvar_locacao.getScene().getWindow();
//            helper.loadScene("/locatario.fxml", currentStage);
//        }

    }

    @FXML
    public void voltar(ActionEvent event) {
        Stage currentStage = (Stage) btn_backto_locatario.getScene().getWindow();
        helper.loadScene("/locatario.fxml", currentStage);
    }


}
