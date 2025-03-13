package com.mixbaaljun.mpb.components;

import java.io.IOException;
import java.net.URL;

import com.mixbaaljun.mpb.controller.HorizontalBalanceController;
import com.mixbaaljun.mpb.shared.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

public class HBalanceProcessBar {

  private HBox component;
  private HorizontalBalanceController controller;

  public HBalanceProcessBar(String title, Double balance, Double max, String color) throws IOException {

    FXMLLoader fxmlLoader = new FXMLLoader(
        getClass().getResource("horizontal-balance/horizontal-balance-component.fxml"));
    this.component = (HBox) fxmlLoader.load();

    // URL url = getClass().getResource("horizontal-balance/index.css");
    String style = "#horizontalContainer:hover { -fx-background-color: #a7b0bf;}";
    // this.processbarhpb.setStyle(style);
    // this.component.getStylesheets().add("index");
    this.component.setStyle(style);
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
