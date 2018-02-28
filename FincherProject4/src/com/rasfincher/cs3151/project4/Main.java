package com.rasfincher.cs3151.project4;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	public static final String MAIN_WINDOW_TITLE = "Ras Fincher - Project 4";
	public static final String MAIN_WINDOW = "view/MainView.fxml";
	
	@Override
	public void start(Stage primaryStage) {
		try {
			final FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource(MAIN_WINDOW));
			loader.load();
			Parent parent = loader.getRoot();
			Scene scene = new Scene(parent);
			primaryStage.setTitle(MAIN_WINDOW_TITLE);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			/*
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			*/
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
