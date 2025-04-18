package fileWork;

import java.io.BufferedReader;
import menu.*;
import Interface.*;
import fileWork.*;
import boxPushing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Read {
	//基本属性定义
	//读入关卡的难度
	private String inputLevel;
	//选择第几个关卡
	private String whichLevel;
	//读取的地图大小
	private int [] finalMapSize = new int [2];
	//读取的胜利点的个数
	private int victoryPointNum;
	//读取的胜利点的坐标
	private int [] victoryPointLocation;
	//读取的建议步数
	private int adviseSteps;
	//输出主地图变量
	private int [] [] finalMapLocation;
	//返回存档内的步数
	private int steps;
	
	//设定mainRead没被调用前的读取的关卡难度方法
	public String getLevelBigin() {
		//读取关卡难度
		String getLevel = "D:\\Java\\BoxPushing\\playerChoose.txt";
		BufferedReader bRLevel = null;
		try {
			bRLevel = new BufferedReader(new FileReader(getLevel));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		 try {
			this.inputLevel = bRLevel.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return inputLevel; 
	}
	
	
	//返回读取的关卡
	public String getWhichLevel () {
		return whichLevel;
	}
	
	//返回读取关卡难度的普通方法
	public String getLevel() {
		return inputLevel; 
	}
	
	//返回读取的关卡的步数的方法
	public int getSteps () {
		return steps;
	}
	
	//返回地图大小方法
	public int [] getMapSize() {
		return finalMapSize;
	}
	
	//返回胜利点个数方法
	public int getVictoryPointNum (){
		return victoryPointNum;
	}
	
	//返回胜利点方法
	public int [] getVictoryPoint () {
		return victoryPointLocation;
	}
	
	//返回建议步数方法
	public int getAdviseSteps () {
		return adviseSteps;
	}
	
	//返回主地图方法
	public int [] [] getFinalMapLocation () {
		return finalMapLocation;
	}
	
	//设定读取文件内容并拆分内容的方法
	public void mainRead () throws IOException {
		//新建读入要读入的地图的位置文件
		String getLevel = "D:\\Java\\BoxPushing\\playerChoose.txt";
		String getWhichLevel = "D:\\Java\\BoxPushing\\playerChooseLevel.txt";
		
		//新建读入地图路径的实例
		BufferedReader bRLevel = new BufferedReader(new FileReader(getLevel));
		BufferedReader bRWhichLevel = new BufferedReader(new FileReader(getWhichLevel));
		//读入玩家选择的关卡难度
		//初始化
		inputLevel = null;
		whichLevel = null;
		 this.inputLevel = bRLevel.readLine();//难度
		 this.whichLevel = bRWhichLevel.readLine();//关卡
		
		//新建读取地图文件的路径
		String inputLocation = "D:\\Java\\BoxPushing\\Map\\OffcialMap\\" + inputLevel + "\\Level" + whichLevel +".txt";

		//新建读取地图文件的实例
		BufferedReader bRMap = new BufferedReader(new FileReader(inputLocation));
		
		//确认读入的关卡难度设置正确
		//确认地图难度变量
		String confirmLevel;
		confirmLevel = bRMap.readLine();
		if(!confirmLevel.equals("E") && inputLevel.equals("Easy")) {
			System.out.println("关卡文件读入错误！请检查关卡难度设置！");
			System.exit(0);
		}
		else if(!confirmLevel.equals("M") && inputLevel.equals("Medium")) {
			System.out.println("关卡文件读入错误！请检查关卡难度设置！");
			System.exit(0);
		}
		else if(!confirmLevel.equals("H") && inputLevel.equals("Hard")) {
			System.out.println("关卡文件读入错误！请检查关卡难度设置！");
			System.exit(0);
		}
		
		//读入地图大小
		//拆分出地图大小变量
		String mapSize;
		mapSize = bRMap.readLine();
		String [] size = mapSize.split(",");
		finalMapSize[0] = Integer.parseInt(size[0]);
		finalMapSize[1] = Integer.parseInt(size[1]);
		
		//读入胜利点坐标
		//拆分出胜利点坐标变量
		String victoryPoint;//读入
		victoryPoint = bRMap.readLine();
		String [] getFirst;
		//检查没有峰号，即只有一个胜利点的情况
		if(victoryPoint.indexOf(";") != -1) {
			getFirst = victoryPoint.split(";");//由峰号分隔整体胜利点
		}
		else {
			getFirst = new String [1];
			getFirst[0] = victoryPoint;
		}
		
		victoryPointNum = getFirst.length;
		
		String [][] getSceond = new String [getFirst.length][2];//横纵坐标是切割出来总坐标集的2倍
		for(int i = 0; i < getFirst.length; i++) {
			getSceond[i] = getFirst[i].split(",");//由逗号分隔出横纵坐标
		}
		//将胜利点坐标赋出
		victoryPointLocation = new int [2 * getFirst.length];//设定大小
		//赋值
		int temp = 0;//用于胜利点坐标的下标
		for(int i = 0; i < getFirst.length; i++) {
			victoryPointLocation[temp] =  Integer.parseInt(getSceond[i][0]);
			victoryPointLocation[temp + 1] = Integer.parseInt(getSceond[i][1]);
			
			temp += 2;
		}
		
		//将建议步数读出的方法
		String getAdviseSteps = bRMap.readLine();
		adviseSteps = Integer.parseInt(getAdviseSteps);
		
		//拆分主地图的方法
		//拆分出主地图变量
		String [] mainMap = new String [finalMapSize[0]];
		//读入每一行
		for(int i = 0; i < finalMapSize[0]; i++) {
			mainMap[i] = bRMap.readLine();
		}
		finalMapLocation = new int [finalMapSize[0]][finalMapSize[1]];

		//分割出每个数字
		for(int i = 0; i < finalMapSize[0]; i++) {
			for(int j =0; j < finalMapSize[1]; j++) {
					finalMapLocation[i][j] = Integer.parseInt(mainMap[i].substring(j, j + 1));
			}
		}
	}
	
	//读入玩家存档的方法
	public void loadSave(String name,String where) throws IOException {
		File Save = new File("D:\\Java\\BoxPushing\\Save\\" + where + "\\" + name);
		BufferedReader read = new BufferedReader(new FileReader(Save));
		
		//读取哪个难度
		String level = null;
		level = read.readLine();
		
		//读取哪个关卡
		String whichLevel = null;
		whichLevel = read.readLine();
		
		//读取地图大小
		String sizeSave = read.readLine();
		
		//从地图存档中读取原地图数据
		File inintialMap = new File("D:\\Java\\BoxPushing\\Map\\OffcialMap\\" + level + "\\Level" + whichLevel + ".txt");
		BufferedReader readInitMap = new BufferedReader(new FileReader(inintialMap));
		
		//确认地图难度变量
		String confirmLevel;
		confirmLevel = readInitMap.readLine();
		
		//读取原地图大小
		String firstGet = null;
		firstGet = readInitMap.readLine();
		String [] secondGet = firstGet.split(",");
		int [] size = new int [2];//地图大小只有横纵大小
		for(int i = 0; i < 2; i++) {
			size[i] = Integer.parseInt(secondGet[i]);
		}
		finalMapSize[0] = size[0];
		finalMapSize[1] = size[1];
		
		//读取玩家当前步数
		String inSteps =null;
		inSteps = read.readLine();
		steps = Integer.parseInt(inSteps);
		
		//读入胜利点坐标
		//拆分出胜利点坐标变量
		String victoryPoint;//读入
		victoryPoint = readInitMap.readLine();
		String [] getFirst;
		//检查没有峰号，即只有一个胜利点的情况
		if(victoryPoint.indexOf(";") != -1) {
			getFirst = victoryPoint.split(";");//由峰号分隔整体胜利点
		}
		else {
			getFirst = new String [1];
			getFirst[0] = victoryPoint;
		}
		
		victoryPointNum = getFirst.length;
		
		String [][] getSceond = new String [getFirst.length][2];//横纵坐标是切割出来总坐标集的2倍
		for(int i = 0; i < getFirst.length; i++) {
			getSceond[i] = getFirst[i].split(",");//由逗号分隔出横纵坐标
		}
		//将胜利点坐标赋出
		victoryPointLocation = new int [2 * getFirst.length];//设定大小
		//赋值
		int temp = 0;//用于胜利点坐标的下标
		for(int i = 0; i < getFirst.length; i++) {
			victoryPointLocation[temp] =  Integer.parseInt(getSceond[i][0]);
			victoryPointLocation[temp + 1] = Integer.parseInt(getSceond[i][1]);
			
			temp += 2;
		}
		
		//将建议步数读出的方法
		String getAdviseSteps = readInitMap.readLine();
		adviseSteps = Integer.parseInt(getAdviseSteps);
		
		//读取玩家修改过的地图
		String [] firstMap = new String [size[0]];
		int [] [] saveMapLocation;
		//读入每一行
		for(int i = 0; i < size[0]; i++) {
			firstMap[i] = read.readLine();
		}
		saveMapLocation = new int [size[0]][size[1]];

		//分割出每个数字
		for(int i = 0; i < size[0]; i++) {
			for(int j =0; j < size[1]; j++) {
				saveMapLocation[i][j] = Integer.parseInt(firstMap[i].substring(j, j + 1));
			}
		}
		
		//将值赋出
		this.inputLevel = level;
		this.whichLevel = whichLevel;
		this.finalMapLocation = saveMapLocation;
	}
	
	//读入自动存档的方法
	public void loadAutoSave() throws IOException {
		File Save = new File("D:\\Java\\BoxPushing\\Save\\AutoSave\\AutoSave.txt");
		BufferedReader read = new BufferedReader(new FileReader(Save));
		
		//读取哪个难度
		String level = null;
		level = read.readLine();
		
		//读取哪个关卡
		String whichLevel = null;
		whichLevel = read.readLine();
		
		//读取地图大小
		String firstGet = null;
		firstGet = read.readLine();
		String [] secondGet = firstGet.split(",");
		int [] size = new int [2];//地图大小只有横纵大小
		for(int i = 0; i < 2; i++) {
			size[i] = Integer.parseInt(secondGet[i]);
		}
		
		//读取玩家修改过的地图
		String [] firstMap = new String [size[0]];
		int [] [] saveMapLocation;
		//读入每一行
		for(int i = 0; i < size[0]; i++) {
			firstMap[i] = read.readLine();
		}
		saveMapLocation = new int [size[0]][size[1]];

		//分割出每个数字
		for(int i = 0; i < size[0]; i++) {
			for(int j =0; j < size[1]; j++) {
				saveMapLocation[i][j] = Integer.parseInt(firstMap[i].substring(j, j + 1));
			}
		}
		
		//将值赋出
		this.inputLevel = level;
		this.whichLevel = whichLevel;
		this.finalMapLocation = saveMapLocation;
	}
}
