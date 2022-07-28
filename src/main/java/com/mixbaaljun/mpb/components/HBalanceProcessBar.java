package com.mixbaaljun.mpb.components;

import java.io.IOException;

import com.mixbaaljun.mpb.controller.HorizontalBalanceController;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

public class HBalanceProcessBar {

  private HBox component;
  private HorizontalBalanceController controller;

  public HBalanceProcessBar(String title, String color) throws IOException {

    FXMLLoader fxmlLoader = new FXMLLoader(
        getClass().getResource("horizontal-balance/horizontal-balance-component.fxml"));
    this.component = (HBox) fxmlLoader.load();
    this.component.getStylesheets().add(getClass().getResource("horizontal-balance/index.css").toExternalForm());
    this.controller = fxmlLoader.getController();
    this.controller.setTitle(title);
    this.controller.setTextColor(color);
    this.controller.setProgressBarColor(color);
  }

  public HBox getComponent() {
    return this.component;
  }

}
