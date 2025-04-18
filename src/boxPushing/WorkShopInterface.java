package boxPushing;

import saveIO.SaveSelfEdit;
import saveIO.Write;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.UnaryOperator;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WorkShopInterface extends Application{

	@Override
	public void start(Stage stage) throws FileNotFoundException{
		//定义窗口基本属性
		stage.setTitle("自定地图");
		int len = StageManager.MAP.get("len");
		int wid = StageManager.MAP.get("wid");
		//文字提示内容
		Text tipWords = new Text( 150, 220,"创意工坊，使用上面四个按钮并点击下方操作区方块实现创建地图");
		tipWords.setFont(new Font("KaiTi", 24));
		
		//墙
		FileInputStream inputstream = new FileInputStream(new File("C:\\Users\\Fake\\Desktop\\imgs\\SmallPicture\\wall.png"));
		javafx.scene.image.Image imageWall = new javafx.scene.image.Image(inputstream, 1200 / len , 700 / wid , false , false); //读入图片
		
		//箱子
		FileInputStream inputstream1 = new FileInputStream(new File("C:\\Users\\Fake\\Desktop\\imgs\\SmallPicture\\box1.png"));
		javafx.scene.image.Image imageBox = new javafx.scene.image.Image(inputstream1, 1200 / len , 700 / wid , false , false); //读入图片
		
		//胜利点
		FileInputStream inputstream2 = new FileInputStream(new File("C:\\Users\\Fake\\Desktop\\imgs\\SmallPicture\\flag.png"));
		javafx.scene.image.Image imagePlayer = new javafx.scene.image.Image(inputstream2, 1200 / len , 700 / wid , false , false); //读入图片
		
		//玩家
		FileInputStream inputstream3 = new FileInputStream(new File("C:\\Users\\Fake\\Desktop\\imgs\\SmallPicture\\playerRight2"));
		javafx.scene.image.Image imageVic = new javafx.scene.image.Image(inputstream3, 1200 / len , 700 / wid , false , false); //读入图片
		
		GridPane obj = new GridPane();//创建了存放图片和按钮的布局
		Button[] bu = new Button[ wid * len ];
		obj.setLayoutX(0);
		obj.setLayoutY(140);//放置相应区域
		SaveSelfEdit sa = new SaveSelfEdit( len , wid );//创建后端读入地图
		String path = "D:\\Java\\BoxPushing";//指定目录
		int label = 1;
		sa.inLabel(label);//为墙创建标签
		for( int i = 0 ; i < wid * len  ; i++ ) {
			bu[i] = new Button();
			bu[i].setPrefWidth( 1200 / len );
			bu[i].setPrefHeight( 700 / wid );//Button初始化
			if( i / len == 0 || i % len  == 0 || i % len == len - 1  || i / len == wid - 1 ) {
				sa.saveMap( i % len  , i / len );
				BackgroundImage buback = new BackgroundImage( imageWall , null , null ,null , null );
				bu[i].setBackground(new Background( buback ));
			}
			obj.add( bu[i] , i % len , i / len );//添加到组件
		}

		//保存与加载预设按钮
		Button save = new Button("保存预设");
		save.setFont(new Font("KaiTi", 24));
		
		Button load = new Button("加载预设");
		load.setFont(new Font("KaiTi", 24));
		
		//返回创意工坊首页与主菜单按钮
		Button backMM = new Button("主菜单");
		backMM.setFont(new Font("KaiTi", 24));
		
		Button backWSM = new Button("创意工坊首页");
		backWSM.setFont(new Font("KaiTi", 24));
		
		//右侧重置与与完成按钮
		Button finish = new Button("完成");
		finish.setFont(new Font("KaiTi", 24));
		finish.setOnMouseClicked (new EventHandler<Event>() {
				public void handle(Event arg0) {
					Stage dialog = new Stage();
					dialog.setTitle("提示");
					dialog.initModality(Modality.APPLICATION_MODAL); 
					dialog.initOwner(stage);
			       
					Button confirm = new Button ("确认");//查询是否有重名的，有提示重新输入，无则创建文件
					StringBuffer storeMapName = new StringBuffer();
					confirm.setOnMouseClicked(e2 -> {//创建一个提示窗口提示玩家新建存档
				   	if( !sa.alignmentOfObj() ) {
				   		dialog.close();
				   		atteWindow(stage , "玩家、箱子、胜利点数量不匹配");
				   	}
				   	else {
				   		dialog.close();
				   		Stage saveMap = new Stage();
				   		saveMap.setTitle("新建存档");
				   		saveMap.initModality(Modality.APPLICATION_MODAL); 
				   		saveMap.initOwner(stage);
						
						Button cancel2 = new Button("取消");
						cancel2.setOnMouseClicked(e1 -> {
							saveMap.close();
						});	
						
						TextField mapName = new TextField();
						mapName.setTextFormatter(new TextFormatter<String>(new UnaryOperator<TextFormatter.Change>() {
				            @Override
				            public TextFormatter.Change apply( TextFormatter.Change t) {
				            String value = t.getText();//获取文本
				             	if (value.matches("^[A-Za-z0-9-]{0,20}$")){
				             		storeMapName.append(value);
				             		return t;//限制用户输入使之为数字英文字母还有-
				             	}
				             	else {
				            	 return null;
				             	}
				            }
				        }));
						mapName.setPromptText("请输入你的文件名（只允许英文字母和-）");
						
						Button confirm2 = new Button("确认");
						confirm2.setOnMouseClicked(e3 -> {
							if( !(storeMapName.length() > 0)) {
								//提醒文件名不能为空
							}
						   	else if(  new File( path , storeMapName.toString() + ".txt" ).exists()) {
						   		//查询到有重复的提醒，询问是否覆盖
						   		dialog.close();//创建一个提示窗口
								Stage error1 = new Stage();
								error1.setTitle("错误");
								error1.initModality(Modality.APPLICATION_MODAL); 
								error1.initOwner(stage);
								
								Button cancel1 = new Button ("取消");//退出提示界面
								cancel1.setLayoutX(250);
								cancel1.setLayoutY(160);
								cancel1.setOnMouseClicked( e4 -> {
									error1.close();
								});
								
								Button confirm1 = new Button("确认");
								confirm1.setLayoutX(200);
								confirm1.setLayoutY(160);
								confirm1.setOnMouseClicked(e5 -> {
									File dp = new File( path , storeMapName.toString() + ".txt");//根据用户输入创建文件（放在默认目录下）
									Write file = new Write();//创建文件读出流
									file.getSave(sa.getMap());
									file.outPutUserMap(dp);
									error1.close();
								});
								
								VBox error1Vbox = new VBox(new Text("存在同名文件，是否选择覆盖"));
								error1Vbox.setLayoutX(60);
								error1Vbox.setLayoutY(70);
						    
								AnchorPane total = new AnchorPane();
								total.getChildren().addAll( error1Vbox , confirm1 , cancel1);
						    
								Scene error1Scene = new Scene( total , 300, 200);
								error1.setScene(error1Scene);
								error1.show();
						   	}
							else{
								File dp = new File( path , storeMapName.toString() + ".txt");//根据用户输入创建文件（放在默认目录下）
								Write file = new Write();//创建文件读出流
								file.getSave(sa.getMap());
								file.outPutUserMap(dp);
								dialog.close();//TO-DO create the attention window
								atteWindow( stage , "恭喜，文件创建成功");
							}
						});	

						HBox putBtn = new HBox(20);
						putBtn.getChildren().addAll(confirm2, cancel2 );
						putBtn.setLayoutX(650);
						putBtn.setLayoutY(400);
						
						AnchorPane total = new AnchorPane();
						total.getChildren().addAll( putBtn , mapName );
						confirm2.setLayoutX(250);
						confirm2.setLayoutY(160);
							                  
						Scene Scene = new Scene( total , 800 ,450 );
						saveMap.setScene(Scene);
						saveMap.show();
				   	}
				   	});
					
					Button cancel = new Button("取消");
			   		cancel.setOnMouseClicked(e2 -> {
			   		File dp = new File( path , storeMapName.toString() + ".txt");
			   		dp.delete();
			   		dialog.close();
			   		});
			   		
			   		HBox putBtn = new HBox(20);
					putBtn.getChildren().addAll(confirm, cancel);
					putBtn.setLayoutX(180);
					putBtn.setLayoutY(160);
					
			       VBox dialogVbox = new VBox(new Text("是否要导出存档") );
			       dialogVbox.setLayoutX(110);
			       dialogVbox.setLayoutY(70);
			       
			       AnchorPane total = new AnchorPane();
			       total.getChildren().addAll( dialogVbox , putBtn );
			       
			       Scene dialogScene = new Scene( total , 300, 200);
			       dialog.setScene(dialogScene);
			       dialog.show();
			   }
		});
		
		
		Button wall = new Button("墙");
		wall.setFont(new Font("KaiTi", 20));
		wall.setOnMouseClicked (new EventHandler<Event>() {
			public void handle(Event arg0) {
				int label = 1;
				sa.inLabel(label);
				wall.setTextFill(Color.RED);
				for( int i = 0 ; i < wid * len  ; i++ ) {
					final Integer inneri = new Integer(i);
					bu[i].setOnMouseClicked(new EventHandler<Event>() {//为每个墙创建一个监听器
						public void handle(Event arg0) {
							if( sa.getMap()[ inneri.intValue() / len ][ inneri.intValue() % len ] == 0 ) {
								sa.saveMap( inneri.intValue() % len , inneri.intValue() / len );
								BackgroundImage buback = new BackgroundImage( imageWall , null , null ,null , null );
								bu[inneri.intValue()].setBackground(new Background( buback ));
							}	
						}
					});
				}
			}
		});
		
		
		Button box = new Button("箱子");
		box.setFont(new Font("KaiTi", 20));
		box.setOnMouseClicked (new EventHandler<Event>() {
			public void handle(Event arg0) {
				int label = 2;
				sa.inLabel(label);
				box.setTextFill(Color.RED);
				for( int i = 0 ; i < wid * len  ; i++ ) {
					final Integer inneri = new Integer(i);
					bu[i].setOnMouseClicked(new EventHandler<Event>() {//为每个箱子创建一个监听器
						public void handle(Event arg0) {
								if( sa.getMap()[ inneri.intValue() / len ][ inneri.intValue() % len ] == 0 || sa.getMap()[ inneri.intValue() / len ][ inneri.intValue() % len ] == 3){
									if(sa.getMap()[ inneri.intValue() / len ][ inneri.intValue() % len ] == 3 ) {
										sa.saveMap( inneri.intValue() % len , inneri.intValue() / len , 3 );
									}
									else {
										sa.saveMap( inneri.intValue() % len , inneri.intValue() / len  );
									}
										BackgroundImage buback = new BackgroundImage( imageBox , null , null ,null , null );
										bu[inneri.intValue()].setBackground(new Background( buback ));
									}
						}
					});
				}
			}
		});
		
		Button victoryPoint = new Button("胜利点");
		victoryPoint.setFont(new Font("KaiTi", 20));
		victoryPoint.setOnMouseClicked (new EventHandler<Event>() {
			public void handle(Event arg0) {
				int label = 3;
				sa.inLabel(label);
				victoryPoint.setTextFill(Color.RED);
				for( int i = 0 ; i < wid * len  ; i++ ) {
					final Integer inneri = new Integer(i);
					bu[i].setOnMouseClicked(new EventHandler<Event>() {//为每个胜利点创建一个监听器
						public void handle(Event arg0) {
							if( sa.getMap()[ inneri.intValue() / len ][ inneri.intValue() % len ] == 0 ||  sa.getMap()[ inneri.intValue() / len ][ inneri.intValue() % len ] == 2 ||  sa.getMap()[ inneri.intValue() / len][ inneri.intValue() % len ] == 4 ) {
								if( sa.getMap()[ inneri.intValue() / len ][ inneri.intValue() % len ] == 2) {
									sa.saveMap( inneri.intValue() % len , inneri.intValue() / len ,  2 );
								}
								else if( sa.getMap()[ inneri.intValue() / len ][ inneri.intValue() % len ] == 4) {
									sa.saveMap( inneri.intValue() % len , inneri.intValue() / len  , 4 );
								}
								else {
									sa.saveMap( inneri.intValue() % len , inneri.intValue() / len );
								}
								BackgroundImage buback = new BackgroundImage( imageVic , null , null ,null , null );
								bu[inneri.intValue()].setBackground(new Background( buback ));
								}
						}
					});
				}
			}
		});
		
		Button bornPoint = new Button("出生点");
		bornPoint.setFont(new Font("KaiTi", 20));
		bornPoint.setOnMouseClicked (new EventHandler<Event>() {
			public void handle(Event arg0) {
				int label = 4;
				sa.inLabel(label);
				bornPoint.setTextFill(Color.RED);
				for( int i = 0 ; i < wid * len  ; i++ ) {
					final Integer inneri = new Integer(i);
					bu[i].setOnMouseClicked(new EventHandler<Event>() {//为每个出生点创建一个监听器
					public void handle(Event arg0) {
						if( sa.getMap()[ inneri.intValue() / len ][ inneri.intValue() % len ] == 0  ||  sa.getMap()[ inneri.intValue() / len ][ inneri.intValue() % len ] == 3) {
							if( sa.getMap()[ inneri.intValue() / len ][ inneri.intValue() % len ] == 3) {
								sa.saveMap( inneri.intValue() % len  , inneri.intValue() / len  , 3 );
							}
							else {
								sa.saveMap( inneri.intValue() % len , inneri.intValue() / len );
							}
							BackgroundImage buback = new BackgroundImage( imagePlayer , null , null ,null , null );
							bu[inneri.intValue()].setBackground(new Background( buback ));
							}
						}
					});
				}
			}
		});
		
		//新建放置控件的布局
		Button reload = new Button("重置");
		reload.setFont(new Font("KaiTi", 24));
		reload.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				sa.initMap();//初始化地图
				for( int i = 0 ; i < wid * len  ; i++ ) {
					sa.inLabel(1);
					bu[i] = new Button();
					bu[i].setPrefWidth( 1200 / len );
					bu[i].setPrefHeight( 700 / wid );//Button初始化
					if( i / len == 0 || i % len  == 0 || i % len == len - 1 || i / len == wid - 1) {
						sa.saveMap( i % len  , i / len );
						BackgroundImage buback = new BackgroundImage( imageWall , null , null ,null , null );
						bu[i].setBackground(new Background( buback ));
					}
					obj.add( bu[i] , i % len, i / len );//添加到组件
				}
				for( int i = 0 ; i < sa.getMap().length ; i++ ) {
					for( int j = 0 ; j < sa.getMap()[0].length ; j++ ) {
						System.out.print( sa.getMap()[i][j] + " ");
					}
				System.out.println();
			}
			}
		});
		
		//返回主菜单与创意工坊首页
			HBox back = new HBox(20);
			back.getChildren().addAll(backMM, backWSM);
			back.setLayoutX(720);
			back.setLayoutY(80);
				
		//保存与加载
			HBox SL = new HBox(20);
			SL.getChildren().addAll(save, load);
			SL.setLayoutX(250);
			SL.setLayoutY(20); 
				
		//重置与完成
			VBox RF = new VBox(40);
			RF.getChildren().addAll(reload, finish);
			RF.setLayoutX(1300);
			RF.setLayoutY(250);
				
		
		//顶部四个方块种类
		HBox species = new HBox(20);
		species.getChildren().addAll(wall, box, victoryPoint, bornPoint);
		species.setLayoutX(150);
		species.setLayoutY(80);
		
		
		
		//总布局
		AnchorPane total = new AnchorPane();
		total.getChildren().addAll( species, back, SL, RF, tipWords  , obj );
//		//新建场景与舞台
		
		Scene scene = new Scene(total, 1500 , 800 );
		stage.setScene(scene);
		stage.show();//显示整个界面
	}
	public void atteWindow( Stage PrimaryStage , String TextName) {
		//创建一个提示窗口
		Stage error1 = new Stage();
		error1.setTitle("错误");
		error1.initModality(Modality.APPLICATION_MODAL); 
		error1.initOwner(PrimaryStage);
		
		Button confirm1 = new Button ("确认");//退出提示界面
		confirm1.setLayoutX(250);
		confirm1.setLayoutY(160);
		confirm1.setOnMouseClicked( e3 -> {
		error1.close();
		});
		
		VBox error1Vbox = new VBox(new Text(TextName));
		error1Vbox.setLayoutX(60);
		error1Vbox.setLayoutY(70);
    
		AnchorPane total = new AnchorPane();
		total.getChildren().addAll( error1Vbox , confirm1 );
    
		Scene error1Scene = new Scene( total , 300, 200);
		error1.setScene(error1Scene);
		error1.show();
	}
	
	public static void main(String [] args) {
		launch(args);
	}
}