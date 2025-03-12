package com.mixbaaljun.mpb.incomes.domain;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Expense {
  private final BigDecimal expense;
  private final String description;
  private final ExpenseCategory category;
}
