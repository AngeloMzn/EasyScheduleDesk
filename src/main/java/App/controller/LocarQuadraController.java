package App.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class LocarQuadraController extends Controller{

    @FXML
    private Button btn_backto_locatario;

    @FXML
    private Button btn_salvar_locacao;

    @FXML
    private DatePicker input_data;

    @FXML
    private ComboBox<?> input_hora;

    @FXML
    private Label login_tittle;

    @FXML
    void locar(ActionEvent event) {

    }

}
