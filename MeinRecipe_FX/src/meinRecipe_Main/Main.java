package meinRecipe_Main;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage mainWindow) throws IOException {
		
		Parent homePageScene = FXMLLoader.load(getClass().getResource("/meinRecipe_View/HomePage.fxml"));
		Scene mainPage = new Scene(homePageScene);
		
		mainWindow.setResizable(false);
		mainWindow.setTitle("MeinRecipe - Home page");
		mainWindow.setScene(mainPage);
		mainWindow.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
