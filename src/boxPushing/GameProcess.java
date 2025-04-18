package boxPushing;

import java.util.ArrayList;

public class GameProcess {
	private char cmm ;
	private int[][] mapInit;
	private int[][] mapCurr ;
	private int numOfBox;//  ¼  ʼ     Ӻ  յ    
	public int moveState;
	private int posiX = 0 ; private int posiY = 0;
	private ArrayList<Integer> step = new ArrayList<Integer>();
	public void inMove( char cmm ) {
		if( Character.isUpperCase(cmm)) {
			cmm = Character.toLowerCase(cmm);//Ĭ  ΪСд
		}
		this.cmm = cmm ;
	}
	public  GameProcess( int [][] mapInit ) {//  ʼ  ͼ
		int numOfBox = 0;
		for( int i = 0 ; i < mapInit.length ; i++ ) {
			for( int j = 0; j < mapInit[0].length ; j++ ) {
				if( mapInit[i][j] == 4 || mapInit[i][j] == 6 || mapInit[i][j] == 7) {
					numOfBox++;
				}
				}
			}
		this.numOfBox = numOfBox;
		mapCurr = mapInit;
		this.mapInit = mapInit;
	}
	
	public boolean isSuccess( ) {//3,4  ʾ   Ӻ  յ //ÿ  һ     յ     ж 
	int cont = 0;
	for( int i = 0 ; i < mapCurr.length ; i++ ) {
		for( int j = 0; j < mapCurr[0].length ; j++ ) {
			if( mapCurr[i][j] == 7) {
				cont++;
			}
			}
		}
	if( cont == numOfBox) {
		return true;
	}
	else {
		return false;
	}
	}
	
	public int[] getStep() {
		step.add((int)cmm);
		int size=step.size();
		int[] intarrs = new int[size];
		Integer[] arrs=(Integer[]) step.toArray(new Integer[size]);
		for(int i=0;i<intarrs.length;i++){
             intarrs[i]=arrs[i].intValue();
         }
 		return intarrs;
	}
	
	public void isMove() {
		int x = 0; int y = 0;//xy     ƶ       
		if( cmm == 'w') {
			y = -1;
		}
		if( cmm == 's') {
			y = 1;
		}
		if( cmm == 'a') {
			x = -1;
		}
		if( cmm == 'd') {
			x = 1;
		}
//	int posiX = 0 ; int posiY = 0;
		for( int i = 0 ; i < mapCurr.length ; i++ ) {
			for( int j = 0; j < mapCurr[0].length ; j++ ) {
				if( mapCurr[i][j] == 2 || mapCurr[i][j] == 6 ) {
					posiX = j;
					posiY = i;
				}
			}
		}//Ѱ   ˵ ǰ     겢    
		if( mapCurr[posiY + y][posiX + x] == 3 || mapCurr[ posiY + y ][ posiX + x] == 7 ) {//   ƶ   ĵ һ    λ        
			if( mapCurr[posiY + 2 * y][ posiX + 2 * x] == 3 || mapCurr[ posiY + 2 * y ][posiX + 2 * x  ] == 7 ) {// ƶ   ĵڶ     λ        
				moveState = 0;
			}
			else if( mapCurr[posiY + 2 * y][ posiX + 2 * x] == 1 ) { // ƶ   ĵڶ     λ    ǽ  
				moveState = 0;
			}
			else {
				moveState = 2;//          
			}
		}
		else if( mapCurr[posiY + y][ posiX + x] == 1 ) {
			moveState = 0;
		}
		else {
			moveState = 1;
		}
	}
	public void move() {//    cmm    mapCurr
		int x = 0; int y = 0;//xy     ƶ       
		if( cmm == 'w') {
			y = -1;
		}
		if( cmm == 's') {
			y = 1;
		}
		if( cmm == 'a') {
			x = -1;
		}
		if( cmm == 'd') {
			x = 1;
		}
		if( moveState == 1) {
			mapCurr[posiY + y][posiX + x] += 2;
			mapCurr[ posiY ][ posiX ] -= 2;
		}
		if( moveState == 2) {
			mapCurr[posiY + 2 * y][ posiX + 2 * x] += 3;
			mapCurr[posiY + y][posiX + x] -= 3;
			mapCurr[posiY + y][posiX + x] += 2;
			mapCurr[ posiY ][ posiX ] -= 2;
		}//    ޸     
	}
}
