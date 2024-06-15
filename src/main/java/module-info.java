module com.example.easyschedule {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires java.sql;

    opens App to javafx.fxml;
    exports App;
    opens App.model to javafx.fxml;
    exports App.model.QuadraEsportiva;
    opens App.model.QuadraEsportiva to javafx.fxml;
}