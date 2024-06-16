package App.controller;

import App.model.QuadraEsportiva.QuadraEsportiva;
import App.model.QuadraEsportiva.QuadraEsportivaRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EditarQuadraController {

    private int idQuadra;

    private QuadraEsportiva quadra;

    @FXML
    private Button btn_editar_quadra;

    @FXML
    private ChoiceBox<?> choice_box_disponivel;

    @FXML
    private ComboBox<?> combo_dono_quadra;

    @FXML
    private TextField input_nome_quadra;

    @FXML
    private Label login_tittle;

    @FXML
    private TextField preco_input;

    @FXML
    private TextField tipo_input;

    @FXML
    void salvarQuadra(ActionEvent event) {

    }

    public void initData(int idQuadra) {
        this.idQuadra = idQuadra;
        QuadraEsportivaRepository quadraEsportivaRepository = new QuadraEsportivaRepository();
        quadra = quadraEsportivaRepository.buscarQuadraPorId(idQuadra);
        input_nome_quadra.setText(quadra.getNome());
        tipo_input.setText(quadra.getTipo());
        preco_input.setText(String.valueOf(quadra.getPrecoPorHora()));
    }

}
