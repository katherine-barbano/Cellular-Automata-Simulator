package view.LanguageScreen;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;

class LanguageScreenTest extends DukeApplicationTest {

  private LanguageScreen myView;
  private Scene myScene;
  @Override
  public void start(Stage stage) throws Exception {
    myView = new LanguageScreen();
    myScene = myView.setupScene(400,80);
    stage.setScene(myScene);
    stage.setTitle("Simulation");
    stage.show();
  }


  @Test
  void UITest(){
    sleep(10000);
  }


}