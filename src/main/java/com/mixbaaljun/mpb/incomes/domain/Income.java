package com.mixbaaljun.mpb.incomes.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Income {

    public static final String AMOUNTFORMAT ="#,##0.00";


    private IncomeType type;
    private BigDecimal expectedAmount;
    private BigDecimal realAmount;

    public BigDecimal getDifference(){
        return this.realAmount.divide(this.expectedAmount);
    }

}
