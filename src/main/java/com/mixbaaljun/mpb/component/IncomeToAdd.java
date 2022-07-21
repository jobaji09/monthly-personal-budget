package com.mixbaaljun.mpb.component;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.text.DecimalFormat;
import java.util.function.Consumer;

import com.mixbaaljun.mpb.incomes.domain.Income;

public class IncomeToAdd extends HBox {

    private Label typeLabel;
    private Label earningLabel;
    private Button delete;

    private Consumer<ActionEvent> consumer;
    public IncomeToAdd(Income earning){
        super();
        // this.setAlignment(Pos.CENTER);
        this.setId(earning.toString());

        this.typeLabel = new Label(earning.getType().getName());
        typeLabel.setMinWidth(140);

        DecimalFormat df = new DecimalFormat(Income.AMOUNTFORMAT);

        this.earningLabel = new Label("$"+df.format(earning.getExpectedAmount()));
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
