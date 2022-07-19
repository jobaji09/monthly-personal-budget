package com.mixbaaljun.mpb.component;

import com.mixbaaljun.mpb.incomes.domain.Income;
import com.mixbaaljun.mpb.incomes.domain.IncomeType;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ExpectedIncome extends HBox {

    private Label name;
    private TextField incomeValue;
    private Button addIncome;

    private IncomeType incomeType;

    private Consumer<List<String>> onError;

    private BiConsumer<Income,HBox> onAdded;

    public IncomeType getIncomeType() {
        return incomeType;
    }

    public void setOnAdded(BiConsumer<Income,HBox> onAdded){
        this.onAdded = onAdded;
    }

    public void setOnError(Consumer<List<String>> onError){
        this.onError = onError;
    }
    public ExpectedIncome(IncomeType incomeType) {
        super();
        this.incomeType = incomeType;
        this.setMinWidth(428);

        this.addIncome();
    }

    private void addIncome() {
        this.name = new Label(incomeType.getName());
        this.name.setMinWidth(142);
        this.incomeValue = new TextField();
        this.addIncome = new Button();
        this.addIncome.setText("Agrega");
        this.addIncome.setOnAction(this::addIncomeOnAction);
        this.incomeValue.setOnAction(this::addIncomeOnAction);

        this.getChildren().addAll(name, incomeValue, addIncome);
    }

    private void addIncomeOnAction(ActionEvent event){

        List<String> errors = validEarningField();
        if(!errors.isEmpty()){
            this.onError.accept(errors);
            return;
        }
        Income income = getValuesFromFields();
        this.onAdded.accept(income,this);
    }


    private Income getValuesFromFields(){
        BigDecimal earning = new BigDecimal(String.format("%.2f",Double.parseDouble(this.incomeValue.getText()) ));
        return new Income(incomeType,earning,BigDecimal.ZERO);

    }

    private List<String> validEarningField(){
        List<String> errors = new ArrayList<>();
        if(this.incomeValue.getText().isEmpty()){
            errors.add(incomeType.getName()+": No puedes agregar un valor vacio " );
        }

        try {
            Double.parseDouble(this.incomeValue.getText());
        }catch (NumberFormatException e){
            errors.add(incomeType.getName()+": Debe ser un número con decimal" );
        }

        return errors;
    }
}

