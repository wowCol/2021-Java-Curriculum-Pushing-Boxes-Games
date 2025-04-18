package menu;

import java.io.File;
import menu.*;
import Interface.*;
import fileWork.*;
import boxPushing.*;
import java.io.FileInputStream;
import java.io.IOException;

import Interface.PlayInterface;
import fileWork.ControlText;
import javafx.application.Application;
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

public class LoadMenu extends Application{
	//新建储存玩家选择的存档名的变量
	private String chooseSave;
	//新建储存玩家选择的存档种类的变量
	private String chooseType;
	//新建载入新游戏的提示窗口关闭变量
	private Stage getStage;
	//选择了哪一个按钮控件
	private int chooseWhich;
	
	//得到上一舞台方法
	public void setLostStage(Stage input) {
		getStage = input;
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		//设置窗口名字
		stage.setTitle("载入游戏");
		
		//设置窗体图标
		stage.getIcons().add(new Image("C:\\Users\\Fake\\Desktop\\imgs\\SmallPicture\\1.png"));
		
		//导入需要的字体
		Font fontTopWords = null;
		try(FileInputStream in = new FileInputStream(new File("D:\\Java\\BoxPushing\\Font\\HYTianMaXingKaiW.ttf"))){
			fontTopWords = Font.loadFont(in, 40);
		} catch (IOException e2) {
			e2.printStackTrace();
		} 
		Font fontSaveWords = null;
		try(FileInputStream in = new FileInputStream(new File("D:\\Java\\BoxPushing\\Font\\HYZiKuTangSongKeBenLiKaiW.ttf"))){
			fontSaveWords = Font.loadFont(in, 24);
		} catch (IOException e2) {
			e2.printStackTrace();
		} 
		Font fontButton = null;
		try(FileInputStream in = new FileInputStream(new File("D:\\Java\\BoxPushing\\Font\\HYZiKuTangYinJiaKaiShuW.ttf"))){
			fontButton = Font.loadFont(in, 32);
		} catch (IOException e2) {
			e2.printStackTrace();
		} 
		
		//设置顶部文字
		Text topWords1 = new Text("载入自动游戏存档");
		topWords1.setFont(fontTopWords);
		topWords1.setFill(Color.WHITE);
		topWords1.setLayoutX(240);
		topWords1.setLayoutY(60);
		
		Text topWords2 = new Text("载入手动游戏存档");
		topWords2.setFont(fontTopWords);
		topWords2.setFill(Color.WHITE);
		topWords2.setLayoutX(240);
		topWords2.setLayoutY(185);
		
		//读入存档文件并显示
		File getPlayerFile = new File("D:\\Java\\BoxPushing\\Save\\PlayerSave");//读取玩家存档的内容
		File getAutoFile = new File("D:\\Java\\BoxPushing\\Save\\AutoSave\\AutoSave.txt");//读取自动存档内容
		
		//将玩家存档与自动存档分开两部分保存
		//自动存档只有一个，新建一个Text储存
		Button autoSave = new Button("AutoSave.txt");
		autoSave.setFont(fontSaveWords);
		autoSave.setLayoutX(100);
		autoSave.setLayoutY(80);
		
		autoSave.setTextFill(Paint.valueOf("#87CEFA"));
		
		autoSave.setBackground(null);
		autoSave.setBorder(null);
		
		//设置边框
		autoSave.setStyle("-fx-border-style: solid;"+ "-fx-border-color: #F5FFFA;" + " -fx-border-width: 1;" + " -fx-border-radius: 10;");
		
		//设置宽高
		autoSave.setPrefHeight(30);
		autoSave.setPrefWidth(600);
		
		autoSave.setOnMouseClicked(e -> {
			autoSave.requestFocus();
			chooseType = "AutoSave";
			chooseSave = "AutoSave.txt";
		});
		
		//新建Text数组储存手动存档
		String [] getName = getPlayerFile.list();
		Button [] showSave = new Button [6];//存档最多为六个
		for(int i = 0; i < getName.length; i++) {
			//将得到的文件名放入并显示
			showSave [i] = new Button(getName[i]);
			showSave[i].setFont(fontSaveWords);
			
			showSave[i].setTextFill(Paint.valueOf("#87CEFA"));
			
			showSave[i].setBackground(null);
			showSave[i].setBorder(null);
			
			//设置边框
			showSave[i].setStyle("-fx-border-style: solid;"+ "-fx-border-color: #F5FFFA;" + " -fx-border-width: 1;" + " -fx-border-radius: 10;");
			
			//设置宽高
			showSave[i].setPrefHeight(24);
			showSave[i].setPrefWidth(600);
		}
		
		//设置点击反应
		if(showSave[0] != null) {
			showSave[0].setOnMouseClicked(e -> {
				chooseType = "PlayerSave";
				chooseWhich = 0;
				chooseSave = getName[0];
			});
		}
		if(showSave[1] != null) {
			showSave[1].setOnMouseClicked(e -> {
				chooseType = "PlayerSave";
				chooseWhich = 1;
				chooseSave = getName[1];
			});
		}
		if(showSave[2] != null) {
			showSave[2].setOnMouseClicked(e -> {
				chooseType = "PlayerSave";
				chooseWhich = 2;
				chooseSave = getName[2];
			});
		}
		if(showSave[3] != null) {
			showSave[3].setOnMouseClicked(e -> {
				chooseType = "PlayerSave";
				chooseWhich = 3;
				chooseSave = getName[3];
			});
		}
		if(showSave[4] != null) {
			showSave[3].setOnMouseClicked(e -> {
				chooseType = "PlayerSave";
				chooseWhich = 4;
				chooseSave = getName[4];
			});
		}
		if(showSave[5] != null) {
			showSave[3].setOnMouseClicked(e -> {
				chooseType = "PlayerSave";
				chooseWhich = 5;
				chooseSave = getName[5];
			});
		}
		
		//定义按钮
		Button loadGame = new Button("载入存档");
		loadGame.setFont(fontButton);
		loadGame.setFont(fontButton);
		loadGame.setBackground(null);
		loadGame.setBorder(null);
		loadGame.setTextFill(Paint.valueOf("#87CEFA"));
		
		loadGame.setOnMouseEntered(e -> {
			loadGame.setTextFill(Color.WHITE);
		});
		
		loadGame.setOnMouseExited(e -> {
			loadGame.setTextFill(Paint.valueOf("#87CEFA"));
		});
		
		loadGame.setOnMouseClicked(e -> {
			//载入游戏打开游玩界面并关闭当前界面
			PlayInterface PF = new PlayInterface();
			
			//设定存档属性
			PF.setReadType("Save");
			PF.setSave(chooseSave, chooseType);
			
			PF.start(new Stage());
			
			//关闭前一个窗口与当前窗口
			getStage.close();
			stage.close();
		});
		
		Button removeSave = new Button("删除存档");
		removeSave.setFont(fontButton);
		removeSave.setBackground(null);
		removeSave.setBorder(null);
		removeSave.setTextFill(Paint.valueOf("#87CEFA"));
		
		removeSave.setOnMouseEntered(e -> {
			removeSave.setTextFill(Color.WHITE);
		});
		
		removeSave.setOnMouseExited(e -> {
			removeSave.setTextFill(Paint.valueOf("#87CEFA"));
		});
		
		Button cancel = new Button("取消");
		cancel.setFont(fontButton);
		cancel.setFont(fontButton);
		cancel.setBackground(null);
		cancel.setBorder(null);
		cancel.setTextFill(Paint.valueOf("#87CEFA"));
		
		cancel.setOnMouseEntered(e -> {
			cancel.setTextFill(Color.WHITE);
		});
		
		cancel.setOnMouseExited(e -> {
			cancel.setTextFill(Paint.valueOf("#87CEFA"));
		});
		
		cancel.setOnMouseClicked(e -> {
			stage.close();
		});
		
		//定义布局收纳按钮
		//收纳按钮
		HBox getButton = new HBox();
		getButton.setLayoutX(340);
		getButton.setLayoutY(500);
		getButton.getChildren().addAll(loadGame, removeSave, cancel);
		
		//收纳手动存档
		VBox getSaveList = new VBox(20);
		getSaveList.setLayoutX(100);
		getSaveList.setLayoutY(200);
		for(int i = 0; i < 6; i ++)	{
			if(showSave[i] != null)
				getSaveList.getChildren().add(showSave[i]);
			else
				break;//如果已经没有内容则退出
		}
		
		//设置删除存档行为
		removeSave.setOnMouseClicked(e -> {
			ControlText CT = new ControlText();
			
			//删除文件
			CT.removeText(chooseSave);
			//删除显示控件
			getSaveList.getChildren().remove(chooseWhich);
		});
		
		//总布局 
		AnchorPane total = new AnchorPane();
		
		//美化
		//背景图片
		Image backgroundPicture = new Image("D:\\Java\\BoxPushing\\Picture\\BackGround\\23.png");
		ImageView showBGP = new ImageView(backgroundPicture);
		showBGP.setLayoutX(-150);
		showBGP.setFitWidth(1066.666);
		showBGP.setFitHeight(600);
		
		total.getChildren().add(showBGP);
		
		total.getChildren().addAll(getButton, getSaveList, topWords1, topWords2,autoSave);
		
		//新建场景与舞台
		Scene scene = new Scene(total, 800, 600);
		stage.setScene(scene);
		
		stage.show();
	}
}
