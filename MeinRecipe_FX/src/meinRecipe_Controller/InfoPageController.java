package meinRecipe_Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InfoPageController {
	
	@FXML
    void backClicked(ActionEvent event) throws IOException {
		Parent homePageScene = FXMLLoader.load(getClass().getResource("/meinRecipe_View/HomePage.fxml"));
		Scene mainPage = new Scene(homePageScene);
		Stage mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

		mainWindow.setTitle("MeinRecipe - Home page");
		mainWindow.setScene(mainPage);
    }
}
