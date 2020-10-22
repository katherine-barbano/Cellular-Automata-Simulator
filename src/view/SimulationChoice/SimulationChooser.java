package view.SimulationChoice;

import java.io.File;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;

/**
 * The SimulationChooser extends ChoiceBox to create a dropdown menu of all of the Simulation Types.
 * @author Heather Grune (hlg20)
 */
public class SimulationChooser extends ChoiceBox {

  public static final String CHOICE_SUFFIX = "Title";
  public static final String SIMULATION_PROPERTIES_FOLDER = "data/simulationProperties";
  public static final String PROPERTIES_FILE_SUFFIX = ".properties";
  private String[] simulationTypes;
  private String[] simulationTypeNames;
  private ResourceBundle myBundle;
  private ObservableList myTypes;
  private String myChosenType;


  public SimulationChooser(ResourceBundle resources){
    super();
    this.myBundle=resources;
    this.setTooltip(new Tooltip("Select a Simulation Type: "));
    this.simulationTypes = this.getTypesFromFileNames();
    this.simulationTypeNames = getNamesFromSimulationTypes();
    this.setId("simulation-chooser");

    myTypes = FXCollections.observableArrayList(Arrays.asList(simulationTypeNames));
    this.setItems(myTypes);

    this.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
      @Override
      public void changed(ObservableValue<? extends Number> observable, Number oldValue,
          Number newValue) {
        myChosenType = simulationTypes[newValue.intValue()];
      }
    });
  }

  private String[] getNamesFromSimulationTypes(){
    int numTypes = simulationTypes.length;
    String[] names = new String[numTypes];
    for(int num=0;num<numTypes;num++){
      names[num]=myBundle.getString(simulationTypes[num]+CHOICE_SUFFIX);
    }
    return names;
  }

  /**
   * Source https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
   */
  private String[] getTypesFromFileNames(){
    File folder = new File(SIMULATION_PROPERTIES_FOLDER);
    File[] listOfFiles = folder.listFiles();

    String[] simTypes=new String[listOfFiles.length];
    for(int fileNum=0;fileNum< listOfFiles.length;fileNum++){
      String fileName = listOfFiles[fileNum].getName();
      int propertiesIndex = fileName.indexOf(PROPERTIES_FILE_SUFFIX);
      simTypes[fileNum]= fileName.substring(0,propertiesIndex);
    }
    return simTypes;
  }

  /**
   * Accessor for the user's chosen type of simulation
   * @return chosen type of simulation (string)
   */
  public String getMyChosenType(){
    return myChosenType;
  }

}
