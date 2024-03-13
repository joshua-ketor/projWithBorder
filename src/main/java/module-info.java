module org.example.projwithborder {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.projwithborder to javafx.fxml;
    exports org.example.projwithborder;
}