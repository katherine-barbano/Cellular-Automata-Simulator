package model;

import controller.State;
import controller.StateType;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

/***
 * Maintains a List<List<Cell>> as the backend for the simulation.
 * This class serves as the boundary between the model and the controller/view.
 * Has methods to return the next Grid based on this current Grid, analyze
 * data about the States in this Grid for the view's graph, and private methods to
 * create Cells, Neighborhoods, NeighborPolicy, and EdgePolicy based on the given
 * constructor.
 * @author Katherine Barbano
 */
public class Grid {

  public static final String NEIGHBORHOOD_NAME_PREFIX_PROPERTIES = "neighborhoodNamePrefix";
  public static final String NEIGHBORHOOD_NAME_SUFFIX_PROPERTIES = "neighborhoodNameSuffix";
  public static final String NEIGHBOR_POLICY_NAME_PREFIX_PROPERTIES = "neighborPolicyNamePrefix";
  public static final String NEIGHBOR_POLICY_NAME_SUFFIX_PROPERTIES = "neighborPolicyNameSuffix";
  public static final String EDGE_POLICY_NAME_PREFIX_PROPERTIES = "edgePolicyNamePrefix";
  public static final String EDGE_POLICY_NAME_SUFFIX_PROPERTIES = "edgePolicyNameSuffix";
  public static final String REFLECTION_EXCEPTION_MESSAGE_PROPERTIES = "reflectionExceptionMessage";

  private List<List<Cell>> cellGrid;
  private String simulationType;
  private String edgePolicyName;
  private String neighborPolicyName;
  private ResourceBundle modelResources;
  private double optionalProbability;

  /***
   * Constructor used for creating first initial grid from CSV file for a simulation without
   * a probability. This should be called by the Controller.
   * @param simulationType String such as "WaTorWorld" describing what type of simulation subclass should be instantiated
   * @param edgePolicyName String such as "Finite" describing the edge policy to be instantiated
   * @param neighborPolicyName String such as "Complete" describing the neighbor policy subclass to instantiate
   * @param allStatesInCSV A 2D array of States, taken directly from the csv file in the Controller
   */
  public Grid(String simulationType, String edgePolicyName, String neighborPolicyName, State[][] allStatesInCSV) {
    setupPrivateVariables(simulationType, edgePolicyName, neighborPolicyName);
    cellGrid = createEmptyCellGrid(allStatesInCSV.length, allStatesInCSV[0].length);
    initializeCurrentCellGrid(allStatesInCSV);
  }

  /***
   * Constructor used for creating the next Grid in Model. Should not be called by Controller.
   * @param simulationType String such as "WaTorWorld" describing what type of simulation subclass should be instantiated
   * @param edgePolicyName String such as "Finite" describing the edge policy to be instantiated
   * @param neighborPolicyName String such as "Complete" describing the neighbor policy subclass to instantiate
   * @param rowLength Length of rows
   * @param columnLength Length of columns
   * @param optionalProbability Double corresponding to an optional probability. Should be 0.0 if no optional probability for the simulation.
   */
  public Grid(String simulationType, String edgePolicyName, String neighborPolicyName, int rowLength, int columnLength, double optionalProbability) {
    setupPrivateVariables(simulationType, edgePolicyName, neighborPolicyName);
    cellGrid = createEmptyCellGrid(rowLength, columnLength);
    this.optionalProbability = optionalProbability;
  }

  /***
   * Constructor used for creating first initial grid from CSV file for a simulation without
   * a probability. This should be called by the Controller.
   * @param simulationType String such as "WaTorWorld" describing what type of simulation subclass should be instantiated
   * @param edgePolicyName String such as "Finite" describing the edge policy to be instantiated
   * @param neighborPolicyName String such as "Complete" describing the neighbor policy subclass to instantiate
   * @param allStatesInCSV A 2D array of States, taken directly from the csv file in the Controller
   * @param optionalProbability Double corresponding to an optional probability, from the properties file in Controller
   */
  public Grid(String simulationType, String edgePolicyName, String neighborPolicyName, State[][] allStatesInCSV, double optionalProbability) {
    this.optionalProbability = optionalProbability;
    setupPrivateVariables(simulationType, edgePolicyName, neighborPolicyName);
    cellGrid = createEmptyCellGrid(allStatesInCSV.length, allStatesInCSV[0].length);
    initializeCurrentCellGrid(allStatesInCSV);
  }

