import javafx.application.Application;
import java.io.FileNotFoundException;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.Stage;
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

@Override
public void start (Stage stage) throws FileNotFoundException {

  populateBoard();
  generateShape();
  startFalling();


  Scene scene = new Scene(board);
  // scene.setFill(Color.BLACK);
  stage.setScene(scene);
  stage.setTitle("tetris");
  stage.show();
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
    Random randomNumber = new Random();
    fallingPiece = shapeList.get(randomNumber.nextInt(7));
}
  public static void startFalling(){
    SquareFills[0][4]=fallingPiece[0][0];
    SquareFills[0][5]=fallingPiece[0][1];
    SquareFills[0][6]=fallingPiece[0][2];
    SquareFills[1][4]=fallingPiece[1][0];
    SquareFills[1][5]=fallingPiece[1][1];
    SquareFills[1][6]=fallingPiece[1][2];
    SquareFills[2][4]=fallingPiece[2][0];
    SquareFills[2][5]=fallingPiece[2][1];
    SquareFills[2][6]=fallingPiece[2][2];
    populateBoard();

  }
  public static void populateBoard(){
    board = new GridPane();
    board.setVgap(1.0);
    board.setHgap(1.0);
    System.out.println("populating board");
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
}
