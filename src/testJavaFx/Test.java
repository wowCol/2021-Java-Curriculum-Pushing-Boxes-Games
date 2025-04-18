package testJavaFx;

import java.io.IOException;

import fileWork.Read;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Test extends Application{

	@Override
	public void start(Stage arg0) throws Exception {
		Button btn1 = new Button("1");
		btn1.setOnMouseClicked(e -> {
			TranslateTransition toTest = new TranslateTransition();
			toTest.setByX(40);
			toTest.play();
		});
		
		
		
		VBox vbox =new VBox();
		vbox.getChildren().add(btn1);
		
		Scene scene = new Scene(vbox, 800, 400);
		
		arg0.setScene(scene);
		
		arg0.show();
	}
	
	public static void main(String [] args) {
		launch(args);
	}

	
}
