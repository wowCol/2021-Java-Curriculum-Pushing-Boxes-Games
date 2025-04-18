package testJavaFx;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TextJavaFX extends Application{
	int [] te = new int [] {1, 2}; 
	@Override
	public void start(Stage stage) {
		Button btn = new Button("使用键盘");
		btn.setOnKeyPressed(new EventHandler<Event>() {
			@Override
			public void handle(Event e) {
				//获取输入的字符
				KeyEvent ke = (KeyEvent) e;
				//强转
				KeyCode code = ke.getCode();
				
				if(code == KeyCode.G) {
					System.out.println("被调用了一次");
					Test t = new Test();
//					System.out.println(t.test('g' , te));
				}
			}
		});
		
		HBox bp = new HBox();
		bp.getChildren().add(btn);
		
		Scene sc = new Scene(bp , 1080, 720);
		stage.setScene(sc);
		
		stage.show();
	}
	
	public static void main(String [] args) {
		launch(args);
	}

}
