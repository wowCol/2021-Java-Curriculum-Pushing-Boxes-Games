package menu;

import javafx.application.Application;
import menu.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import Interface.*;
import fileWork.*;
import boxPushing.*;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RankingMenu extends Application{

	@Override
	public void start(Stage stage) {
		//设置窗口基础属性
		stage.setTitle("排行榜");
		
		//设置窗体图标
		stage.getIcons().add(new Image("C:\\Users\\Fake\\Desktop\\imgs\\SmallPicture\\1.png"));
		
		// 加载所需字体
		Font fontTop = null;
		try (FileInputStream in = new FileInputStream(new File("D:\\Java\\BoxPushing\\Font\\HYTianMaXingKaiW.ttf"))) {
			fontTop = Font.loadFont(in, 120);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		Font fontPlayer = null;
		try (FileInputStream in = new FileInputStream(new File("D:\\Java\\BoxPushing\\Font\\HYZiKuTangSongKeBenLiKaiW.ttf"))) {
			fontPlayer = Font.loadFont(in, 36);
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		//顶部文字
		Text topWords = new Text( 120, 140,"排行榜");
		topWords.setFill(Paint.valueOf("#F0E68C"));
		topWords.setFont(fontTop);
		
		//从后端获得排行榜数据并显示在窗口中
		//使用for循环来多重显示后台排行榜数据
		//获得文档内容，一次最多十个
		Text [] input = new Text[10];
		for(int i = 0; i < 10; i++) {
			input[i] = new Text("玩家1: 100");
			input[i].setFill(Paint.valueOf("#FFA500"));
			input[i].setFont(fontPlayer);
		}
		//赋值给布局
		VBox putInfo = new VBox(10);
		for(int i = 0; i < 10; i++) {
			putInfo.getChildren().add(input[i]);
		}
		putInfo.setLayoutX(210);
		putInfo.setLayoutY(160);
		
		//新建布局储存所有控件
		AnchorPane total = new AnchorPane();
		
		//美化
		//导入背景图片
		Image backGroundPicture = new Image("D:\\Java\\BoxPushing\\Picture\\BackGround\\18.jpg");
		ImageView showBGP = new ImageView(backGroundPicture);

		showBGP.setLayoutX(-500);

		showBGP.setFitWidth(1244.444);
		showBGP.setFitHeight(720);
		
		total.getChildren().add(showBGP);
		
		total.getChildren().addAll(topWords, putInfo);
		
		//新建场景与布局
		Scene scene = new Scene(total, 600, 700);
		stage.setScene(scene);
		
		//设置该舞台打开时不能使用其它窗口
		stage.initModality(Modality.APPLICATION_MODAL);
		
		stage.show();
	}
}
