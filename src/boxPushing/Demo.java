package boxPushing;

import java.io.File;

import java.util.Scanner;

public class Demo {
	public static void main(String[] args ){
		File f = new File( "C:\\Users\\saunmy\\Desktop" , "lel1.txt");
		
		Read re = new Read();
		re.readSetting(f);
		re.setMap();
		int [][] map = new int[re.getMap().length][re.getMap()[0].length];
		map = re.getMap();// Ӵ浵 ж ȡ  ͼ
		
		GameProcess ga = new GameProcess(map);//  ȡ µ ͼ   ʤ        
		for( int i = 0 ; i < map.length ; i++ ) {
			for( int j = 0; j < map[0].length ; j++ ) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println("");
		}//  ӡ µ ͼ
		Scanner sc = new Scanner(System.in);
		char cmm ;
		while( true ) {
			cmm = sc.next().charAt(0);
			int[] step = new int[(ga.getStep()).length];
			ga.inMove(cmm);
			ga.isMove();
			ga.move();
			if( ga.moveState != 0) {
			step = ga.getStep();
		}
			for( int i = 0 ; i < map.length ; i++ ) {
				for( int j = 0; j < map[0].length ; j++ ) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println("");
			}
			if( ga.isSuccess() ) {
				System.out.println("Game over");
				break;
			}
		}
	}
}

