package App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/app.fxml"));
        AnchorPane root = fxmlLoader.load();

        // Set minimum width and height
        stage.setMinWidth(800);
        stage.setMinHeight(600);

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("EasySchedule");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
