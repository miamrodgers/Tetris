import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.*;
import java.io.FileNotFoundException;
import javafx.geometry.*;
import java.lang.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import java.time.*;
import java.util.*;

public class tetris extends Application{

public static GridPane board;
public static int[][] fallingPiece;
public static int[][] SquareFills=
{
  {0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0},
  {0,0,0,0,0,0,0,0,0,0}
};
//current rows
public static int currentRow1;
public static int currentRow2;
public static int currentRow3;
public static int currentRow4;
public static int currentRow5;
public static int currentRow6;
public static int currentRow7;
public static int currentRow8;
public static int currentRow9;
//current columns
public static int currentCol1;
public static int currentCol2;
public static int currentCol3;
public static int currentCol4;
public static int currentCol5;
public static int currentCol6;
public static int currentCol7;
public static int currentCol8;
public static int currentCol9;

public static Random randomNumber;

public static int colorID;

@Override
public void start (Stage stage) throws FileNotFoundException {

  populateBoard();
  generateShape();
  initializeFallingPiece();

  Timer timer = new Timer();
  timer.scheduleAtFixedRate(new ScheduleTask(), 1000, 500);

  Scene scene = new Scene(board);
  // scene.setFill(Color.BLACK);
  scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
              if(event.getCode()==KeyCode.UP){
                rotatePiece();
              }
              else if(event.getCode()==KeyCode.LEFT){
                shiftLeft();
              }
              else if(event.getCode()==KeyCode.RIGHT){
                shiftRight();
              }
            }
        });
  stage.setScene(scene);
  stage.setTitle("tetris");
  stage.show();
  }
  public static boolean checkFullRow(int row){
    System.out.println("checking for full rows...");
    for(int i: SquareFills[row]) {
      if(i == 0) {
      System.out.println("row " + row + " is not full");
      return false;
    } else {
      System.out.println("row " + row + " is full");
      return true;
      }
    }
    return false;
  }
  public static void clearShape(){
    System.out.println("clearing...");
    if(SquareFills[currentRow1][currentCol1]==colorID){
      SquareFills[currentRow1][currentCol1]=0;
    }
    if(SquareFills[currentRow2][currentCol2]==colorID){
      SquareFills[currentRow2][currentCol2]=0;
    }
    if(SquareFills[currentRow3][currentCol3]==colorID){
      SquareFills[currentRow3][currentCol3]=0;
    }
    if(SquareFills[currentRow4][currentCol4]==colorID){
      SquareFills[currentRow4][currentCol4]=0;
    }
    if(SquareFills[currentRow5][currentCol5]==colorID){
      SquareFills[currentRow5][currentCol5]=0;
    }
    if(SquareFills[currentRow6][currentCol6]==colorID){
      SquareFills[currentRow6][currentCol6]=0;
    }
    if(SquareFills[currentRow7][currentCol7]==colorID){
      SquareFills[currentRow7][currentCol7]=0;
    }
    if(SquareFills[currentRow8][currentCol8]==colorID){
      SquareFills[currentRow8][currentCol8]=0;
    }
    if(SquareFills[currentRow9][currentCol9]==colorID){
      SquareFills[currentRow9][currentCol9]=0;
    }
  }
  public static void fall(){
    if(positionValid()){
    clearShape();
    currentRow1++;
    currentRow2++;
    currentRow3++;
    currentRow4++;
    currentRow5++;
    currentRow6++;
    currentRow7++;
    currentRow8++;
    currentRow9++;
    update();
    } else {
      refresh();
    }
  }
  public static boolean positionValid(){
    if(currentRow9<19){
      // if(SquareFills[currentRow9-1][currentCol9]==0&&SquareFills[currentRow8-1][currentCol8]==0&&SquareFills[currentRow7-1][currentCol7]==0){
      //   return true;
      // } else {
      //   return false;
      //}
      return true;
    } else {
      return false;
    }
  }
  public static void refresh(){
    generateShape();
    initializeFallingPiece();

  }
  public static void update(){
    System.out.println("updating...");
    SquareFills[currentRow1][currentCol1]=fallingPiece[0][0];
    SquareFills[currentRow2][currentCol2]=fallingPiece[0][1];
    SquareFills[currentRow3][currentCol3]=fallingPiece[0][2];
    SquareFills[currentRow4][currentCol4]=fallingPiece[1][0];
    SquareFills[currentRow5][currentCol5]=fallingPiece[1][1];
    SquareFills[currentRow6][currentCol6]=fallingPiece[1][2];
    SquareFills[currentRow7][currentCol7]=fallingPiece[2][0];
    SquareFills[currentRow8][currentCol8]=fallingPiece[2][1];
    SquareFills[currentRow9][currentCol9]=fallingPiece[2][2];
    loopRectangles(board);
  }
  public static void shiftLeft(){
    System.out.println("shift left");
    clearShape();
    currentCol1--;
    currentCol2--;
    currentCol3--;
    currentCol4--;
    currentCol5--;
    currentCol6--;
    currentCol7--;
    currentCol8--;
    currentCol9--;
    update();
  }
  public static void shiftRight(){
    System.out.println("shift right");
    clearShape();
     currentCol1++;
     currentCol2++;
     currentCol3++;
     currentCol4++;
     currentCol5++;
     currentCol6++;
     currentCol7++;
     currentCol8++;
     currentCol9++;
     update();
  }
  public static void rotatePiece(){
    System.out.println("rotate piece");
    int spot1= fallingPiece[0][0];
    int spot2= fallingPiece[0][1];
    int spot3= fallingPiece[0][2];
    int spot4= fallingPiece[1][0];
    int spot5= fallingPiece[1][1];
    int spot6= fallingPiece[1][2];
    int spot7= fallingPiece[2][0];
    int spot8= fallingPiece[2][1];
    int spot9= fallingPiece[2][2];

    fallingPiece[0][0]=spot3;
    fallingPiece[0][1]=spot6;
    fallingPiece[0][2]=spot9;
    fallingPiece[1][0]=spot2;
    fallingPiece[1][1]=spot5;
    fallingPiece[1][2]=spot8;
    fallingPiece[2][0]=spot1;
    fallingPiece[2][1]=spot4;
    fallingPiece[2][2]=spot7;

    update();
  }
  public static void generateShape(){
    int[][] shape1 = {
      {1,1,0},
      {1,0,0},
      {1,0,0}};
    int[][] shape2 = {
      {2,2,0},
      {2,2,0},
      {0,0,0}};
    int[][] shape3 = {
      {3,0,0},
      {3,3,0},
      {0,3,0}};
    int[][] shape4 = {
      {0,4,0},
      {4,4,0},
      {4,0,0}};
    int[][] shape5 = {
      {5,0,0},
      {5,0,0},
      {5,5,0}};
    int[][] shape6 = {
      {0,6,0},
      {6,6,0},
      {0,6,0}};
    int[][] shape7 = {
      {7,0,0},
      {7,0,0},
      {7,0,0}};
    List<int[][]> shapeList = new ArrayList<int[][]>();
    shapeList.add(shape1);
    shapeList.add(shape2);
    shapeList.add(shape3);
    shapeList.add(shape4);
    shapeList.add(shape5);
    shapeList.add(shape6);
    shapeList.add(shape7);
    randomNumber = new Random();
    colorID = randomNumber.nextInt(7);
    fallingPiece = shapeList.get(colorID-1);
  }
  public static void initializeFallingPiece(){
    currentRow1 = 0;
    currentCol1 = 4;

    currentRow2 = 0;
    currentCol2 = 5;

    currentRow3 = 0;
    currentCol3 = 6;

    currentRow4 = 1;
    currentCol4 = 4;

    currentRow5 = 1;
    currentCol5 = 5;

    currentRow6 = 1;
    currentCol6 = 6;

    currentRow7 = 2;
    currentCol7 = 4;

    currentRow8 = 2;
    currentCol8 = 5;

    currentRow9 = 2;
    currentCol9 = 6;

    update();

  }
  public static void populateBoard(){
    board = new GridPane();
    board.setVgap(1.0);
    board.setHgap(1.0);
    loopRectangles(board);
  }
  public static void loopRectangles(GridPane board){
    Map<Integer, Color> ColorValues = new HashMap<Integer, Color>();
    ColorValues.put(0, Color.BLACK);
    ColorValues.put(1, Color.DODGERBLUE);
    ColorValues.put(2, Color.RED);
    ColorValues.put(3, Color.GREEN);
    ColorValues.put(4, Color.HOTPINK);
    ColorValues.put(5, Color.YELLOW);
    ColorValues.put(6, Color.DARKVIOLET);
    ColorValues.put(7, Color.DARKORANGE);
    for(int row = 0; row<20; row++){
      for(int col=0; col<10; col++){
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(20);
        rectangle.setHeight(20);
        rectangle.setFill(ColorValues.get(SquareFills[row][col]));
        GridPane.setRowIndex(rectangle, row);
        GridPane.setColumnIndex(rectangle, col);
        board.getChildren().addAll(rectangle);
      }
    }
  }

  private class ScheduleTask extends TimerTask{

      @Override
      public void run(){
        Platform.runLater(() -> {
        fall();
      });
      }
  }
}
