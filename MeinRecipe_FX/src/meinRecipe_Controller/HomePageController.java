package meinRecipe_Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import meinRecipe_Model.Recipe;

/**
 * Controller class for home page
 * 
 * @author SecKona
 *
 */
public class HomePageController {
	
	@FXML
	/**
	 * Change scene to search page, is called when corresponding button is clicked
	 * 
	 * @param event javaFX event
	 * @throws IOException java IOException
	 */
	public void turnToSearchPage(ActionEvent event) throws IOException {
		Parent searchPageScene = FXMLLoader.load(getClass().getResource("/meinRecipe_View/SearchPage.fxml"));
		Scene mainPage = new Scene(searchPageScene);
		Stage mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

		mainWindow.setTitle("MeinRecipe - Search page");
		mainWindow.setScene(mainPage);
	}

	@FXML
	/**
	 * Change scene to edit page, is called when corresponding button is clicked
	 * 
	 * @param event javaFX event
	 * @throws IOException java IOException
	 */
	public void turnToEditPage(ActionEvent event) throws IOException {
		EditPageController.setEditingRecipe(new Recipe());

		Parent editPageScene = FXMLLoader.load(getClass().getResource("/meinRecipe_View/EditPage.fxml"));
		Scene mainPage = new Scene(editPageScene);
		Stage mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

		mainWindow.setTitle("MeinRecipe - Edit page");
		mainWindow.setScene(mainPage);
	}

	@FXML
	/**
	 * Change scene to info page, is called when corresponding button is clicked
	 * 
	 * @param event javaFX event
	 * @throws IOException java IOException
	 */
	public void showInf(ActionEvent event) throws IOException {
		Parent infoPageScene = FXMLLoader.load(getClass().getResource("/meinRecipe_View/InfoPage.fxml"));
		Scene mainPage = new Scene(infoPageScene);
		Stage mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

		mainWindow.setTitle("MeinRecipe - Info page");
		mainWindow.setScene(mainPage);
	}

}
