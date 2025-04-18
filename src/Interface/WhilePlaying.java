package Interface;

import java.io.IOException;
import menu.*;
import fileWork.*;
import boxPushing.*;
import java.util.ArrayList;
import menu.*;
import fileWork.*;
import boxPushing.*;

import fileWork.Read;

public class WhilePlaying {
	//基本属性定义
	//设置得到的难度
	private String Level;
	//设置得到的关卡数
	private String whichLevel;
	//设置得到的关卡地图变量
	public int [] [] getMap;
	//设置得到的胜利点的数量
	private int victoryPointNum;
	//使用动态数组实时记录玩家当前关卡的移动步骤
	private ArrayList<String>playerSteps = new ArrayList<String>();//默认为0，动态自动增加
	//新建数组实时记录玩家位置
	private int [] playerLocation = new int [2];
	private int [][] boxLocation = new int [victoryPointNum] [2];//箱子个数与胜利点个数一致
	
	//设置得到难度的方法
	public void setLevel(String setLevel) {
		Level = setLevel;
	}
	
	//设置得到关卡数的方法
	public void setWhichLevel(String getWhichLevel) {
		whichLevel = getWhichLevel;
	}
	
	//返回关卡数方法
	public String getWhichLevel() {
		return whichLevel;
	}
	
	//设置当前后端的地图，用于读取存档刷新后端
	public void setSaveMap(int [] [] saveMap) {
		getMap = saveMap;
	}
	
	//设置返回玩家当前地图方法
	public int [] [] getAlterMap() {
		return getMap;
	}
	
