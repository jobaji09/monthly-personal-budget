package com.mixbaaljun.mpb.components;

import java.io.IOException;

import com.mixbaaljun.mpb.controller.VerticalBalanceController;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public final class VBalanceProgressBar {

  private Pane component;
  private VerticalBalanceController controller;

  public VBalanceProgressBar(String title, String balance, String color) throws IOException {

    FXMLLoader fxmlLoader = new FXMLLoader(
        getClass().getResource("vertical-balance/vertical-balance-component.fxml"));
    this.component = (Pane) fxmlLoader.load();
    // this.component.getStylesheets().add(getClass().getResource("horizontal-balance/index.css").toExternalForm());
    this.controller = fxmlLoader.getController();
    this.controller.setTitle(title);
    this.controller.setTextColor(color);
    this.controller.setProgressBarColor(color);
  }

  public Pane getComponent() {
    return this.component;
  }
}
