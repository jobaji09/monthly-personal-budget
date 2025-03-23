package com.mixbaaljun.mpb.components;

import java.io.IOException;

import com.mixbaaljun.mpb.DTO.Expense;
import com.mixbaaljun.mpb.controller.AddExpenseController;
import com.mixbaaljun.mpb.shared.Utils;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.VBox;

public class DialogAddExpense {

  private final Dialog<Expense> dialog;
  private VBox child;
  private AddExpenseController addExpenseController;
  private Expense expense;

  public DialogAddExpense() {
    this.dialog = new Dialog<>();
    this.dialog.setTitle("Agrega tu gasto");
    this.dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
    FXMLLoader fxmlLoader = new FXMLLoader(Utils.getUrl("components/add-expense/add-expense.fxml"));
    try {
      this.child = (VBox) fxmlLoader.load();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    this.addExpenseController = fxmlLoader.getController();
    this.dialog.getDialogPane().setContent(this.child);

    this.dialog.setOnShown(event -> {
      Platform.runLater(this.addExpenseController::requestFocus);
      event.consume();
    });

    Button okButton = (Button) this.dialog.getDialogPane().lookupButton(ButtonType.OK);
    okButton.addEventFilter(ActionEvent.ACTION, (event) -> {
      if (!addExpenseController.hasAllFields()) {
        event.consume();
      } else {
        expense = addExpenseController.getValues();
      }
    });

    this.dialog.setResultConverter((ButtonType param) -> {
      if (param == ButtonType.OK) {
        return expense;
      }
      return null;
    });
  }

  public Dialog<Expense> getDialog() {
    return this.dialog;
  }

}
