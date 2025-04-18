package testJavaFx;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ControlMapWrite {
	public static void main(String [] args) throws IOException {
		//打开输入流，由控制台新建地图
		Scanner sc = new Scanner(System.in);
	
		//每个难度写十个关卡
		System.out.print("（每个难度十个关卡）写出为第几个关卡：");
		String name = "Level" + sc.next();
			
		//写入难度 
		System.out.print("请输入难度（用Easy，Medium，Hard。简单，中等，困难）：");
		String level = sc.next();
		
		//路径总名字
		String location = "D:\\Java\\BoxPushingMap\\OffcialMap\\" + level + "\\" + name + ".txt";
		
		//打开文件
		File file = new File(location);
		//如果没有新建文件
		if(!file.exists())
			file.createNewFile();
		
		//新建写文件
		FileWriter write = new FileWriter(file);
		
		System.out.print("请写入地图难度：（1表示E，2表示M，3表示H）");
		int mapLevel = sc.nextInt();
		if(mapLevel == 1)
			write.write("E\n");
		else if(mapLevel == 2)
			write.write("M\n");
		else if(mapLevel == 3)
			write.write("H\n");
		write.flush();
		
		System.out.print("请写入地图大小（用逗号分隔地图大小，先横坐标后纵坐标）：");
		String mapLocation = sc.next();
		write.write(mapLocation + "\n");
		write.flush();
		
		System.out.print("请输入胜利点坐标（用逗号分隔横纵坐标，（英语）句号分隔坐标，由0开始）：");
		String victoryPoint = sc.next();
		write.write(victoryPoint + "\n");
		write.flush();
		
		System.out.print("请输入该关的建议步数:");
		String advisSteps = sc.next();
		write.write(advisSteps + "\n");
		write.flush();
		
		//分割输入坐标
		String [] coord = mapLocation.split(",");
		int abscissa = Integer.parseInt(coord[0]);
		
		for(int j = 1; j < abscissa + 1; j++) {
			System.out.print("请写入的地图的第" + j +"行");
			String line = sc.next();
			write.write(line + "\n");
			write.flush();
		}
	}
}
