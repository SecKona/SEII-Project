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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import meinRecipe_DB.DBOperator;
import meinRecipe_Model.Ingredient;
import meinRecipe_Model.Instruction;
import meinRecipe_Model.Recipe;

/**
 * Controller class for display page
 * 
 * @author SecKona
 *
 */
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
	
	@FXML
	private Button delete;

	private static Recipe viewingRecipe;

	@FXML
	/**
	 * Change scene to edit page, is called when corresponding button is clicked
	 * 
	 * @param event javaFX event
	 * @throws IOException java IOException
	 */
	void editRecipe(ActionEvent event) throws IOException {
		EditPageController.setEditingRecipe(viewingRecipe);

		Parent editPageScene = FXMLLoader.load(getClass().getResource("/meinRecipe_View/EditPage.fxml"));
		Scene mainPage = new Scene(editPageScene);

		Stage mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

		mainWindow.setOnCloseRequest(e -> {
			if (showAlert(Alert.AlertType.CONFIRMATION, "Warning", "All work will be leave unsaved",
					"Are you sure to quit?")) {
				mainWindow.close();
			} else {
				e.consume();
			}
		});
		mainWindow.setTitle("MeinRecipe - Edit page");
		mainWindow.setScene(mainPage);
	}

	@FXML
	/**
	 * Delete the displaying recipe in this scene and return to search page, is
	 * called when corresponding button is clicked
	 * 
	 * @param event javaFX event
	 * @throws IOException java IOException
	 */
	void deleteRecipe(ActionEvent event) throws IOException {
		if (showAlert(Alert.AlertType.CONFIRMATION, "Warning", "This recipe will be deleted",
				"Are you sure to delete it?")) {
			if (DBOperator.delete(viewingRecipe.getRecipeId())) {
				showAlert(Alert.AlertType.INFORMATION, "Info", "Delete successful", "This recipe is deleted");
				Parent searchPageScene = FXMLLoader.load(getClass().getResource("/meinRecipe_View/SearchPage.fxml"));
				Scene mainPage = new Scene(searchPageScene);

				Stage mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

				mainWindow.setTitle("MeinRecipe - Search page");
				mainWindow.setScene(mainPage);
			} else {
				showAlert(Alert.AlertType.ERROR, "Error", "Delete failed", "Unable to delete this recipe!");
				return;
			}
		}
	}

	@FXML
	/**
	 * Calculate ingredient amount with given serve number, is called when
	 * corresponding button is clicked
	 * 
	 * @param event javaFX event
	 */
	void calculateClicked(ActionEvent event) {
		if (checkInput(serveNum, "[1-9][0-9]?")) {
			viewingRecipe.calIngredients(Integer.valueOf(this.serveNum.getText()));
			this.ingredientList.refresh();
		} else {
			showAlert(Alert.AlertType.ERROR, "Error", "Illegal input", "Please input a positive number [1~99]!");
			return;
		}
	}

	@FXML
	/**
	 * Change scene to home page, is called when corresponding button is clicked
	 * 
	 * @param event javaFX event
	 * @throws IOException java IOException
	 */
	public void returnHome(ActionEvent event) throws IOException {
		Parent homePageScene = FXMLLoader.load(getClass().getResource("/meinRecipe_View/HomePage.fxml"));
		Scene mainPage = new Scene(homePageScene);
		Stage mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

		mainWindow.setOnCloseRequest(e -> {
			if (showAlert(Alert.AlertType.CONFIRMATION, "Warning", "",
					"Are you sure to quit?")) {
				mainWindow.close();
			} else {
				e.consume();
			}
		});
		mainWindow.setTitle("MeinRecipe - Home page");
		mainWindow.setScene(mainPage);
	}

	@FXML
	/**
	 * Change scene to search page, is called when corresponding button is clicked
	 * 
	 * @param event javaFX event
	 * @throws IOException java IOException
	 */
	void back(ActionEvent event) throws IOException {
		Parent searchPageScene = FXMLLoader.load(getClass().getResource("/meinRecipe_View/SearchPage.fxml"));
		Scene mainPage = new Scene(searchPageScene);
		Stage mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

		mainWindow.setOnCloseRequest(e -> {
			if (showAlert(Alert.AlertType.CONFIRMATION, "Warning", "",
					"Are you sure to quit?")) {
				mainWindow.close();
			} else {
				e.consume();
			}
		});
		mainWindow.setTitle("MeinRecipe - Search page");
		mainWindow.setScene(mainPage);
	}

	@FXML
	/**
	 * JavaFX scene initialize method, is called when setting scene
	 */
	public void initialize() {
		if (viewingRecipe.getRecipeId() == null) {
			this.delete.setDisable(true);
		}
		this.fillinRecipeView(viewingRecipe);
	}

	/**
	 * Set object of this.viewingRecipe
	 * 
	 * @param vr viewingRecipe object
	 */
	public static void setViewingRecipe(Recipe vr) {
		viewingRecipe = new Recipe(vr);
	}

	/**
	 * A customized method, fill in this scene with given recipe object
	 * 
	 * @param r given recipe object
	 */
	public void fillinRecipeView(Recipe r) {
		this.recipeName.setText(r.getRecipeName());
		this.region.setText(r.getRecipeRegion());
		this.prepTime.setText(String.valueOf(r.getPrepTime()) + "min");
		this.cookTime.setText(String.valueOf(r.getCookTime()) + "min");
		this.serveNum.setText(String.valueOf(r.getServe()));

		ObservableList<Ingredient> ingList = FXCollections.observableArrayList(r.getIngredients());
		this.ingredientList.setItems(ingList);

		ObservableList<Instruction> insList = FXCollections.observableArrayList(r.getInstructions());
		this.instructionList.setItems(insList);

		try {
			FileInputStream inputImage = new FileInputStream(System.getProperty("user.dir") + "/src" + r.getImageURL());
			this.recipeImage.setImage(new Image(inputImage));
			inputImage.close();
		} catch (IOException e) {
			viewingRecipe.setImageURL("/recipeImage/default.jpg");
			showAlert(Alert.AlertType.ERROR, "Error", "Failed to load recipe image",
					"Recipe image will be changed to default");
			return;
		}
	}

	/**
	 * A customized method, check input text in given textField object with given
	 * Regular expression
	 * 
	 * @param textField given textField
	 * @param regex     given Regular expression
	 * @return if check passed or not
	 */
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

	/**
	 * A customized method, show alert according to given settings
	 * 
	 * @param alertType   alert type
	 * @param title       alert title
	 * @param headerText  alert header text
	 * @param contentText alert content text
	 * @return if user confirm or not
	 */
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
