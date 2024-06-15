package App.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class AppController {

    @FXML
    private Button btn_goto_login;

    @FXML
    private Button btn_goto_register;

    @FXML
    private void initialize() {
        btn_goto_login.setOnAction(event -> {
            loadScene("/login.fxml");
        });

        btn_goto_register.setOnAction(event -> {
            loadScene("/register.fxml");
        });
    }

    private void loadScene(String fxmlFile) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

            // Fechar a janela anterior (opcional)
            Stage currentStage = (Stage) btn_goto_login.getScene().getWindow();
            currentStage.close();

        } catch (IOException e) {
            System.out.println("Erro ao carregar o arquivo FXML: " + fxmlFile);
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Elementos FXML não foram carregados corretamente no controlador.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erro inesperado ao carregar a cena.");
            e.printStackTrace();
        }
    }

}
