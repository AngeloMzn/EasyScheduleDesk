module com.example.easyschedule {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;

    opens com.example.easyschedule to javafx.fxml;
    exports com.example.easyschedule;
    exports com.example.easyschedule.model;
    opens com.example.easyschedule.model to javafx.fxml;
    exports com.example.easyschedule.model.QuadraEsportiva;
    opens com.example.easyschedule.model.QuadraEsportiva to javafx.fxml;
}