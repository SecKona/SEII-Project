package meinRecipe_Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import meinRecipe_DB.DBOperator;
import meinRecipe_Model.Ingredient;
import meinRecipe_Model.Instruction;
import meinRecipe_Model.Recipe;

/**
 * Controller class for edit page
 * 
 * @author SecKona
 *
 */
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

	@FXML
	private Button deleteRecipeButton;

	private static Recipe editingRecipe;

	@SuppressWarnings("resource")
	@FXML
	/**
	 * Edit the editing recipe in this scene and open a fileChooser to choose recipe
	 * image, is called when corresponding button is clicked
	 * 
	 * @param event javaFX event
	 * @throws IOException java IOException
	 */
	void imageEditClicked(ActionEvent event) throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters()
				.addAll(new FileChooser.ExtensionFilter("Image Files", "*.bmp;*.jpg;*.jpeg;*.png"));
		File selectedImage = fileChooser.showOpenDialog((Stage) ((Node) event.getSource()).getScene().getWindow());
		if (selectedImage != null) {
			String storagePath = System.getProperty("user.dir") + "/src/recipeImage/" + selectedImage.getName();
			FileOutputStream outputImage = new FileOutputStream(storagePath);
			if(ImageIO.read(selectedImage) != null) {
				ImageIO.write(ImageIO.read(selectedImage), "png", outputImage);
			}else {
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to storage recipe image",
						"Recipe image is damaged!");
				return;
			}
			outputImage.close();
			editingRecipe.setImageURL("/recipeImage/" + selectedImage.getName());
			try {
				FileInputStream inputImage = new FileInputStream(
						System.getProperty("user.dir") + "/src" + editingRecipe.getImageURL());
				this.recipeImage.setImage(new Image(inputImage));
				inputImage.close();
			} catch (IOException e) {
				showAlert(Alert.AlertType.ERROR, "Error", "Failed to load recipe image",
						"Recipe image will be changed to default");
				return;
			}
		}
	}

	@FXML
	/**
	 * Add a new ingredient in tableView, is called when corresponding button is
	 * clicked
	 * 
	 * @param event javaFX event
	 */
	void addIngredientClicked(ActionEvent event) {
		int ingredientNum = editingRecipe.getIngredients().size();
		Ingredient tmp = new Ingredient(++ingredientNum);
		editingRecipe.addIngredient(tmp);
		this.ingredientTable.getItems().add(tmp);
	}

	@FXML
	/**
	 * Delete the selected ingredient in tableView, is called when corresponding
	 * button is clicked
	 * 
	 * @param event javaFX event
	 */
	void deleteIngredientClicked(ActionEvent event) {
		if (this.ingredientTable.getSelectionModel().getSelectedItem() != null) {
			if (showAlert(Alert.AlertType.CONFIRMATION, "Warning", "The selected ingredient will be deleted",
					"Are you sure to delete this ingredient?")) {
				editingRecipe.getIngredients().remove(this.ingredientTable.getSelectionModel().getSelectedItem());
				editingRecipe.refreshIngredientId();
				ObservableList<Ingredient> ingList = FXCollections.observableArrayList(editingRecipe.getIngredients());
				this.quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
				this.ingDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
				this.ingredientTable.setItems(ingList);
			}
		} else {
			showAlert(Alert.AlertType.ERROR, "Error", "None of ingredient is selected",
					"Please select an ingredient to delete!");
		}
	}

	@FXML
	/**
	 * Delete all ingredients in tableView, is called when corresponding button is
	 * clicked
	 * 
	 * @param event javaFX event
	 */
	void clearIngredientClicked(ActionEvent event) {
		if (showAlert(Alert.AlertType.CONFIRMATION, "Warning", "All ingredients will be deleted",
				"Are you sure to delete these ingredients?")) {
			editingRecipe.getIngredients().clear();
			ObservableList<Ingredient> ingList = FXCollections.observableArrayList(editingRecipe.getIngredients());
			this.quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
			this.ingDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
			this.ingredientTable.setItems(ingList);
		}
	}

	@FXML
	/**
	 * Add a new instruction in tableView, is called when corresponding button is
	 * clicked
	 * 
	 * @param event javaFX event
	 */
	void addInstructionClicked(ActionEvent event) {
		int instructionNum = editingRecipe.getInstructions().size();
		Instruction tmp = new Instruction(++instructionNum);
		editingRecipe.addInstruction(tmp);
		this.instructionTable.getItems().add(tmp);
	}

	@FXML
	/**
	 * Delete the selected instruction in tableView, is called when corresponding
	 * button is clicked
	 * 
	 * @param event javaFX event
	 */
	void deleteInstructionClicked(ActionEvent event) {
		if (this.instructionTable.getSelectionModel().getSelectedItem() != null) {
			if (showAlert(Alert.AlertType.CONFIRMATION, "Warning", "The selected instruction will be deleted",
					"Are you sure to delete this instruction?")) {
				editingRecipe.getInstructions().remove(this.instructionTable.getSelectionModel().getSelectedItem());
				editingRecipe.refreshInstructionId();
				ObservableList<Instruction> insList = FXCollections
						.observableArrayList(editingRecipe.getInstructions());
				this.stepCol.setCellValueFactory(new PropertyValueFactory<>("instructionId"));
				this.insDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
				this.instructionTable.setItems(insList);
			}
		} else {
			showAlert(Alert.AlertType.ERROR, "Error", "None of instruction is selected",
					"Please select an instruction to delete!");
		}
	}

	@FXML
	/**
	 * Delete all instruction in tableView, is called when corresponding button is
	 * clicked
	 * 
	 * @param event javaFX event
	 */
	void clearInstructionClicked(ActionEvent event) {
		if (showAlert(Alert.AlertType.CONFIRMATION, "Warning", "All instructions will be deleted",
				"Are you sure to delete these instructions?")) {
			editingRecipe.getInstructions().clear();
			ObservableList<Instruction> insList = FXCollections.observableArrayList(editingRecipe.getInstructions());
			this.stepCol.setCellValueFactory(new PropertyValueFactory<>("instructionId"));
			this.insDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
			this.instructionTable.setItems(insList);
		}
	}

	@FXML
	/**
	 * Save the displaying recipe in this scene and return edited recipe to display
	 * page, is called when corresponding button is clicked
	 * 
	 * @param event javaFX event
	 * @throws IOException java IOException
	 */
	void saveClicked(ActionEvent event) throws IOException {
		if (this.recipeName.getText() == null) {
			showAlert(Alert.AlertType.ERROR, "Error", "Null input: recipe name", "Please input the recipe name!");
			return;
		} else {
			editingRecipe.setRecipeName(this.recipeName.getText());
		}

		editingRecipe.setRecipeRegion(this.regionChoices.getSelectionModel().getSelectedItem());

		if (editingRecipe.getImageURL().equals("/recipeImage/default.jpg")) {
			if (showAlert(Alert.AlertType.CONFIRMATION, "Warning", "The recipe image is default",
					"Are you sure to leave it as default and save?")) {
			} else {
				return;
			}
		}

		if (checkInput(this.prepTime, "[0-9]{0,3}")) {
			editingRecipe.setPrepTime(Integer.valueOf(this.prepTime.getText()));
		} else {
			showAlert(Alert.AlertType.ERROR, "Error", "Illegal input: preparation time",
					"Please input a positive number [0~999]!");
			return;
		}

		if (checkInput(this.cookTime, "[0-9]{0,3}")) {
			editingRecipe.setCookTime(Integer.valueOf(this.cookTime.getText()));
		} else {
			showAlert(Alert.AlertType.ERROR, "Error", "Illegal input: cook time",
					"Please input a positive number [0~999]!");
			return;
		}

		if (checkInput(this.serveNum, "[1-9][0-9]?")) {
			editingRecipe.setServe(Integer.valueOf(this.serveNum.getText()));
		} else {
			showAlert(Alert.AlertType.ERROR, "Error", "Illegal input: serve number",
					"Please input a positive number [1~99]!");
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
				showAlert(Alert.AlertType.ERROR, "Error", "Update failed", "Database connection lost!");
				return;
			}
		} else {
			if (DBOperator.insert(editingRecipe)) {
				showAlert(Alert.AlertType.INFORMATION, "Info", "Save successful", "The recipe is saved");
			} else {
				showAlert(Alert.AlertType.ERROR, "Error", "Save failed", "Database connection lost!");
				return;
			}
		}

		DisplayPageController.setViewingRecipe(editingRecipe);

		Parent recipeDisplayScene = FXMLLoader.load(getClass().getResource("/meinRecipe_View/DisplayPage.fxml"));
		Scene mainPage = new Scene(recipeDisplayScene);
		Stage mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

		mainWindow.setOnCloseRequest(e -> {
			if (showAlert(Alert.AlertType.CONFIRMATION, "Warning", "",
					"Are you sure to quit?")) {
				mainWindow.close();
			} else {
				e.consume();
			}
		});
		mainWindow.setTitle("MeinRecipe - Display page");
		mainWindow.setScene(mainPage);
	}

	@FXML
	/**
	 * Delete the editing recipe in this scene and return to search page, is called
	 * when corresponding button is clicked
	 * 
	 * @param event javaFX event
	 * @throws IOException java IOException
	 */
	void deleteRecipe(ActionEvent event) throws IOException {
		if (editingRecipe.getRecipeId() == null) {
			if (showAlert(Alert.AlertType.CONFIRMATION, "Warning", "This recipe will not be saved",
					"Are you sure to leave it unsaved and return to home page?")) {
				Parent homePageScene = FXMLLoader.load(getClass().getResource("/meinRecipe_View/HomePage.fxml"));
				Scene homePage = new Scene(homePageScene);
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
				mainWindow.setScene(homePage);
				return;
			} else {
				return;
			}
		}
		if (showAlert(Alert.AlertType.CONFIRMATION, "Warning", "This recipe will be deleted",
				"Are you sure to delete it?")) {
			if (DBOperator.delete(editingRecipe.getRecipeId())) {
				showAlert(Alert.AlertType.INFORMATION, "Info", "Delete successful", "This recipe is deleted");
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
			} else {
				showAlert(Alert.AlertType.ERROR, "Error", "Delete failed", "Unable to delete this recipe!");
				return;
			}
		}
	}

	@FXML
	/**
	 * Change scene to home page, is called when corresponding button is clicked
	 * 
	 * @param event javaFX event
	 * @throws IOException java IOException
	 */
	void returnHome(ActionEvent event) throws IOException {
		if (showAlert(Alert.AlertType.CONFIRMATION, "Warning", "All change will be lost",
				"Are you sure to quit without saving?")) {
			Parent homePageScene = FXMLLoader.load(getClass().getResource("/meinRecipe_View/HomePage.fxml"));
			Scene homePage = new Scene(homePageScene);
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
			mainWindow.setScene(homePage);
		}
	}

	@FXML
	/**
	 * Change scene to display page or home page according to recipeId, is called
	 * when corresponding button is clicked
	 * 
	 * @param event javaFX event
	 * @throws IOException java IOException
	 */
	void back(ActionEvent event) throws IOException {
		if (showAlert(Alert.AlertType.CONFIRMATION, "Warning", "All change will be lost",
				"Are you sure to quit without saving?")) {
			if (editingRecipe.getRecipeId() != null) {
				DisplayPageController.setViewingRecipe(editingRecipe);

				Parent DisplayPageScene = FXMLLoader.load(getClass().getResource("/meinRecipe_View/DisplayPage.fxml"));
				Scene mainPage = new Scene(DisplayPageScene);
				Stage mainWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

				mainWindow.setOnCloseRequest(e -> {
					if (showAlert(Alert.AlertType.CONFIRMATION, "Warning", "",
							"Are you sure to quit?")) {
						mainWindow.close();
					} else {
						e.consume();
					}
				});
				mainWindow.setTitle("MeinRecipe - Display page");
				mainWindow.setScene(mainPage);
			} else {
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
		}
	}

	@FXML
	/**
	 * JavaFX scene initialize method, is called when setting scene
	 */
	public void initialize() {
		ObservableList<String> choiceList = FXCollections.observableArrayList("EU", "SEA", "SA", "LA", "A", "Others");
		this.regionChoices.setItems(choiceList);
		this.fillinRecipeView(editingRecipe);
		this.setEditView();
	}

	/**
	 * Set object of this.editingRecipe
	 * 
	 * @param er editingRecipe object
	 */
	public static void setEditingRecipe(Recipe er) {
		editingRecipe = new Recipe(er);
	}

	/**
	 * A customized method, fill in this scene with given recipe object
	 * 
	 * @param r given recipe object
	 */
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
			showAlert(Alert.AlertType.ERROR, "Error", "Failed to load recipe image",
					"Recipe image will be changed to default");
			return;
		}
	}

	/**
	 * A customized method, set all tableViewCell to editable textField
	 */
	public void setEditView() {
		ingredientTable.setEditable(true);
		ingredientTable.getColumns().addListener(new ListChangeListener<Object>() {
			@SuppressWarnings("unchecked")
			@Override
			public void onChanged(Change<?> change) {
				change.next();
				if (change.wasReplaced()) {
					ingredientTable.getColumns().clear();
					ingredientTable.getColumns().addAll(quantityCol, ingDescriptionCol);
				}
			}
		});
		quantityCol.setCellFactory(t -> {
			TextFieldTableCell<Ingredient, Integer> cell = new TextFieldTableCell<Ingredient, Integer>(
					new IntegerStringConverter());
			cell.setOnKeyReleased(e -> {
				if (!e.getText().matches("[0-9]?[\b]?")) {
					showAlert(Alert.AlertType.ERROR, "Error", "Illegal input", "Please input a number!");
					cell.cancelEdit();
				}
			});
			return cell;
		});
		quantityCol.setOnEditCommit(event -> {
			if (event.getNewValue().toString().matches("[0-9]{0,4}")) {
				((Ingredient) event.getTableView().getItems().get(event.getTablePosition().getRow()))
						.setQuantity(event.getNewValue());
			} else {
				showAlert(Alert.AlertType.ERROR, "Error", "Illegal input", "Please input a number [0~9999]!");
				ingredientTable.refresh();
			}
		});
		ingDescriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
		ingDescriptionCol.setOnEditCommit(event -> {
			((Ingredient) event.getTableView().getItems().get(event.getTablePosition().getRow()))
					.setDescription(event.getNewValue());
		});

		instructionTable.setEditable(true);
		instructionTable.getColumns().addListener(new ListChangeListener<Object>() {
			@SuppressWarnings("unchecked")
			@Override
			public void onChanged(Change<?> change) {
				change.next();
				if (change.wasReplaced()) {
					instructionTable.getColumns().clear();
					instructionTable.getColumns().addAll(stepCol, insDescriptionCol);
				}
			}
		});
		insDescriptionCol.setCellFactory(TextFieldTableCell.forTableColumn());
		insDescriptionCol.setOnEditCommit(event -> {
			((Instruction) event.getTableView().getItems().get(event.getTablePosition().getRow()))
					.setDescription(event.getNewValue());
		});
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
