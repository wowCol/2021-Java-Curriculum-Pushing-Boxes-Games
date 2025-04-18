package menu;

import java.io.BufferedInputStream;
import menu.*;
import Interface.*;
import fileWork.*;
import boxPushing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainMenu extends Application {
	// 防止用户多次点击变量
	private boolean useOnce = true;
	//确定当前音乐的播放状态
	private boolean isPlay = true;
	
	//得到其它菜单输入是否播放音乐
	public void setIsPlay(boolean input) {
		isPlay = input;
	}

	// 开始菜单
	public void start(Stage primaryStage) {
		// 窗口文字打印
		primaryStage.setTitle("推箱子");
		
		//设置窗体图标
		primaryStage.getIcons().add(new Image("C:\\Users\\Fake\\Desktop\\imgs\\SmallPicture\\1.png"));

		// 加载所需字体
		Font fontTop1 = null;
		try (FileInputStream in = new FileInputStream(new File("D:\\Java\\BoxPushing\\Font\\slideyouran-Regular.ttf"))) {
			fontTop1 = Font.loadFont(in, 180);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		Font fontTop2 = null;
		try (FileInputStream in = new FileInputStream(new File("D:\\Java\\BoxPushing\\Font\\ZCOOL Addict Italic 01.ttf"))) {
			fontTop2 = Font.loadFont(in, 44);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		// 设置按钮要使用的字体
		Font fontButton = null;
		try (FileInputStream in = new FileInputStream(new File("D:\\Java\\BoxPushing\\Font\\千图笔锋手写体.ttf"))) {
			fontButton = Font.loadFont(in, 45);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		//导入要使用的字体
		Font fontTopMusic = null;
		try (FileInputStream in = new FileInputStream(new File("D:\\Java\\BoxPushing\\Font\\瑞美加张清平硬笔行书.ttf"))) {
			fontTopMusic = Font.loadFont(in, 40);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		Font fontTip = null;
		try (FileInputStream in = new FileInputStream(new File("D:\\Java\\BoxPushing\\Font\\HYZheFengSongChaoW.ttf"))) {
			fontTip = Font.loadFont(in, 35);
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		// 退出询问是否关闭
		Platform.setImplicitExit(false);
		primaryStage.setOnCloseRequest(event -> {
			event.consume();
			Alert outMenu = new Alert(Alert.AlertType.CONFIRMATION);
			outMenu.setTitle("退出程序");
			outMenu.setHeaderText(null);
			outMenu.setContentText("您是否要退出游戏？");

			Optional<ButtonType> result = outMenu.showAndWait();
			if (result.get() == ButtonType.OK) {
				Platform.exit();
				//关闭主菜单时退出所有线程
				System.exit(0);
			}
				
		});

		// 新建最终布局容纳组件
		AnchorPane root = new AnchorPane();

		// 美化
		// 导入背景图片
		Image backGroundPicture = new Image("D:\\Java\\BoxPushing\\Picture\\BackGround\\9.jpg");
		ImageView showBGP = new ImageView(backGroundPicture);

		showBGP.setLayoutY(130);

		showBGP.setFitWidth(1080);
		showBGP.setFitHeight(720);

		showBGP.setScaleX(5);
		showBGP.setScaleY(5);

		// 对图片进行放缩与移动的动画
		// 放缩
		ScaleTransition scaleBGP = new ScaleTransition();
		scaleBGP.setFromX(5);
		scaleBGP.setToX(1);
		scaleBGP.setFromY(5);
		scaleBGP.setToY(1);
		scaleBGP.setDuration(Duration.millis(1400));
		scaleBGP.setNode(showBGP);

		// 平移
		TranslateTransition translateBGp = new TranslateTransition();
		translateBGp.setByY(-130);
		translateBGp.setDuration(Duration.millis(1400));
		translateBGp.setNode(showBGP);

		// 播放动画
		scaleBGP.play();
		translateBGp.play();

		root.getChildren().add(showBGP);

		// 新建存放顶部文字的布局
		AnchorPane topWords = new AnchorPane();

		// 开场放文字移动动画
		// 界面中文字与其基本位置
		Text beginTitleTui = new Text("推");
		beginTitleTui.setFill(Color.WHITE);
		beginTitleTui.setFont(fontTop1);
		beginTitleTui.setLayoutX(150);
		beginTitleTui.setLayoutY(300);
		beginTitleTui.setOpacity(0);

		Text beginTitleXiang = new Text("箱");
		beginTitleXiang.setFill(Color.WHITE);
		beginTitleXiang.setFont(fontTop1);
		beginTitleXiang.setLayoutX(460);
		beginTitleXiang.setLayoutY(150);
		beginTitleXiang.setOpacity(0);

		Text beginTitleZi = new Text("子");
		beginTitleZi.setFill(Color.WHITE);
		beginTitleZi.setFont(fontTop1);
		beginTitleZi.setLayoutX(770);
		beginTitleZi.setLayoutY(300);
		beginTitleZi.setOpacity(0);

		// 设置开头三个字的不同动画
		// “推”字
		FadeTransition fadeTranslateTui = new FadeTransition();// 淡化效果
		fadeTranslateTui.setFromValue(0.0);
		fadeTranslateTui.setToValue(1.0);
		fadeTranslateTui.setDuration(Duration.millis(1800));
		fadeTranslateTui.setNode(beginTitleTui);

		TranslateTransition transitionTui = new TranslateTransition();// 平移效果
		transitionTui.setByX(150);
		transitionTui.setDuration(Duration.millis(1800));
		transitionTui.setNode(beginTitleTui);

		// “箱”字
		FadeTransition fadeTranslateXiang = new FadeTransition();// 淡化效果
		fadeTranslateXiang.setFromValue(0.0);
		fadeTranslateXiang.setToValue(1.0);
		fadeTranslateXiang.setDuration(Duration.millis(1800));
		fadeTranslateXiang.setNode(beginTitleXiang);

		TranslateTransition transitionXiang = new TranslateTransition();// 平移效果
		transitionXiang.setByY(150);
		transitionXiang.setDuration(Duration.millis(1800));
		transitionXiang.setNode(beginTitleXiang);

		// “子”字
		FadeTransition fadeTranslateZi = new FadeTransition();// 淡化效果
		fadeTranslateZi.setFromValue(0.0);
		fadeTranslateZi.setToValue(1.0);
		fadeTranslateZi.setDuration(Duration.millis(1800));
		fadeTranslateZi.setNode(beginTitleZi);

		TranslateTransition transitionZi = new TranslateTransition();// 平移效果
		transitionZi.setByX(-150);
		transitionZi.setDuration(Duration.millis(1800));
		transitionZi.setNode(beginTitleZi);

		// 界面顶部英文字
		Text beginTitle2 = new Text("Welcome to the Box Pushing Game");
		beginTitle2.setFill(Color.WHITE);
		beginTitle2.setFont(fontTop2);
		beginTitle2.setLayoutX(200);
		beginTitle2.setLayoutY(480);
		beginTitle2.setOpacity(0);

		// 英文字的动画
		FadeTransition fadeTranslateE = new FadeTransition();// 淡化效果
		fadeTranslateE.setFromValue(0.0);
		fadeTranslateE.setToValue(1.0);
		fadeTranslateE.setDuration(Duration.millis(1800));
		fadeTranslateE.setNode(beginTitle2);

		TranslateTransition transitionE = new TranslateTransition();// 平移效果
		transitionE.setByY(-100);
		transitionE.setDuration(Duration.millis(1800));
		transitionE.setNode(beginTitle2);

		// 新建一个反复闪烁的文字，用于提示点击
		Text tipsClick = new Text(">>点击任意按键开始游戏<<");
		tipsClick.setFont(fontTip);
		tipsClick.setFill(Color.TRANSPARENT);
		tipsClick.setLayoutX(320);
		tipsClick.setLayoutY(510);
		tipsClick.setOpacity(0);// 设置初始透明度，防止闪烁

		// 底部文字闪烁动画
		FadeTransition fadeTranslateTips = new FadeTransition();// 反复淡化的效果
		fadeTranslateTips.setAutoReverse(true);
		fadeTranslateTips.setFromValue(1.0);
		fadeTranslateTips.setToValue(0.0);
		fadeTranslateTips.setDuration(Duration.millis(1200));
		fadeTranslateTips.setCycleCount(Timeline.INDEFINITE);// 无限循环
		fadeTranslateTips.setNode(tipsClick);

		// 动画开始,在背景图片放缩完成后才播放
		translateBGp.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				fadeTranslateTui.play();
				transitionTui.play();
				fadeTranslateXiang.play();
				transitionXiang.play();
				fadeTranslateZi.play();
				transitionZi.play();
				transitionZi.setOnFinished(e -> {//英文字在中文字出来后才出现
					fadeTranslateE.play();
					transitionE.play();
				});
				
				transitionE.setOnFinished(e -> {
					tipsClick.setFill(Color.WHITE);
					fadeTranslateTips.play();// 等待以上部分执行完才执行
				});
			}
		});

		topWords.getChildren().addAll(beginTitleTui, beginTitleXiang, beginTitleZi, beginTitle2, tipsClick);

		// 新建存放按钮的布局
		AnchorPane buttons = new AnchorPane();

		// 定义所需按钮,所有按钮背景都透明
		Button continuePlay = new Button("载入游戏");
		continuePlay.setBackground(null);
		continuePlay.setBorder(null);
		continuePlay.setFont(fontButton);
		continuePlay.setTextFill(Paint.valueOf("#2E8B57"));
		continuePlay.setOpacity(0);// 设置初始透明度，防止闪烁

		continuePlay.setLayoutX(300);
		continuePlay.setLayoutY(250);
		
		//设置对鼠标进入的反应
		continuePlay.setOnMouseEntered(e -> {
			continuePlay.setTextFill(Paint.valueOf("#FFD700"));
		});
		
		continuePlay.setOnMouseExited(e -> {
			continuePlay.setTextFill(Paint.valueOf("#2E8B57"));
		});

		continuePlay.setOnMouseClicked(new EventHandler<Event>() {
			public void handle(Event event) {
				// 实例化载入菜单
				LoadMenu LM = new LoadMenu();
				try {
					LM.start(new Stage());
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				// 检查是否关闭当前窗口
				LM.setLostStage(primaryStage);
			}
		});

		Button loadGame = new Button("选择关卡");
		loadGame.setBackground(null);
		loadGame.setBorder(null);
		loadGame.setFont(fontButton);
		loadGame.setTextFill(Paint.valueOf("#2E8B57"));
		loadGame.setOpacity(0);

		loadGame.setLayoutX(500);
		loadGame.setLayoutY(320);
		
		//设置对鼠标进入的反应
		loadGame.setOnMouseEntered(e -> {
			loadGame.setTextFill(Paint.valueOf("#FFD700"));
		});
		
		loadGame.setOnMouseExited(e -> {
			loadGame.setTextFill(Paint.valueOf("#2E8B57"));
		});

		// 定义选择关卡的动作
		// 要求是要有额外游戏关卡才可以访问
		loadGame.setOnMouseClicked(new EventHandler<Event>() {
			public void handle(Event event) {
				LevelMenu LM = new LevelMenu();
				LM.start(new Stage());

				primaryStage.close();
			}
		});

		Button newGame = new Button("新游戏");
		newGame.setBackground(null);
		newGame.setBorder(null);
		newGame.setFont(fontButton);
		newGame.setTextFill(Paint.valueOf("#FF4500"));
		newGame.setOpacity(0);

		newGame.setLayoutX(300);
		newGame.setLayoutY(390);
		
		//设置对鼠标进入的反应
		newGame.setOnMouseEntered(e -> {
			newGame.setTextFill(Paint.valueOf("#FFD700"));
		});
		
		newGame.setOnMouseExited(e -> {
			newGame.setTextFill(Paint.valueOf("#FF4500"));
		});

		// 定义开始新游戏的动作
		newGame.setOnMouseClicked(new EventHandler<Event>() {
			public void handle(Event event) {
				// 打开另一个窗口
				SelectLevelMenu SLM = new SelectLevelMenu();
				SLM.start(new Stage());

				// 关闭当前窗口
				primaryStage.hide();
			}
		});

		Button workShop = new Button("创意工坊");
		workShop.setBackground(null);
		workShop.setBorder(null);
		workShop.setFont(fontButton);
		workShop.setTextFill(Paint.valueOf("#00CED1"));
		workShop.setOpacity(0);

		workShop.setLayoutX(500);
		workShop.setLayoutY(470);
		
		//设置对鼠标进入的反应
		workShop.setOnMouseEntered(e -> {
			workShop.setTextFill(Paint.valueOf("#FFD700"));
		});
		
		workShop.setOnMouseExited(e -> {
			workShop.setTextFill(Paint.valueOf("#00CED1"));
		});

		// 定义打开创意工坊的动作
		workShop.setOnMouseClicked(new EventHandler<Event>() {
			public void handle(Event event) {
				WorkShopMenu WSM = new WorkShopMenu();
				WSM.start(new Stage());

				primaryStage.close();
			}
		});

		Button rankingList = new Button("排行榜");
		rankingList.setBackground(null);
		rankingList.setBorder(null);
		rankingList.setFont(fontButton);
		rankingList.setTextFill(Paint.valueOf("#00CED1"));
		rankingList.setOpacity(0);

		rankingList.setLayoutX(300);
		rankingList.setLayoutY(540);
		
		//设置对鼠标进入的反应
		rankingList.setOnMouseEntered(e -> {
			rankingList.setTextFill(Paint.valueOf("#FFD700"));
		});
		
		rankingList.setOnMouseExited(e -> {
			rankingList.setTextFill(Paint.valueOf("#00CED1"));
		});

		// 定义打开排行榜的动作
		rankingList.setOnMouseClicked(new EventHandler<Event>() {
			public void handle(Event event) {
				RankingMenu RM = new RankingMenu();
				RM.start(new Stage());
			}
		});
		
		//只使用一个按钮来控制背景音乐的播放
		Button controlBGM = new Button("音乐开启");
		controlBGM.setFont(fontTopMusic);
		controlBGM.setBackground(null);
		controlBGM.setBorder(null);
		controlBGM.setTextFill(Color.WHITE);
		controlBGM.setLayoutX(20);
		controlBGM.setLayoutY(20);
		controlBGM.setOpacity(0);
		
		//设置对鼠标进入的反应
		controlBGM.setOnMouseEntered(e -> {
			controlBGM.setTextFill(Paint.valueOf("#FFD700"));
		});
		
		controlBGM.setOnMouseExited(e -> {
			controlBGM.setTextFill(Color.WHITE);
		});

		// 建立场景并打开窗口
		root.getChildren().add(topWords);

		Scene MainMenu = new Scene(root, 1080, 720);
		primaryStage.setScene(MainMenu);

		// 当得到用户点击后将按钮控件加入布局线程,只有当得到键盘输入才执行
		// 只能使用一次
		MainMenu.setOnKeyPressed(new EventHandler<Event>() {
			public void handle(Event event) {
				if (useOnce == true) {
					useOnce = false;//让键盘读入失效

					// 将topWords得到内容进行更改
					topWords.getChildren().remove(tipsClick);// 删除闪烁文字

					// 将topWords上移
					TranslateTransition translateTopWords = new TranslateTransition();
					translateTopWords.setByY(-140);
					translateTopWords.setDuration(Duration.millis(1200));
					translateTopWords.setNode(topWords);
					translateTopWords.play();

					// 当topWords完成才开始进入按钮动画
					translateTopWords.setOnFinished(e -> {

						// 设置放入的按钮控件的动画
						// 载入游戏
						FadeTransition fadeTranslateCP = new FadeTransition();// 淡化效果
						fadeTranslateCP.setFromValue(0.0);
						fadeTranslateCP.setToValue(1.0);
						fadeTranslateCP.setDuration(Duration.millis(1200));
						fadeTranslateCP.setNode(continuePlay);

						TranslateTransition transitionCP = new TranslateTransition();// 平移效果
						transitionCP.setByX(100);
						transitionCP.setDuration(Duration.millis(1200));
						transitionCP.setNode(continuePlay);

						// 选择关卡
						FadeTransition fadeTranslateLG = new FadeTransition();// 淡化效果
						fadeTranslateLG.setFromValue(0.0);
						fadeTranslateLG.setToValue(1.0);
						fadeTranslateLG.setDuration(Duration.millis(1200));
						fadeTranslateLG.setNode(loadGame);

						TranslateTransition transitionLG = new TranslateTransition();// 平移效果
						transitionLG.setByX(-100);
						transitionLG.setDuration(Duration.millis(1200));
						transitionLG.setNode(loadGame);

						// 开始新游戏
						FadeTransition fadeTranslateNG = new FadeTransition();// 淡化效果
						fadeTranslateNG.setFromValue(0.0);
						fadeTranslateNG.setToValue(1.0);
						fadeTranslateNG.setDuration(Duration.millis(1200));
						fadeTranslateNG.setNode(newGame);

						TranslateTransition transitionNG = new TranslateTransition();// 平移效果
						transitionNG.setByX(100);
						transitionNG.setDuration(Duration.millis(1200));
						transitionNG.setNode(newGame);

						// 创意工坊
						FadeTransition fadeTranslateWS = new FadeTransition();// 淡化效果
						fadeTranslateWS.setFromValue(0.0);
						fadeTranslateWS.setToValue(1.0);
						fadeTranslateWS.setDuration(Duration.millis(1200));
						fadeTranslateWS.setNode(workShop);

						TranslateTransition transitionWS = new TranslateTransition();// 平移效果
						transitionWS.setByX(-100);
						transitionWS.setDuration(Duration.millis(1200));
						transitionWS.setNode(workShop);

						// 排行榜
						FadeTransition fadeTranslateR = new FadeTransition();// 淡化效果
						fadeTranslateR.setFromValue(0.0);
						fadeTranslateR.setToValue(1.0);
						fadeTranslateR.setDuration(Duration.millis(1200));
						fadeTranslateR.setNode(rankingList);

						TranslateTransition transitionR = new TranslateTransition();// 平移效果
						transitionR.setByX(100);
						transitionR.setDuration(Duration.millis(1200));
						transitionR.setNode(rankingList);

						//音乐按钮
						FadeTransition fadeTranslateMusic = new FadeTransition();// 淡化效果
						fadeTranslateMusic.setFromValue(0.0);
						fadeTranslateMusic.setToValue(1.0);
						fadeTranslateMusic.setDuration(Duration.millis(1200));
						fadeTranslateMusic.setNode(controlBGM);
						
						// 设置动画开始
						fadeTranslateCP.play();
						transitionCP.play();
						fadeTranslateLG.play();
						transitionLG.play();
						fadeTranslateNG.play();
						transitionNG.play();
						fadeTranslateWS.play();
						transitionWS.play();
						fadeTranslateR.play();
						transitionR.play();
						fadeTranslateMusic.play();

						// 放入布局
						buttons.getChildren().addAll(continuePlay, loadGame, newGame, workShop, rankingList);

					});
				}
			}
		});
		root.getChildren().addAll(buttons , controlBGM);
		
		//新建用于播放背景音乐的线程
		BackGroundMusic BGM = new BackGroundMusic();
		Thread showBGM = new Thread(BGM);
		
		//未开启时才调用，点击进入主菜单后也不在自动播放
		if(!showBGM.isAlive() & isPlay == true) {
			showBGM.start();
		}
		else {
			controlBGM.setText("音乐关闭");
		}
		
		controlBGM.setOnMouseClicked(e -> {
			controlBGM.setText("音乐关闭");
			BGM.stop();
		});
	
		primaryStage.show();

	}
	
	class BackGroundMusic extends Task<Number> {
		//定义所需基本变量
		//播放的歌曲名字
		String name = "若能绽放光芒.mp3";
		//播放对象
		Player player;
		@Override
		protected Number call() throws Exception {
			//打开背景音乐窗口
			play();
			return null;
		}
		
		
		//播放方法
		public void play() {
			//新建读取文件的方法
			BufferedInputStream buffer = null;
			try {
				buffer = new BufferedInputStream(new FileInputStream(new File("D:\\Java\\BoxPushing\\Music\\" + name)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			try {
				player = new Player(buffer);
			} catch (JavaLayerException e) {
				e.printStackTrace();
			}
			try {
				player.play();
			} catch (JavaLayerException e) {
				e.printStackTrace();
			}
		}
		
		//暂停方法
		public void stop() {
			player.close();
		}
		
	}

	// 运行主类
	public static void main(String[] args) {
		launch(args);
	}

}
