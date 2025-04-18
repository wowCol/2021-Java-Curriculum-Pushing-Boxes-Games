package Interface;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.Key;
import java.security.Provider;
import java.security.Provider.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.w3c.dom.css.CSSRule;

import fileWork.Read;
import fileWork.Write;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import menu.LevelMenu;
import menu.LoadMenu;
import menu.MainMenu;
import menu.SaveMenu;
import menu.VictoryMenu;

public class PlayInterface extends Application {
	// 设置得到的难度
	private String Level;
	// 设置得到的关卡数
	private String whichLevel;
	// 设置得到的关卡地图变量
	private int[][] getMap;
	// 设置得到的胜利点
	private int[] getVictoryPoint;
	// 玩家坐标
	private int[] playerLocation;
	// 玩家图标
	private Label setPlayer;
	// 读入方式变量
	private String readType;
	// 读入存档名字变量
	private String saveName;
	// 读入存档种类变量
	private String saveType;
	// 记录玩家已经走过的步数
	private int alreadyWalked = 0;
	// 记录玩家当前分数
	private int score;
	// 记录推荐步数
	private int adviseSteps;

	// 设置得到难度的方法
	public void setLevel(String setLevel) {
		Level = setLevel;
	}

	// 设置得到关卡数的方法
	public void setWhichLevel(String getWhichLevel) {
		whichLevel = getWhichLevel;
	}

	// 设置读入内容的方式
	public void setReadType(String type) {
		readType = type;
	}

	// 设置得到存档种类和名字的方法
	public void setSave(String name, String type) {
		saveName = name;
		saveType = type;
	}

