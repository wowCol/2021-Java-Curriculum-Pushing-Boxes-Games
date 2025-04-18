package saveIO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Write {
	 private int[][] UserSave;
	 public void getSave( int [][] UserSave ) {
	  this.UserSave = UserSave;
	 }
	 public void outPutUserMap( File fd ) {
		  try {
		   try (BufferedWriter br = new BufferedWriter(new FileWriter(fd))) {
		    br.write( Integer.toString(UserSave.length) + "," + Integer.toString(UserSave[0].length) );
		    br.write("\n");
		    for( int m = 0 ; m < UserSave.length ; m++ ) {
		     for( int n = 0 ; n < UserSave[0].length ; n++ ) {
		      br.write(Integer.toString(UserSave[ m ][ n ]) );
		     }
		     br.write("\n");
		    }
		   }
		  } catch (FileNotFoundException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  } catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
		  
		 }
}
