package com.mixbaaljun.mpb.controller;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.mixbaaljun.mpb.components.HBalanceProcessBar;
import com.mixbaaljun.mpb.components.VBalanceProgressBar;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public final class PrincipalController implements Initializable {

  @FXML
  private VBox principalIncomesContainer;

  @FXML
  private HBox firstQuadrant;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    try {
      // HBalanceProcessBar processBar = ;
      this.principalIncomesContainer.getChildren().addAll(Arrays.asList(
          (new HBalanceProcessBar("Previsto", "#f46524")).getComponent(),
          (new HBalanceProcessBar("Previsto", "#000000")).getComponent()));

      this.firstQuadrant.getChildren()
          .addAll(
              (new VBalanceProgressBar("Saldo actual del mes", "$ 30,000,000.00", "#334960")).getComponent(),
              (new VBalanceProgressBar("Saldo actual del mes", "$ 30,000,000.00", "#334960")).getComponent());
    } catch (Exception e) {
      System.err.println("Error--->>" + e.getMessage());
    }

  }

}
