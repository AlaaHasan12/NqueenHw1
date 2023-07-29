module com.example.nqueenhw1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires AnimateFX;
    requires org.kordamp.bootstrapfx.core;
    requires java.datatransfer;
    opens com.example.nqueenhw1 to javafx.fxml;
    exports com.example.nqueenhw1;
}