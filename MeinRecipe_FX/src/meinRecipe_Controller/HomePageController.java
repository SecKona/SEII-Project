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

public class HomePageController {

    @FXML
    public void turnToSearchPage(ActionEvent event) throws IOException {
    	Parent searchPageScene = FXMLLoader.load(getClass().getResource("/meinRecipe_View/SearchPage.fxml"));
		Scene mainPage = new Scene(searchPageScene);
		Stage mainWindow = (Stage)((Node) event.getSource()).getScene().getWindow();
		
		mainWindow.setTitle("MeinRecipe - Search page");
		mainWindow.setScene(mainPage);
    }

    @FXML
    public void turnToEditRecipePage(ActionEvent event) throws IOException {
		EditPageController.setEditingRecipe(new Recipe());
		
    	Parent editRecipePageScene = FXMLLoader.load(getClass().getResource("/meinRecipe_View/EditPage.fxml"));
		Scene mainPage = new Scene(editRecipePageScene);
		Stage mainWindow = (Stage)((Node) event.getSource()).getScene().getWindow();
		
		mainWindow.setTitle("MeinRecipe - Edit recipe");
		mainWindow.setScene(mainPage);
    }

    @FXML
    public void showInf(ActionEvent event) {
    	// need a new window (maybe)
    }

}
