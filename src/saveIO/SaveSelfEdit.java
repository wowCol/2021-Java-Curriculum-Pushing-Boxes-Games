package saveIO;

public class SaveSelfEdit {
 private int len;//X
 private int wid;//Y
 private int label = 1;
 private int[][] map;
 
 public void inLabel( int label ) {
  this.label = label;
 }
 public int[][] getMap(){
  return map;
 }
 public int getLabel() {
  return label;
 }
 public SaveSelfEdit( int len , int wid ) {
   this.map =new int[wid][len];
  for( int i = 0 ; i < wid ; i++) {
   for( int j = 0 ; j < len ; j++ ) {
    map[i][j] = 0;
   }
  }
  this.len = len;
  this.wid = wid;
 }
 public void saveMap( int x , int y ) {//标记物件
  this.map[y][x] = label;
 }
 public void saveMap( int x , int y , int labeladd ) {
  this.map[y][x] = labeladd + label; 
 }
 public void initMap() {
  for( int i = 0 ; i < wid ; i++) {
   for( int j = 0 ; j < len ; j++ ) {
    map[i][j] = 0;
   }
  }
 }
 public boolean alignmentOfObj() {
  int numOfBox = 0;
  int numOfVic = 0;
  int numOfPlay = 0;
  for( int i = 0 ; i < wid ; i++) {
   for( int j = 0 ; j < len ; j++ ) {
    if( map[i][j] == 2 || map[ i ][ j ] == 5  ) {//统计箱子个数
     numOfBox++;
    }
    if( map[i][j] == 3 || map[ i ][ j ] == 5 ) {//统计胜利点个数
     numOfVic++;
    }
    if( map[i][j] == 4 || map[ i ][ j ] == 7 ) {//统计人物个数
     numOfPlay++;
    }
    
   }
  }
  if( numOfBox != numOfVic ) {
   return false;
  }
  if( numOfPlay != 1 ) {
   return false;
  }
  return true;
 }
}