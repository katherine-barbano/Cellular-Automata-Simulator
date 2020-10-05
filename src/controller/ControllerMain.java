package controller;

import java.net.MalformedURLException;
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
import view.SimulationView;
//import view.SimulationView;

public class ControllerMain extends Application {

  public static final int FRAMES_PER_SECOND = 60;
  public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  public static final double SECOND_DELAY_LONG = 1.0;
  public static final int FRAME_SIZE = 400;
  public static final Paint BACKGROUND = Color.AZURE;
  public static final int SCREEN_WIDTH = 400;
  public static final int SCREEN_HEIGHT = 400;
  private Scene myScene;
  private Group root;
  private Simulation currentSimulation;
  private SimulationView currentSimulationView;
  private boolean isPaused;

  @Override
  public void start(Stage stage) throws MalformedURLException {
    // attach scene to the stage and display it
    //
    setUpStage(stage);
    //
    KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY_LONG), e -> step(SECOND_DELAY_LONG));
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
    animation.play();
  }

  /*
   * Sets up the stage size and title
   */
  protected void setUpStage(Stage stage) throws MalformedURLException {
    setupScene(FRAME_SIZE, FRAME_SIZE, BACKGROUND);
    //currentSimulationView = new SimulationView();
    stage.setScene(myScene);
    stage.setTitle("Testing");
    stage.show();
    isPaused = false; //CHECK should change this to true when buttons work
  }

  /*
   * Create the game's "scene": what shapes will be in the game and their starting properties
   */
  Scene setupScene(int width, int height, Paint background) throws MalformedURLException {
    root = new Group();
    currentSimulation = new GameOfLifeSimulation();
    SimulationView currSimView = currentSimulation.getSimulationView();
    myScene = currentSimulation.getSimulationView().setupScene("GameOfLife", SCREEN_WIDTH, SCREEN_HEIGHT);

    //currentSimulation.displayGridScene(new Grid(SimulationType.GAME_OF_LIFE, 3,4));

    //clickButton();
    //myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
    return myScene;
  }


  void step (double elapsedTime) { //CHECK should be taking in elapsedTime now?
    updateShapes(elapsedTime);
  }

  private void updateShapes(double elapsedTime) {
    currentSimulation.updateSimulationGrid(isPaused);
  }

 void unpause() {
    isPaused = false;
 }

 void pause() {
    isPaused = true;
 }

  public static void main (String[] args) {
    launch(args);
  }
}
