package com.mixbaaljun.mpb.components;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import com.mixbaaljun.mpb.DTO.ExpenseCategory;
import com.mixbaaljun.mpb.controller.AddExpectedExpensesController;
import com.mixbaaljun.mpb.shared.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DialogAddExpectedExpenses {
  private final Dialog<Map<ExpenseCategory, BigDecimal>> dialog;
  private VBox child;
  private AddExpectedExpensesController addExpectedExpensesController;
  private Map<ExpenseCategory, BigDecimal> values;

  public DialogAddExpectedExpenses(Map<ExpenseCategory, BigDecimal> expectedExpenses) {
    this.dialog = new Dialog<>();
    this.dialog.setTitle("Gastos previstos");
    this.dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    FXMLLoader fxmlLoader = new FXMLLoader(Utils.getUrl("components/add-expected-expenses/add-expected-expenses.fxml"));
    try {
      this.child = (VBox) fxmlLoader.load();
    } catch (IOException e) {
      System.out.println(e.getMessage());
      this.child = new VBox();
      Label label = new Label(e.getMessage());
      this.child.getChildren().add(label);
    }
    this.addExpectedExpensesController = fxmlLoader.getController();

    if (!expectedExpenses.isEmpty()) {
      this.addExpectedExpensesController.setDefaultValues(expectedExpenses);
    }

    this.dialog.getDialogPane().setContent(this.child);

    Button okButton = (Button) this.dialog.getDialogPane().lookupButton(ButtonType.OK);
    okButton.addEventFilter(ActionEvent.ACTION, (event) -> {
      values = addExpectedExpensesController.getValues();
    });

    this.dialog.setResultConverter((ButtonType param) -> {
      if (param == ButtonType.OK) {
        return values;
      }
      return null;
    });
  }

  public Dialog<Map<ExpenseCategory, BigDecimal>> getDialog() {
    return this.dialog;
  }
}
