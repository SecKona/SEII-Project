package meinRecipe_Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import meinRecipe_DB.DBOperator;
import meinRecipe_Model.Ingredient;
import meinRecipe_Model.Instruction;
import meinRecipe_Model.Recipe;

public class EditPageController {

	@FXML
	private TableView<Ingredient> ingredientTable;

	@FXML
	private TableColumn<Ingredient, Integer> quantityCol;

	@FXML
	private TableColumn<Ingredient, String> ingDescriptionCol;

	@FXML
	private TableView<Instruction> instructionTable;

	@FXML
	private TableColumn<Instruction, Integer> stepCol;

	@FXML
	private TableColumn<Instruction, String> insDescriptionCol;

	@FXML
	private TextField recipeName;

	@FXML
	private ChoiceBox<String> regionChoices;

	@FXML
	private ImageView recipeImage;

	@FXML
	private TextField serveNum;

	@FXML
	private TextField prepTime;

	@FXML
	private TextField cookTime;

	private static Recipe editingRecipe;
	private Integer ingredientNum;
	private Integer instructionNum;

	@FXML
	void imageEditClicked(ActionEvent event) throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters()
				.addAll(new FileChooser.ExtensionFilter("Image Files", "*.bmp;*.jpg;*.jpeg;*.png"));
		File selectedImage = fileChooser.showOpenDialog((Stage) ((Node) event.getSource()).getScene().getWindow());
		if (selectedImage != null) {
			String storagePath = System.getProperty("user.dir") + "/src/recipeImage/" + selectedImage.getName();
			FileOutputStream outputImage = new FileOutputStream(storagePath);
			ImageIO.write(ImageIO.read(selectedImage), "png", outputImage);
			outputImage.close();
			editingRecipe.setImageURL("/recipeImage/" + selectedImage.getName());
			fillinRecipeView(editingRecipe);
		}
	}

	@FXML
	void addIngredientClicked(ActionEvent event) {
		Ingredient tmp = new Ingredient(++ingredientNum);
		this.ingredientTable.getItems().add(tmp);
		editingRecipe.addIngredient(tmp);
	}

	@FXML
	void deleteIngredientClicked(ActionEvent event) {
		if (this.ingredientTable.getSelectionModel().getSelectedItem() != null) {
			if (showAlert(Alert.AlertType.CONFIRMATION, "Warning", "The selected ingredient will be deleted",
					"Are you sure to delete this ingredient?")) {
				editingRecipe.getIngredients().remove(this.ingredientTable.getSelectionModel().getSelectedItem());
				editingRecipe.refreshIngredientId();
				fillinRecipeView(editingRecipe);
			}
		} else {
			showAlert(Alert.AlertType.ERROR, "Error", "None of ingredient is selected",
					"Please select an ingredient to delete!");
		}
	}

	@FXML
	void clearIngredientClicked(ActionEvent event) {
		if (showAlert(Alert.AlertType.CONFIRMATION, "Warning", "All ingredients will be deleted",
				"Are you sure to delete these ingredients?")) {
			editingRecipe.getIngredients().clear();
			fillinRecipeView(editingRecipe);
		}
	}

	@FXML
	void addInstructionClicked(ActionEvent event) {
		Instruction tmp = new Instruction(++instructionNum);
		this.instructionTable.getItems().add(tmp);
		editingRecipe.addInstruction(tmp);
	}

	@FXML
	void deleteInstructionClicked(ActionEvent event) {
		if (this.instructionTable.getSelectionModel().getSelectedItem() != null) {
			if (showAlert(Alert.AlertType.CONFIRMATION, "Warning", "The selected instruction will be deleted",
					"Are you sure to delete this instruction?")) {
				editingRecipe.getInstructions().remove(this.instructionTable.getSelectionModel().getSelectedItem());
				editingRecipe.refreshInstructionId();
				fillinRecipeView(editingRecipe);
			}
		} else {
			showAlert(Alert.AlertType.ERROR, "Error", "None of instruction is selected",
					"Please select an instruction to delete!");
		}
	}

	@FXML
	void clearInstructionClicked(ActionEvent event) {
		if (showAlert(Alert.AlertType.CONFIRMATION, "Warning", "All instructions will be deleted",
				"Are you sure to delete these instructions?")) {
			editingRecipe.getInstructions().clear();
			fillinRecipeView(editingRecipe);
		}
	}

	@FXML
	void saveClicked(ActionEvent event) throws IOException {
		if (this.recipeName.getText() == null || this.recipeName.getText() == "Default") {
			showAlert(Alert.AlertType.ERROR, "Error", "Null input: recipe name", "Please input the recipe name!");
			return;
		} else {
			editingRecipe.setRecipeName(this.recipeName.getText());
		}

		editingRecipe.setRecipeRegion(this.regionChoices.getSelectionModel().getSelectedItem());

		if (checkInput(this.prepTime, "^[1-9]\\d*$")) {
			editingRecipe.setPrepTime(Integer.valueOf(this.prepTime.getText()));
		} else {
			showAlert(Alert.AlertType.ERROR, "Error", "Illegal input: preparation time",
					"Please input a positive number!");
			return;
		}

		if (checkInput(this.cookTime, "^[1-9]\\d*$")) {
			editingRecipe.setCookTime(Integer.valueOf(this.cookTime.getText()));
		} else {
			showAlert(Alert.AlertType.ERROR, "Error", "Illegal input: cook time", "Please input a positive number!");
			return;
		}

		if (checkInput(this.serveNum, "^[1-9]\\d*$")) {
			editingRecipe.setServe(Integer.valueOf(this.serveNum.getText()));
		} else {
			showAlert(Alert.AlertType.ERROR, "Error", "Illegal input: serve number", "Please input a positive number!");
			return;
		}

		if (this.ingredientTable.getItems().isEmpty()) {
			if (showAlert(Alert.AlertType.CONFIRMATION, "Warning", "The ingredient table is empty",
					"Are you sure to leave it empty and save?")) {
				editingRecipe.setIngredients(this.ingredientTable.getItems());
			} else {
				return;
			}
		} else {
			editingRecipe.setIngredients(this.ingredientTable.getItems());
		}

		if (this.instructionTable.getItems().isEmpty()) {
			if (showAlert(Alert.AlertType.CONFIRMATION, "Warning", "The instruction table is empty",
					"Are you sure to leave it empty and save?")) {
				editingRecipe.setInstructions(this.instructionTable.getItems());
			} else {
				return;
			}
		} else {
			editingRecipe.setInstructions(this.instructionTable.getItems());
		}

		if (editingRecipe.getRecipeId() != null) {
			if (DBOperator.update(editingRecipe)) {
				showAlert(Alert.AlertType.INFORMATION, "Info", "Update successful", "The recipe is updated");
			} else {
				showAlert(Alert.AlertType.ERROR, "Error", "Update failed", "The recipe have invalid name!");
				return;
			}
			DisplayPageController.setViewingRecipe(editingRecipe);

			Parent recipeDisplayScene = FXMLLoader.load(getClass().getResource("/meinRecipe_View/DisplayPage.fxml"));
			Scene mainPage = new Scene(recipeDisplayScene);
			Stage mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

			mainWindow.setTitle("MeinRecipe - Display page");
			mainWindow.setScene(mainPage);
		} else {
			if (DBOperator.insert(editingRecipe)) {
				showAlert(Alert.AlertType.INFORMATION, "Info", "Save successful", "The recipe is saved");
			} else {
				showAlert(Alert.AlertType.ERROR, "Error", "Save failed", "The recipe have invalid name!");
				return;
			}

			Parent homePageScene = FXMLLoader.load(getClass().getResource("/meinRecipe_View/HomePage.fxml"));
			Scene mainPage = new Scene(homePageScene);
			Stage mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

			mainWindow.setTitle("MeinRecipe - Home page");
			mainWindow.setScene(mainPage);
		}
	}

	@FXML
	void returnHome(ActionEvent event) throws IOException {
		if (showAlert(Alert.AlertType.CONFIRMATION, "Warning", "All change will be lost",
				"Are you sure to quit without saving?")) {
			Parent homePageScene = FXMLLoader.load(getClass().getResource("/meinRecipe_View/HomePage.fxml"));
			Scene homePage = new Scene(homePageScene);
			Stage mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

			mainWindow.setTitle("MeinRecipe - Home page");
			mainWindow.setScene(homePage);
		}
	}

	@FXML
	void back(ActionEvent event) throws IOException {
		if (showAlert(Alert.AlertType.CONFIRMATION, "Warning", "All change will be lost",
				"Are you sure to quit without saving?")) {
			if (editingRecipe.getRecipeId() != null) {
				DisplayPageController.setViewingRecipe(editingRecipe);

				Parent recipeDisplayScene = FXMLLoader
						.load(getClass().getResource("/meinRecipe_View/DisplayPage.fxml"));
				Scene mainPage = new Scene(recipeDisplayScene);
				Stage mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

				mainWindow.setTitle("MeinRecipe - Display page");
				mainWindow.setScene(mainPage);
			} else {
				Parent homePageScene = FXMLLoader.load(getClass().getResource("/meinRecipe_View/HomePage.fxml"));
				Scene mainPage = new Scene(homePageScene);
				Stage mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

				mainWindow.setTitle("MeinRecipe - Home page");
				mainWindow.setScene(mainPage);
			}
		}
	}

	@FXML
	void showInf(ActionEvent event) {
		// need a new window (maybe)
	}

	@FXML
	public void initialize() {
		ObservableList<String> choiceList = FXCollections.observableArrayList("EU", "SEA", "SA", "LA", "A", "Others");
		this.regionChoices.setItems(choiceList);
		this.fillinRecipeView(editingRecipe);
		this.setEditView();
		this.ingredientNum = editingRecipe.getIngredients().size();
		this.instructionNum = editingRecipe.getInstructions().size();
	}

	public static void setEditingRecipe(Recipe er) {
		editingRecipe = new Recipe(er);
	}

	public void fillinRecipeView(Recipe r) {
		this.recipeName.setText(r.getRecipeName());
		this.regionChoices.setValue(r.getRecipeRegion());
		this.prepTime.setText(String.valueOf(r.getPrepTime()));
		this.cookTime.setText(String.valueOf(r.getCookTime()));
		this.serveNum.setText(String.valueOf(r.getServe()));

		ObservableList<Ingredient> ingList = FXCollections.observableArrayList(r.getIngredients());
		this.quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		this.ingDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
		this.ingredientTable.setItems(ingList);

		ObservableList<Instruction> insList = FXCollections.observableArrayList(r.getInstructions());
		this.stepCol.setCellValueFactory(new PropertyValueFactory<>("instructionId"));
		this.insDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
		this.instructionTable.setItems(insList);

		try {
			FileInputStream inputImage = new FileInputStream(System.getProperty("user.dir") + "/src" + r.getImageURL());
			this.recipeImage.setImage(new Image(inputImage));
			inputImage.close();
		} catch (IOException e) {
			showAlert(Alert.AlertType.ERROR, "Error", "Failed to load recipe image", "Recipe image will be changed to default");
			return;
		}
	}

	public void setEditView() {
		ingredientTable.setEditable(true);
		ingredientTable.addEventFilter(MouseEvent.MOUSE_DRAGGED, Event::consume);
		quantityCol.setCellFactory(t -> {
			TextFieldTableCell<Ingredient, Integer> cell = new TextFieldTableCell<Ingredient, Integer>(
					new IntegerStringConverter());
			cell.setOnKeyReleased(e -> {
				if(!e.getText().matches("^[0-9]\\d*$")) {
					showAlert(Alert.AlertType.ERROR, "Error", "Illegal input", "Please input a number!");
					cell.cancelEdit();
				}
			});
			return cell;
		});
		quantityCol.setOnEditCommit(event -> {
			((Ingredient) event.getTableView().getItems().get(event.getTablePosition().getRow()))
					.setQuantity(event.getNewValue());
		});
		ingDescriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
		ingDescriptionCol.setOnEditCommit(event -> {
			((Ingredient) event.getTableView().getItems().get(event.getTablePosition().getRow()))
					.setDescription(event.getNewValue());
		});

		instructionTable.setEditable(true);
		instructionTable.addEventFilter(MouseEvent.MOUSE_DRAGGED, Event::consume);
		insDescriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
		insDescriptionCol.setOnEditCommit(event -> {
			((Instruction) event.getTableView().getItems().get(event.getTablePosition().getRow()))
					.setDescription(event.getNewValue());
		});
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
