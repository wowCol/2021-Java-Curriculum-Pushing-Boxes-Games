package menu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import Interface.PlayInterface;
import fileWork.Read;
import fileWork.Write;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LevelMenu extends Application {
	public void start(Stage primaryStage) {
		// 窗口文字打印
		primaryStage.setTitle("推箱子");
		
		//设置窗体图标
		primaryStage.getIcons().add(new Image("C:\\Users\\Fake\\Desktop\\imgs\\SmallPicture\\1.png"));

		// 导入所需字体
		Font fontTop = null;
		try (FileInputStream in = new FileInputStream(new File("D:\\Java\\BoxPushing\\Font\\HanyiSentyPomelo.ttf"))) {
			fontTop = Font.loadFont(in, 80);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		Font fontButtonLevel = null;
		try (FileInputStream in = new FileInputStream(new File("D:\\Java\\BoxPushing\\Font\\ZhiyongWrite.ttf"))) {
			fontButtonLevel = Font.loadFont(in, 60);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		Font fontTopButton = null;
		try (FileInputStream in = new FileInputStream(new File("D:\\Java\\BoxPushing\\Font\\HYKongShanKaiW.ttf"))) {
			fontTopButton = Font.loadFont(in, 36);
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		// 新建读入方法
		Read r = new Read();
		String level = null;
		level = r.getLevelBigin();
		// 确定顶部输入当前关卡
		String topLevel = null;
		if (level.equals("Easy"))
			topLevel = "（简单）";
		else if (level.equals("Medium"))
			topLevel = "（中等）";
		else if (level.equals("Hard"))
			topLevel = "（困难）";

		// 顶部文字
		Text topWords = new Text("请选择关卡" + topLevel);
		topWords.setFont(fontTop);
		topWords.setFill(Color.WHITE);
		topWords.setLayoutX(160);
		topWords.setLayoutY(240);

		// 关卡选择按钮
		Button level1 = new Button("1");

		level1.setOnMouseClicked(e -> {
			// 实例化游戏界面与写入方法
			PlayInterface PIF = new PlayInterface();
			Write w = new Write();
			try {
				w.setPlayerChooseWhichLevel(1);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			// 打开新舞台
			PIF.setReadType("Map");
			PIF.start(new Stage());

			// 关闭当前窗口
			primaryStage.close();
		});

		Button level2 = new Button("2");

		level2.setOnMouseClicked(e -> {
			// 实例化游戏界面与写入方法
			PlayInterface PIF = new PlayInterface();
			Write w = new Write();
			try {
				w.setPlayerChooseWhichLevel(2);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			// 打开新舞台
			PIF.setReadType("Map");
			PIF.start(new Stage());

			// 关闭当前窗口
			primaryStage.close();
		});

		Button level3 = new Button("3");

		level3.setOnMouseClicked(e -> {
			// 实例化游戏界面与写入方法
			PlayInterface PIF = new PlayInterface();
			Write w = new Write();
			try {
				w.setPlayerChooseWhichLevel(3);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			// 打开新舞台
			PIF.setReadType("Map");
			PIF.start(new Stage());

			// 关闭当前窗口
			primaryStage.close();
		});

		Button level4 = new Button("4");

		level4.setOnMouseClicked(e -> {
			// 实例化游戏界面与写入方法
			PlayInterface PIF = new PlayInterface();
			Write w = new Write();
			try {
				w.setPlayerChooseWhichLevel(4);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			// 打开新舞台
			PIF.setReadType("Map");
			PIF.start(new Stage());

			// 关闭当前窗口
			primaryStage.close();
		});

		Button level5 = new Button("5");

		level5.setOnMouseClicked(e -> {
			// 实例化游戏界面与写入方法
			PlayInterface PIF = new PlayInterface();
			Write w = new Write();
			try {
				w.setPlayerChooseWhichLevel(5);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			// 打开新舞台
			PIF.setReadType("Map");
			PIF.start(new Stage());

			// 关闭当前窗口
			primaryStage.close();
		});

		Button level6 = new Button("6");

		level6.setOnMouseClicked(e -> {
			// 实例化游戏界面与写入方法
			PlayInterface PIF = new PlayInterface();
			Write w = new Write();
			try {
				w.setPlayerChooseWhichLevel(6);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			// 打开新舞台
			PIF.setReadType("Map");
			PIF.start(new Stage());

			// 关闭当前窗口
			primaryStage.close();
		});

		Button level7 = new Button("7");

		level7.setOnMouseClicked(e -> {
			// 实例化游戏界面与写入方法
			PlayInterface PIF = new PlayInterface();
			Write w = new Write();
			try {
				w.setPlayerChooseWhichLevel(7);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			// 打开新舞台
			PIF.setReadType("Map");
			PIF.start(new Stage());

			// 关闭当前窗口
			primaryStage.close();
		});

		Button level8 = new Button("8");

		level8.setOnMouseClicked(e -> {
			// 实例化游戏界面与写入方法
			PlayInterface PIF = new PlayInterface();
			Write w = new Write();
			try {
				w.setPlayerChooseWhichLevel(8);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			// 打开新舞台
			PIF.setReadType("Map");
			PIF.start(new Stage());

			// 关闭当前窗口
			primaryStage.close();
		});

		Button level9 = new Button("9");

		level9.setOnMouseClicked(e -> {
			// 实例化游戏界面与写入方法
			PlayInterface PIF = new PlayInterface();
			Write w = new Write();
			try {
				w.setPlayerChooseWhichLevel(9);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			// 打开新舞台
			PIF.setReadType("Map");
			PIF.start(new Stage());

			// 关闭当前窗口
			primaryStage.close();
		});

		Button level10 = new Button("10");

		level10.setOnMouseClicked(e -> {
			// 实例化游戏界面与写入方法
			PlayInterface PIF = new PlayInterface();
			Write w = new Write();
			try {
				w.setPlayerChooseWhichLevel(10);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			// 打开新舞台
			PIF.setReadType("Map");
			PIF.start(new Stage());

			// 关闭当前窗口
			primaryStage.close();
		});

		Button backMM = new Button("主菜单");

		backMM.setOnMouseClicked(e -> {
			MainMenu MM = new MainMenu();
			MM.setIsPlay(false);
			MM.start(new Stage());
	
			primaryStage.close();
		});

		Button backSLM = new Button("返回选择难度菜单");

		backSLM.setOnMouseClicked(e -> {
			SelectLevelMenu SLM = new SelectLevelMenu();
			SLM.start(new Stage());

			primaryStage.close();
		});

		// 退出询问是否关闭
		Platform.setImplicitExit(false);
		primaryStage.setOnCloseRequest(event -> {
			event.consume();
			Alert outMenu = new Alert(Alert.AlertType.CONFIRMATION);
			outMenu.setTitle("退出程序");
			outMenu.setHeaderText(null);
			outMenu.setContentText("您是否要退出游戏？");

			Optional<ButtonType> result = outMenu.showAndWait();
			if (result.get() == ButtonType.OK)
				Platform.exit();
		});

		// 储存右上角按钮布局
		HBox selectMenu = new HBox(-10);
		selectMenu.getChildren().addAll(backSLM, backMM);
		selectMenu.setLayoutX(600);
		selectMenu.setLayoutY(40);

		// 新建两个个横排布局，储存关卡的按钮，并且每个按钮间距为30像素
		HBox putLevel1 = new HBox(30);
		putLevel1.getChildren().addAll(level1, level2, level3, level4, level5);
		putLevel1.setLayoutX(210);
		putLevel1.setLayoutY(320);
		HBox putLevel2 = new HBox(30);
		putLevel2.getChildren().addAll(level6, level7, level8, level9, level10);
		putLevel2.setLayoutX(210);
		putLevel2.setLayoutY(460);

		// 新建一个锚点布局，并将横排布局放入其中
		AnchorPane total = new AnchorPane();

		// 美化
		// 背景图片,不同难度导入不同背景图片，使用不同颜色的按钮
		if (level.equals("Easy")) {
			//背景图片
			Image backgroundPicture = new Image("D:\\Java\\BoxPushing\\Picture\\BackGround\\2.png");
			ImageView showBGP = new ImageView(backgroundPicture);
			showBGP.setFitWidth(1080);
			showBGP.setFitHeight(720);

			//关卡按钮
			level1.setFont(fontButtonLevel);
			level1.setBackground(null);
			level1.setTextFill(Paint.valueOf("#00CED1"));
			
			//设置对鼠标进入推出的反应
			level1.setOnMouseEntered(e -> {
				level1.setTextFill(Paint.valueOf("#FF4500"));
			});
			
			level1.setOnMouseExited(e -> {
				level1.setTextFill(Paint.valueOf("#00CED1"));
			});
			
			level2.setFont(fontButtonLevel);
			level2.setBackground(null);
			level2.setTextFill(Paint.valueOf("#00CED1"));
			
			//设置对鼠标进入推出的反应
			level2.setOnMouseEntered(e -> {
				level2.setTextFill(Paint.valueOf("#FF4500"));
			});
			
			level2.setOnMouseExited(e -> {
				level2.setTextFill(Paint.valueOf("#00CED1"));
			});
			
			level3.setFont(fontButtonLevel);
			level3.setBackground(null);
			level3.setTextFill(Paint.valueOf("#00CED1"));
			
			//设置对鼠标进入推出的反应
			level3.setOnMouseEntered(e -> {
				level3.setTextFill(Paint.valueOf("#FF4500"));
			});
			
			level3.setOnMouseExited(e -> {
				level3.setTextFill(Paint.valueOf("#00CED1"));
			});

			level4.setFont(fontButtonLevel);
			level4.setBackground(null);
			level4.setTextFill(Paint.valueOf("#00CED1"));
			
			//设置对鼠标进入推出的反应
			level4.setOnMouseEntered(e -> {
				level4.setTextFill(Paint.valueOf("#FF4500"));
			});
			
			level4.setOnMouseExited(e -> {
				level4.setTextFill(Paint.valueOf("#00CED1"));
			});
			
			level5.setFont(fontButtonLevel);
			level5.setBackground(null);
			level5.setTextFill(Paint.valueOf("#00CED1"));
			
			//设置对鼠标进入推出的反应
			level5.setOnMouseEntered(e -> {
				level5.setTextFill(Paint.valueOf("#FF4500"));
			});
			
			level5.setOnMouseExited(e -> {
				level5.setTextFill(Paint.valueOf("#00CED1"));
			});
			
			level6.setFont(fontButtonLevel);
			level6.setBackground(null);
			level6.setTextFill(Paint.valueOf("#00CED1"));
			
			//设置对鼠标进入推出的反应
			level6.setOnMouseEntered(e -> {
				level6.setTextFill(Paint.valueOf("#FF4500"));
			});
			
			level6.setOnMouseExited(e -> {
				level6.setTextFill(Paint.valueOf("#00CED1"));
			});
			
			level7.setFont(fontButtonLevel);
			level7.setBackground(null);
			level7.setTextFill(Paint.valueOf("#00CED1"));
			
			//设置对鼠标进入推出的反应
			level7.setOnMouseEntered(e -> {
				level7.setTextFill(Paint.valueOf("#FF4500"));
			});
			
			level7.setOnMouseExited(e -> {
				level7.setTextFill(Paint.valueOf("#00CED1"));
			});
			
			level8.setFont(fontButtonLevel);
			level8.setBackground(null);
			level8.setTextFill(Paint.valueOf("#00CED1"));
			
			//设置对鼠标进入推出的反应
			level8.setOnMouseEntered(e -> {
				level8.setTextFill(Paint.valueOf("#FF4500"));
			});
			
			level8.setOnMouseExited(e -> {
				level8.setTextFill(Paint.valueOf("#00CED1"));
			});
			
			level9.setFont(fontButtonLevel);
			level9.setBackground(null);
			level9.setTextFill(Paint.valueOf("#00CED1"));
			
			//设置对鼠标进入推出的反应
			level9.setOnMouseEntered(e -> {
				level9.setTextFill(Paint.valueOf("#FF4500"));
			});
			
			level9.setOnMouseExited(e -> {
				level9.setTextFill(Paint.valueOf("#00CED1"));
			});
			
			level10.setFont(fontButtonLevel);
			level10.setBackground(null);
			level10.setTextFill(Paint.valueOf("#00CED1"));
			
			//设置对鼠标进入推出的反应
			level10.setOnMouseEntered(e -> {
				level10.setTextFill(Paint.valueOf("#FF4500"));
			});
			
			level10.setOnMouseExited(e -> {
				level10.setTextFill(Paint.valueOf("#00CED1"));
			});
			
			//菜单按钮
			backSLM.setFont(fontTopButton);
			backSLM.setBackground(null);
			backSLM.setTextFill(Paint.valueOf("#32CD32"));
			
			//设置对鼠标进入推出的反应
			backSLM.setOnMouseEntered(e -> {
				backSLM.setTextFill(Paint.valueOf("#87CEEB"));
			});
			
			backSLM.setOnMouseExited(e -> {
				backSLM.setTextFill(Paint.valueOf("#32CD32"));
			});
			
			backMM.setFont(fontTopButton);
			backMM.setBackground(null);
			backMM.setTextFill(Paint.valueOf("#32CD32"));
			
			//设置对鼠标进入推出的反应
			backMM.setOnMouseEntered(e -> {
				backMM.setTextFill(Paint.valueOf("#87CEEB"));
			});
			
			backMM.setOnMouseExited(e -> {
				backMM.setTextFill(Paint.valueOf("#32CD32"));
			});
			
			total.getChildren().add(showBGP);
		}
		if(level.equals("Medium")) {		
			//背景图片
			Image backgroundPicture = new Image("D:\\Java\\BoxPushing\\Picture\\BackGround\\6.jpg");
			ImageView showBGP = new ImageView(backgroundPicture);
			showBGP.setFitWidth(1080);
			showBGP.setFitHeight(720);

			//关卡按钮
			level1.setFont(fontButtonLevel);
			level1.setBackground(null);
			level1.setTextFill(Paint.valueOf("#FFC0CB"));
			
			//设置对鼠标进入推出的反应
			level1.setOnMouseEntered(e -> {
				level1.setTextFill(Paint.valueOf("#FFFF00"));
			});
			
			level1.setOnMouseExited(e -> {
				level1.setTextFill(Paint.valueOf("#FFC0CB"));
			});
			
			level2.setFont(fontButtonLevel);
			level2.setBackground(null);
			level2.setTextFill(Paint.valueOf("#FFC0CB"));
			
			//设置对鼠标进入推出的反应
			level2.setOnMouseEntered(e -> {
				level2.setTextFill(Paint.valueOf("#FFFF00"));
			});
			
			level2.setOnMouseExited(e -> {
				level2.setTextFill(Paint.valueOf("#FFC0CB"));
			});
			
			level3.setFont(fontButtonLevel);
			level3.setBackground(null);
			level3.setTextFill(Paint.valueOf("#FFC0CB"));
			
			//设置对鼠标进入推出的反应
			level3.setOnMouseEntered(e -> {
				level3.setTextFill(Paint.valueOf("#FFFF00"));
			});
			
			level3.setOnMouseExited(e -> {
				level3.setTextFill(Paint.valueOf("#FFC0CB"));
			});

			level4.setFont(fontButtonLevel);
			level4.setBackground(null);
			level4.setTextFill(Paint.valueOf("#FFC0CB"));
			
			//设置对鼠标进入推出的反应
			level4.setOnMouseEntered(e -> {
				level4.setTextFill(Paint.valueOf("#FFFF00"));
			});
			
			level4.setOnMouseExited(e -> {
				level4.setTextFill(Paint.valueOf("#FFC0CB"));
			});
			
			level5.setFont(fontButtonLevel);
			level5.setBackground(null);
			level5.setTextFill(Paint.valueOf("#FFC0CB"));
			
			//设置对鼠标进入推出的反应
			level5.setOnMouseEntered(e -> {
				level5.setTextFill(Paint.valueOf("#FFFF00"));
			});
			
			level5.setOnMouseExited(e -> {
				level5.setTextFill(Paint.valueOf("#FFC0CB"));
			});
			
			level6.setFont(fontButtonLevel);
			level6.setBackground(null);
			level6.setTextFill(Paint.valueOf("#FFC0CB"));
			
			//设置对鼠标进入推出的反应
			level6.setOnMouseEntered(e -> {
				level6.setTextFill(Paint.valueOf("#FFFF00"));
			});
			
			level6.setOnMouseExited(e -> {
				level6.setTextFill(Paint.valueOf("#FFC0CB"));
			});
			
			level7.setFont(fontButtonLevel);
			level7.setBackground(null);
			level7.setTextFill(Paint.valueOf("#FFC0CB"));
			
			//设置对鼠标进入推出的反应
			level7.setOnMouseEntered(e -> {
				level7.setTextFill(Paint.valueOf("#FFFF00"));
			});
			
			level7.setOnMouseExited(e -> {
				level7.setTextFill(Paint.valueOf("#FFC0CB"));
			});
			
			level8.setFont(fontButtonLevel);
			level8.setBackground(null);
			level8.setTextFill(Paint.valueOf("#FFC0CB"));
			
			//设置对鼠标进入推出的反应
			level8.setOnMouseEntered(e -> {
				level8.setTextFill(Paint.valueOf("#FFFF00"));
			});
			
			level8.setOnMouseExited(e -> {
				level8.setTextFill(Paint.valueOf("#FFC0CB"));
			});
			
			level9.setFont(fontButtonLevel);
			level9.setBackground(null);
			level9.setTextFill(Paint.valueOf("#FFC0CB"));
			
			//设置对鼠标进入推出的反应
			level9.setOnMouseEntered(e -> {
				level9.setTextFill(Paint.valueOf("#FFFF00"));
			});
			
			level9.setOnMouseExited(e -> {
				level9.setTextFill(Paint.valueOf("#FFC0CB"));
			});
			
			level10.setFont(fontButtonLevel);
			level10.setBackground(null);
			level10.setTextFill(Paint.valueOf("#FFC0CB"));
			
			//设置对鼠标进入推出的反应
			level10.setOnMouseEntered(e -> {
				level10.setTextFill(Paint.valueOf("#FFFF00"));
			});
			
			level10.setOnMouseExited(e -> {
				level10.setTextFill(Paint.valueOf("#FFC0CB"));
			});
			
			//菜单按钮
			backSLM.setFont(fontTopButton);
			backSLM.setBackground(null);
			backSLM.setTextFill(Paint.valueOf("#FFA500"));
			
			//设置对鼠标进入推出的反应
			backSLM.setOnMouseEntered(e -> {
				backSLM.setTextFill(Paint.valueOf("#00FFFF"));
			});
			
			backSLM.setOnMouseExited(e -> {
				backSLM.setTextFill(Paint.valueOf("#FFA500"));
			});
			
			backMM.setFont(fontTopButton);
			backMM.setBackground(null);
			backMM.setTextFill(Paint.valueOf("#FFA500"));
			
			//设置对鼠标进入推出的反应
			backMM.setOnMouseEntered(e -> {
				backMM.setTextFill(Paint.valueOf("#00FFFF"));
			});
			
			backMM.setOnMouseExited(e -> {
				backMM.setTextFill(Paint.valueOf("#FFA500"));
			});
			
			total.getChildren().add(showBGP);
		}
		if(level.equals("Medium")) {		
			Image backgroundPicture = new Image("D:\\Java\\BoxPushing\\Picture\\BackGround\\6.jpg");
			ImageView showBGP = new ImageView(backgroundPicture);
			showBGP.setFitWidth(1080);
			showBGP.setFitHeight(720);
			
			total.getChildren().add(showBGP);
		}
		if(level.equals("Hard")) {
			//背景图片
			Image backgroundPicture = new Image("D:\\Java\\BoxPushing\\Picture\\BackGround\\12.png");
			ImageView showBGP = new ImageView(backgroundPicture);
			showBGP.setFitWidth(1080);
			showBGP.setFitHeight(720);
			
			//关卡按钮
			level1.setFont(fontButtonLevel);
			level1.setBackground(null);
			level1.setTextFill(Paint.valueOf("#FF4500"));
			
			//设置对鼠标进入推出的反应
			level1.setOnMouseEntered(e -> {
				level1.setTextFill(Paint.valueOf("#1E90FF"));
			});
			
			level1.setOnMouseExited(e -> {
				level1.setTextFill(Paint.valueOf("#FF4500"));
			});
			
			level2.setFont(fontButtonLevel);
			level2.setBackground(null);
			level2.setTextFill(Paint.valueOf("#FF4500"));
			
			//设置对鼠标进入推出的反应
			level2.setOnMouseEntered(e -> {
				level2.setTextFill(Paint.valueOf("#1E90FF"));
			});
			
			level2.setOnMouseExited(e -> {
				level2.setTextFill(Paint.valueOf("#FF4500"));
			});
			
			level3.setFont(fontButtonLevel);
			level3.setBackground(null);
			level3.setTextFill(Paint.valueOf("#FF4500"));
			
			//设置对鼠标进入推出的反应
			level3.setOnMouseEntered(e -> {
				level3.setTextFill(Paint.valueOf("#1E90FF"));
			});
			
			level3.setOnMouseExited(e -> {
				level3.setTextFill(Paint.valueOf("#FF4500"));
			});

			level4.setFont(fontButtonLevel);
			level4.setBackground(null);
			level4.setTextFill(Paint.valueOf("#FF4500"));
			
			//设置对鼠标进入推出的反应
			level4.setOnMouseEntered(e -> {
				level4.setTextFill(Paint.valueOf("#1E90FF"));
			});
			
			level4.setOnMouseExited(e -> {
				level4.setTextFill(Paint.valueOf("#FF4500"));
			});
			
			level5.setFont(fontButtonLevel);
			level5.setBackground(null);
			level5.setTextFill(Paint.valueOf("#FF4500"));
			
			//设置对鼠标进入推出的反应
			level5.setOnMouseEntered(e -> {
				level5.setTextFill(Paint.valueOf("#1E90FF"));
			});
			
			level5.setOnMouseExited(e -> {
				level5.setTextFill(Paint.valueOf("#FF4500"));
			});
			
			level6.setFont(fontButtonLevel);
			level6.setBackground(null);
			level6.setTextFill(Paint.valueOf("#FF4500"));
			
			//设置对鼠标进入推出的反应
			level6.setOnMouseEntered(e -> {
				level6.setTextFill(Paint.valueOf("#1E90FF"));
			});
			
			level6.setOnMouseExited(e -> {
				level6.setTextFill(Paint.valueOf("#FF4500"));
			});
			
			level7.setFont(fontButtonLevel);
			level7.setBackground(null);
			level7.setTextFill(Paint.valueOf("#FF4500"));
			
			//设置对鼠标进入推出的反应
			level7.setOnMouseEntered(e -> {
				level7.setTextFill(Paint.valueOf("#1E90FF"));
			});
			
			level7.setOnMouseExited(e -> {
				level7.setTextFill(Paint.valueOf("#FF4500"));
			});
			
			level8.setFont(fontButtonLevel);
			level8.setBackground(null);
			level8.setTextFill(Paint.valueOf("#FF4500"));
			
			//设置对鼠标进入推出的反应
			level8.setOnMouseEntered(e -> {
				level8.setTextFill(Paint.valueOf("#1E90FF"));
			});
			
			level8.setOnMouseExited(e -> {
				level8.setTextFill(Paint.valueOf("#FF4500"));
			});
			
			level9.setFont(fontButtonLevel);
			level9.setBackground(null);
			level9.setTextFill(Paint.valueOf("#FF4500"));
			
			//设置对鼠标进入推出的反应
			level9.setOnMouseEntered(e -> {
				level9.setTextFill(Paint.valueOf("#1E90FF"));
			});
			
			level9.setOnMouseExited(e -> {
				level9.setTextFill(Paint.valueOf("#FF4500"));
			});
			
			level10.setFont(fontButtonLevel);
			level10.setBackground(null);
			level10.setTextFill(Paint.valueOf("#FF4500"));
			
			//设置对鼠标进入推出的反应
			level10.setOnMouseEntered(e -> {
				level10.setTextFill(Paint.valueOf("#1E90FF"));
			});
			
			level10.setOnMouseExited(e -> {
				level10.setTextFill(Paint.valueOf("#FF4500"));
			});
			
			//菜单按钮
			backSLM.setFont(fontTopButton);
			backSLM.setBackground(null);
			backSLM.setTextFill(Paint.valueOf("#4169E1"));
			
			//设置对鼠标进入推出的反应
			backSLM.setOnMouseEntered(e -> {
				backSLM.setTextFill(Paint.valueOf("#FF4500"));
			});
			
			backSLM.setOnMouseExited(e -> {
				backSLM.setTextFill(Paint.valueOf("#4169E1"));
			});
			
			backMM.setFont(fontTopButton);
			backMM.setBackground(null);
			backMM.setTextFill(Paint.valueOf("#4169E1"));
			
			//设置对鼠标进入推出的反应
			backMM.setOnMouseEntered(e -> {
				backMM.setTextFill(Paint.valueOf("#FF4500"));
			});
			
			backMM.setOnMouseExited(e -> {
				backMM.setTextFill(Paint.valueOf("#4169E1"));
			});
			
			total.getChildren().add(showBGP);
		}

		total.getChildren().addAll(putLevel1, putLevel2, topWords, selectMenu);

		Scene LM = new Scene(total, 1080, 720);
		primaryStage.setScene(LM);

		primaryStage.show();
		
		primaryStage.setOnCloseRequest(e -> {
			System.exit(0);
		});

	}
}
