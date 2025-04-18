package boxPushing;
import java.io.*;
public class Read{
	private int[][][] fileIn ;
	private int [][] map;
	private int[] step;
	public int[][] getMap(){
		return map;
	}
	public int [] step() {
		return step;
	}
	public void readSetting( File fp ) {
	try{
		int[][][] ValReturn = new int[ 5 ][  ][  ];
		FileInputStream fis = new FileInputStream(fp);
		try (BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {//Construct BufferedReader from InputStreamReader//creat the inputflow
			String line = null;
			line = br.readLine();
			String[] split ;
			split = line.split(",");
			int length = Integer.valueOf(split[0]);
			int width = Integer.valueOf(split[1]);//require the len and wid
			String[][] splited = new String[ length * width ][2];
			
			ValReturn[0] = new int [1][2];

			ValReturn[0][0][0] = length;
			ValReturn[0][0][1] = width;
			int i = 1;
			int j = 0;//j refers to the num of elements in each group
			splited[0][0] = "0";splited[0][1] = "0";
			while( i <= 4 ){
			//i record the represent arrray's(the outerest one) index
				line = br.readLine();
				splited[j] = line.split(",");
				if( splited[j][0].equals("-1") && splited[j][1].equals("-1") ){
					ValReturn[i] = new int[ j ][2];
					for(int k = 0 ; k < j ; k++ ) {
						ValReturn[i][k][0] = Integer.valueOf(splited[k][0]) ;
						ValReturn[i][k][1] = Integer.valueOf(splited[k][1]) ;
					}
					j = 0;
					i++;
					continue;
				}
				j++;
			}
			br.close();
			fis.close();
			fileIn = (int[][][])ValReturn.clone();
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
		}
 		}
		catch(FileNotFoundException e ){
			System.out.println("The file is not found.");
		}
		catch( IOException e ){
			e.printStackTrace();
		}
	}
	public void readStep( File fp ){
		try{
			FileInputStream fis = new FileInputStream(fp);
			try (BufferedReader br = new BufferedReader(new InputStreamReader(fis))) {//Construct BufferedReader from InputStreamReader//creat the inputflow
				String line ;
				String[] split ;
				line = br.readLine();
				
				split = line.split(line);
				int[] valReturn = new int[split.length];
				for( int i = 0 ; i < split.length ; i++ ) {
					valReturn[i] = Integer.valueOf(split[i]);
				}
				br.close();
				fis.close();
				step = ( int[] )valReturn.clone();
			}
			catch (NumberFormatException e) {
				e.printStackTrace();
			}
	 	}
		catch(FileNotFoundException e ){
			System.out.println("The file is not found.");
		}
		catch( IOException e ){
				e.printStackTrace();
			}
	}
	
	public void setMap() {// ˺  յ 6    Ӻ  յ 7
		int [][] map1 = new int[fileIn[0][0][0]][fileIn[0][0][1]];//   õ ͼ      С
		for( int i = 0 ; i < 5 ; i++ ) {
			for( int j = 0 ; j < fileIn[i].length ; j++) {
				System.out.print( fileIn[i][j][0] + "," + fileIn[i][j][1] + " ");
			}
			System.out.println("");
		}
		
		for( int m = 1 ; m < fileIn.length ; m++ ) {//ѡ     
			for( int n = 0 ; n < fileIn[m].length ; n++ ) {//    ı   
				map1[fileIn[m][n][0]][ fileIn[m][n][1]] += m ;
			}
		}
		map = ( int [][] )map1.clone();
	}
}
