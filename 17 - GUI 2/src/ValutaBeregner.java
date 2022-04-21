import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ValutaBeregner extends Application{
	private Stage window;
	private Scene valg;
	private VBox layout_valg = new VBox(10);
	private ListView<String> konverterFra , konverterTil;
	private Button konverter;
	private TextField mengdeInput = new TextField();
	private Label output = new Label("");
	private ValutaTabell valutaliste = new ValutaTabell();
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		window.setMinHeight(600);
		window.setMinWidth(400);
		window.setTitle("ValutaBeregner");
			
		//Knapp for å konvertere etter valg valutaer
		konverter = new Button("Konverter");
		konverter.setOnAction(e -> konverterClicked());
		
		//Konvert Fra valuta liste
		konverterFra = new ListView<String>();
		for(Valuta valuta: valutaliste.getAllValuta()) {
			konverterFra.getItems().add(valuta.getValuta());
		}
		konverterFra.getItems().add("Ny valuta");
		konverterFra.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		//Konvert Til valuta liste
		konverterTil = new ListView<String>();
		for(Valuta valuta: valutaliste.getAllValuta()) {
			konverterTil.getItems().add(valuta.getValuta());
		}
		konverterTil.getItems().add("Ny valuta");
		konverterTil.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		Label fra_text = new Label("Fra");
		Label til_text = new Label("Til");
		mengdeInput.setPromptText("Mengde");
		
		layout_valg.setPadding(new Insets(20,30,20,30));
		layout_valg.getChildren().addAll(fra_text, konverterFra, til_text, konverterTil, mengdeInput, konverter, output);
		
		valg = new Scene(layout_valg,0,0);
		window.setScene(valg);
		window.show();
	}
	
	private boolean isDouble(String input_string) {
		try {
			Double.parseDouble(input_string);
			return true;
		}catch(NumberFormatException e) {
			output.setText("Feil: \""+ input_string + "\" er ikke en gyldig mengde");
			output.setTextFill(Color.RED);
			return false;
		}
	}
	
	private void konverterClicked(){
		double mengde = 0;
		if(isDouble(mengdeInput.getText())) {
			mengde = Double.parseDouble(mengdeInput.getText());
			try {
				String fra = konverterFra.getSelectionModel().getSelectedItem();
				String til = konverterTil.getSelectionModel().getSelectedItem();
				
				NyValuta nValuta = new NyValuta();
				if(fra.contentEquals("Ny valuta")) {
					nValuta.Display();
					fra = nValuta.getNyValuta().getValuta();
					valutaliste.add(nValuta.getNyValuta());
					konverterFra.getItems().add(nValuta.getNyValuta().getValuta());
					konverterTil.getItems().add(nValuta.getNyValuta().getValuta());
				}
				if(til.contentEquals("Ny valuta")) {
					nValuta.Display();
					til = nValuta.getNyValuta().getValuta();
					valutaliste.add(nValuta.getNyValuta());
					konverterFra.getItems().add(nValuta.getNyValuta().getValuta());
					konverterTil.getItems().add(nValuta.getNyValuta().getValuta());
				}
				
				String resultat_string = String.format("du vil få %.2f " + til + " for " + mengde + " " + fra + ".", valutaliste.beregn(fra,  til, mengde));
				output.setText(resultat_string);
				output.setTextFill(Color.BLACK);
			}catch(NullPointerException e) {
				output.setText("Du har ikke valgt valuta!");
				output.setTextFill(Color.RED);
			}
		}
		window.setScene(valg);
		window.show();
	}
	
	
	public static void main(String[] args) throws Exception {
		launch(args);
	}
}
