package controller;

import javafx.scene.Group;

public abstract class Simulation {
  /**
  private Grid currentGrid;
  private Grid nextGrid;
  private final String simulationName;
  private final List<State> possibleStates;
  private Group root;



  public Simulation(String SimulationNameType, List<State> simulationStates) {
    simulationName = SimulationNameType;
    currentGrid = new Grid();
    nextGrid = new Grid();
    possibleStates = simulationStates;
  }

//CHECK can remove this method if initializing in the constructor itself
  void initializeSimulation(Group root) { //CHECK might not need to pass root in
    currentGrid.initalizeGrid(this.simulationName); //CHECK use method in Grid
    nextGrid = currentGrid.getUpdateGrid(this.simulationName); //CHECK method names?
    displayGridScene(currentGrid);
  }

  void displayGridScene(Grid gridToBeDisplayed) {
    //CHECK depending on how Grid is storing cells
    //CHECK need to create instance of cellDisplay ?
    for (int i = 0; i < gridToBeDisplayed.getNumberOfCells(); i++) {
      cellDisplay.displayCellMethod(gridToBeDisplayed.getCell(i));
    }
  }

  void updateSimulationGrid() {
    this.currentGrid = nextGrid;
    this.nextGrid = currentGrid.getUpdateGrid(this.simulationName);
    //Need to update display
    for (int i = 0; i < currentGrid.getNumberOfCells(); i++) {
      root.getChildren().remove(currentGrid.getCell(i));
    }
    displayGridScene(currentGrid);
  };
   **/
}
