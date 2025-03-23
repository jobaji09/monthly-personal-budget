package com.mixbaaljun.mpb.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.mixbaaljun.mpb.DTO.ExpenseDetailDTO;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ExpensesDetailController implements Initializable {

  @FXML
  private PieChart pieChart;

  @FXML
  private TableView<ExpenseDetailDTO> table;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    this.pieChart.setTitle("Gastos");
    this.pieChart.setTitleSide(Side.TOP);

  }

  public void setPieChartData(List<ExpenseDetailDTO> details) {
    List<PieChart.Data> data = details.stream()
        .filter(detail -> !detail.getExpense().equals(BigDecimal.ZERO))
        .map((detail) -> {
          PieChart.Data pieData = new PieChart.Data(detail.getCategory().toString(), detail.getExpense().doubleValue());
          pieData.nameProperty().bind(Bindings.concat(pieData.getName(), " $",
              pieData.pieValueProperty()));
          return pieData;
        })
        .collect(Collectors.toList());

    if (data.isEmpty()) {
      this.pieChart.setTitle("No hay datos que mostrar!");
      return;

    }

    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(data);

    this.pieChart.getData().addAll(pieChartData);

  }

  public void setExpenses(List<ExpenseDetailDTO> details) {
    TableColumn<ExpenseDetailDTO, String> categoryNameColumn = new TableColumn<>("");
    categoryNameColumn.setPrefWidth(182d);
    categoryNameColumn.setCellValueFactory(cellData -> cellData.getValue().getCategoryProperty());

    TableColumn<ExpenseDetailDTO, String> expectedColumn = new TableColumn<>("Previsto");
    expectedColumn.setPrefWidth(96d);
    expectedColumn.setCellValueFactory(cellData -> cellData.getValue().getExpectedValueProperty());
    TableColumn<ExpenseDetailDTO, String> expenseColumn = new TableColumn<>("Real");
    expenseColumn.setCellValueFactory(cellData -> cellData.getValue().getExpenseProperty());
    expenseColumn.setPrefWidth(96d);
    TableColumn<ExpenseDetailDTO, String> diferenceColumn = new TableColumn<>("Diferencia");
    diferenceColumn.setCellValueFactory(cellData -> cellData.getValue().getDiferenceProperty());
    diferenceColumn.setPrefWidth(96d);
    this.table.getColumns().addAll(categoryNameColumn, expectedColumn, expenseColumn, diferenceColumn);

    ObservableList<ExpenseDetailDTO> data = FXCollections.observableList(details);

    this.table.setItems(data);
  }

}
