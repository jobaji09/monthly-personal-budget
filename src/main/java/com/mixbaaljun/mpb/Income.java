package com.mixbaaljun.mpb;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Income {
    private IncomeType type;
    private BigDecimal expectedAmount;
    private BigDecimal realAmount;

    public BigDecimal getDifference(){
        return this.realAmount.divide(this.expectedAmount);
    }

}
