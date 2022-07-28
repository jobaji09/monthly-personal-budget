package com.mixbaaljun.mpb.controller;

import com.mixbaaljun.mpb.components.ExpectedIncome;
import com.mixbaaljun.mpb.components.IncomeToAdd;
import com.mixbaaljun.mpb.incomes.domain.Income;
import com.mixbaaljun.mpb.incomes.domain.IncomeType;
import com.mixbaaljun.mpb.shared.Utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class EarningsViewController implements Initializable {

    @FXML
    private VBox earningsVBox;
    @FXML
    private VBox infoVbox;

    @FXML
    private VBox incomeExpectedList;

    @FXML
    private Label totalIncomeLabel;

    @FXML
    private Button ready;

    private BigDecimal totalIncomeToAdd;

    private List<IncomeType> earningTypes;

    private List<Income> expectedIncomes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.earningTypes = new ArrayList<>(Arrays.asList(new IncomeType("Ahorro"),
                new IncomeType("Sueldo"),
                new IncomeType("Bonificaciones"),
                new IncomeType("Intereses")));
        this.expectedIncomes = new ArrayList<>();
        this.totalIncomeLabel.setText("Total: $0.0");
        this.totalIncomeToAdd = BigDecimal.ZERO;
        this.ready.setOnAction(this::saveTotalExpectedIncomes);

        this.toIncomeExpectedList();
    }

    private void saveTotalExpectedIncomes(ActionEvent event) {
        System.err.println("Hola");
    }

    private void ok(String type) {
        this.infoVbox.getChildren().clear();
        Label ok = new Label(type + ": Se agrego tu ingreso previsto");
        ok.setTextFill(Color.GREEN);
        this.infoVbox.getChildren().add(ok);
    }

    private void sendToEarningVBox(Income earning, HBox toDelete) {

        this.incomeExpectedList.getChildren().remove(toDelete);
        IncomeType typeToDelete = ((ExpectedIncome) toDelete).getIncomeType();
        earningTypes.remove(typeToDelete);

        this.updateExpectedIncomes(earning);
        this.updateTotalExpectedIncome((total) -> this.totalIncomeToAdd = total.add(earning.getExpectedAmount()));

        this.ok(earning.getType().getName());

    }

    private void updateTotalExpectedIncome(Consumer<BigDecimal> consumer) {
        consumer.accept(this.totalIncomeToAdd);
        String total = String.format("Total: %s", Utils.decimalForted(this.totalIncomeToAdd));
        this.totalIncomeLabel.setText(total);
    }

    private void updateExpectedIncomes(Income income) {
        this.expectedIncomes.add(income);

        List<IncomeToAdd> incomeToAdds = this.expectedIncomes.stream()
                .sorted(Comparator.comparing(Income::getType))
                .map(this::incomeToIncomeToAdd).toList();

        this.earningsVBox.getChildren().clear();
        this.earningsVBox.getChildren().addAll(incomeToAdds);
    }

    private IncomeToAdd incomeToIncomeToAdd(Income earning) {
        IncomeToAdd incomeToAdd = new IncomeToAdd(earning);
        incomeToAdd.setOnActionToButton((event) -> {
            this.addToIncomeExpectedList(earning.getType());
            this.expectedIncomes.remove(earning);
            this.earningsVBox.getChildren().remove(incomeToAdd);
            this.updateTotalExpectedIncome(
                    (total) -> this.totalIncomeToAdd = total.subtract(earning.getExpectedAmount()));

        });
        return incomeToAdd;
    }

    private void addToIncomeExpectedList(IncomeType type) {
        this.earningTypes.add(type);
        this.toIncomeExpectedList();
    }

    private void sendErrorsToView(List<String> errors) {
        this.infoVbox.getChildren().clear();
        errors.stream().forEach((error) -> {
            Label errorLabel = new Label(error);
            errorLabel.setTextFill(Color.RED);
            this.infoVbox.getChildren().add(errorLabel);
        });
    }

    private void toIncomeExpectedList() {
        this.earningTypes = earningTypes.stream().sorted(IncomeType::compareTo).collect(Collectors.toList());
        List<ExpectedIncome> expectedIncomes = earningTypes.stream().map(this::IncomeTypetoExpectedIncome).toList();
        this.incomeExpectedList.getChildren().clear();
        this.incomeExpectedList.getChildren().addAll(expectedIncomes);
    }

    private ExpectedIncome IncomeTypetoExpectedIncome(IncomeType type) {
        ExpectedIncome expectedIncome = new ExpectedIncome(type);
        expectedIncome.setOnError(this::sendErrorsToView);
        expectedIncome.setOnAdded(this::sendToEarningVBox);
        return expectedIncome;
    }
}
