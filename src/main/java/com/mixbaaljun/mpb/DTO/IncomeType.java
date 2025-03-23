package com.mixbaaljun.mpb.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IncomeType implements Comparable<IncomeType>{
    private String name;

    @Override
    public int compareTo(IncomeType o) {
        if (name == null || o.name == null) {
            return 0;
        }
        return name.compareTo(o.name);
    }
}
