import javafx.application.Application;
import javafx.stage.Stage;
import view.SimulationView;

public class Main extends Application {

  public static final String SIMULATION_TYPE= "Game Of Life";

  @Override
  public void start(Stage primaryStage) throws Exception {

    SimulationView view = new SimulationView();

    primaryStage.setTitle("Simulation");
    primaryStage.setScene(view.setupScene(SIMULATION_TYPE, 400,400));
    primaryStage.show();

  }

  public static void main (String[] args) {
    launch(args);
  }


}
