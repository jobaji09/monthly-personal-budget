package com.mixbaaljun.mpb.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.mixbaaljun.mpb.DTO.Expense;
import com.mixbaaljun.mpb.DTO.ExpenseCategory;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class AddExpenseController implements Initializable {

  @FXML
  private TextField expenseValueId;

  @FXML
  private TextField descriptionValueId;

  @FXML
  private ChoiceBox<ExpenseCategory> categoryTypeId;

  private Integer categoryIndex;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.categoryTypeId.setItems(FXCollections.observableArrayList(ExpenseCategory.values()));

    this.categoryTypeId
        .getSelectionModel()
        .selectedIndexProperty()
        .addListener((ov, value, newValue) -> {
          this.categoryIndex = newValue.intValue();
        });
  }

  public boolean hasAllFields() {
    return !this.expenseValueId.getText().isEmpty() && Objects.nonNull(categoryIndex);
  }

  public Expense getValues() {

    BigDecimal expense = BigDecimal.valueOf(Double.valueOf(expenseValueId.getText()));
    return Expense.builder()
        .expense(expense)
        .description(descriptionValueId.getText())
        .category(ExpenseCategory.fromIndex(this.categoryIndex))
        .build();

  }

  public void requestFocus() {
    this.expenseValueId.requestFocus();
  }

}
