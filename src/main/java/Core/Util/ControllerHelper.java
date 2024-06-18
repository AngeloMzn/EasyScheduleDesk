package Core.Util;

import App.controller.Controller;
import App.controller.EditarQuadraController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ControllerHelper {

    public ControllerHelper(){}
    public void loadScene(String fxmlFile, Stage currentStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ControllerHelper.class.getResource(fxmlFile));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setMinWidth(800);
            stage.setMinHeight(600);
            stage.setScene(new Scene(root, 800, 600));
            stage.show();

            // Fechar a janela anterior (opcional)
            if (currentStage != null) {
                currentStage.close();
            }

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

    public void loadWithParams(Stage stage, String fxml, Map<String, Object> params) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent root = loader.load();

            Controller controller = loader.getController();
            // Passando o mapa de parâmetros para o controlador
            controller.initData(params);

            if (stage != null) {
                stage.close();
            }

            Scene scene = new Scene(root, 800, 600);
            Stage editStage = new Stage();
            editStage.setMinWidth(800);
            editStage.setMinHeight(600);
            editStage.setScene(scene);
            editStage.setTitle("Quadra Esportiva");
            editStage.initOwner(stage);
            editStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  boolean isNull(List<?> lista) {
        if (lista == null || lista.isEmpty()) {
            return true;
        }
        for (Object elemento : lista) {
            if (elemento == null) {
                return true;
            }
        }
        return false;
    }


    public void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