  private void setupPrivateVariables(String simulationType, String edgePolicyName, String neighborPolicyName) {
    modelResources = ResourceBundle.getBundle(Neighborhood.MODEL_RESOURCE_PATH);
    this.simulationType = formatClassName(simulationType);
    this.edgePolicyName = formatClassName(edgePolicyName);
    this.neighborPolicyName = formatClassName(neighborPolicyName);
  }

  private List<List<Cell>> createEmptyCellGrid(int rowCount, int columnCount) {
    List<List<Cell>> cellListOfLists = new ArrayList<>();
    for(int row=0; row<rowCount;row++) {
      List<Cell> cellList = new ArrayList<>();
      for (int column = 0; column < columnCount; column++) {
        cellList.add(null);
      }
      cellListOfLists.add(cellList);
    }
    return cellListOfLists;
  }

  private String formatClassName(String input) {
    String formattedString = "";
    String[] inputWords = input.split(" ");
    for(String word:inputWords)
    {
      if(word.length() > 0) {
        String firstLetter = word.substring(0,1);
        String addition = String.format("%s", firstLetter.toUpperCase());
        formattedString = String.format("%s%s",formattedString,addition);
      }
      if(word.length() > 1) {
        String restOfWord = word.substring(1);
        String addition = String.format("%s", restOfWord);
        formattedString = String.format("%s%s",formattedString,addition);
      }
    }
    return formattedString;
  }

  /***
   * Checks if states of cells in current cellGrid are the same as states of cells in next grid
   * @return true if current grid is stable
   */
  public boolean currentGridIsStable() {
    return getNextGrid().equalsGrid(this);
  }

  /***
   * Call this method from controller to return a Grid object filled with cells in the next state.
   * @return Grid for one step later
   */
  public Grid getNextGrid() {
    Grid initialNextGridFromSurroundingStates = getInitialNextGrid();
    return getNextGridAfterMove(initialNextGridFromSurroundingStates);
  }

  private Grid getInitialNextGrid() {
    Grid nextGridWithOldNeighborhoods = getGridWithNextCells();
    nextGridWithOldNeighborhoods.updateNeighborhoodsWithOldNeighborhoods(this);
    nextGridWithOldNeighborhoods.updateNeighborhoodsOfNeighbors();
    return nextGridWithOldNeighborhoods;
  }

  private Grid getNextGridAfterMove(Grid initialNextGrid) {
    initialNextGrid.updateCellsFromOverlappedNeighborsAfterInitialMove();
    initialNextGrid.updateNeighborhoodsWithNewNeighborhoods();
    initialNextGrid.updateNeighborhoodsOfNeighbors();
    return initialNextGrid;
  }

  /***
   * Iterates through all cells in the table. For each cell, populate a position to state map of all the
   * neighbors of neighbors that overlap on the center cell.
   */
  private void updateCellsFromOverlappedNeighborsAfterInitialMove() {
    for(int row = 0; row<cellGrid.size(); row++) {
      for(int column = 0; column<cellGrid.get(row).size(); column++) {
        Cell centerCell = cellGrid.get(row).get(column);
        Neighborhood centerCellNeighborhood = centerCell.getNeighborhood();

        Map<int[], State> statesOfOverlappingNeighbors = new HashMap<>();
        populateStatesOfOverlappingNeighbors(row, column, statesOfOverlappingNeighbors);
        centerCell.setStatesOfOverlappingNeighbors(statesOfOverlappingNeighbors);

        Cell updatedCell = centerCell.getCellFromOverlappingNeighbors();
        updatedCell.setNeighborhood(centerCellNeighborhood);
        cellGrid.get(row).set(column, updatedCell);
      }
    }
  }

  private void populateStatesOfOverlappingNeighbors(int row, int column, Map<int[], State> statesOfOverlappingNeighbors) {
    Map<int[], Neighborhood> neighborhoodsOfNeighbors = cellGrid.get(row).get(column).getNeighborhoodsOfNeighbors();
    for(int[] neighborPosition : neighborhoodsOfNeighbors.keySet()) {
      putValidNeighborPositionsIntoStatesOfOverlappingNeighbors(neighborPosition, neighborhoodsOfNeighbors, statesOfOverlappingNeighbors);
    }
  }

