package com.mixbaaljun.mpb.components;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.text.DecimalFormat;
import java.util.function.Consumer;

import com.mixbaaljun.mpb.incomes.domain.Income;
import com.mixbaaljun.mpb.shared.Utils;

public class IncomeToAdd extends HBox {

    private Label typeLabel;
    private Label earningLabel;
    private Button delete;

    private Consumer<ActionEvent> consumer;
    public IncomeToAdd(Income earning){
        super();
        this.setId(earning.toString());

        this.typeLabel = new Label(earning.getType().getName());
        typeLabel.setMinWidth(140);


        this.earningLabel = new Label(Utils.decimalForted(earning.getExpectedAmount()));
        earningLabel.setMinWidth(140); 

        this.delete = new Button();
        this.delete.setText("delete");

        this.delete.setOnAction(event -> this.consumer.accept(event));

        this.getChildren().add(typeLabel);
        this.getChildren().add(earningLabel);
        this.getChildren().add(delete);
    }

    public void setOnActionToButton(Consumer<ActionEvent> consumer){
        this.consumer = consumer;
    }
}
