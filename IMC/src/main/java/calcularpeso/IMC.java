package calcularpeso;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class IMC extends Application {

	private TextField texto1 = new TextField();
	private TextField texto2 = new TextField();;
	private Label label1 = new Label("IMC: ");
	private Label label2 = new Label("Bajo peso | Normal | Sobrepeso | Obeso");;
	
	private DoubleProperty imc = new SimpleDoubleProperty(0);
	private DoubleProperty peso = new SimpleDoubleProperty(0);
	private DoubleProperty altura = new SimpleDoubleProperty(0);
	private StringProperty result = new SimpleStringProperty();

	@Override
	public void start(Stage primaryStage) throws Exception {	
		
		VBox root =	 new VBox(5, texto1, texto2, label1, label2);
		root.setAlignment(Pos.CENTER);
		root.setFillWidth(false);
		
		primaryStage.setTitle("IMC");
		primaryStage.setScene(new Scene(root, 320, 200));
		primaryStage.show();
		
		texto1.textProperty().bindBidirectional(peso, new NumberStringConverter());
		texto2.textProperty().bindBidirectional(altura, new NumberStringConverter());
		label1.textProperty().bindBidirectional(imc, new NumberStringConverter());
		label2.textProperty().bindBidirectional(result);

		
		//altura=altura.divide(100);
		imc.bind(peso.divide(altura.multiply(altura)));
		
		imc.addListener((o, ov, nv) -> {
			double i = nv.doubleValue();
			if (i < 18.5) {	
				result.set("Bajo peso");
			} else if (i < 25) {
				result.set("Normal");
			} else if (i < 30) {
				result.set("Sobrepeso");
			} else {
				result.set("Obeso");
			}
		});
				
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
