package com.mixbaaljun.mpb.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

import com.mixbaaljun.mpb.DTO.BudgetDTO;
import com.mixbaaljun.mpb.components.DialogAddExpense;
import com.mixbaaljun.mpb.components.DialogAddInitialIncome;
import com.mixbaaljun.mpb.components.HBalanceProcessBar;
import com.mixbaaljun.mpb.components.VBalanceProgressBar;
import com.mixbaaljun.mpb.incomes.domain.Expense;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public final class PrincipalController implements Initializable {

  @FXML
  private VBox principalIncomesContainer;

  @FXML
  private HBox firstQuadrant;

  @FXML
  private Button addExpenseId;

  @FXML
  private HBox initialIncomeContainer;

  @FXML
  private Label incomeLabel;

  private BudgetDTO budgetDTO;

  private VBalanceProgressBar initialProgressBar;
  private VBalanceProgressBar finalProgressBar;

  @Override
  public void initialize(URL location, ResourceBundle resources) {

    this.initUserData();

    try {

      this.addExpenseId.setOnAction((event) -> {
        DialogAddExpense dialog = new DialogAddExpense();
        Optional<Expense> result = dialog.getDialog().showAndWait();
        if (result.isPresent()) {
          Expense expense = result.get();
          System.out.println(expense);
          this.budgetDTO.addExpense(expense);
          this.updateProgressBar();

        }
      });

      this.initialIncomeContainer.setOnMouseClicked((event) -> {
        DialogAddInitialIncome dialog = new DialogAddInitialIncome(this.budgetDTO.getInitialBalance());
        Optional<String> result = dialog.getDialog().showAndWait();
        if (result.isPresent()) {
          String income = result.get();
          this.budgetDTO.setInitialBalanceFromString(income);
          this.incomeLabel.setText(this.budgetDTO.getFormatedInitialBalance());
          this.updateProgressBar();
        }
      });

      // HBalanceProcessBar processBar = ;
      this.principalIncomesContainer.getChildren().addAll(Arrays.asList(
          (new HBalanceProcessBar("Previsto", "#f46524")).getComponent(),
          (new HBalanceProcessBar("Previsto", "#000000")).getComponent()));

      this.initFirstQuadrant();

    } catch (Exception e) {
      System.err.println("Error--->>" + e.getMessage());
    }

  }

  private void initUserData() {
    // TODO load data form db
    this.budgetDTO = new BudgetDTO();

    this.incomeLabel.setText(this.budgetDTO.getFormatedInitialBalance());
  }

  private void initFirstQuadrant() throws IOException {

    Double max = Math.max(this.budgetDTO.getInitialBalance().doubleValue(), this.budgetDTO.getBalance().doubleValue());

    this.initialProgressBar = new VBalanceProgressBar("Saldo actual del mes",
        this.budgetDTO.getInitialBalance().doubleValue(), max, "#334960");

    this.finalProgressBar = new VBalanceProgressBar("Saldo final del mes",
        this.budgetDTO.getBalance().doubleValue(), max, "#f46524");

    this.firstQuadrant.getChildren()
        .addAll(
            initialProgressBar.getComponent(),
            finalProgressBar.getComponent());
  }

  private void updateProgressBar() {
    Double max = Math.max(this.budgetDTO.getInitialBalance().doubleValue(), this.budgetDTO.getBalance().doubleValue());

    this.initialProgressBar.updateProgressBar(this.budgetDTO.getInitialBalance().doubleValue(), max);
    this.finalProgressBar.updateProgressBar(this.budgetDTO.getBalance().doubleValue(), max);
  }

}
