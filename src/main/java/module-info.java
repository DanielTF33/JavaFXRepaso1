module org.example.estudio3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.estudio3 to javafx.fxml;
    exports org.example.estudio3;
}