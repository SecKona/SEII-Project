package meinRecipe_Main;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;

/**
 * Main class of cookBook, launch the application
 * 
 * @author SecKona
 *
 */
public class Main extends Application {

	@Override
	public void start(Stage mainWindow) throws IOException {

		Parent homePageScene = FXMLLoader.load(getClass().getResource("/meinRecipe_View/HomePage.fxml"));
		Scene mainPage = new Scene(homePageScene);

		mainWindow.getIcons().add(new Image("/icon/chef5.jpg"));
		mainWindow.setResizable(false);
		mainWindow.setOnCloseRequest(event -> {
			if (showAlert(Alert.AlertType.CONFIRMATION, "Warning", "",
					"Are you sure to quit?")) {
				mainWindow.close();
			} else {
				event.consume();
			}
		});
		mainWindow.setTitle("MeinRecipe - Home page");
		mainWindow.setScene(mainPage);
		mainWindow.show();
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

	public static void main(String[] args) {
		launch(args);
	}
}
