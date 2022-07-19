module com.mixbaaljun.mpb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires lombok;

    opens com.mixbaaljun.mpb to javafx.fxml;
    exports com.mixbaaljun.mpb;
    opens com.mixbaaljun.mpb.controller to javafx.fxml;
    exports com.mixbaaljun.mpb.controller;
    exports com.mixbaaljun.mpb.component;
    exports com.mixbaaljun.mpb.incomes.domain;

}