package com.mixbaaljun.mpb.controller;

import com.mixbaaljun.mpb.Income;
import com.mixbaaljun.mpb.IncomeType;
import com.mixbaaljun.mpb.component.ExpectedIncome;
import com.mixbaaljun.mpb.component.IncomeToAdd;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.math.BigDecimal;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;


public class EarningsViewController implements Initializable {

    @FXML
    private VBox earningsVBox;
    @FXML
    private VBox infoVbox;

    @FXML
    private VBox incomeExpectedList;

    private List<IncomeType> earningTypes;

    private List<Income> expectedIncomes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.earningTypes =  new ArrayList<>(Arrays.asList(new IncomeType("Ahorro"),
                new IncomeType("Sueldo"),
                new IncomeType("Bonificaciones"),
                new IncomeType("Intereses")));
        this.expectedIncomes = new ArrayList<>();
        this.toIncomeExpectedList();
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

        this.expectedIncomes.add(earning);

        List<IncomeToAdd> incomeToAdds = this.expectedIncomes.stream()
                .sorted(Comparator.comparing(Income::getType))
                .map(this::incomeToIncomeToAdd).toList();
        this.earningsVBox.getChildren().clear();
        this.earningsVBox.getChildren().addAll(incomeToAdds);

        this.ok(earning.getType().getName());

    }

    private IncomeToAdd incomeToIncomeToAdd(Income earning){
        IncomeToAdd incomeToAdd = new IncomeToAdd(earning);
        incomeToAdd.setOnActionToButton((event) -> {
            this.addToIncomeExpectedList(earning.getType());
            this.expectedIncomes.remove(earning);
            this.earningsVBox.getChildren().remove(incomeToAdd);
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



    private void toIncomeExpectedList(){
        this.earningTypes = earningTypes.stream().sorted(IncomeType::compareTo).collect(Collectors.toList());
        earningTypes.stream().forEach(System.out::println);
        List<ExpectedIncome> expectedIncomes =  earningTypes.stream().map(this::IncomeTypetoExpectedIncome).toList();
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
