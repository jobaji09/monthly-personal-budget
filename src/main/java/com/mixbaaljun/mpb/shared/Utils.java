package com.mixbaaljun.mpb.shared;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public final class Utils {

  private static final String AMOUNTFORMAT = "#,##0.00";

  public static String decimalForted(BigDecimal bigDecimal) {
    DecimalFormat df = new DecimalFormat(AMOUNTFORMAT);
    return String.format("$ %s", df.format(bigDecimal));

  }
}
