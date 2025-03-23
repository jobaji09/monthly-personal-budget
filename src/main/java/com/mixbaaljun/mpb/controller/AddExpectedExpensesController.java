package com.mixbaaljun.mpb.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

import com.mixbaaljun.mpb.DTO.ExpenseCategory;
import com.mixbaaljun.mpb.shared.Utils;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class AddExpectedExpensesController implements Initializable {

  @FXML
  private TextField foodField;
  @FXML
  private TextField givesTextField;
  @FXML
  private TextField healthTextField;
  @FXML
  private TextField houseTextField;
  @FXML
  private TextField trasportTextField;
  @FXML
  private TextField personalTextField;
  @FXML
  private TextField petTextField;
  @FXML
  private TextField suppliesTextField;
  @FXML
  private TextField tripsTextField;
  @FXML
  private TextField deptTextField;
  @FXML
  private TextField othersTextField;

  private Map<ExpenseCategory, TextField> textFieldMap;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.textFieldMap = new HashMap<>() {
      {
        put(ExpenseCategory.FOOD, foodField);
        put(ExpenseCategory.GIVES, givesTextField);
        put(ExpenseCategory.HEALTH, healthTextField);
        put(ExpenseCategory.HOUSE, houseTextField);
        put(ExpenseCategory.TRASPORT, trasportTextField);
        put(ExpenseCategory.PERSONAL, personalTextField);
        put(ExpenseCategory.PET, petTextField);
        put(ExpenseCategory.SUPPLIES, suppliesTextField);
        put(ExpenseCategory.TRIPS, tripsTextField);
        put(ExpenseCategory.DEPT, deptTextField);
        put(ExpenseCategory.OTHERS, othersTextField);
      }
    };

  }

  public Map<ExpenseCategory, BigDecimal> getValues() {
    return new HashMap<>() {
      {
        put(ExpenseCategory.FOOD, convertTextToBigDecimal(foodField.getText()));
        put(ExpenseCategory.GIVES, convertTextToBigDecimal(givesTextField.getText()));
        put(ExpenseCategory.HEALTH, convertTextToBigDecimal(healthTextField.getText()));
        put(ExpenseCategory.HOUSE, convertTextToBigDecimal(houseTextField.getText()));
        put(ExpenseCategory.TRASPORT, convertTextToBigDecimal(trasportTextField.getText()));
        put(ExpenseCategory.PERSONAL, convertTextToBigDecimal(personalTextField.getText()));
        put(ExpenseCategory.PET, convertTextToBigDecimal(petTextField.getText()));
        put(ExpenseCategory.SUPPLIES, convertTextToBigDecimal(suppliesTextField.getText()));
        put(ExpenseCategory.TRIPS, convertTextToBigDecimal(tripsTextField.getText()));
        put(ExpenseCategory.DEPT, convertTextToBigDecimal(deptTextField.getText()));
        put(ExpenseCategory.OTHERS, convertTextToBigDecimal(othersTextField.getText()));
      }
    };
  }

  private BigDecimal convertTextToBigDecimal(String text) {
    if (Objects.isNull(text) || text.isEmpty()) {
      return BigDecimal.ZERO;
    }
    BigDecimal number = BigDecimal.ZERO;
    try {
      number = BigDecimal.valueOf(Double.valueOf(text));

    } catch (

    Exception e) {
      System.out.println(e.getMessage());
    }
    return number;
  }

  public void setDefaultValues(Map<ExpenseCategory, BigDecimal> expectedExpenses) {

    for (ExpenseCategory expectedCategory : ExpenseCategory.values()) {
      TextField textField = this.textFieldMap.get(expectedCategory);
      textField.setText(expectedExpenses.get(expectedCategory).toString());
      textField.setPromptText(Utils.decimalForted(expectedExpenses.get(expectedCategory)));
    }

  }

}
