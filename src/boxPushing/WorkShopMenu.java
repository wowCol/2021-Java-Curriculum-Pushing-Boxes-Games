package boxPushing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.function.UnaryOperator;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import menu.MainMenu;

public class WorkShopMenu extends Application {
	//定义在另一个窗口使用的字体
	Font fontBuildButton = null;
	Font fontBuildTopWords = null;
	Font fontBuildTip = null;
	
	@Override
	public void start(Stage stage) {
		// 窗口文字打印
		stage.setTitle("推箱子");

		// 导入所需字体
		Font fontTopWords = null;
		try (FileInputStream in = new FileInputStream(new File("D:\\Java\\BoxPushing\\Font\\HYHuaMuLanW.ttf"))) {
			fontTopWords = Font.loadFont(in, 100);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		Font fontButton = null;
		try (FileInputStream in = new FileInputStream(new File("D:\\Java\\BoxPushing\\Font\\HYZheFengSongChaoW.ttf"))) {
			fontButton = Font.loadFont(in, 44);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		//定义另一个窗口使用的字体
		try (FileInputStream in = new FileInputStream(new File("D:\\Java\\BoxPushing\\Font\\HanyiSentyPomelo.ttf"))) {
			fontBuildButton = Font.loadFont(in, 36);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		try (FileInputStream in = new FileInputStream(new File("D:\\Java\\BoxPushing\\Font\\HYKongShanKaiW.ttf"))) {
			fontBuildTopWords = Font.loadFont(in, 40);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		try (FileInputStream in = new FileInputStream(new File("D:\\Java\\BoxPushing\\Font\\HYWuYouRuiKaiW.ttf"))) {
			fontBuildTip = Font.loadFont(in, 24);
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		// 顶部文字
		Text topWords = new Text("欢迎来到创意工坊");
		topWords.setFill(Color.WHITE);
		topWords.setFont(fontTopWords);
		topWords.setLayoutX(140);
		topWords.setLayoutY(200);

		// 按钮定义
		Button makingNewLevel = new Button("制作新关卡");
		makingNewLevel.setTextFill(Paint.valueOf("#FFFF00"));
		makingNewLevel.setFont(fontButton);

		// 设置对鼠标进入的反应
		makingNewLevel.setOnMouseEntered(e -> {
			makingNewLevel.setTextFill(Paint.valueOf("#FF0000"));
		});

		makingNewLevel.setOnMouseExited(e -> {
			makingNewLevel.setTextFill(Paint.valueOf("#FFFF00"));
		});

		makingNewLevel.setBackground(null);
		makingNewLevel.setBorder(null);

		makingNewLevel.setOnMouseClicked(e -> {
			Stage askHowBig = new Stage();

			askHowBig.setTitle("确认地图大小");

			askHowBig.setHeight(360);
			askHowBig.setWidth(500);
			askHowBig.initModality(Modality.APPLICATION_MODAL);

			// 定义放入选择初始化自定地图内容的输入框与提示短语
			Label tipWords = new Label("确认您的地图大小");
			tipWords.setTextFill(Color.WHITE);
			tipWords.setFont(fontBuildTopWords);
			tipWords.setLayoutX(90);
			tipWords.setLayoutY(20);

			Label whatHeight = new Label("请输入地图长：");
			whatHeight.setTextFill(Paint.valueOf("#DB7093"));
			whatHeight.setFont(fontBuildTip);
			
			Label whatWidth = new Label("请输入地图宽：");
			whatWidth.setTextFill(Paint.valueOf("#DB7093"));
			whatWidth.setFont(fontBuildTip);

			TextField height = new TextField();
			StringBuffer storeHeight = new StringBuffer();
			height.setTextFormatter(new TextFormatter<String>(new UnaryOperator<TextFormatter.Change>() {
				@Override
				public TextFormatter.Change apply(TextFormatter.Change t) {
					String value = t.getText();// 获取文本
					if (value.matches("^[0-9]*$")) {
						storeHeight.append(value);
						return t;// 限制用户输入，使用正则表达式很重要
					} else {
						return null;
					}
				}
			}));
			height.setPrefSize(150, 25);
			height.setPromptText("请输入3到30之间的数字");

			TextField width = new TextField();
			StringBuffer storeWidth = new StringBuffer();
			width.setTextFormatter(new TextFormatter<String>(new UnaryOperator<TextFormatter.Change>() {
				@Override
				public TextFormatter.Change apply(TextFormatter.Change t) {
					String value = t.getText();// 获取文本
					if (value.matches("^[0-9]*$")) {
						storeWidth.append(value);
						return t;// 限制用户输入，使用正则表达式很重要
					} else {
						return null;
					}
				}
			}));
			width.setPromptText("请输入3到30之间的数字");
			width.setPrefSize(150, 25);

			// 定义存放获取长宽的布局
			HBox putHeight = new HBox();
			putHeight.getChildren().addAll(whatHeight, height);
			putHeight.setLayoutX(90);
			putHeight.setLayoutY(90);
			HBox putWidth = new HBox();
			putWidth.setLayoutX(90);
			putWidth.setLayoutY(140);

			putWidth.getChildren().addAll(whatWidth, width);

			// 定义确认输入按钮
			Button confirm0 = new Button("确认");
			confirm0.setTextFill(Paint.valueOf("#1E90FF"));
			confirm0.setFont(fontBuildButton);
			confirm0.setBackground(null);
			confirm0.setBorder(null);
			
			// 设置对鼠标进入的反应
			confirm0.setOnMouseEntered(new EventHandler<Event>() {
				@Override
				public void handle(Event arg0) {
					confirm0.setTextFill(Paint.valueOf("#FF7F50"));
				}
			});

			confirm0.setOnMouseExited(new EventHandler<Event>() {
				@Override
				public void handle(Event arg0) {
					confirm0.setTextFill(Paint.valueOf("#1E90FF"));
				}
			});

			WorkShopInterface WSI = new WorkShopInterface();
			confirm0.setOnMouseClicked(new EventHandler<Event>() {
				@Override
				public void handle(Event arg0) {
					// TODO Auto-generated method stub
					try {
						int len = Integer.valueOf(storeHeight.toString());
						int wid = Integer.valueOf(storeWidth.toString());
						if (len > 30 || len < 3 || wid > 30 || wid < 3) {
							askHowBig.close();
							comfirmWindowClose(stage, askHowBig, "请输入3-30之间的数");
						} else {
							StageManager.MAP.put("len", len);
							StageManager.MAP.put("wid", wid);
							askHowBig.close();
							stage.close();
							WSI.start(new Stage());
						}
					} catch (NumberFormatException e) {
						comfirmWindowClose(stage, askHowBig, "请输入3-30之间的数");
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});

			Button cancel0 = new Button("取消");
			cancel0.setTextFill(Paint.valueOf("#1E90FF"));
			cancel0.setFont(fontBuildButton);
			cancel0.setBackground(null);
			cancel0.setBorder(null);
			
			// 设置对鼠标进入的反应
			cancel0.setOnMouseEntered(new EventHandler<Event>() {
				@Override
				public void handle(Event arg0) {
					cancel0.setTextFill(Paint.valueOf("#FF7F50"));
				}
			});

			cancel0.setOnMouseExited(new EventHandler<Event>() {
				@Override
				public void handle(Event arg0) {
					cancel0.setTextFill(Paint.valueOf("#1E90FF"));
				}
			});

			cancel0.setOnMouseClicked(e2 -> {
				askHowBig.close();
			});

			HBox putBtn = new HBox(20);
			putBtn.getChildren().addAll(confirm0, cancel0);
			putBtn.setLayoutX(100);
			putBtn.setLayoutY(180);

			// 定义总存放布局
			AnchorPane total = new AnchorPane();
			
			//美化
			//导入背景图片
			Image backGroundPictureIn = new Image("D:\\Java\\BoxPushing\\Picture\\BackGround\\20.jpg");
			ImageView showBGPIn = new ImageView(backGroundPictureIn);
			showBGPIn.setFitWidth(600);
			showBGPIn.setFitHeight(360);

			total.getChildren().add(showBGPIn);
			
			total.getChildren().addAll(tipWords, putHeight, putWidth, putBtn);

			// 新建场景与舞台
			Scene askScene = new Scene(total);
			askHowBig.setScene(askScene);

			askHowBig.show();
		});

		Button playCustomContent = new Button("游玩自定义关卡");
		playCustomContent.setTextFill(Paint.valueOf("#FFFF00"));
		playCustomContent.setFont(fontButton);

		// 设置对鼠标进入的反应
		playCustomContent.setOnMouseEntered(e -> {
			playCustomContent.setTextFill(Paint.valueOf("#FF0000"));
		});

		playCustomContent.setOnMouseExited(e -> {
			playCustomContent.setTextFill(Paint.valueOf("#FFFF00"));
		});

		playCustomContent.setBackground(null);
		playCustomContent.setBorder(null);

		Button manageCustomContent = new Button("管理自定义关卡");
		manageCustomContent.setTextFill(Paint.valueOf("#FFFF00"));
		manageCustomContent.setFont(fontButton);

		// 设置对鼠标进入的反应
		manageCustomContent.setOnMouseEntered(e -> {
			manageCustomContent.setTextFill(Paint.valueOf("#FF0000"));
		});

		manageCustomContent.setOnMouseExited(e -> {
			manageCustomContent.setTextFill(Paint.valueOf("#FFFF00"));
		});

		manageCustomContent.setBackground(null);
		manageCustomContent.setBorder(null);

		manageCustomContent.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				Stage manaSave = new Stage();
				manaSave.setTitle("管理存档");
				manaSave.initModality(Modality.APPLICATION_MODAL);
				manaSave.initOwner(stage);

				Button cancel1 = new Button("取消");
				cancel1.setFont(fontBuildButton);
				cancel1.setBackground(null);
				cancel1.setBorder(null);
				cancel1.setTextFill(Paint.valueOf("#FFD700"));
				
				// 设置对鼠标进入的反应
				cancel1.setOnMouseEntered(e -> {
					cancel1.setTextFill(Paint.valueOf("#00FF00"));
				});

				cancel1.setOnMouseExited(e -> {
					cancel1.setTextFill(Paint.valueOf("#FFD700"));
				});
				
				cancel1.setOnMouseClicked(e1 -> {
					manaSave.close();
				});

				Button confirm1 = new Button("完成");
				confirm1.setFont(fontBuildButton);
				confirm1.setBackground(null);
				confirm1.setBorder(null);
				confirm1.setTextFill(Paint.valueOf("#FFD700"));
				
				// 设置对鼠标进入的反应
				confirm1.setOnMouseEntered(e -> {
					confirm1.setTextFill(Paint.valueOf("#00FF00"));
				});

				confirm1.setOnMouseExited(e -> {
					confirm1.setTextFill(Paint.valueOf("#FFD700"));
				});
				
				confirm1.setOnMouseClicked(e2 -> {

				});
				
				HBox putBtn = new HBox(20);
				putBtn.getChildren().addAll(confirm1, cancel1);
				putBtn.setLayoutX(520);
				putBtn.setLayoutY(360);

				AnchorPane total = new AnchorPane();
				
				//美化
				//导入背景图片
				Image backGroundPictureSave = new Image("D:\\Java\\BoxPushing\\Picture\\BackGround\\15.jpg");
				ImageView showBGPSave = new ImageView(backGroundPictureSave);
				showBGPSave.setFitWidth(800);
				showBGPSave.setFitHeight(450);
				
				total.getChildren().add(showBGPSave);
				
				total.getChildren().addAll(putBtn);

				Scene Scene = new Scene(total, 800, 450);
				manaSave.setScene(Scene);
				manaSave.show();
			}
		});

		Button backMM = new Button("主菜单");
		backMM.setTextFill(Paint.valueOf("#00FFFF"));
		backMM.setFont(fontButton);
		backMM.setLayoutX(880);
		backMM.setLayoutY(20);

		// 设置对鼠标进入的反应
		backMM.setOnMouseEntered(e -> {
			backMM.setTextFill(Paint.valueOf("#000080"));
		});

		backMM.setOnMouseExited(e -> {
			backMM.setTextFill(Paint.valueOf("#00FFFF"));
		});

		backMM.setBackground(null);
		backMM.setBorder(null);

		backMM.setOnMouseClicked(e -> {
			MainMenu MM = new MainMenu();
			MM.setIsPlay(false);
			MM.start(new Stage());

			stage.close();
		});

		// 退出询问是否关闭
		Platform.setImplicitExit(false);
		stage.setOnCloseRequest(event -> {
			event.consume();
			Alert outMenu = new Alert(Alert.AlertType.CONFIRMATION);
			outMenu.setTitle("退出程序");
			outMenu.setHeaderText(null);
			outMenu.setContentText("您是否要退出创意工坊？");

			Optional<ButtonType> result = outMenu.showAndWait();
			if (result.get() == ButtonType.OK)
				Platform.exit();
		});

		// 新建存放按钮布局
		VBox putButton = new VBox(20);
		putButton.getChildren().addAll(makingNewLevel, playCustomContent, manageCustomContent);
		putButton.setLayoutX(370);
		putButton.setLayoutY(260);

		// 新建整体布局
		AnchorPane total = new AnchorPane();

		// 美化
		// 导入背景图片
		Image backGroundPicture = new Image("D:\\Java\\BoxPushing\\Picture\\BackGround\\14.png");
		ImageView showBGP = new ImageView(backGroundPicture);
		showBGP.setFitWidth(1080);
		showBGP.setFitHeight(720);

		total.getChildren().add(showBGP);

		total.getChildren().addAll(putButton, topWords, backMM);

		// 新建场景与舞台
		Scene scene = new Scene(total, 1080, 720);
		stage.setScene(scene);
		stage.show();
	}

	public void comfirmWindowClose(Stage Priamrystage, Stage closeStage, String windowName) {
		Stage error1 = new Stage();
		error1.setTitle("错误");
		error1.initModality(Modality.APPLICATION_MODAL);
		error1.initOwner(Priamrystage);

		Button confirm1 = new Button("确认");// 退出提示界面
		confirm1.setLayoutX(250);
		confirm1.setLayoutY(160);
		confirm1.setOnMouseClicked(e3 -> {
			error1.close();
			closeStage.close();
		});

		VBox error1Vbox = new VBox(new Text(windowName));
		error1Vbox.setLayoutX(60);
		error1Vbox.setLayoutY(70);

		AnchorPane total = new AnchorPane();
		total.getChildren().addAll(error1Vbox, confirm1);

		Scene error1Scene = new Scene(total, 500, 300);
		error1.setScene(error1Scene);
		error1.show();
	}
}