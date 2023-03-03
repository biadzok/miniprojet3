package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;

public class FirstController {
	
	// liste des boutons correspondant aux cartes affichées
	private ArrayList<Button> cardList = new ArrayList<Button>();
	
	// liste des boutons dont le texte est retourné
	private ArrayList<String> flippedCardList = new ArrayList<String>();
		
	@FXML
	private Button Button00;
	
	@FXML
	private Button Button01;
	
	@FXML
	private Button Button10;
	
	@FXML
	private Button Button11;
	
	
	
	private void verifyCardFlipped(String lastText) {
		if (flippedCardList.contains(lastText)) {
			for (Button b : cardList) {
				if (b.getText().equals(lastText)) {
					b.disableProperty().set(true);
				}
			}
		}
		else {
			flippedCardList.add(lastText);
		}
	}
	
	
	/*
	 * 
	 */
	@FXML
	public void evntButtonClick(ActionEvent arg0) {
		if (arg0.getSource() instanceof Button) {
			Button b = (Button) arg0.getSource();
			if (!b.isDisable()) {
				if (b.getContentDisplay().equals(ContentDisplay.GRAPHIC_ONLY)) {
					b.contentDisplayProperty().set(ContentDisplay.CENTER);
					verifyCardFlipped(b.getText());
				}
				else {
					b.contentDisplayProperty().set(ContentDisplay.GRAPHIC_ONLY);
					flippedCardList.removeIf(s -> s.equals(b.getText()));
				}
			}
		}
	}
	
	
	/*
	 * initialise la vue. Ajoute le texte des boutons dans une liste définie 
	 * et les répartit aléatoirement entre les boutons
	 * 
	 */
	@FXML
	public void initialize() {
		
		cardList.add(Button00);
		cardList.add(Button01);
		cardList.add(Button10);
		cardList.add(Button11);
		
//		Button00.setOnAction(arg0 -> this.evntButtonClick(arg0));
		
		
		Random randomcardInit = new Random();
		ArrayList<String> initList = new ArrayList<String>();
		initList.add("chat");
		initList.add("chat");
		initList.add("chien");
		initList.add("chien");
		int nb;
		
		for (int i = 4; i > 0; i--) {
			nb = randomcardInit.nextInt(i);
			String text = initList.get(nb);
			Button b = cardList.get(i - 1);
			b.textProperty().set(text);
			b.contentDisplayProperty().set(ContentDisplay.GRAPHIC_ONLY);
			initList.remove(nb);
		}
		
	}
}