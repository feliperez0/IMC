package dad.calculadora;

import javafx.application.Application;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class CalculadoraIMC extends Application {
	
	private Label imcLabel = new Label();
	private Label resultLabel = new Label();
	
	private Label pesoLabel = new Label("Peso: ");
	private TextField textPeso = new TextField();
	private Label kgLabel = new Label("kg");
	
	private Label alturaLabel = new Label("Altura: ");
	private TextField textAltura = new TextField();;
	private Label cmLabel = new Label("cm");

	private DoubleProperty imc = new SimpleDoubleProperty(0);
	private DoubleProperty peso = new SimpleDoubleProperty(0);
	private DoubleProperty altura = new SimpleDoubleProperty(0);
	private StringProperty result = new SimpleStringProperty();
	private DoubleExpression alturaM;
	
	@Override
	public void start(Stage primaryStage) throws Exception {	
		
		HBox pesoPane = new HBox(5);
		pesoPane.setAlignment(Pos.CENTER);
		pesoPane.getChildren().addAll(pesoLabel, textPeso, kgLabel);
		
		HBox alturaPane = new HBox(5);
		alturaPane.setAlignment(Pos.CENTER);
		alturaPane.getChildren().addAll(alturaLabel, textAltura, cmLabel);
		
		VBox root =	 new VBox(5);
		root.setAlignment(Pos.CENTER);
		root.setFillWidth(false);
		root.getChildren().addAll(pesoPane, alturaPane, imcLabel, resultLabel);
		
		primaryStage.setTitle("IMC");
		primaryStage.setScene(new Scene(root, 320, 200));
		primaryStage.show();
		
		textPeso.textProperty().bindBidirectional(peso, new NumberStringConverter());
		textAltura.textProperty().bindBidirectional(altura, new NumberStringConverter());
		imcLabel.textProperty().bind(imc.asString("IMC: %.2f"));
		resultLabel.textProperty().bindBidirectional(result);
		
		alturaM=altura.divide(100);
		imc.bind(peso.divide(alturaM.multiply(alturaM)));
		
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
	
}
