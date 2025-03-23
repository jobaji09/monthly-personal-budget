package com.mixbaaljun.mpb.components;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.mixbaaljun.mpb.DTO.Income;
import com.mixbaaljun.mpb.DTO.IncomeType;
import com.mixbaaljun.mpb.controller.InputBalanceController;
import com.mixbaaljun.mpb.shared.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class InputBalance extends Pane {

  private VBox child;
  private InputBalanceController controller;

  private IncomeType incomeType;

  private Consumer<String> onError;

  private BiConsumer<Income, Pane> onAdded;

  public InputBalance(IncomeType incomeType, Consumer<String> onError) {
    super();
    this.incomeType = incomeType;
    this.onError = onError;
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(Utils.getUrl("components/input-balance/input-balance-view.fxml"));
      this.setId(UUID.randomUUID().toString());
      this.child = (VBox) fxmlLoader.load();
      this.controller = fxmlLoader.getController();
      this.controller.setTitleText(incomeType.getName());
      this.controller.setOnAction(this::addIncomeOnAction);

      this.maxHeight(this.child.getMaxHeight());
      this.maxWidth(this.child.getMaxWidth());
      this.getChildren().add(this.child);

    } catch (IOException e) {
      System.err.println(e.getMessage());
    }

  }

  public IncomeType getIncomeType() {
    return incomeType;
  }

  public void setOnAdded(BiConsumer<Income, Pane> onAdded) {
    this.onAdded = onAdded;
  }

  private void addIncomeOnAction(ActionEvent event) {

    Optional<BigDecimal> expected = this.controller.getIncomeValue();
    if (!expected.isPresent()) {
      this.onError.accept(String.format("Ocurrio un error en %s", this.incomeType.getName()));
      return;
    }
    Income income = new Income(incomeType, expected.get(), BigDecimal.ZERO);
    this.onAdded.accept(income, this);
  }

  // @Override
  // public void initialize(URL location, ResourceBundle resources) {

  // }

}
