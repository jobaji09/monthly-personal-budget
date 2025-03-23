package com.mixbaaljun.mpb.components;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.mixbaaljun.mpb.DTO.ExpenseDetailDTO;
import com.mixbaaljun.mpb.controller.ExpensesDetailController;
import com.mixbaaljun.mpb.shared.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SceneExpensesDetail {

  private AnchorPane root;
  private final List<ExpenseDetailDTO> expenseDetails;

  public SceneExpensesDetail(List<ExpenseDetailDTO> expenseDetails) {
    this.expenseDetails = expenseDetails;
  }

  public void show() throws IOException {
    Stage stage = new Stage();

    FXMLLoader fxmlLoader = new FXMLLoader(Utils.getUrl("components/expenses-detail/expenses-detail.fxml"));

    this.root = (AnchorPane) fxmlLoader.load();
    ExpensesDetailController controller = fxmlLoader.getController();
    controller.setPieChartData(expenseDetails);
    controller.setExpenses(expenseDetails);

    Scene scene = new Scene(root);
    URL url = getClass().getResource("/com/mixbaaljun/mpb/images/icon.png");
    stage.getIcons().add(new Image(url.toString()));
    stage.setResizable(false);
    stage.setTitle("Detalle de gastos");
    stage.setScene(scene);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.show();
  }

}