	@Override
	public void start(Stage stage) {
		// 定义标题
		stage.setTitle("推箱子");

		// 设置窗体图标
		stage.getIcons().add(new Image("C:\\Users\\Fake\\Desktop\\imgs\\SmallPicture\\1.png"));

		// 设置要使用的字体
		Font scoreWords = null;
		try (FileInputStream in = new FileInputStream(new File("D:\\Java\\BoxPushing\\Font\\HYHuaMuLanW.ttf"))) {
			scoreWords = Font.loadFont(in, 30);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		Font functionButton = null;
		try (FileInputStream in = new FileInputStream(new File("D:\\Java\\BoxPushing\\Font\\HanyiSentyScholar.ttf"))) {
			functionButton = Font.loadFont(in, 36);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		Font functionButtonR = null;
		try (FileInputStream in = new FileInputStream(new File("D:\\Java\\BoxPushing\\Font\\HanyiSentyScholar.ttf"))) {
			functionButtonR = Font.loadFont(in, 50);
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		// 新建后端处理
		WhilePlaying WP = new WhilePlaying();

		// 前端处理
		PlayInterface PIF = new PlayInterface();
		// 得到地图
		// 设置读入方法
		Read r = new Read();

		if (readType.equals("Map")) {
			// 通过地图存档读入数据
			// 读入总体文件数据
			try {
				r.mainRead();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// 得到最初地图数据，用于画图
			getMap = r.getFinalMapLocation();
		} else if (readType.equals("Save")) {
			// 通过存档读入数据
			try {
				r.loadSave(saveName, saveType);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			// 得到玩家游玩过的地图数据
			alreadyWalked = r.getSteps();
			getMap = r.getFinalMapLocation();
			WP.setSaveMap(getMap);
		}

		// 得到胜利点坐标
		getVictoryPoint = r.getVictoryPoint();

		// 分数统计控件
		// 简单模式初始分数为10，普通为20，困难为30
		if (r.getLevel().equals("Easy"))
			score = 10;
		else if (r.getLevel().equals("Medium"))
			score = 20;
		else if (r.getLevel().equals("Hard"))
			score = 30;

		// 得到建议步数
		adviseSteps = r.getAdviseSteps();

		// 通过读入的地图大小，设定不同的地图显示位置
		// 地图第一个方块位置
		int locationX = 0;
		int locationY = 0;
		int[] size = r.getMapSize();
		// 设置纵坐标
		if (size[0] <= 4)
			locationY = 300;
		else if (size[0] <= 6)
			locationY = 250;
		else if (size[0] <= 8)
			locationY = 200;
		else if (size[0] <= 12)
			locationY = 150;

		// 设置横坐标
		if (size[1] <= 6)
			locationX = 350;
		else if (size[1] <= 9)
			locationX = 260;
		else if (size[1] <= 12)
			locationX = 200;

		Text scoreStatistics = new Text("当前分数：");
		Text showScore = new Text(String.valueOf(score));

		scoreStatistics.setFill(Color.WHITE);
		showScore.setFill(Color.WHITE);

		showScore.setFont(scoreWords);
		scoreStatistics.setFont(scoreWords);

		// 步数统计控件
		// 初始步数为0，超过标准步数，每两步扣一分
		Text stepStatistics = new Text("当前步数：");
		Text showSteps = new Text(String.valueOf(alreadyWalked));// 转换成字符串放入

		stepStatistics.setFill(Color.WHITE);
		showSteps.setFill(Color.WHITE);

		showSteps.setFont(scoreWords);
		stepStatistics.setFont(scoreWords);

		// 提示文字
		Text tipWords = new Text("使用w，a，s，d或上下左右键移动");
		tipWords.setLayoutX(320);
		tipWords.setLayoutY(160);
		tipWords.setFill(Color.WHITE);
		tipWords.setFont(scoreWords);

		// 按钮定义
		Button backLM = new Button("关卡菜单");
		backLM.setFont(functionButton);
		backLM.setBackground(null);
		backLM.setBorder(null);
		backLM.setTextFill(Paint.valueOf("#6495ED"));

		backLM.setOnMouseEntered(e -> {
			backLM.setTextFill(Paint.valueOf("#00FF7F"));
		});

		backLM.setOnMouseExited(e -> {
			backLM.setTextFill(Paint.valueOf("#6495ED"));
		});

		backLM.setOnMouseClicked(e -> {
			Alert LMasking = new Alert(Alert.AlertType.CONFIRMATION);
			LMasking.setTitle("返回关卡菜单");
			LMasking.setHeaderText(null);
			LMasking.setContentText("您确认要返回关卡菜单吗？\n注意：您的进度将自动保存");

			Optional<ButtonType> result = LMasking.showAndWait();
			if (result.get() == ButtonType.OK) {
				// 用自动存档保存
				Write writeAutoSave = new Write();
				try {
					writeAutoSave.setAutoSave(r.getLevel(), r.getWhichLevel(), r.getMapSize(), alreadyWalked,
							WP.getMap);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				LevelMenu LM = new LevelMenu();
				LM.start(new Stage());

				stage.close();
			}
		});

		// 保存按钮
		Button save = new Button("保存游戏");
		save.setFont(functionButton);
		save.setBackground(null);
		save.setBorder(null);
		save.setTextFill(Paint.valueOf("#FFD700"));

		save.setOnMouseEntered(e -> {
			save.setTextFill(Paint.valueOf("#00FF7F"));
		});

		save.setOnMouseExited(e -> {
			save.setTextFill(Paint.valueOf("#FFD700"));
		});

		save.setOnMouseClicked(e -> {
			// 实例化保存菜单
			SaveMenu SM = new SaveMenu(r.getLevel(), r.getWhichLevel(), r.getMapSize(), alreadyWalked, WP.getMap);
			SM.start(new Stage());
		});

		// 载入按钮
		Button load = new Button("载入游戏");
		load.setFont(functionButton);
		load.setBackground(null);
		load.setBorder(null);
		load.setTextFill(Paint.valueOf("#FFD700"));

		load.setOnMouseEntered(e -> {
			load.setTextFill(Paint.valueOf("#00FF7F"));
		});

		load.setOnMouseExited(e -> {
			load.setTextFill(Paint.valueOf("#FFD700"));
		});

		load.setOnMouseClicked(e -> {
			// 实例化载入菜单
			LoadMenu LM = new LoadMenu();

			try {
				LM.start(new Stage());
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			LM.setLostStage(stage);
		});

		// 返回主菜单按钮
		Button backMM = new Button("主菜单");
		backMM.setFont(functionButton);
		backMM.setBackground(null);
		backMM.setBorder(null);
		backMM.setTextFill(Paint.valueOf("#6495ED"));

		backMM.setOnMouseEntered(e -> {
			backMM.setTextFill(Paint.valueOf("#00FF7F"));
		});

		backMM.setOnMouseExited(e -> {
			backMM.setTextFill(Paint.valueOf("#6495ED"));
		});

		backMM.setOnMouseClicked(e -> {
			Alert MMasking = new Alert(Alert.AlertType.CONFIRMATION);
			MMasking.setTitle("返回主菜单");
			MMasking.setHeaderText(null);
			MMasking.setContentText("您确认要返回主菜单吗？\n注意：您的进度将自动保存");

			Optional<ButtonType> result = MMasking.showAndWait();
			if (result.get() == ButtonType.OK) {
				// 用自动存档保存
				Write writeAutoSave = new Write();
				try {
					writeAutoSave.setAutoSave(r.getLevel(), r.getWhichLevel(), r.getMapSize(), alreadyWalked,
							WP.getMap);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				MainMenu MM = new MainMenu();
				MM.setIsPlay(false);
				MM.start(new Stage());

				stage.close();
			}
		});

		// 导入墙体图片，将墙体图片放入Label
		Image wall = new Image("file:C:\\Users\\Fake\\Desktop\\imgs\\SmallPicture\\wall.png");
		Label[][] putWall = new Label[getMap.length][getMap[0].length];
		// 导入玩家图片并将图片放入Label
		Image player = new Image("file:C:\\Users\\Fake\\Desktop\\imgs\\SmallPicture\\playeRright2.png");
		setPlayer = new Label();
		setPlayer.setGraphic(new ImageView(player));
		// 导入箱子图片并将图片放入Label
		Image box = new Image("file:C:\\Users\\Fake\\Desktop\\imgs\\SmallPicture\\box1.png");
		Label[][] setBox = new Label[getMap.length][getMap[0].length];
		// 导入胜利点图片放入Label
		Image victoryPoint = new Image("file:C:\\Users\\Fake\\Desktop\\imgs\\SmallPicture\\flag.png");
		Label[] setvictoryPoint = new Label[r.getVictoryPointNum()];

		// 重置按钮
		Button reloadLevel = new Button("重置");
		reloadLevel.setFont(functionButtonR);
		reloadLevel.setBackground(null);
		reloadLevel.setBorder(null);
		reloadLevel.setTextFill(Paint.valueOf("#FF7F50"));

		reloadLevel.setOnMouseEntered(e -> {
			reloadLevel.setTextFill(Paint.valueOf("#FF0000"));
		});

		reloadLevel.setOnMouseExited(e -> {
			reloadLevel.setTextFill(Paint.valueOf("#FF7F50"));
		});

		// 设置重置事件
		reloadLevel.setOnMouseClicked(e -> {
			Alert reloadAsking = new Alert(Alert.AlertType.CONFIRMATION);
			reloadAsking.setTitle("重置游戏");
			reloadAsking.setHeaderText(null);
			reloadAsking.setContentText("您确认要重置游戏吗？");

			Optional<ButtonType> result = reloadAsking.showAndWait();
			if (result.get() == ButtonType.OK) {
				PlayInterface newPIF = new PlayInterface();
				newPIF.setReadType(readType);// 重置保存当前读入方式
				newPIF.start(new Stage());

				stage.close();
			}
		});

		// 撤回按钮
		Button backLostStep = new Button("撤回");
		backLostStep.setFont(functionButtonR);
		backLostStep.setBackground(null);
		backLostStep.setBorder(null);
		backLostStep.setTextFill(Paint.valueOf("#FFA07A"));

		backLostStep.setOnMouseEntered(e -> {
			backLostStep.setTextFill(Paint.valueOf("#FF0000"));
		});

		backLostStep.setOnMouseExited(e -> {
			backLostStep.setTextFill(Paint.valueOf("#FFA07A"));
		});

		// 设置撤回事件，鼠标点击与快捷键
		backLostStep.setOnMouseClicked(e -> {
			// 移动步数减少
			if (alreadyWalked > 0)
				alreadyWalked -= 1;
			showSteps.setText(String.valueOf(alreadyWalked));

			String lostStep = WP.backLostStep();
			// 1是有箱子，2是无箱子
			// 左退
			if (lostStep.equals("a1")) {
				// 前端寻找用户输入方向的箱子并移动
				// 得到玩家坐标
				playerLocation = WP.getPlayerLocation();

				// 找到移动方向的物品并移动
				Label toBackA = setBox[playerLocation[0]][playerLocation[1] + 1];

				// 将原位置的箱子实例移动
				setBox[playerLocation[0]][playerLocation[1]] = setBox[playerLocation[0]][playerLocation[1] + 1];
				setBox[playerLocation[0]][playerLocation[1] + 1] = null;

				TranslateTransition translateA = new TranslateTransition();
				TranslateTransition translateAB = new TranslateTransition();

				translateA.setByX(-50);
				translateAB.setByX(-50);

				translateA.setDuration(Duration.millis(300));
				translateAB.setDuration(Duration.millis(300));

				translateA.setNode(setPlayer);
				translateAB.setNode(toBackA);

				translateA.play();
				translateAB.play();
			} else if (lostStep.equals("a2")) {
				TranslateTransition translateA = new TranslateTransition();
				translateA.setByX(-50);
				translateA.setDuration(Duration.millis(300));
				translateA.setNode(setPlayer);
				translateA.play();
			}

			// 右退
			if (lostStep.equals("d1")) {
				// 前端寻找用户输入方向的箱子并移动
				// 得到玩家坐标
				playerLocation = WP.getPlayerLocation();

				// 找到移动方向的物品并移动
				Label toBackD = setBox[playerLocation[0]][playerLocation[1] - 1];

				// 将原位置的箱子实例移动
				setBox[playerLocation[0]][playerLocation[1]] = setBox[playerLocation[0]][playerLocation[1] - 1];
				setBox[playerLocation[0]][playerLocation[1] - 1] = null;

				TranslateTransition translateD = new TranslateTransition();
				TranslateTransition translateDB = new TranslateTransition();

				translateD.setByX(50);
				translateDB.setByX(50);

				translateD.setDuration(Duration.millis(300));
				translateDB.setDuration(Duration.millis(300));

				translateD.setNode(setPlayer);
				translateDB.setNode(toBackD);

				translateD.play();
				translateDB.play();
			} else if (lostStep.equals("d2")) {
				TranslateTransition translateD = new TranslateTransition();
				translateD.setByX(50);
				translateD.setDuration(Duration.millis(300));
				translateD.setNode(setPlayer);
				translateD.play();
			}

			// 上退
			if (lostStep.equals("w1")) {
				// 前端寻找用户输入方向的箱子并移动
				// 得到玩家坐标
				playerLocation = WP.getPlayerLocation();

				// 找到移动方向的物品并移动
				Label toBackW = setBox[playerLocation[0] + 1][playerLocation[1]];

				// 将原位置的箱子实例移动
				setBox[playerLocation[0]][playerLocation[1]] = setBox[playerLocation[0] + 1][playerLocation[1]];
				setBox[playerLocation[0] + 1][playerLocation[1]] = null;

				TranslateTransition translateW = new TranslateTransition();
				TranslateTransition translateWB = new TranslateTransition();

				translateW.setByY(-50);
				translateWB.setByY(-50);

				translateW.setDuration(Duration.millis(300));
				translateWB.setDuration(Duration.millis(300));

				translateW.setNode(setPlayer);
				translateWB.setNode(toBackW);

				translateW.play();
				translateWB.play();
			} else if (lostStep.equals("w2")) {
				TranslateTransition translateW = new TranslateTransition();
				translateW.setByY(-50);
				translateW.setDuration(Duration.millis(300));
				translateW.setNode(setPlayer);
				translateW.play();
			}

			// 下退
			if (lostStep.equals("s1")) {
				// 前端寻找用户输入方向的箱子并移动
				// 得到玩家坐标
				playerLocation = WP.getPlayerLocation();

				// 找到移动方向的物品并移动
				Label toBackS = setBox[playerLocation[0] - 1][playerLocation[1]];

				// 将原位置的箱子实例移动
				setBox[playerLocation[0]][playerLocation[1]] = setBox[playerLocation[0] - 1][playerLocation[1]];
				setBox[playerLocation[0] - 1][playerLocation[1]] = null;

				TranslateTransition translateS = new TranslateTransition();
				TranslateTransition translateSB = new TranslateTransition();

				translateS.setByY(50);
				translateSB.setByY(50);

				translateS.setDuration(Duration.millis(300));
				translateSB.setDuration(Duration.millis(300));

				translateS.setNode(setPlayer);
				translateSB.setNode(toBackS);

				translateS.play();
				translateSB.play();
			} else if (lostStep.equals("s2")) {
				TranslateTransition translateS = new TranslateTransition();
				translateS.setByY(50);
				translateS.setDuration(Duration.millis(300));
				translateS.setNode(setPlayer);
				translateS.play();
			}
		});

		// 关闭游戏提醒
		Platform.setImplicitExit(false);
		stage.setOnCloseRequest(event -> {
			event.consume();
			Alert outMenu = new Alert(Alert.AlertType.CONFIRMATION);
			outMenu.setTitle("关闭游戏");
			outMenu.setHeaderText(null);
			outMenu.setContentText("您确认要退出游戏吗？\n注意：您的进度将自动保存");

			Optional<ButtonType> result = outMenu.showAndWait();
			if (result.get() == ButtonType.OK) {
				// 用自动存档保存
				Write writeAutoSave = new Write();
				try {
					writeAutoSave.setAutoSave(r.getLevel(), r.getWhichLevel(), r.getMapSize(), alreadyWalked,
							WP.getMap);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				Platform.exit();
			}

		});

		// 新建布局收纳顶部按钮
		HBox topContain = new HBox(-26);
		HBox topContianScore = new HBox(10);
		HBox topContianSteps = new HBox(10);

		topContianScore.getChildren().addAll(scoreStatistics, showScore);
		topContianSteps.getChildren().addAll(stepStatistics, showSteps);
		topContain.getChildren().addAll(save, load, backLM, backMM);

		topContianScore.setLayoutX(20);
		topContianScore.setLayoutY(60);

		topContianSteps.setLayoutX(220);
		topContianSteps.setLayoutY(60);

		topContain.setLayoutX(400);
		topContain.setLayoutY(50);

		// 新建布局收纳右部按钮
		VBox rightContain = new VBox(60);
		rightContain.getChildren().addAll(reloadLevel, backLostStep);
		rightContain.setLayoutX(800);
		rightContain.setLayoutY(280);

		// 使用for循环放置墙体，出身点与箱子
		for (int i = 0; i < getMap.length; i++) {
			for (int j = 0; j < getMap[0].length; j++) {
				if (getMap[i][j] == 1) {
					putWall[i][j] = new Label(null, new ImageView(wall));
					putWall[i][j].setLayoutX(locationX + 50 * j);
					putWall[i][j].setLayoutY(locationY + 50 * i);
				} else if (getMap[i][j] == 2) {
					setPlayer.setLayoutX(locationX + 50 * j);
					setPlayer.setLayoutY(locationY + 50 * i);
				} else if (getMap[i][j] == 3) {
					setBox[i][j] = new Label(null, new ImageView(box));
					setBox[i][j].setLayoutX(locationX + 50 * j);
					setBox[i][j].setLayoutY(locationY + 50 * i);
				}
			}
		}
		// 额外设置胜利点
		int whichPoint = 0;
		for (int i = 0; i < r.getVictoryPointNum(); i++) {
			setvictoryPoint[i] = new Label(null, new ImageView(victoryPoint));
			setvictoryPoint[i].setLayoutX(locationX + 50 * getVictoryPoint[1 + whichPoint]);
			setvictoryPoint[i].setLayoutY(locationY + 50 * getVictoryPoint[0 + whichPoint]);

			whichPoint += 2;
		}

		// 新建布局，收纳墙体
		AnchorPane playPane = new AnchorPane();

		// 绘制游玩时的地面
		// 新建存放地面的数组
		Rectangle[][] ground = new Rectangle[getMap.length][getMap[0].length];
		// 进入for循环绘图，赋值
		boolean isDrawable = false;// 让画图持续画图的判断变量
		for (int i = 0; i < getMap.length; i++) {
			for (int j = 0; j < getMap[0].length - 1; j++) {// j会加1，所以j小于数组最大一位
				if (getMap[i][j] == 1 & getMap[i][j + 1] != 1) {
					isDrawable = true;
				}

				if (getMap[i][j] != 1 & getMap[i][j + 1] == 1) {
					isDrawable = false;
				}
				if (getMap[i][j] == 1 & getMap[i][j + 1] == 1) {
					isDrawable = false;
				}

				if (isDrawable == true) {
					ground[i][j + 1] = new Rectangle(locationX + 50 * (j + 1), locationY + 50 * i, 51, 51);
					ground[i][j + 1].setStyle("-fx-background-insets: 0;");// 将画出的矩形边缘取消
					ground[i][j + 1].setFill(Color.WHITE);
				}
			}
		}

		// 将地面控件放入布局
		for (int i = 0; i < getMap.length; i++) {
			for (int j = 0; j < getMap[0].length; j++) {
				if (ground[i][j] != null)
					playPane.getChildren().add(ground[i][j]);
			}
		}

		// 将墙体控件放入布局
		for (int i = 0; i < getMap.length; i++) {
			for (int j = 0; j < getMap[0].length; j++) {
				if (putWall[i][j] != null)
					playPane.getChildren().add(putWall[i][j]);
			}
		}
		// 将箱子控件放入布局
		for (int i = 0; i < getMap.length; i++) {
			for (int j = 0; j < getMap[0].length; j++) {
				if (setBox[i][j] != null)
					playPane.getChildren().add(setBox[i][j]);
			}
		}
		// 将胜利点控件放入布局
		for (int i = 0; i < r.getVictoryPointNum(); i++) {
			playPane.getChildren().add(setvictoryPoint[i]);
		}

		// 游玩布局
		playPane.getChildren().add(setPlayer);

		// 总布局
		AnchorPane finallPut = new AnchorPane();

		// 美化
		// 导入背景图片
		Image backgroundPicture = new Image("D:\\Java\\BoxPushing\\Picture\\BackGround\\21.jpg");
		ImageView showBGP = new ImageView(backgroundPicture);
		finallPut.getChildren().add(showBGP);
		showBGP.setFitWidth(1080);
		showBGP.setFitHeight(720);

		finallPut.getChildren().addAll(playPane, topContianScore, topContianSteps, topContain, rightContain, tipWords);

		// 新建场景与设置 舞台
		Scene scene = new Scene(finallPut, 1080, 720);
		stage.setScene(scene);

		// 通过键盘输入，移动图片，实现游戏运动
		// 用玩家对象与箱子对象来调用键盘输入事件
		finallPut.requestFocus();// 让方向键能使用而不是在按钮上移动
		finallPut.setOnKeyPressed(new EventHandler<Event>() {
			public void handle(Event event) {
				// 获取输入的字符
				KeyEvent ke = (KeyEvent) event;
				// 改变类型以便查看
				KeyCode code = ke.getCode();

				// 实现扣分，分数不为负
				if ((alreadyWalked - adviseSteps) % 2 >= 1 & score >= 0) {
					score -= (alreadyWalked - adviseSteps) % 2;
				}
				showScore.setText(String.valueOf(score));

				if (code == KeyCode.LEFT || code == KeyCode.A) {
					int WPa = WP.isMoveAble('a');
					if (WPa == 2) {
						// 更换玩家图片
						setPlayer.setGraphic(new ImageView(new Image("C:\\Users\\Fake\\Desktop\\imgs\\SmallPicture\\playerLeft2.png")));

						TranslateTransition translateA = new TranslateTransition();
						translateA.setByX(-50);
						translateA.setDuration(Duration.millis(300));
						translateA.setNode(setPlayer);
						translateA.play();

						// 移动步数增加
						alreadyWalked += 1;
						showSteps.setText(String.valueOf(alreadyWalked));
					} else if (WPa == 3) {
						// 前端寻找用户输入方向的箱子并移动
						// 得到玩家坐标
						playerLocation = WP.getPlayerLocation();

						// 找到移动方向的物品并移动
						Label toMoveBoxA = setBox[playerLocation[0]][playerLocation[1] - 1];

						// 将原位置的箱子实例移动
						setBox[playerLocation[0]][playerLocation[1] - 2] = setBox[playerLocation[0]][playerLocation[1]
								- 1];
						setBox[playerLocation[0]][playerLocation[1] - 1] = null;

						// 更换玩家图片
						setPlayer.setGraphic(new ImageView(
								new Image("C:\\Users\\Fake\\Desktop\\imgs\\SmallPicture\\playerLeft1.png")));

						// 此时需要箱子与玩家一起移动
						TranslateTransition translateA = new TranslateTransition();
						TranslateTransition translateAB = new TranslateTransition();

						translateA.setByX(-50);
						translateAB.setByX(-50);

						translateA.setDuration(Duration.millis(300));
						translateAB.setDuration(Duration.millis(300));

						translateA.setNode(setPlayer);
						translateAB.setNode(toMoveBoxA);

						translateA.play();
						translateAB.play();

						// 移动步数增加
						alreadyWalked += 1;
						showSteps.setText(String.valueOf(alreadyWalked));

						// 每次推箱子移动完成查看是否成功
						if (WP.isSucess(getVictoryPoint) == true) {
							VictoryMenu VM = new VictoryMenu();

							VM.setVictoryWhichLevel(r.getWhichLevel());
							VM.setLostStage(stage);
							VM.setScore(score);
							VM.setLostStage(stage);

							VM.start(new Stage());
						}
					}
				} else if (code == KeyCode.DOWN || code == KeyCode.S) {
					int WPs = WP.isMoveAble('s');
					if (WPs == 2) {
						// 更换玩家图片
						setPlayer.setGraphic(new ImageView(
								new Image("C:\\Users\\Fake\\Desktop\\imgs\\SmallPicture\\playerDown2.png")));

						TranslateTransition translateS = new TranslateTransition();
						translateS.setByY(50);
						translateS.setDuration(Duration.millis(300));
						translateS.setNode(setPlayer);
						translateS.play();

						// 移动步数增加
						alreadyWalked += 1;
						showSteps.setText(String.valueOf(alreadyWalked));
					} else if (WPs == 3) {
						// 前端寻找用户输入方向的箱子并移动
						// 得到玩家坐标
						playerLocation = WP.getPlayerLocation();

						// 找到移动方向的物品并移动
						Label toMoveBoxS = setBox[playerLocation[0] + 1][playerLocation[1]];

						// 将原位置的箱子实例移动
						setBox[playerLocation[0] + 2][playerLocation[1]] = setBox[playerLocation[0]
								+ 1][playerLocation[1]];
						setBox[playerLocation[0] + 1][playerLocation[1]] = null;

						// 更换玩家图片
						setPlayer.setGraphic(new ImageView(
								new Image("C:\\Users\\Fake\\Desktop\\imgs\\SmallPicture\\playerDown1.png")));

						TranslateTransition translateS = new TranslateTransition();
						TranslateTransition translateSB = new TranslateTransition();

						translateS.setByY(50);
						translateSB.setByY(50);

						translateS.setDuration(Duration.millis(300));
						translateSB.setDuration(Duration.millis(300));

						translateS.setNode(setPlayer);
						translateSB.setNode(toMoveBoxS);

						translateS.play();
						translateSB.play();

						// 移动步数增加
						alreadyWalked += 1;
						showSteps.setText(String.valueOf(alreadyWalked));

						// 每次推箱子移动完成查看是否成功
						if (WP.isSucess(getVictoryPoint) == true) {
							VictoryMenu VM = new VictoryMenu();

							VM.setVictoryWhichLevel(r.getWhichLevel());
							VM.setScore(score);
							VM.setLostStage(stage);

							VM.start(new Stage());
						}
					}
				} else if (code == KeyCode.UP || code == KeyCode.W) {
					int WPw = WP.isMoveAble('w');
					if (WPw == 2) {
						// 更换玩家图片
						setPlayer.setGraphic(new ImageView(
								new Image("C:\\Users\\Fake\\Desktop\\imgs\\SmallPicture\\playerUp2.png")));

						TranslateTransition translateW = new TranslateTransition();
						translateW.setByY(-50);
						translateW.setDuration(Duration.millis(300));
						translateW.setNode(setPlayer);
						translateW.play();

						// 移动步数增加
						alreadyWalked += 1;
						showSteps.setText(String.valueOf(alreadyWalked));
					} else if (WPw == 3) {
						// 前端寻找用户输入方向的箱子并移动
						// 得到玩家坐标
						playerLocation = WP.getPlayerLocation();

						// 找到移动方向的物品并移动
						Label toMoveBoxW = setBox[playerLocation[0] - 1][playerLocation[1]];

						// 将原位置的箱子实例移动
						setBox[playerLocation[0] - 2][playerLocation[1]] = setBox[playerLocation[0]
								- 1][playerLocation[1]];
						setBox[playerLocation[0] - 1][playerLocation[1]] = null;

						// 更换玩家图片
						setPlayer.setGraphic(new ImageView(
								new Image("C:\\Users\\Fake\\Desktop\\imgs\\SmallPicture\\playerUp1.png")));

						TranslateTransition translateW = new TranslateTransition();
						TranslateTransition translateWB = new TranslateTransition();

						translateW.setByY(-50);
						translateWB.setByY(-50);

						translateW.setDuration(Duration.millis(300));
						translateWB.setDuration(Duration.millis(300));

						translateW.setNode(setPlayer);
						translateWB.setNode(toMoveBoxW);

						translateW.play();
						translateWB.play();

						// 移动步数增加
						alreadyWalked += 1;
						showSteps.setText(String.valueOf(alreadyWalked));

						// 每次推箱子移动完成查看是否成功
						if (WP.isSucess(getVictoryPoint) == true) {
							VictoryMenu VM = new VictoryMenu();

							VM.setVictoryWhichLevel(r.getWhichLevel());
							VM.setScore(score);
							VM.setLostStage(stage);

							VM.start(new Stage());
						}
					}
				} else if (code == KeyCode.RIGHT || code == KeyCode.D) {
					int WPd = WP.isMoveAble('d');
					if (WPd == 2) {
						// 更换玩家图片
						setPlayer.setGraphic(new ImageView(
								new Image("C:\\Users\\Fake\\Desktop\\imgs\\SmallPicture\\playerRight2.png")));

						TranslateTransition translateD = new TranslateTransition();
						translateD.setByX(50);
						translateD.setDuration(Duration.millis(300));
						translateD.setNode(setPlayer);
						translateD.play();

						// 移动步数增加
						alreadyWalked += 1;
						showSteps.setText(String.valueOf(alreadyWalked));
					} else if (WPd == 3) {
						// 前端寻找用户输入方向的箱子并移动
						// 得到玩家坐标
						playerLocation = WP.getPlayerLocation();

						// 找到移动方向的物品并移动
						Label toMoveBoxD = setBox[playerLocation[0]][playerLocation[1] + 1];

						// 将原位置的箱子实例移动
						setBox[playerLocation[0]][playerLocation[1] + 2] = setBox[playerLocation[0]][playerLocation[1]
								+ 1];
						setBox[playerLocation[0]][playerLocation[1] + 1] = null;

						// 更换玩家图片
						setPlayer.setGraphic(new ImageView(
								new Image("C:\\Users\\Fake\\Desktop\\imgs\\SmallPicture\\playerRight1.png")));
						;

						TranslateTransition translateD = new TranslateTransition();
						TranslateTransition translateDB = new TranslateTransition();

						translateD.setByX(50);
						translateDB.setByX(50);

						translateD.setDuration(Duration.millis(300));
						translateDB.setDuration(Duration.millis(300));

						translateD.setNode(setPlayer);
						translateDB.setNode(toMoveBoxD);

						translateD.play();
						translateDB.play();

						// 移动步数增加
						alreadyWalked += 1;
						showSteps.setText(String.valueOf(alreadyWalked));

						// 每次推箱子移动完成查看是否成功
						if (WP.isSucess(getVictoryPoint) == true) {
							VictoryMenu VM = new VictoryMenu();

							VM.setVictoryWhichLevel(r.getWhichLevel());
							VM.setScore(score);
							VM.setLostStage(stage);

							VM.start(new Stage());
						}
					}
				}
			}
		});

		stage.show();

		stage.setOnCloseRequest(e -> {
			System.exit(0);
		});

	}
}
