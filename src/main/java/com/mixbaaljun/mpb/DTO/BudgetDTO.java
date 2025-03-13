package com.mixbaaljun.mpb.DTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.mixbaaljun.mpb.incomes.domain.Expense;
import com.mixbaaljun.mpb.incomes.domain.ExpenseCategory;
import com.mixbaaljun.mpb.shared.Utils;

import lombok.Data;

@Data
public class BudgetDTO {

  private BigDecimal balance;
  private BigDecimal initialBalance;
  private BigDecimal totalExpenses;
  private BigDecimal totalExpetedExpenses;
  private BigDecimal totalIncomes;
  private BigDecimal totalExpetedIncomes;
  private List<Expense> expenses;
  private Map<ExpenseCategory, BigDecimal> expetedExpenses;

  public BudgetDTO() {
    this.balance = BigDecimal.ZERO;
    this.initialBalance = BigDecimal.ZERO;
    this.totalExpenses = BigDecimal.ZERO;
    this.totalExpetedExpenses = BigDecimal.ZERO;
    this.totalIncomes = BigDecimal.ZERO;
    this.totalExpetedIncomes = BigDecimal.ZERO;
    this.expenses = new ArrayList<>();
    this.expetedExpenses = new HashMap<>();
  }

  public void setExpetedExpenses(Map<ExpenseCategory, BigDecimal> expetedExpenses) {
    this.expetedExpenses = expetedExpenses;
    this.totalExpetedExpenses = BigDecimal.ZERO;
    for (ExpenseCategory expenseCategory : ExpenseCategory.values()) {
      this.totalExpetedExpenses = this.totalExpetedExpenses.add(this.expetedExpenses.get(expenseCategory));
    }
  }

  public String getFormatedBalance() {
    return Objects.isNull(balance) ? Utils.decimalForted(BigDecimal.ZERO) : Utils.decimalForted(balance);
  }

  public String getFormatedInitialBalance() {
    return Objects.isNull(initialBalance) ? Utils.decimalForted(BigDecimal.ZERO) : Utils.decimalForted(initialBalance);
  }

  public String getFormatedTotalExpenses() {
    return Objects.isNull(this.totalExpenses) ? Utils.decimalForted(BigDecimal.ZERO)
        : Utils.decimalForted(this.totalExpenses);
  }

  public void setInitialBalanceFromString(String initialBalance) {
    this.initialBalance = BigDecimal.valueOf(Double.valueOf(initialBalance));
    this.balance = this.initialBalance.add(totalIncomes.subtract(this.totalExpenses));

  }

  public boolean hasNotInitialBalance() {
    return Objects.isNull(initialBalance) || initialBalance.equals(BigDecimal.ZERO);
  }

  public void addExpense(Expense expense) {
    this.expenses.add(expense);
    this.totalExpenses = this.totalExpenses.add(expense.getExpense());
    this.balance = this.initialBalance.add(totalIncomes.subtract(this.totalExpenses));
  }
}
