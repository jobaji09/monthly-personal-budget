package com.mixbaaljun.mpb.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.UUID;
import java.util.function.BiConsumer;

import com.mixbaaljun.mpb.DTO.Income;
import com.mixbaaljun.mpb.controller.LabelBalanceController;
import com.mixbaaljun.mpb.shared.Utils;

public class LabelBalance extends Pane {

    private VBox child;

    private LabelBalanceController controller;

    public LabelBalance(Income earning, BiConsumer<Income, Pane> ondeleted) {
        super();
        this.setId(UUID.randomUUID().toString());

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Utils.getUrl("components/label-balance/label-balance-view.fxml"));
            this.setId(UUID.randomUUID().toString());
            this.child = (VBox) fxmlLoader.load();
            this.controller = fxmlLoader.getController();
            this.controller.setTitleText(earning.getType().getName());
            this.controller.setBalance(Utils.decimalForted(earning.getExpectedAmount()));
            this.controller.setButtonOnAction(event -> {
                ondeleted.accept(earning, this);
            });

            this.maxHeight(this.child.getMaxHeight());
            this.maxWidth(this.child.getMaxWidth());
            this.getChildren().add(this.child);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        // this.delete.setOnAction(event -> this.consumer.accept(event));

    }

}
