package fileWork;

import java.io.File;
import menu.*;
import Interface.*;
import fileWork.*;
import boxPushing.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Write {
	//新建玩家选择文件方法
	public void setPlayerChooseLevel(int difficulty) throws IOException {
		//为游戏地图绘制设定难度
		//新建暂时文档储存玩家选择
		File file = new File("D:\\Java\\BoxPushing\\playerChoose.txt");
		if(!file.exists())//如果文件不存在则创建
			file.createNewFile();
		
		//新建写入
		FileWriter write = new FileWriter(file);
		
		//清空原文件内容
		write.write("");
		write.flush();
		
		//写入内容
		if(difficulty == 1)
			write.write("Easy\n");
		else if(difficulty == 2)
			write.write("Medium\n");
		else if(difficulty == 3)
			write.write("Hard\n");
		
		write.flush();
		write.close();
	}
	
	//新建玩家选择文件关卡方法
	public void setPlayerChooseWhichLevel(int whichLevel) throws IOException {
		//新建暂时文档储存玩家关卡选择
		File file = new File("D:\\Java\\BoxPushing\\playerChooseLevel.txt");
		if(!file.exists())//如果文件不存在则创建
			file.createNewFile();
		
		//为文档追加内容
		//新建追加写入
		FileWriter write = new FileWriter("D:\\Java\\BoxPushing\\playerChooseLevel.txt");
		
		//初始清空
		write.write("");
		write.flush();
		
		//写入内容
		if(whichLevel == 1)
			write.write("01");
		else if(whichLevel == 2)
			write.write("02");
		else if(whichLevel == 3)
			write.write("03");
		else if(whichLevel == 4)
			write.write("04");
		else if(whichLevel == 5)
			write.write("05");
		else if(whichLevel == 6)
			write.write("06");
		else if(whichLevel == 7)
			write.write("07");
		else if(whichLevel == 8)
			write.write("08");
		else if(whichLevel == 9)
			write.write("09");
		else if(whichLevel == 10)
			write.write("10");
		
		write.flush();
		write.close();
	}
	
	//新建玩家存档的方法
	public void setPlayerSave (String name, String level, String whichLevel, int [] size, int steps,int [] [] alterMap) throws IOException {
		//新建文档写入玩家存档
		File pSave = new File("D:\\Java\\BoxPushing\\Save\\PlayerSave\\" + name + ".txt");
		
		//为文档写入内容
		FileWriter write = new FileWriter(pSave);
		
		//如果不存在就新建，如果存在就覆盖
		if(pSave.exists()) {
			write.write("");
			write.flush();
		}
		else
			pSave.createNewFile();
		
		//写入哪个难度的关卡
		write.write(level + "\n");
		write.flush();
		
		//写入哪一关
		write.write(whichLevel + "\n");
		write.flush();
		
		//写入地图大小
		write.write(size[0] + "," + size[1] + "\n");
		write.flush();
		
		//写入玩家已经移动的步数
		write.write(steps + "\n");
		write.flush();
		
		//写入玩家游玩过后改变的地图
		for(int i = 0; i < alterMap.length; i++) {
			for(int j = 0; j < alterMap[0].length; j ++) {
				write.write(String.valueOf(alterMap[i][j]));
				write.flush();
			}
			write.write("\n");
			write.flush();
		}
	}
	
	//新建自动存档的方法
	public void setAutoSave (String level, String whichLevel, int [] size, int steps,int [] [] alterMap) throws IOException {
		//新建文档写入玩家存档
		File aSave = new File("D:\\Java\\BoxPushing\\Save\\AutoSave\\AutoSave.txt");
		//为文档写入内容
		FileWriter write = new FileWriter(aSave);
		
		//如果不存在就新建，如果存在就覆盖
		if(aSave.exists()) {
			write.write("");// 清空文档
			write.flush();
		}
		else
			aSave.createNewFile();
		
		//写入哪个难度的关卡
		write.write(level + "\n");
		write.flush();
		
		//写入哪一关
		write.write(whichLevel + "\n");
		write.flush();
		
		//写入地图大小
		write.write(size[0] + "," + size[1] + "\n");
		write.flush();
		
		//写入当前步数
		write.write(steps + "\n");
		write.flush();
		
		//写入玩家游玩过后改变的地图
		for(int i = 0; i < alterMap.length; i++) {
			for(int j = 0; j < alterMap[0].length; j ++) {
				write.write(String.valueOf(alterMap[i][j]));
				write.flush();
			}
			write.write("\n");
			write.flush();
		}
	}
}
