package fileWork;

import java.io.File;
import menu.*;
import Interface.*;
import fileWork.*;
import boxPushing.*;

public class ControlText {
	//管理删除的方法定义
	public boolean removeText(String name) {
		//打开文档
		File getFile = new File("D:\\Java\\BoxPushing\\Save\\PlayerSave\\" + name);
		if(!getFile.exists()) {
			System.out.println("文件不存在，打开错误！");
			System.exit(0);
		}
		
		//判断是否为文件，然后删除
		if(getFile.isFile())
			getFile.delete();
		
		//判断是否成功删除
		if(!getFile.exists())
			return true;
		else
			return false;
	}
}
