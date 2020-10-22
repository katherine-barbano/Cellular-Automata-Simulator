package controller;

import controller.stateType.SpreadingOfFireState;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;
import model.Grid;

/**
 * This class is the specific class extending the Simulation class that represents the Spreading
 * of Fire simulation. This class alone assumes that the properties file associated with this
 * simulation has the name "SpreadingOfFire". If the properties file name is not this, then the
 * initial set up of the simulation would not be possible because there is no default set up. In
 * addition, this class assumes that the states available for this simulation are defined within the
 * spreadingOfFireState enum so that the correct formation of states based on the configuration can
 * be created. An example of how to use this class is to instantiate it when you want to create a
 * new spreading of fire simulation. In order to actually display it, it will need to be connected
 * with a simulation view. This class is an extension of the abstract simulation class so it depends
 * on what that class depends on. In addition, spreadingOfFireSimulation needs to work with the
 * spreadingOfFireState to get the states found within that enum.
 */

public class SpreadingOfFireSimulation extends Simulation {

  private static final String STORING_FILE_NAME = "data/outputGrids/spreadingOfFire";
  private int numberRows;
  private int numberCols;
  private HashMap<Integer,StateType> statesForInteger;
  private HashMap<StateType, Integer> integerForStates;
  ;

  public SpreadingOfFireSimulation() {
    super("SpreadingOfFire");
  }

  /*
  This method returns the state types specific to the spreading of fire simulation by getting the
  spreading of fire state's values from that enum
   */
  @Override
  public StateType[] getStateTypesForSimulation() {
    return SpreadingOfFireState.values();
  }


}

