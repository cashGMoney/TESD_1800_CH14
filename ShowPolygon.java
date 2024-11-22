import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Polygon;

// Added imports for Label
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.FontPosture;

public class ShowPolygon extends Application {
	@Override
	public void start(Stage primaryStage) {   

		// Create a scene and place it in the stage
		Scene scene = new Scene(new MyPolygon(), 400, 400);
		primaryStage.setTitle("ShowPolygon");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/**
	 * The main method is only needed for the IDE with limited
	 * JavaFX support. Not needed for running from the command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}

class MyPolygon extends Pane {
	private void paint() {
		// Create a polygon and place polygon to pane
		Polygon polygon = new Polygon();

		// Changed setFILL to RED
		polygon.setFill(Color.RED);
		polygon.setStroke(Color.BLACK);
		ObservableList<Double> list = polygon.getPoints();
		
		double centerX = getWidth() / 2, centerY = getHeight() / 2;
		double radius = Math.min(getWidth(), getHeight()) * 0.4;

		// s represents the number of sides of the shape
		// Make sure to update this number when necessary
		
		// Changed from hexagon to octogon with 8 sides
		int s = 8;
		// Add points to the polygon list
		for (int i = 0; i < s; i++) {
			list.add(centerX + radius * Math.cos(2 * i * Math.PI / s)); 
			list.add(centerY - radius * Math.sin(2 * i * Math.PI / s));
		}     

		// Rotate the polygon 22.5 degrees
		polygon.setRotate(22.5);

		// Create and style the STOP label
        Label label = new Label("STOP");
        label.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.REGULAR, 80));
        label.setTextFill(Color.WHITE);

		// Place label within octagon
        label.setLayoutX(centerX - 105);
        label.setLayoutY(centerY - 55);
		
		// Clear any existing children to avoid multiple "STOP" labels
        getChildren().clear();

		// Add the polygon and label to the scene
		getChildren().addAll(polygon, label);
	}
	
	@Override
	public void setWidth(double width) {
		super.setWidth(width);
		paint();
	}
	
	@Override
	public void setHeight(double height) {
		super.setHeight(height);
		paint();
	}
}