  private void putValidNeighborPositionsIntoStatesOfOverlappingNeighbors(int[] neighborPosition, Map<int[], Neighborhood> neighborhoodsOfNeighbors, Map<int[], State> statesOfOverlappingNeighbors) {
    try {
      int[] positionOfCenterCellInNeighbor = negateArray(neighborPosition);
      Neighborhood neighborhoodOfNeighbor = neighborhoodsOfNeighbors.get(neighborPosition);
      State stateOfCenterCellInNeighborsNeighbor = neighborhoodOfNeighbor
          .getStateFromNeighborPosition(positionOfCenterCellInNeighbor);
      statesOfOverlappingNeighbors.put(neighborPosition, stateOfCenterCellInNeighborsNeighbor);
    }
    catch(ModelException e) {
      //for NeighborPolicies like TriangleNeighborPolicy, if cell A has a neighbor B, A won't necessarily be a neighbor of B. In this case, nothing should be put into the map since the neighbors don't overlap in that direction.
    }
  }

  private int[] negateArray(int[] array) {
    int[] newArray = new int[array.length];
    for(int index = 0; index<array.length; index++) {
      newArray[index] = array[index]*(-1);
    }
    return newArray;
  }

  private Map<int[], Neighborhood> getNeighborhoodsOfNeighbors(Neighborhood centerCellNeighborhood) {
    Map<int[], Neighborhood> neighborhoodsOfNeighbors = new HashMap<>();
    Set<int[]> centerNeighborRelativePositions = centerCellNeighborhood.allPossibleRelativePositions();
    for (int[] neighborPosition : centerNeighborRelativePositions) {
      int[] positionOfNeighbor = centerCellNeighborhood.getPositionOfNeighbor(neighborPosition);
      Cell neighborCellOfNeighbor = cellGrid.get(positionOfNeighbor[0]).get(positionOfNeighbor[1]);
      Neighborhood neighborhoodOfNeighbor = neighborCellOfNeighbor.getNeighborhood();
      neighborhoodsOfNeighbors.put(neighborPosition, neighborhoodOfNeighbor);
    }
    return neighborhoodsOfNeighbors;
  }

  private Grid getGridWithNextCells() {
    Grid nextGrid = new Grid(simulationType, edgePolicyName, neighborPolicyName, cellGrid.size(), cellGrid.get(0).size(), optionalProbability);
    for(int gridRow = 0; gridRow < cellGrid.size(); gridRow++) {
      for(int gridColumn =0; gridColumn < cellGrid.get(gridRow).size(); gridColumn++) {
        addNextCellToNextGrid(nextGrid, gridRow, gridColumn);
      }
    }
    return nextGrid;
  }

  private void addNextCellToNextGrid(Grid nextGrid, int gridRow, int gridColumn) {
    Cell currentCell = cellGrid.get(gridRow).get(gridColumn);
    Cell nextCell = currentCell.getNextCell();
    nextGrid.addCellToGrid(nextCell, gridRow, gridColumn);
  }

  private void updateNeighborhoodsWithOldNeighborhoods(Grid oldGrid) {
    for(int gridRow = 0; gridRow < cellGrid.size(); gridRow++) {
      for(int gridColumn =0; gridColumn < cellGrid.get(gridRow).size(); gridColumn++) {
        Neighborhood cellNeighborhood = oldGrid.getCell(gridRow, gridColumn).getNeighborhood();
        Cell cell = cellGrid.get(gridRow).get(gridColumn);
        cell.setNeighborhood(cellNeighborhood);
      }
    }
  }

  private void updateNeighborhoodsWithNewNeighborhoods() {
    for(int gridRow = 0; gridRow < cellGrid.size(); gridRow++) {
      for(int gridColumn =0; gridColumn < cellGrid.get(gridRow).size(); gridColumn++) {
        updateNewNeighborhood(gridRow, gridColumn);
      }
    }
  }

  private void updateNewNeighborhood(int gridRow, int gridColumn) {
    State[][] stateMatrix = createStateMatrixFromCellGrid();
    Neighborhood cellNeighborhood = createNeighborhoodForSimulationType(gridRow, gridColumn,
        stateMatrix);
    Cell cell = cellGrid.get(gridRow).get(gridColumn);
    cell.setNeighborhood(cellNeighborhood);
  }

  private void initializeCurrentCellGrid(State[][] allStatesInCSV) {
    for (int csvRow = 0; csvRow < allStatesInCSV.length; csvRow++) {
      for (int csvColumn = 0; csvColumn < allStatesInCSV[csvRow].length; csvColumn++) {
        putCellWithNeighborhoodInGrid(csvRow, csvColumn, allStatesInCSV);
      }
    }
    updateNeighborhoodsWithOldNeighborhoods(this);
    updateNeighborhoodsOfNeighbors();
  }

