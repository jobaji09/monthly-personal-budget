package com.mixbaaljun.mpb.controller;

import java.util.function.Consumer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class LabelBalanceController {

  @FXML
  private Label titleLabelBalance;

  @FXML
  private Label labelBalance;

  @FXML
  private Button buttonBalance;

  public void setTitleText(String name) {
    this.titleLabelBalance.setText(name);
  }

  public void setBalance(String balance) {
    this.labelBalance.setText(balance);
  }

  public void setButtonOnAction(Consumer<ActionEvent> consumer) {
    this.buttonBalance.setOnAction(consumer::accept);
  }

}
