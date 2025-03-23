package com.mixbaaljun.mpb.DTO;

import java.math.BigDecimal;

import com.mixbaaljun.mpb.shared.Utils;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExpenseDetailDTO {
  private final BigDecimal expense;
  private final BigDecimal expectedValue;
  private final ExpenseCategory category;

  public String getDiference() {
    return Utils.decimalForted(expectedValue.subtract(expense));
  }

  @Override
  public String toString() {
    return String.format("%s | %s | %s | %s", category.toString(), Utils.decimalForted(expectedValue),
        Utils.decimalForted(this.expense), this.getDiference());
  }

  public SimpleStringProperty getExpenseProperty() {
    return new SimpleStringProperty(Utils.decimalForted(expense));
  }

  public SimpleStringProperty getExpectedValueProperty() {
    return new SimpleStringProperty(Utils.decimalForted(expectedValue));
  }

  public SimpleStringProperty getCategoryProperty() {
    return new SimpleStringProperty(category.toString());
  }

  public SimpleStringProperty getDiferenceProperty() {
    return new SimpleStringProperty(this.getDiference());
  }

}
