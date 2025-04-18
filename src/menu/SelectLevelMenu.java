package menu;

import java.io.File;
import menu.*;
import Interface.*;
import fileWork.*;
import boxPushing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

import fileWork.Write;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class SelectLevelMenu extends Application {
	//设置为地图绘制程序找到难度的变量
	private String level;
	
	public void start(Stage primaryStage) {
		//窗口文字打印
		primaryStage.setTitle("推箱子");
		
		//设置窗体图标
		primaryStage.getIcons().add(new Image("C:\\Users\\Fake\\Desktop\\imgs\\SmallPicture\\1.png"));
		
		//导入所需字体
		Font fontTop =null;
		try(FileInputStream in = new FileInputStream(new File("D:\\Java\\BoxPushing\\Font\\瑞美加张清平硬笔行书.ttf"))){
			fontTop = Font.loadFont(in, 100);
		} catch (IOException e2) {
			e2.printStackTrace();
		} 
		Font fontButton = null;
		try(FileInputStream in = new FileInputStream(new File("D:\\Java\\BoxPushing\\Font\\Mengshen-Handwritten.ttf"))){
			fontButton = Font.loadFont(in, 46);
		} catch (IOException e2) {
			e2.printStackTrace();
		} 
		
		//定义顶部文字
		Text topWords = new Text("请 选 择 难 度");
		topWords.setFont(fontTop);
		topWords.setFill(Color.WHITE);
		topWords.setLayoutX(240);
		topWords.setLayoutY(180);
		
		//定义按钮,背景全部透明
		Button easy = new Button("简单");
		easy.setFont(fontButton);
		easy.setBorder(null);
		easy.setBackground(null);
		easy.setTextFill(Paint.valueOf("#00FF7F"));
		
		//设置对鼠标进入推出的反应
		easy.setOnMouseEntered(e -> {
			easy.setTextFill(Paint.valueOf("#87CEEB"));
		});
		
		easy.setOnMouseExited(e -> {
			easy.setTextFill(Paint.valueOf("#00FF7F"));
		});
		
		easy.setOnMouseClicked(e -> {
			//实例化选择关卡菜单与写入方法
			LevelMenu LM = new LevelMenu();
			Write w = new Write();
			try {
				w.setPlayerChooseLevel(1);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			//打开另一个窗口
			LM.start(new Stage());
			
			//关闭当前窗口
			primaryStage.close();
		});
		
		Button medium = new Button("中等");
		medium.setFont(fontButton);
		medium.setBorder(null);
		medium.setBackground(null);
		medium.setTextFill(Paint.valueOf("#FFA500"));
		
		//设置对鼠标进入推出的反应
		medium.setOnMouseEntered(e -> {
			medium.setTextFill(Paint.valueOf("#87CEEB"));
		});
		
		medium.setOnMouseExited(e -> {
			medium.setTextFill(Paint.valueOf("#FFA500"));
		});
		
		medium.setOnMouseClicked(e -> {
			//实例化选择关卡菜单与写入方法
			LevelMenu LM = new LevelMenu();
			Write w = new Write();
			try {
				w.setPlayerChooseLevel(2);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			//打开另一个窗口
			LM.start(new Stage());
			
			//关闭当前窗口
			primaryStage.close();
		});
		
		Button hard = new Button("困难");
		hard.setFont(fontButton);
		hard.setBorder(null);
		hard.setBackground(null);
		hard.setTextFill(Paint.valueOf("#FF6347"));
		
		//设置对鼠标进入推出的反应
		hard.setOnMouseEntered(e -> {
			hard.setTextFill(Paint.valueOf("#87CEEB"));
		});
		
		hard.setOnMouseExited(e -> {
			hard.setTextFill(Paint.valueOf("#FF6347"));
		});
		
		hard.setOnMouseClicked(e -> {
			//实例化选择关卡菜单与写入方法
			LevelMenu LM = new LevelMenu();
			Write w = new Write();
			try {
				w.setPlayerChooseLevel(3);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			//打开另一个窗口
			LM.start(new Stage());
			
			//关闭当前窗口
			primaryStage.close();
		});
		
		Button backMM = new Button("主菜单");
		backMM.setBackground(null);
		backMM.setBorder(null);
		backMM.setFont(fontButton);
		backMM.setLayoutX(880);
		backMM.setLayoutY(20);
		backMM.setTextFill(Color.WHITE);
		
		//设置对鼠标进入推出的反应
		backMM.setOnMouseEntered(e -> {
			backMM.setTextFill(Paint.valueOf("#87CEEB"));
		});
		
		backMM.setOnMouseExited(e -> {
			backMM.setTextFill(Color.WHITE);
		});
		
		backMM.setOnMouseClicked(e -> {
			MainMenu MM = new MainMenu();
			MM.setIsPlay(false);
			MM.start(new Stage());
			
			primaryStage.close();
		});
		
		//退出询问是否关闭
		Platform.setImplicitExit(false);
		primaryStage.setOnCloseRequest(event -> {
			event.consume();
			Alert outMenu = new Alert(Alert.AlertType.CONFIRMATION);
			outMenu.setTitle("退出程序");
			outMenu.setHeaderText(null);
			outMenu.setContentText("您是否要退出游戏？");
			
			Optional<ButtonType>result = outMenu.showAndWait();
			if(result.get() == ButtonType.OK)
				Platform.exit();
		});
		
		//新建存放按钮布局
		VBox putButtons = new VBox(-20);
		putButtons.getChildren().addAll(easy, medium, hard);
		putButtons.setLayoutX(440);
		putButtons.setLayoutY(226);
		
		//新建布局
		AnchorPane SelectLevelpane = new AnchorPane();
		
		//美化
		//导入背景图片
		Image backgroundPicture = new Image("D:\\Java\\BoxPushing\\Picture\\BackGround\\5.png");
		ImageView showBGP = new ImageView(backgroundPicture);
		showBGP.setFitWidth(1080);
		showBGP.setFitHeight(720);

		SelectLevelpane.getChildren().add(showBGP);
		
		SelectLevelpane.getChildren().addAll(topWords, putButtons, backMM);
		
		Scene SLMScene = new Scene(SelectLevelpane, 1080, 720);
		primaryStage.setScene(SLMScene);
		
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(e -> {
			System.exit(0);
		});

	}
}
