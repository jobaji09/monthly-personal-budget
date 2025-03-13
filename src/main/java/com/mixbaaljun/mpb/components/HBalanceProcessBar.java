package com.mixbaaljun.mpb.components;

import java.io.IOException;

import com.mixbaaljun.mpb.controller.HorizontalBalanceController;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

public class HBalanceProcessBar {

  private HBox component;
  private HorizontalBalanceController controller;

  public HBalanceProcessBar(String title, Double balance, Double max, String color) throws IOException {

    FXMLLoader fxmlLoader = new FXMLLoader(
        getClass().getResource("horizontal-balance/horizontal-balance-component.fxml"));
    this.component = (HBox) fxmlLoader.load();
    this.controller = fxmlLoader.getController();
    this.controller.setTitle(title);
    this.controller.setTextColor(color);
    this.controller.setProgressBarColor(color);

    this.controller.setBalance(balance, max);
  }

  public void setProcessBar(Double balance, Double max) {
    this.controller.setBalance(balance, max);
  }

  public HBox getComponent() {
    return this.component;
  }

}
