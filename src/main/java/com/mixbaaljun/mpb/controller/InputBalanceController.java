package com.mixbaaljun.mpb.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.math.BigDecimal;
import java.net.URL;
import java.util.*;

public class InputBalanceController implements Initializable {

    @FXML
    private Label labelName;
    @FXML
    private TextField incomeValue;
    @FXML
    private Button addIncome;

    private String title;

    public void setTitleText(String title) {
        this.title = title;
        this.labelName.setText(title);
    }

    private BigDecimal parseIconcomeValue() {
        return new BigDecimal(String.format("%.2f",
                Double.parseDouble(this.incomeValue.getText())));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }

    public Optional<BigDecimal> getIncomeValue() {

        String error = "No puede ser vacío";
        if (!this.incomeValue.getText().isEmpty()) {
            try {
                return Optional.of(this.parseIconcomeValue());
            } catch (NumberFormatException e) {
                error = "Debe ser un número con decimal";
                System.err.println(e.getMessage());
            }
        }
        this.addErrorMessage(error);
        return Optional.empty();
    }

    public void setOnAction(EventHandler<ActionEvent> value) {
        this.addIncome.setOnAction(value);
        this.incomeValue.setOnAction(value);
    }

    private void addErrorMessage(String error) {
        String message = String.format("%s* %s", this.title, error);
        this.labelName.setText(message);
        this.labelName.setTextFill(Color.RED);
    }

}
