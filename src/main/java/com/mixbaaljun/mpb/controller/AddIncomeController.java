package com.mixbaaljun.mpb.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class AddIncomeController implements Initializable {

  @FXML
  private TextField intialIncomeField;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    intialIncomeField.setPromptText("MX $");
  }

  public void setIntialIncomePrompt(String income) {
    intialIncomeField.setPromptText(income);

  }

  public String getInitialincome() {
    return this.intialIncomeField.getText();
  }

  public boolean hasAllFields() {
    return !this.intialIncomeField.getText().isEmpty();
  }

}
