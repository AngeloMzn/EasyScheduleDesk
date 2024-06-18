module main.java.easyschedule {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires java.sql;

    opens App to javafx.fxml;
    exports App;

    opens App.controller to javafx.fxml;
    exports App.controller;

    opens App.model.LocacaoQuadra to javafx.base, javafx.fxml;
    exports App.model.LocacaoQuadra;

    opens App.model.Locador to javafx.base, javafx.fxml;
    exports App.model.Locador;

    opens App.model.Locatario to javafx.base, javafx.fxml;
    exports App.model.Locatario;

    opens App.model.QuadraEsportiva to javafx.base, javafx.fxml;
    exports App.model.QuadraEsportiva;

    opens App.model.Usuario to javafx.base, javafx.fxml;
    exports App.model.Usuario;
}