  private void putCellWithNeighborhoodInGrid(int csvRow, int csvColumn, State[][] allStatesInCSV) {
    Neighborhood cellNeighborhood = createNeighborhoodForSimulationType(csvRow, csvColumn, allStatesInCSV);
    Cell cellInPosition = new Cell(cellNeighborhood, allStatesInCSV[csvRow][csvColumn]);
    cellGrid.get(csvRow).set(csvColumn, cellInPosition);
  }

  private void updateNeighborhoodsOfNeighbors() {
    for(int row = 0; row<cellGrid.size(); row++) {
      for(int column = 0; column < cellGrid.get(row).size(); column++) {
        Neighborhood centerNeighborhood = cellGrid.get(row).get(column).getNeighborhood();
        Map<int[], Neighborhood> neighborhoodOfNeighbors = getNeighborhoodsOfNeighbors(centerNeighborhood);
        cellGrid.get(row).get(column).setNeighborhoodsOfNeighbors(neighborhoodOfNeighbors);
      }
    }
  }

  private Neighborhood createNeighborhoodForSimulationType(int centerCellRow, int centerCellColumn, State[][] stateGrid) {
    String classNamePrefix = modelResources.getString(NEIGHBORHOOD_NAME_PREFIX_PROPERTIES);
    String classNameSuffix = modelResources.getString(NEIGHBORHOOD_NAME_SUFFIX_PROPERTIES);
    NeighborPolicy neighborPolicy = createNeighborPolicy(centerCellRow, centerCellColumn, stateGrid);
    Object subclass = tryToCreateSubclassWithOptionalProbability(classNamePrefix, classNameSuffix, neighborPolicy);
    return (Neighborhood) subclass;
  }

  private Object tryToCreateSubclassWithOptionalProbability(String classNamePrefix, String classNameSuffix, NeighborPolicy neighborPolicy) {
    try {
      Class<?>[] type = {NeighborPolicy.class, double.class};
      Object[] constructorArguments = {neighborPolicy, optionalProbability};
      return applyReflectionToSubclassCreation(classNamePrefix, classNameSuffix, simulationType, type, constructorArguments);
    }
    catch(Exception e) {
      return createSubclassWithoutOptionalProbability(classNamePrefix, classNameSuffix, neighborPolicy);
    }
  }

  private Object createSubclassWithoutOptionalProbability(String classNamePrefix, String classNameSuffix, NeighborPolicy neighborPolicy) {
    Class<?>[] type = {NeighborPolicy.class};
    Object[] constructorArguments = {neighborPolicy};
    return applyReflectionToSubclassCreation(classNamePrefix, classNameSuffix, simulationType, type, constructorArguments);
  }

  private NeighborPolicy createNeighborPolicy(int centerCellRow, int centerCellColumn, State[][] stateGrid) {
    String classNamePrefix = modelResources.getString(NEIGHBOR_POLICY_NAME_PREFIX_PROPERTIES);
    String classNameSuffix = modelResources.getString(NEIGHBOR_POLICY_NAME_SUFFIX_PROPERTIES);
    Class<?>[] type = {EdgePolicy.class};
    EdgePolicy edgePolicy = createEdgePolicy(centerCellRow, centerCellColumn, stateGrid);
    Object[] constructorArguments = {edgePolicy};

    Object subclass = applyReflectionToSubclassCreation(classNamePrefix, classNameSuffix, neighborPolicyName, type, constructorArguments);
    return (NeighborPolicy) subclass;
  }

  private EdgePolicy createEdgePolicy(int centerCellRow, int centerCellColumn, State[][] stateGrid) {
    String classNamePrefix = modelResources.getString(EDGE_POLICY_NAME_PREFIX_PROPERTIES);
    String classNameSuffix = modelResources.getString(EDGE_POLICY_NAME_SUFFIX_PROPERTIES);
    Class<?>[] type = {int.class,int.class,State[][].class};
    Object[] constructorArguments = {centerCellRow, centerCellColumn, stateGrid};

    Object subclass = applyReflectionToSubclassCreation(classNamePrefix, classNameSuffix, edgePolicyName, type, constructorArguments);
    return (EdgePolicy) subclass;
  }

