module ifpr.pgua.eic {
    requires javafx.controls;
    requires javafx.fxml;

    opens ifpr.pgua.eic to javafx.fxml;
    exports ifpr.pgua.eic;
}
