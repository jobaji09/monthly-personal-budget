package com.mixbaaljun.mpb.shared;

import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Optional;

public final class Utils {

  private static final String AMOUNTFORMAT = "#,##0.00";

  public static String decimalForted(BigDecimal bigDecimal) {
    if (Objects.isNull(bigDecimal)) {
      return "$ 0.0";
    }

    DecimalFormat df = new DecimalFormat(AMOUNTFORMAT);
    return String.format("$ %s", df.format(bigDecimal));

  }

  public static String toMoneyformat(String number) {
    BigDecimal bigDecimal = BigDecimal.valueOf(Double.valueOf(number));
    DecimalFormat df = new DecimalFormat(AMOUNTFORMAT);
    return String.format("$ %s", df.format(bigDecimal));

  }

  public static String toMoneyformat(Double number) {
    DecimalFormat df = new DecimalFormat(AMOUNTFORMAT);
    return String.format("$ %s", df.format(number));

  }

  public static URL getUrl(String path) {

    URL url = Utils.class.getClassLoader().getResource(String.format("com/mixbaaljun/mpb/%s",
        path));
    return Optional.ofNullable(url).orElseThrow();
  }
}
