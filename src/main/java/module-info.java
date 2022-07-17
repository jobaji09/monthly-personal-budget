module com.mixbaaljun.mpb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.mixbaaljun.mpb to javafx.fxml;
    exports com.mixbaaljun.mpb;
}