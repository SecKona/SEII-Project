package meinRecipe_Controller;

import java.io.FileInputStream;
import java.io.IOException;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import meinRecipe_DB.DBOperator;
import meinRecipe_Model.Ingredient;
import meinRecipe_Model.Instruction;
import meinRecipe_Model.Recipe;

public class DisplayPageController {

	@FXML
	private ListView<Ingredient> ingredientList;

	@FXML
	private ListView<Instruction> instructionList;

	@FXML
	private Label recipeName;

	@FXML
	private Label region;

	@FXML
	private ImageView recipeImage;

	@FXML
	private Label prepTime;

	@FXML
	private Label cookTime;

	@FXML
	private TextField serveNum;

	private static Recipe viewingRecipe;

	@FXML
	void editRecipe(ActionEvent event) throws IOException {
		EditPageController.setEditingRecipe(viewingRecipe);

		Parent editPageScene = FXMLLoader.load(getClass().getResource("/meinRecipe_View/EditPage.fxml"));
		Scene mainPage = new Scene(editPageScene);

		Stage mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

		mainWindow.setTitle("MeinRecipe - Edit recipe");
		mainWindow.setScene(mainPage);
	}

	@FXML
	void deleteRecipe(ActionEvent event) throws IOException {
		if (showAlert(Alert.AlertType.CONFIRMATION, "Warning", "The selected recipe will be deleted",
				"Are you sure to delete this recipe?")) {
			if (DBOperator.delete(viewingRecipe.getRecipeId())) {
				showAlert(Alert.AlertType.INFORMATION, "Info", "Delete successful", "The selected recipe is deleted");
				Parent searchPageScene = FXMLLoader.load(getClass().getResource("/meinRecipe_View/SearchPage.fxml"));
				Scene mainPage = new Scene(searchPageScene);

				Stage mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

				mainWindow.setTitle("MeinRecipe - Search page");
				mainWindow.setScene(mainPage);
			} else {
				showAlert(Alert.AlertType.ERROR, "Error", "Delete failed", "Can not delete this recipe!");
				return;
			}
		}
	}

	@FXML
	void calculateClicked(ActionEvent event) {
		if (checkInput(serveNum, "^[1-9]\\d*$")) {
			viewingRecipe.calIngredients(Integer.valueOf(this.serveNum.getText()));
			this.ingredientList.refresh();
		} else {
			showAlert(Alert.AlertType.ERROR, "Error", "Illegal input", "Please input a positive number!");
			return;
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
	void back(ActionEvent event) throws IOException {
		Parent searchPageScene = FXMLLoader.load(getClass().getResource("/meinRecipe_View/SearchPage.fxml"));
		Scene mainPage = new Scene(searchPageScene);
		Stage mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

		mainWindow.setTitle("MeinRecipe - Search page");
		mainWindow.setScene(mainPage);
	}

	@FXML
	void showInf(ActionEvent event) {
		// need a new window (maybe)
	}

	@FXML
	public void initialize() {
		this.fillinRecipeView(viewingRecipe);
	}

	public static void setViewingRecipe(Recipe vr) {
		viewingRecipe = new Recipe(vr);
	}

	public void fillinRecipeView(Recipe r) {
		this.recipeName.setText(r.getRecipeName());
		this.region.setText(r.getRecipeRegion());
		this.prepTime.setText(String.valueOf(r.getPrepTime()));
		this.cookTime.setText(String.valueOf(r.getCookTime()));
		this.serveNum.setText(String.valueOf(r.getServe()));

		ObservableList<Ingredient> ingList = FXCollections.observableArrayList(r.getIngredients());
		this.ingredientList.setItems(ingList);

		ObservableList<Instruction> insList = FXCollections.observableArrayList(r.getInstructions());
		this.instructionList.setItems(insList);
		
		try {
			FileInputStream inputImage = new FileInputStream(
					System.getProperty("user.dir") + "/src" + r.getImageURL());
			this.recipeImage.setImage(new Image(inputImage));
			inputImage.close();
		} catch (IOException e) {
			viewingRecipe.setImageURL("/recipeImage/default.jpg");
			showAlert(Alert.AlertType.ERROR, "Error", "Failed to load recipe image", "Recipe image will be changed to default");
			return;
		}
	}

	public boolean checkInput(TextField textField, String regex) {
		if (textField.getText() == null) {
			return false;
		}
		if (textField.getText().matches(regex)) {
			return true;
		} else {
			return false;
		}
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
