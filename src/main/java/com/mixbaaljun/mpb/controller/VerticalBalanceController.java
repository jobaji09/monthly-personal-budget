package com.mixbaaljun.mpb.controller;

import com.mixbaaljun.mpb.shared.Utils;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;

public final class VerticalBalanceController implements ViewController {

  @FXML
  private Label vtitle;
  @FXML
  private Label vbalance;
  @FXML
  private ProgressBar vprogressbar;

  public void setTitle(String title) {
    this.vtitle.setText(title);
  }

  public void setTextColor(String color) {
    this.vtitle.setTextFill(Color.web(color));
    this.vbalance.setTextFill(Color.web(color));
  }

  public void setProgressBarColor(String color) {
    String style = "-fx-background-color: %s; -fx-accent: %s;".replaceAll("%s", color);
    this.vprogressbar.setStyle(style);

  }

  public void setBalance(Double balance, Double max) {
    Double balancePercentage = (balance) / max;
    this.vprogressbar.setProgress(balancePercentage);
    this.vbalance.setText(Utils.toMoneyformat(balance));
  }

}