	//在构造方法中得到地图数据
	public WhilePlaying() {
		//设置读入方法
		Read r = new Read();
		
		//读入总体文件数据
		try {
			r.mainRead();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//设置本关胜利点数量
		victoryPointNum = r.getVictoryPointNum();
		
		//得到总地图数据
		this.getMap = r.getFinalMapLocation();
	}
	
	//设置得到玩家当前坐标的方法
	public int [] getPlayerLocation () {
		return playerLocation;
	}
	
	//判断是否能移动方法，用0表示无法走动，1表示推箱子无法走动，2表示普通走动，3表示推箱子走动,5表示错误
	public int isMoveAble (char move) {
//		System.out.println("当前后端地图为：");
//		for(int i = 0; i < getMap.length; i++) {
//			for(int j = 0; j < getMap[0].length; j++) {
//				System.out.print(getMap[i][j]);
//			}
//			System.out.println();
//		}
		
		//使用for循环查找当前玩家和箱子位置
		for(int i = 0; i < getMap.length; i++) {
			for(int j = 0; j < getMap[0].length; j++) {
				if(getMap[i][j] == 2) {
					playerLocation[0] = i;
					playerLocation[1] = j;
				}
			}
		}
		
		//判断前端输入的值并返回移动方式
		switch (move) {
		
		case('a'): {
			//左移
			// 有箱子时
			if(getMap[playerLocation[0]][playerLocation[1] - 1] == 3) {
				if(getMap[playerLocation[0]][playerLocation[1] - 2] == 0) {
					//记录玩家有箱子移动
					playerSteps.add("a1");
					
					//原位置玩家移动消失
					getMap[playerLocation[0]][playerLocation[1]] = 0;
					//新位置移动出现玩家并覆盖掉原箱子位置
					getMap[playerLocation[0]][playerLocation[1] - 1] = 2;
					
					//新位置移动出现箱子
					getMap[playerLocation[0]][playerLocation[1] - 2] = 3;
					
					return 3;
				}
				else
					return 1;
			}
			//无箱子时
			else if(getMap[playerLocation[0]][playerLocation[1] - 1] == 1) {
				return 0;
			}
			else if (getMap[playerLocation[0]][playerLocation[1] -1] == 0) {
				//记录玩家无箱子移动
				playerSteps.add("a2");
				
				//原位置玩家消失
				getMap[playerLocation[0]][playerLocation[1]] = 0;
				//新位置玩家出现
				getMap[playerLocation[0]][playerLocation[1] - 1] = 2;
				
				return 2;
			}	
		}
		case('d') :{
			//右移
			//有箱子时
			if(getMap[playerLocation[0]][playerLocation[1] + 1] == 3) {
				if(getMap[playerLocation[0]][playerLocation[1] + 2] == 0) {
					//记录玩家有箱子移动
					playerSteps.add("d1");
					
					//原位置玩家移动消失
					getMap[playerLocation[0]][playerLocation[1]] = 0;
					//新位置移动出现玩家并覆盖掉原箱子位置
					getMap[playerLocation[0]][playerLocation[1] + 1] = 2;
					
					//新位置移动出现箱子
					getMap[playerLocation[0]][playerLocation[1] + 2] = 3;
					
					return 3;
				}
				else
					return 1;
			}
			//无箱子时
			else if(getMap[playerLocation[0]][playerLocation[1] + 1] == 1) {
				return 0;
			}
			else if (getMap[playerLocation[0]][playerLocation[1] +1] == 0) {
				//记录玩家无箱子移动
				playerSteps.add("d2");
				
				//原位置玩家消失
				getMap[playerLocation[0]][playerLocation[1]] = 0;
				//新位置玩家出现
				getMap[playerLocation[0]][playerLocation[1] + 1] = 2;
				
				return 2;
			}
		}
		case('s'): {
			//下移
			//有箱子时
			if(getMap[playerLocation[0] + 1][playerLocation[1]] == 3) {
				if(getMap[playerLocation[0] + 2][playerLocation[1]] == 0) {
					//记录玩家有箱子移动
					playerSteps.add("s1");
					
					//原位置玩家移动消失
					getMap[playerLocation[0]][playerLocation[1]] = 0;
					//新位置移动出现玩家并覆盖掉原箱子位置
					getMap[playerLocation[0] + 1][playerLocation[1]] = 2;
					
					//新位置移动出现箱子
					getMap[playerLocation[0] + 2][playerLocation[1]] = 3;
					
					return 3;
				}
				else 
					return 1;
			}
			//无箱子时
			else if(getMap[playerLocation[0] + 1][playerLocation[1]] == 1) {
				return 0;
			}
			else if (getMap[playerLocation[0] + 1][playerLocation[1]] == 0) {
				//记录玩家无箱子移动
				playerSteps.add("s2");
				
				//原位置玩家消失
				getMap[playerLocation[0]][playerLocation[1]] = 0;
				//新位置玩家出现
				getMap[playerLocation[0] + 1][playerLocation[1]] = 2;
				
				return 2;
			}
		}
		case('w'): {
			//上移
			//有箱子时
			if(getMap[playerLocation[0] - 1][playerLocation[1]] == 3) {
				if(getMap[playerLocation[0] - 2][playerLocation[1]] == 0) {
					//记录玩家有箱子移动
					playerSteps.add("w1");
					
					//原位置玩家移动消失
					getMap[playerLocation[0]][playerLocation[1]] = 0;
					//新位置移动出现玩家并覆盖掉原箱子位置
					getMap[playerLocation[0] - 1][playerLocation[1]] = 2;
					
					//新位置移动出现箱子
					getMap[playerLocation[0] - 2][playerLocation[1]] = 3;
					
					return 3;
				}
				else 
					return 1;
			}
			//无箱子时
			else if(getMap[playerLocation[0] - 1][playerLocation[1]] == 1) {
				return 0;
			}
			else if (getMap[playerLocation[0] - 1][playerLocation[1]] == 0) {
				//记录玩家无箱子移动
				playerSteps.add("w2");
				
				//原位置玩家消失
				getMap[playerLocation[0]][playerLocation[1]] = 0;
				//新位置玩家出现
				getMap[playerLocation[0] - 1][playerLocation[1]] = 2;
				return 2;
			
			}
		}
			default:
				return 5;//默认输入	
		}
	}
	
	//胜利判定方法
		public boolean isSucess(int [] victroyPoint) {
			//确定需要多少箱子
			int boxNum = 0;
			//使用变量储存已经到达位置的箱子
			int alreadySucessBox = 0;
			
			//使用for循环遍历有多少箱子
			for(int i = 0; i < getMap.length; i++) {
				for(int j = 0; j < getMap[0].length; j++) {
					if(getMap [i] [j] == 3) {
						boxNum++;//记录出现的箱子次数
					}
				}
			}
			
			//使用额外一个变量来储存第几个胜利点判断
			int whichPoint = 0;
			// 用for循环判断”所有“箱子是否到达胜利点
			for(int i = 0; i < getMap.length; i++) {
				for(int j = 0; j < getMap[0].length; j++) {
					//必须在横纵坐标都相等的情况下才能判定成功
					if(getMap[i][j] == 3) {
						if(victroyPoint[whichPoint] == i) {
							if(victroyPoint[whichPoint + 1] == j) {
								alreadySucessBox++;
								whichPoint += 2;//判定完一个胜利点则切换下一个胜利点
							}
						}
					}
				}
			}
			//重置判断的数，使当前判断不影响下一次判断
			whichPoint = 0;
			
			//当所有箱子都到达胜利点后返回成功,否则返回失败
			if(alreadySucessBox == boxNum)
				return true;
			else {
				alreadySucessBox = 0;//初始化
				return false;
			}
				
		}
		
		//记录撤回多少位
		int time = 0;	
		//后端撤回事件应对方法
		public String backLostStep() {	
			//增加倒数次数
			time = 1;

			//放置数组下标越界
			if(playerSteps.size() - time < 0) {
				//到最后一步重置倒数次数
				time = 0;
				//用f表示已到最后一位
				return "f";
			}	
			
			//使用for循环查找当前玩家和箱子位置
			for(int i = 0; i < getMap.length; i++) {
				for(int j = 0; j < getMap[0].length; j++) {
					if(getMap[i][j] == 2) {
						playerLocation[0] = i;
						playerLocation[1] = j;
					}
				}
			}
			
			//从数组最后一位开始读取
			String lostStep = playerSteps.get(playerSteps.size() - time);
			
			//设置返回如何移动
			//用equals比较值的内容
			if(lostStep.equals("a1")) {
				//撤回后删除原记录的用户输入
				playerSteps.remove(playerSteps.size() - time);
				
				//刷新后端
				//刷新玩家
				getMap[playerLocation[0]][playerLocation[1]] = 0;
				getMap[playerLocation[0]][playerLocation[1] + 1] = 2;
				
				//刷新箱子
				getMap[playerLocation[0]][playerLocation[1] - 1] = 0;
				getMap[playerLocation[0]][playerLocation[1]] = 3;
				
				return "d1";
			}
			else if(lostStep.equals("a2")) {
				playerSteps.remove(playerSteps.size() - time);
				
				//刷新后端
				getMap[playerLocation[0]][playerLocation[1]] = 0;
				getMap[playerLocation[0]][playerLocation[1] + 1] = 2;

				return "d2";
			}
				
			if(lostStep.equals("d1")) {
				playerSteps.remove(playerSteps.size() - time);
				
				//刷新后端
				getMap[playerLocation[0]][playerLocation[1]] = 0;
				getMap[playerLocation[0]][playerLocation[1] - 1] = 2;
				
				//刷新箱子
				getMap[playerLocation[0]][playerLocation[1] + 1] = 0;
				getMap[playerLocation[0]][playerLocation[1]] = 3;
				
				return "a1";
			}
			else if(lostStep.equals("d2")) {
				playerSteps.remove(playerSteps.size() - time);
				
				//刷新后端
				getMap[playerLocation[0]][playerLocation[1]] = 0;
				getMap[playerLocation[0]][playerLocation[1] - 1] = 2;

				return "a2";
			}
			
			if(lostStep.equals("w1")) {
				playerSteps.remove(playerSteps.size() - time);
				
				//刷新后端
				getMap[playerLocation[0]][playerLocation[1]] = 0;
				getMap[playerLocation[0 + 1]][playerLocation[1]] = 2;
				
				//刷新箱子
				getMap[playerLocation[0] - 1][playerLocation[1]] = 0;
				getMap[playerLocation[0]][playerLocation[1]] = 3;
				
				return "s1";
			}
			else if(lostStep.equals("w2")) {
				playerSteps.remove(playerSteps.size() - time);
				
				//刷新后端
				getMap[playerLocation[0]][playerLocation[1]] = 0;
				getMap[playerLocation[0] + 1][playerLocation[1]] = 2;

				return "s2";
			}
			
			if(lostStep.equals("s1")) {
				playerSteps.remove(playerSteps.size() - time);
				
				//刷新后端
				getMap[playerLocation[0]][playerLocation[1]] = 0;
				getMap[playerLocation[0] - 1][playerLocation[1]] = 2;
				
				//刷新箱子
				getMap[playerLocation[0] + 1][playerLocation[1]] = 0;
				getMap[playerLocation[0]][playerLocation[1]] = 3;
				
				return "w1";
			}
			else if(lostStep.equals("s2")) {
				playerSteps.remove(playerSteps.size() - time);
				
				//刷新后端
				getMap[playerLocation[0]][playerLocation[1]] = 0;
				getMap[playerLocation[0] - 1][playerLocation[1]] = 2;
				
				return "w2";
			}
			
			//没有对应的返回无
			return "f";
		}
}