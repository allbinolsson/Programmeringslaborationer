package lab3;

import java.awt.Button;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

import textproc.GeneralWordCounter;

public class BookReaderController extends Application {
	
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		
		Scene scene = new Scene(root, 500, 500);
		primaryStage.setTitle("BookReader");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		// Skapar knappar och lägger till längst ner i fönstret
		HBox hbox = new HBox();
		Button alphabetButton = new Button("Alphabetical");
		Button frequencyButton = new Button("Frequency");
		
		
		
		
		
		// Läser in och skriver ut orden i fönstret
		Set<String> exceptions = new HashSet<String>();
		Scanner eScan = new Scanner(new File("undantagsord.txt"));
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");
		
		while (eScan.hasNext()) {
			exceptions.add(eScan.next());
		}
		
		GeneralWordCounter c = new GeneralWordCounter(exceptions);
		
		while (s.hasNext()) {
			c.process(s.next());
		}
		
		Set<Map.Entry<String, Integer>> counterWords = c.getWords();
		
		ObservableList<Map.Entry<String, Integer>> words 
			= FXCollections.observableArrayList(counterWords);
		
		ListView<Map.Entry<String, Integer>> listView = new ListView<>(words);
		
		root.setCenter(listView);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
