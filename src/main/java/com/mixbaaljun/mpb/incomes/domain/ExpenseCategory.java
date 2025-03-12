package com.mixbaaljun.mpb.incomes.domain;

import java.util.stream.Stream;

public enum ExpenseCategory {

  FOOD(0, "Comida"),
  GIVES(1, "Regalos"),
  HEALTH(2, "Salud/médicos"),
  HOUSE(3, "Vivienda"),
  TRASPORT(4, "Transporte"),
  PERSONAL(5, "Gastos personales"),
  PET(6, "Mascotas"),
  SUPPLIES(7, "Suministros (luz, agua, gas, etc.)"),
  TRIPS(8, "Viajes"),
  DEPT(9, "Deuda"),
  OTHERS(10, "Otros");

  private ExpenseCategory(int index, String value) {
    this.index = index;
    this.value = value;

  }

  private int index;
  private String value;

  public String toString() {
    return this.value;
  }

  public static ExpenseCategory fromIndex(int index) {
    return Stream.of(ExpenseCategory.values())
        .filter(ec -> ec.index == index)
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}
