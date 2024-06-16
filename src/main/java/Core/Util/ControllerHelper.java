package Core.Util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerHelper {

    public ControllerHelper(){}
    public void loadScene(String fxmlFile, Stage currentStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ControllerHelper.class.getResource(fxmlFile));
            Parent root = fxmlLoader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
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
}
