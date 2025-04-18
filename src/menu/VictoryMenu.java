package menu;

import java.awt.Point;
import menu.*;
import Interface.*;
import fileWork.*;
import boxPushing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PipedOutputStream;

import Interface.PlayInterface;
import fileWork.Write;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class VictoryMenu extends Application{
	//得到当前胜利的是第几关变量
	private String victoryWhichLevel;
	//得到上一舞台的实例
	private Stage getStage;
	//得到玩家得分的变量
	private int score;
	
	//得到第几关变量方法
	public void setVictoryWhichLevel (String input) {
		victoryWhichLevel = input;
	}
	
	//得到上一舞台方法
	public void setLostStage(Stage input) {
		getStage = input;
	}
	
	//得到玩家得分方法
	public void setScore(int input) {
		score = input;
	}
	
	@Override
	public void start(Stage victoryStage){
		//新舞台标题名定义
		victoryStage.setTitle("成功过关");
		
		//设置窗体图标
		victoryStage.getIcons().add(new Image("C:\\Users\\Fake\\Desktop\\imgs\\SmallPicture\\1.png"));
		
		//导入要使用的字体
		Font fontTopWords = null;
		try(FileInputStream in = new FileInputStream(new File("D:\\Java\\BoxPushing\\Font\\HanyiSentyScholar.ttf"))){
			fontTopWords = Font.loadFont(in, 40);
		} catch (IOException e2) {
			e2.printStackTrace();
		} 
		Font scoreWords1 = null;
		try(FileInputStream in = new FileInputStream(new File("D:\\Java\\BoxPushing\\Font\\HanyiSentyScholar.ttf"))){
			scoreWords1 = Font.loadFont(in, 30);
		} catch (IOException e2) {
			e2.printStackTrace();
		} 
		Font scoreWords2 = null;
		try(FileInputStream in = new FileInputStream(new File("D:\\Java\\BoxPushing\\Font\\HYFeiYunTiW.ttf"))){
			scoreWords2 = Font.loadFont(in, 36);
		} catch (IOException e2) {
			e2.printStackTrace();
		} 
		Font fontButton = null;
		try(FileInputStream in = new FileInputStream(new File("D:\\Java\\BoxPushing\\Font\\HYKongShanKaiW.ttf"))){
			fontButton = Font.loadFont(in, 24);
		} catch (IOException e2) {
			e2.printStackTrace();
		} 
		
		//顶部文字定义
		Text topWords = new Text("恭喜您成功过关!");
		topWords.setFont(fontTopWords);
		topWords.setFill(Color.WHITE);
		
		//分数展示文字定义
		Text showPoint = new Text("您得到的分数是：");
		showPoint.setFont(scoreWords1);
		showPoint.setFill(Paint.valueOf("#00BFFF"));
		
		Text point = new Text(String.valueOf(score));
		point.setFont(scoreWords2);
		point.setFill(Color.WHITE);
		
		//按钮定义
		//返回选择关卡菜单按钮
		Button backLM = new Button("关卡菜单");
		backLM.setFont(fontButton);
		backLM.setBackground(null);
		backLM.setBorder(null);
		backLM.setTextFill(Paint.valueOf("#00FFFF"));
		
		//设置对鼠标进入推出的反应
		backLM.setOnMouseEntered(e -> {
			backLM.setTextFill(Paint.valueOf("#FFD700"));
		});
		
		backLM.setOnMouseExited(e -> {
			backLM.setTextFill(Paint.valueOf("#00FFFF"));
		});
		
		backLM.setOnMouseClicked(e -> {
			LevelMenu LM = new LevelMenu();
			LM.start(new Stage());
			
			victoryStage.close();
		});
		
		//下一关按钮
		Button nextLevel = new Button("下一关");
		nextLevel.setFont(fontButton);
		nextLevel.setBackground(null);
		nextLevel.setBorder(null);
		nextLevel.setTextFill(Paint.valueOf("#00FFFF"));
		
		//设置对鼠标进入推出的反应
		nextLevel.setOnMouseEntered(e -> {
			nextLevel.setTextFill(Paint.valueOf("#FFD700"));
		});
		
		nextLevel.setOnMouseExited(e -> {
			nextLevel.setTextFill(Paint.valueOf("#00FFFF"));
		});
		
		nextLevel.setOnMouseClicked(e -> {
			//新建下一关游玩实例
			PlayInterface PIF = new PlayInterface();
			
			Write w = new Write();
			try {
				if(Integer.parseInt(victoryWhichLevel) != 10)
					w.setPlayerChooseWhichLevel(Integer.parseInt(victoryWhichLevel) + 1);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			//打开新舞台
			PIF.setReadType("Map");
			PIF.start(new Stage());
			
			//关闭当前窗口与之前窗口
			getStage.close();
			victoryStage.close();
		});
		
		//重玩当前关卡
		Button playAgain = new Button("重新开始本关");
		playAgain.setFont(fontButton);
		playAgain.setBackground(null);
		playAgain.setBorder(null);
		playAgain.setTextFill(Paint.valueOf("#00FFFF"));
		
		//设置对鼠标进入推出的反应
		playAgain.setOnMouseEntered(e -> {
			playAgain.setTextFill(Paint.valueOf("#FFD700"));
		});
		
		playAgain.setOnMouseExited(e -> {
			playAgain.setTextFill(Paint.valueOf("#00FFFF"));
		});
		
		playAgain.setOnMouseClicked(e -> {
			//新建当前关游玩实例
			PlayInterface PIF = new PlayInterface();
			
			Write w = new Write();
			try {
				w.setPlayerChooseWhichLevel(Integer.parseInt(victoryWhichLevel));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			//打开新舞台
			PIF.setReadType("Map");
			PIF.start(new Stage());
			
			//关闭当前窗口与之前窗口
			getStage.close();
			victoryStage.close();
		});
		
		//新建布局收纳控件
		VBox putWords = new VBox(20);
		putWords.getChildren().addAll(topWords, showPoint, point);
		putWords.setLayoutX(55);
		putWords.setLayoutY(40);
		putWords.setAlignment(Pos.CENTER);
		
		HBox putButtons = new HBox(-10);
		putButtons.getChildren().addAll(backLM, playAgain, nextLevel);
		putButtons.setLayoutX(17);
		putButtons.setLayoutY(240);
		
		//总布局 
		AnchorPane total = new AnchorPane();
		
		//美化
		//背景图片
		Image backgroundPicture = new Image("D:\\Java\\BoxPushing\\Picture\\BackGround\\10.jpg");
		ImageView showBGP = new ImageView(backgroundPicture);
		showBGP.setFitWidth(420);
		showBGP.setFitHeight(360);

		total.getChildren().add(showBGP);
		
		total.getChildren().addAll(putWords, putButtons);
		
		//新建场景舞台
		Scene scene = new Scene(total, 420, 360);
		victoryStage.setScene(scene);
		
		victoryStage.show();
	}
}
