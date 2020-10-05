package controller;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.util.Duration;
import model.Grid;
import model.SimulationType;

public class ControllerMain extends Application {

  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  public static final int FRAME_SIZE = 400;
  public static final Paint BACKGROUND = Color.AZURE;
  private Scene myScene;
  private Group root;
  private Simulation currentSimulation;

  @Override
  public void start(Stage stage){
    // attach scene to the stage and display it
    setUpStage(stage);
    KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
    animation.play();
  }

  /*
   * Sets up the stage size and title
   */
  protected void setUpStage(Stage stage) {
    setupScene(FRAME_SIZE, FRAME_SIZE, BACKGROUND);
    stage.setScene(myScene);
    stage.setTitle("Testing");
    stage.show();
  }

  /*
   * Create the game's "scene": what shapes will be in the game and their starting properties
   */
  Scene setupScene(int width, int height, Paint background) {
    root = new Group();
    myScene = new Scene(root, width, height, background);
    currentSimulation = new GameOfLifeSimulation();
    currentSimulation.displayGridScene(new Grid(SimulationType.GAME_OF_LIFE, 3,4));
    //clickButton();
    //myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
    return myScene;
  }


  void step (double elapsedTime) {
    updateShapes(elapsedTime);
  }

  private void updateShapes(double elapsedTime) {
  }

  private void clickButton() {
  Button playAgainButton = new Button(" Click to play again");
    playAgainButton.setLayoutX(FRAME_SIZE / 2.0); // NOTE position button
    playAgainButton.setLayoutY(FRAME_SIZE / 2.0);
    HBox hbox = new HBox(playAgainButton);
    root.getChildren().add(hbox);
    playAgainButton.setOnAction(actionEvent -> test());
}

void test() {
  System.out.println("working");
}



  public static void main (String[] args) {
    launch(args);
  }
}
