package lab3;

import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
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

		// Skapar knappar och l�gger till l�ngst ner i f�nstret
		HBox hbox = new HBox();
		Button alphaButton = new Button("Alphabetical");
		Button frequencyButton = new Button("Frequency");
		hbox.getChildren().addAll(alphaButton, frequencyButton);
		root.setBottom(hbox);

		// Skapar och l�gger till ett textf�lt med s�kknapp
		Button searchButton = new Button("Search");
		TextField text = new TextField();
		hbox.getChildren().addAll(text, searchButton);

		// L�ser in och skriver ut orden i fönstret
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

		ObservableList<Map.Entry<String, Integer>> words = FXCollections.observableArrayList(counterWords);

		ListView<Map.Entry<String, Integer>> listView = new ListView<>(words);

		root.setCenter(listView);

		// S�ger vad som ska h�nda vid knapptryck
		alphaButton.setOnAction(event -> {
			words.sort((e1, e2) -> e1.getKey().compareTo(e2.getKey()));
			System.out.println("Alphabetical");
		});

		frequencyButton.setOnAction(event -> {
			words.sort((e1, e2) -> e1.getValue().compareTo(e2.getValue()));
			System.out.println("Frequency");
		});
		
		// �ndrar s�kf�ltets storlek
		hbox.setHgrow(text, Priority.ALWAYS);

		// S�kfunktion
		searchButton.setDefaultButton(true);	// Till�ter mig att s�ka med enter
		searchButton.setOnAction(event -> {
			SelectionModel sModel = listView.getSelectionModel();
			for (int i = 0; i < words.size(); i++) {
				if (text.getText().equalsIgnoreCase(words.get(i).getKey())) {
					listView.scrollTo(i);
					sModel.select(i);	// Markerar det s�kta ordet
				}
			}
			
			System.out.println("Searching for: " + text.getText());
			text.clear(); // Removes the text to allow a new search
		});
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