  private Object applyReflectionToSubclassCreation(String classNamePrefix, String classNameSuffix, String className, Class<?>[] constructor, Object[] constructorArguments) {
    try {
      //code referenced from https://java2blog.com/invoke-constructor-using-reflection-java/ provided on course website
      String fullClassName = String.format("%s%s%s", classNamePrefix, className, classNameSuffix);
      Class<?> cl = Class.forName(fullClassName);
      Constructor<?> cons = cl.getConstructor(constructor);
      return cons.newInstance(constructorArguments);
    }
    catch(Exception e) {
      String simulationTypeExceptionMessage = modelResources.getString(REFLECTION_EXCEPTION_MESSAGE_PROPERTIES);
      throw new ModelException(simulationTypeExceptionMessage,e);
    }
  }

  private State[][] createStateMatrixFromCellGrid() {
    State[][] stateMatrix = new State[cellGrid.size()][cellGrid.get(0).size()];
    for (int cellGridRow = 0; cellGridRow < cellGrid.size(); cellGridRow++) {
      for (int cellGridColumn = 0; cellGridColumn < cellGrid.get(cellGridRow).size(); cellGridColumn++) {
        stateMatrix = cellAddedToStateMatrix(cellGridRow,cellGridColumn, stateMatrix);
      }
    }
    return stateMatrix;
  }

  private State[][] cellAddedToStateMatrix(int cellGridRow, int cellGridColumn, State[][] stateMatrix) {
    if (cellGrid.get(cellGridRow).get(cellGridColumn) == null) {
      stateMatrix[cellGridRow][cellGridColumn] = null;
    }
    else {
      Cell cellAtIndex = cellGrid.get(cellGridRow).get(cellGridColumn);
      stateMatrix[cellGridRow][cellGridColumn] = cellAtIndex.getCurrentState();
    }
    return stateMatrix;
  }

  void addCellToGrid(Cell newCell, int cellRow, int cellColumn) {
    cellGrid.get(cellRow).set(cellColumn, newCell);
  }

  /***
   * Determines whether all States in two Grids are equal by comparing
   * the equality of their Cells, and their number of rows/cols.
   * @param otherGrid Another Grid object to compare to
   * @return true if the Grids are equal
   */
  public boolean equalsGrid(Grid otherGrid) {

    if(otherGrid.getGridNumberOfRows()!=getGridNumberOfRows()) {
      return false;
    }
    if(otherGrid.getGridNumberOfColumns()!=getGridNumberOfColumns()) {
      return false;
    }

    for(int row = 0; row< getGridNumberOfRows(); row++) {
      for(int column = 0; column< getGridNumberOfColumns(); column++) {
        Cell otherCell = otherGrid.getCell(row,column);
        Cell thisCell = getCell(row, column);
        boolean onlyOneCellEmpty = (otherCell==null && thisCell!=null) || (otherCell!=null && thisCell==null);
        boolean bothCellsEmpty = otherCell==null && thisCell==null;
        if(onlyOneCellEmpty || (!bothCellsEmpty && !otherCell.equalsCell(thisCell))) {
          return false;
        }
      }
    }
    return true;
  }

  /***
   * Called by View and Controller to return a Cell corresponding to that
   * row and column number. This method allows the data structure for Grid to be
   * hidden from public access.
   * @param rowNumber Int for row number
   * @param columnNumber Int for column number
   * @return Cell object corresponding to that row and column
   */
  public Cell getCell(int rowNumber, int columnNumber) {
    return cellGrid.get(rowNumber).get(columnNumber);
  }

  /***
   * Returns number of rows in Grid
   * @return int of rows
   */
  public int getGridNumberOfRows() {
    return cellGrid.size();
  }

  /***
   * Returns number of columns in Grid
   * @return int of columns
   */
  public int getGridNumberOfColumns() {
    return cellGrid.get(0).size();
  }

  /***
   * Returns the optional probability Grid was instantiated with.
   * Returns 0.0 if not instantiated with optional probability.
   * @return Double of optional probability
   */
  public double getOptionalProbability() {
    return optionalProbability;
  }

  /***
   * Provides data analysis for the View's graph about the number of cells
   * in the Grid that have the same StateType as a given target
   * @param target StateType object
   * @return int count for number of cells
   */
  public int getCountAllCellsWithSameStateTypeAsTarget(StateType target) {
    int numCellsWithTargetStateType=0;
    for(int row = 0; row<cellGrid.size(); row++) {
      for(int column = 0; column<cellGrid.get(row).size(); column++) {
        Cell currentCell = cellGrid.get(row).get(column);
        State currentState = currentCell.getCurrentState();
        if(currentState.equalsState(target)) {
          numCellsWithTargetStateType++;
        }
      }
    }
    return numCellsWithTargetStateType;
  }
}
