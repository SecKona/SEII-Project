package meinRecipe_Controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import meinRecipe_DB.DBOperator;
import meinRecipe_Model.Recipe;

public class SearchPageController {

	@FXML
	private TableView<Recipe> recipeTable;

	@FXML
	private TableColumn<Recipe, String> tableNameSP;

	@FXML
	private TableColumn<Recipe, String> tableRegionSP;

	@FXML
	private ChoiceBox<String> regionChoices;

	@FXML
	private TextField inputName;

	private List<Recipe> recipeList = new LinkedList<>();

	@FXML
	void searchByName(ActionEvent event) {
		if (this.inputName.getText() == null) {
			showAlert(Alert.AlertType.ERROR, "Error", "Null input", "Please input a recipe name for search!");
			return;
		}
		if (DBOperator.searchByName(this.recipeList, inputName.getText())) {
			fillinRecipeTable(this.recipeList);
		} else {
			showAlert(Alert.AlertType.ERROR, "Error", "Search failed", "Illegal recipe name!");
			return;
		}
	}

	@FXML
	void searchByRegion(ActionEvent event) {
		if (DBOperator.searchByRegion(this.recipeList, regionChoices.getSelectionModel().getSelectedItem())) {
			fillinRecipeTable(this.recipeList);
		} else {
			showAlert(Alert.AlertType.ERROR, "Error", "Search failed", "Illegal recipe region!");
			return;
		}
	}

	@FXML
	void deleteClicked(ActionEvent event) {
		if (this.recipeTable.getSelectionModel().getSelectedItem() != null) {
			if (showAlert(Alert.AlertType.CONFIRMATION, "Warning", "The selected recipe will be deleted",
					"Are you sure to delete this recipe?")) {
				if (DBOperator.delete(this.recipeTable.getSelectionModel().getSelectedItem().getRecipeId())) {
					this.recipeList.remove(this.recipeTable.getSelectionModel().getSelectedItem());
					showAlert(Alert.AlertType.INFORMATION, "Info", "Delete successful",
							"The selected recipe is deleted");
					fillinRecipeTable(recipeList);
				} else {
					showAlert(Alert.AlertType.ERROR, "Error", "Delete failed", "Can not delete this recipe!");
					return;
				}
			}
		} else {
			showAlert(Alert.AlertType.ERROR, "Error", "None of recipe is selected",
					"Please select a recipe to delete!");
		}
	}

	@FXML
	void viewClicked(ActionEvent event) throws IOException {
		if (this.recipeTable.getSelectionModel().getSelectedItem() != null) {
			DisplayPageController.setViewingRecipe(recipeTable.getSelectionModel().getSelectedItem());

			Parent recipeDisplayScene = FXMLLoader.load(getClass().getResource("/meinRecipe_View/DisplayPage.fxml"));
			Scene mainPage = new Scene(recipeDisplayScene);

			Stage mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

			mainWindow.setTitle("MeinRecipe - Display page");
			mainWindow.setScene(mainPage);
		} else {
			showAlert(Alert.AlertType.ERROR, "Error", "None of recipe is selected",
					"Please select a recipe to display!");
		}
	}

	@FXML
	public void returnHome(ActionEvent event) throws IOException {
		Parent homePageScene = FXMLLoader.load(getClass().getResource("/meinRecipe_View/HomePage.fxml"));
		Scene mainPage = new Scene(homePageScene);
		Stage mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

		mainWindow.setTitle("MeinRecipe - Home page");
		mainWindow.setScene(mainPage);
	}

	@FXML
	public void initialize() {
		ObservableList<String> choiceList = FXCollections.observableArrayList("EU", "SEA", "SA", "LA", "A", "Others");
		this.regionChoices.setValue("Others");
		this.regionChoices.setItems(choiceList);
		if(!DBOperator.searchAll(recipeList)) {
			showAlert(Alert.AlertType.ERROR, "Error", "Failed to load all existing recipe", "Recipe list will be empty");
			return;
		}
		fillinRecipeTable(recipeList);
	}

	public void fillinRecipeTable(List<Recipe> rl) {
		ObservableList<Recipe> resultList = FXCollections.observableArrayList(rl);
		tableNameSP.setCellValueFactory(new PropertyValueFactory<>("recipeName"));
		tableRegionSP.setCellValueFactory(new PropertyValueFactory<>("recipeRegion"));
		recipeTable.setItems(resultList);
	}

	public boolean showAlert(AlertType alertType, String title, String headerText, String contentText) {
		Alert a = new Alert(alertType);
		a.setTitle(title);
		a.setHeaderText(headerText);
		a.setContentText(contentText);
		Optional<ButtonType> result = a.showAndWait();
		if (result.isPresent() && result.get() == ButtonType.OK) {
			return true;
		} else {
			return false;
		}
	}
}
