import java.util.Scanner;
import java.io.File;
/*COSC 1P03
 *ASSIGNMENT #4
 *Username: (Karanjot Pabla)
 *Student #: (6231377)
 *
 *This class is responsible for the retrieving and parsing of a 'maze' datafile, the  establishment of
 *a Maze object, and the invocation of the Maze's solve() method
 */

public class MazeSolver {
 char[][] charmap=null;
 int startRow=-1;//Beginning row
 int startCol=-1;//Beginning column
 Stack<String> as= new LnkStack<>();//Creates stack to store directional path solution
 
 public MazeSolver() {
 }
 //following method is provided
 private void loadMaze() {
  int height,width;
  String filename;
  Scanner console=new Scanner(System.in);
  Scanner file;
  String temp;
  
  System.out.print("Enter maze filename: ");
  filename=console.nextLine();
  try {
   file=new Scanner(new File(filename));
   
   height=file.nextInt();
   width=file.nextInt();
   charmap=new char[height][width];
   file.nextLine();
   for (int i=0;i<height;i++) {
    temp=file.nextLine();
    charmap[i]=temp.toCharArray();
    if (temp.indexOf('S')>=0) {
     startRow=i;startCol=temp.indexOf('S');
     System.out.println("Start at "+startRow+","+startCol+".");
    }
   }
   
   System.out.println("File transcription complete!\n");
  }
  catch (Exception exn) {
   System.out.println("\nFile transcription problem. Exiting now.");
   System.exit(0);
  }
  solve();
 }
 
 private void solve() {
  boolean solved=false;
  System.out.println("Initial State:");
  printMap();
  
  if (recursiveSolve(startRow, startCol)) {
   System.out.println("\nFinal Maze:");
   printMap();
   System.out.print("Solution Path: ");
   while (!as.isEmpty()) //returns step solutions for the path that is stored in the linked stack class
   System.out.print(as.pop()); //hence,this prints as.pop which returns the "String"'s stored in the stack 
                               //(the direction solution) 
  }
  else System.out.println("Oops! No solution found!");
 }
 
 private boolean recursiveSolve(int R, int C) {
   if(charmap[R][C]=='E'){   //If it is an exit (E), replace with O, as per requirements
       charmap[R][C]='O';
       return true; 
     }else if (charmap[R][C]=='S'){ //Otherwise if its a start (S), replace with a "#" as per requirements
     charmap[R][C]='#';
     }else if (charmap[R][C]=='#'||charmap[R][C]=='X'||charmap[R][C]=='*'){ //Returns false if it is anything other 
       return false;                                                        //than exit (E) or a floor (" ")
     }                                                                      //as per requirements
     else{
       charmap[R][C]='*';
     }
     //Following checks if any direction follows recursiveSolve() method requirements (above)
boolean check = recursiveSolve(R-1,C); //If North, then indicate true
      if(check==true){
      as.push("N, ");//adds String for North (N) to LinkedStack named "as" in public class
      return true;
     }
      check = recursiveSolve(R,C+1); //If East, then indicate true
      if(check==true){
      as.push("E, ");//adds String for East (E) to LinkedStack named "as" in public class
      return true;
     }
      check = recursiveSolve(R+1,C); //If South, then indicate true
      if(check==true){
      as.push("S, "); //adds String for South (S) to LinkedStack named "as" in public class
      return true;
     }
      check = recursiveSolve(R,C-1); //If West, then indicate true
      if(check==true){ //adds String for West (W) to LinkedStack named "as" in public class
      as.push("W, ");
      return true;
     }
      charmap[R][C]=' '; //If a floor (" ") return false
      return false; 
    }
 
 private void printMap() {
  for (char[] row:charmap) {
   for (char c:row) System.out.print(c);
   System.out.println();
  }
 }
 
 public static void main(String args[]) {new MazeSolver().loadMaze();}
}
