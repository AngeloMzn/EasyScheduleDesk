package App.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

public class LocadorController {

    @FXML
    private Button btn_add_quadra;

    @FXML
    private Button btn_delete_quadra;

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
    private TableView<?> table_quadras;

    @FXML
    private ToggleButton toggle_disponivel;

}
