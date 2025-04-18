package menu;

import java.io.File;
import menu.*;
import Interface.*;
import fileWork.*;
import boxPushing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

import fileWork.ControlText;
import fileWork.Write;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SaveMenu extends Application {
	// 定义需要的变量
	// 难度
	private String level;
	// 关卡
	private String whichLevel;
	// 地图大小
	private int[] size;
	// 玩家移动的步数
	private int steps;
	// 玩家游玩过的地图
	private int[][] alterMap;
	// 当前玩家选择的存档名
	private String chooseName;
	// 是否覆盖变量
	private boolean isCover = false;// 默认为false
	// 选择了哪一个按钮控件
	private int chooseWhich;
	// 存档使用的字体
	private Font fontSaveWords = null;

	// 使用一个含参构造方法得到保存所需数据
	public SaveMenu(String getLevel, String getWhcihLevel, int[] getSize, int getSteps, int[][] getAlterMap) {
		level = getLevel;
		whichLevel = getWhcihLevel;
		size = getSize;
		steps = getSteps;
		alterMap = getAlterMap;
	}

	// 定义无参构造方法防止错误
	public SaveMenu() {

	}

	@Override
	public void start(Stage stage) {
		// 设置窗口名
		stage.setTitle("保存游戏");
		
		//设置窗体图标
		stage.getIcons().add(new Image("C:\\Users\\Fake\\Desktop\\imgs\\SmallPicture\\1.png"));

		// 导入需要的字体
		Font fontTopWords = null;
		try (FileInputStream in = new FileInputStream(new File("D:\\Java\\BoxPushing\\Font\\HYTianMaXingKaiW.ttf"))) {
			fontTopWords = Font.loadFont(in, 54);
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		try (FileInputStream in = new FileInputStream(
				new File("D:\\Java\\BoxPushing\\Font\\HYZiKuTangSongKeBenLiKaiW.ttf"))) {
			fontSaveWords = Font.loadFont(in, 24);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		Font fontButton = null;
		try (FileInputStream in = new FileInputStream(
				new File("D:\\Java\\BoxPushing\\Font\\HYZiKuTangYinJiaKaiShuW.ttf"))) {
			fontButton = Font.loadFont(in, 30);
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		// 设置玩家存档选项
		Text topWords = new Text("保存游戏存档");
		topWords.setFont(fontTopWords);
		topWords.setFill(Color.WHITE);
		topWords.setLayoutX(250);
		topWords.setLayoutY(50);

		// 设置玩家存档显示的部位
		// 通过读取已有的文件进行显示,不需要玩家对自动存档进行保存操作
		File getPlayerFile = new File("D:\\Java\\BoxPushing\\Save\\PlayerSave");// 读取玩家存档的内容

		// 用for循环得到玩家存档的内容,将得到的内容赋给getName
		String[] getName = getPlayerFile.list();
		Button[] showSave = new Button[6];// 存档最多为六个
		for (int i = 0; i < getName.length; i++) {
			// 将得到的文件名放入并显示
			showSave[i] = new Button(getName[i]);
			showSave[i].setFont(fontSaveWords);
			showSave[i].setTextFill(Paint.valueOf("#FFD700"));

			showSave[i].setBackground(null);
			showSave[i].setBorder(null);

			// 设置边框
			showSave[i].setStyle("-fx-border-style: solid;" + "-fx-border-color: #F5FFFA;" + " -fx-border-width: 1;"
					+ " -fx-border-radius: 10;");

			// 设置长宽
			showSave[i].setPrefHeight(24);
			showSave[i].setPrefWidth(600);
		}

		// 设置存档文本被点击反应
		if (showSave[0] != null) {
			showSave[0].setOnMouseClicked(e -> {
				chooseName = getName[0];
				chooseWhich = 0;
				isCover = true;
			});
		}
		if (showSave[1] != null) {
			showSave[1].setOnMouseClicked(e -> {
				chooseName = getName[1];
				chooseWhich = 1;
				isCover = true;
			});
		}
		if (showSave[2] != null) {
			showSave[2].setOnMouseClicked(e -> {
				chooseName = getName[2];
				chooseWhich = 2;
				isCover = true;
			});
		}
		if (showSave[3] != null) {
			showSave[3].setOnMouseClicked(e -> {
				chooseName = getName[3];
				chooseWhich = 3;
				isCover = true;
			});
		}
		if (showSave[4] != null) {
			showSave[4].setOnMouseClicked(e -> {
				chooseName = getName[4];
				chooseWhich = 4;
				isCover = true;
			});
		}
		if (showSave[5] != null) {
			showSave[5].setOnMouseClicked(e -> {
				// 设置对鼠标进入推出的反应
				showSave[5].setOnMouseEntered(new EventHandler<Event>() {
					@Override
					public void handle(Event arg0) {
						showSave[5].setTextFill(Paint.valueOf("#FF4500"));
					}
				});

				showSave[5].setOnMouseExited(new EventHandler<Event>() {
					@Override
					public void handle(Event arg0) {
						showSave[5].setTextFill(Paint.valueOf("#87CEFA"));
					}
				});

				chooseName = getName[5];
				chooseWhich = 5;
				isCover = true;
			});
		}

		// 定义读入用户输入存档名字的区域
		TextField inputName = new TextField();
		inputName.setPromptText("请在此输入要保存的新存档名");// 设置提示文字
		inputName.setLayoutX(50);
		inputName.setLayoutY(420);

		inputName.setFocusTraversable(false);// 关闭初始聚焦

		inputName.setPrefColumnCount(24);// 设置输入的字体大小
		inputName.setPrefSize(700, 50);// 设置输入框大小

		// 收纳竖排布局
		VBox saveList = new VBox(20);
		saveList.setLayoutX(120);
		saveList.setLayoutY(80);
		for (int i = 0; i < 6; i++) {
			if (showSave[i] != null)
				saveList.getChildren().add(showSave[i]);
		}

		// 设置要使用的按钮
		Button save = new Button("保存游戏");
		save.setFont(fontButton);
		save.setBackground(null);
		save.setBorder(null);
		save.setTextFill(Paint.valueOf("#87CEEB"));

		save.setOnMouseEntered(e -> {
			save.setTextFill(Color.WHITE);
		});

		save.setOnMouseExited(e -> {
			save.setTextFill(Paint.valueOf("#87CEEB"));
		});

		save.setOnMouseClicked(e -> {
			// 如果存档已满提示用户不能继续输入
			if (showSave[5] != null) {
				Alert noMoreSave = new Alert(AlertType.ERROR);

				noMoreSave.setTitle("保存存档错误");
				noMoreSave.setHeaderText(null);
				noMoreSave.setContentText("当前无法保存更多存档！\n请删除或覆盖一个存档！");

				Optional<ButtonType> result = noMoreSave.showAndWait();
				if (result.get() == ButtonType.OK) {
					e.consume();
					noMoreSave.close();
				} else {
					e.consume();
					noMoreSave.close();
				}
			}

			// 实例化write类
			Write getSave = new Write();

			// 得到用户输入
			// 检查用户是否有输入
			if (inputName.equals(null)) {
				Alert noMoreSave = new Alert(AlertType.ERROR);

				noMoreSave.setTitle("保存存档错误");
				noMoreSave.setHeaderText(null);
				noMoreSave.setContentText("请为存档输入一个存档名！");

				Optional<ButtonType> result = noMoreSave.showAndWait();
				if (result.get() == ButtonType.OK) {
					e.consume();
					noMoreSave.close();
				} else {
					e.consume();
					noMoreSave.close();
				}
			}
			String name = inputName.getText();

			// 调用写文件方法
			try {
				getSave.setPlayerSave(name, level, whichLevel, size, steps, alterMap);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			// 增加存档显示
			if (isCover == false) {
				showSave[getName.length + 1] = new Button(name + ".txt");

				showSave[getName.length + 1].setFont(fontSaveWords);
				showSave[getName.length + 1].setTextFill(Paint.valueOf("#DAA520"));

				showSave[getName.length + 1].setBackground(null);
				showSave[getName.length + 1].setBorder(null);

				// 设置边框
				showSave[getName.length + 1].setStyle("-fx-border-style: solid;" + "-fx-border-color: #F5FFFA;"
						+ " -fx-border-width: 1;" + " -fx-border-radius: 10;");

				// 设置长宽
				showSave[getName.length + 1].setPrefHeight(24);
				showSave[getName.length + 1].setPrefWidth(600);

				saveList.getChildren().add(showSave[getName.length + 1]);
			}

			// 保存成功显示
			Alert saveSucess = new Alert(AlertType.INFORMATION);

			saveSucess.setTitle("保存成功");
			saveSucess.setHeaderText(null);
			saveSucess.setContentText("保存成功");

			Optional<ButtonType> result = saveSucess.showAndWait();
			if (result.get() == ButtonType.OK) {
				saveSucess.close();
			} else {
				saveSucess.close();
			}
		});

		// 删除存档只能删除玩家的手动存档
		Button delete = new Button("删除存档");
		delete.setFont(fontButton);
		delete.setBackground(null);
		delete.setBorder(null);
		delete.setTextFill(Paint.valueOf("#87CEEB"));

		delete.setOnMouseEntered(e -> {
			delete.setTextFill(Color.WHITE);
		});

		delete.setOnMouseExited(e -> {
			delete.setTextFill(Paint.valueOf("#87CEEB"));
		});

		delete.setOnMouseClicked(e -> {
			// 新建后置存档实例
			ControlText conText = new ControlText();

			// 调用删除方法
			conText.removeText(chooseName);
			// 删除显示的控件
			saveList.getChildren().remove(chooseWhich);
		});

		Button cancel = new Button("取消");
		cancel.setFont(fontButton);
		cancel.setBackground(null);
		cancel.setBorder(null);
		cancel.setTextFill(Paint.valueOf("#87CEEB"));

		cancel.setOnMouseEntered(e -> {
			cancel.setTextFill(Color.WHITE);
		});

		cancel.setOnMouseExited(e -> {
			cancel.setTextFill(Paint.valueOf("#87CEEB"));
		});

		cancel.setOnMouseClicked(e -> {
			stage.close();
		});

		// 新建布局收纳
		// 收纳底部按钮
		HBox bottom = new HBox();
		bottom.getChildren().addAll(save, delete, cancel);
		bottom.setLayoutX(340);
		bottom.setLayoutY(485);

		// 新建总布局
		AnchorPane total = new AnchorPane();

		// 美化
		// 背景图片
		Image backgroundPicture = new Image("D:\\Java\\BoxPushing\\Picture\\BackGround\\7.png");
		ImageView showBGP = new ImageView(backgroundPicture);
		showBGP.setLayoutX(-50);
		showBGP.setFitWidth(1066.666);
		showBGP.setFitHeight(600);

		total.getChildren().add(showBGP);

		total.getChildren().addAll(bottom, topWords, inputName, saveList);

		// 新建场景与舞台
		Scene scene = new Scene(total, 800, 600);
		stage.setScene(scene);

		stage.show();
	}
}
