package com.mixbaaljun.mpb.controller;

import com.mixbaaljun.mpb.shared.Utils;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;

public class HorizontalBalanceController implements ViewController {

  @FXML
  private Label titlehpb;

  @FXML
  private Label balancehpb;

  @FXML
  private ProgressBar processbarhpb;

  public void setTitle(String title) {
    this.titlehpb.setText(title);
  }

  public void setTextColor(String color) {
    this.titlehpb.setTextFill(Color.web(color));
    this.balancehpb.setTextFill(Color.web(color));
  }

  public void setProgressBarColor(String color) {
    String style = "-fx-background-color: %s; -fx-accent: %s;".replaceAll("%s", color);
    this.processbarhpb.setStyle(style);

  }

  public void setBalance(Double balance, Double max) {
    Double balancePercentage = (balance) / max;
    this.processbarhpb.setProgress(balancePercentage);
    this.balancehpb.setText(Utils.toMoneyformat(balance));
  }

}
