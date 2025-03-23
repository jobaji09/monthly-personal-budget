package com.mixbaaljun.mpb.components;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

import com.mixbaaljun.mpb.controller.AddIncomeController;
import com.mixbaaljun.mpb.shared.Utils;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.HBox;

public class DialogAddInitialIncome {
  private final Dialog<String> dialog;
  private HBox child;
  private AddIncomeController addIncomeController;
  private String initialIncome;

  public DialogAddInitialIncome(BigDecimal balance) {
    this.dialog = new Dialog<>();
    String title = Objects.isNull(balance) || balance.equals(BigDecimal.ZERO) ? "Introduce tu saldo actual"
        : "Actualiza tu saldo";
    this.dialog.setTitle(title);
    this.dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

    FXMLLoader fxmlLoader = new FXMLLoader(Utils.getUrl("components/add-income/add-income.fxml"));
    try {
      this.child = (HBox) fxmlLoader.load();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
    this.addIncomeController = fxmlLoader.getController();
    if (!balance.equals(BigDecimal.ZERO)) {
      this.addIncomeController.setIntialIncomePrompt(Utils.decimalForted(balance));

    }
    this.dialog.getDialogPane().setContent(this.child);

    this.dialog.setOnShown(event -> {
      Platform.runLater(this.addIncomeController::requestFocus);
      event.consume();
    });

    Button okButton = (Button) this.dialog.getDialogPane().lookupButton(ButtonType.OK);
    okButton.addEventFilter(ActionEvent.ACTION, (event) -> {
      if (!addIncomeController.hasAllFields()) {
        event.consume();
      } else {
        initialIncome = addIncomeController.getInitialincome();
      }
    });

    this.dialog.setResultConverter((ButtonType param) -> {
      if (param == ButtonType.OK) {
        return initialIncome;
      }
      return null;
    });
  }

  public Dialog<String> getDialog() {
    return this.dialog;
  }

}
