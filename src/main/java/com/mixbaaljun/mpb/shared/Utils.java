package com.mixbaaljun.mpb.shared;

import java.math.BigDecimal;
import java.net.URL;
import java.text.DecimalFormat;

import javafx.fxml.FXMLLoader;

public final class Utils {

  private static final String AMOUNTFORMAT = "#,##0.00";

  public static String decimalForted(BigDecimal bigDecimal) {
    DecimalFormat df = new DecimalFormat(AMOUNTFORMAT);
    return String.format("$ %s", df.format(bigDecimal));

  }

  public static URL getUrl(String path) {

    return Utils.class.getResource(String.format("/com/mixbaaljun/mpb/%s", path));
  }
}
