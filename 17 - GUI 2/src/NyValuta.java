import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NyValuta {
	Stage window = new Stage();
	Valuta nyValuta;
	TextField input_valuta = new TextField("ny valuta");
	TextField input_kurs = new TextField();
	TextField input_enhet = new TextField();
	Label feilmelding;
	
	public void Display() {
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("Lag en ny Valuta");
		window.setMinHeight(230);
		window.setMinWidth(360);
		
		input_valuta.setPromptText("valuta");
		input_kurs.setPromptText("kurs");
		input_enhet.setPromptText("enhet");
		
		//Knapp for å sende in en ny valuta og legge den til i listen
		Button submit = new Button("Legg til valuta");
		submit.setOnAction(e -> submitClicked());
		
		feilmelding = new Label("");
		
		VBox layout = new VBox(10);
		layout.setPadding(new Insets(20,30,20,30));
		layout.getChildren().addAll(input_valuta, input_kurs, input_enhet, submit, feilmelding);
		
		Scene nyValuta = new Scene(layout);
		window.setScene(nyValuta);
		window.showAndWait();
	}
	
	private void submitClicked() {
		try{
			nyValuta = new Valuta(input_valuta.getText(), Double.parseDouble(input_kurs.getText()), Integer.parseInt(input_enhet.getText()));
			window.close();
		}catch(NumberFormatException e) {
			feilmelding.setText("En av de innskrevne verdiene inneholder en feil!");
			feilmelding.setTextFill(Color.RED);
		}
		
	}
	public Valuta getNyValuta() {
		return nyValuta;
	}
}
