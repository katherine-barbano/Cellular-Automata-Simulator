package modelTest.structure;

import controller.State;
import controller.stateType.PercolationState;
import model.EdgePolicy;
import model.Grid;
import model.ModelException;
import model.NeighborPolicy;
import model.Neighborhood;
import model.edgePolicies.FiniteEdgePolicy;
import model.neighborPolicies.CompleteNeighborPolicy;
import model.neighborhoods.concrete.PercolationNeighborhood;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ModelExceptionTest {

  @Test
  void finiteEdgePolicyGetNeighborStateOutOfBounds() {
    State[][] firstGrid = new State[][] {
        {new State(PercolationState.OPEN), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.WATER), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.OPEN), new State(PercolationState.OPEN), new State(PercolationState.OPEN)}
    };

    EdgePolicy finite = new FiniteEdgePolicy(0,0,firstGrid);
    int[] position = new int[]{-1,-1};
    assertThrows(ModelException.class, () -> ((FiniteEdgePolicy)finite).getNeighborStateFromPositionForInitialization(position));
  }

  @Test
  void finiteEdgePolicyGetPositionOutOfBounds() {
    State[][] firstGrid = new State[][] {
        {new State(PercolationState.OPEN), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.WATER), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.OPEN), new State(PercolationState.OPEN), new State(PercolationState.OPEN)}
    };

    EdgePolicy finite = new FiniteEdgePolicy(0,0,firstGrid);
    int[] position = new int[]{-1,-1};
    assertThrows(ModelException.class, () -> ((FiniteEdgePolicy)finite).getPositionOfNeighbor(position));
  }

  @Test
  void neighborPolicyGetStateOutOfBounds() {
    State[][] firstGrid = new State[][] {
        {new State(PercolationState.OPEN), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.WATER), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.OPEN), new State(PercolationState.OPEN), new State(PercolationState.OPEN)}
    };

    EdgePolicy finite = new FiniteEdgePolicy(0,0,firstGrid);
    NeighborPolicy neighborPolicy = new CompleteNeighborPolicy(finite);
    int[] position = new int[]{-1,-1};
    assertThrows(ModelException.class, () -> neighborPolicy.getStateFromNeighborPosition(position));
  }

  @Test
  void neighborhoodOfNeighborsGetStateOutOfBounds() {
    State[][] firstGrid = new State[][] {
        {new State(PercolationState.OPEN), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.WATER), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.OPEN), new State(PercolationState.OPEN), new State(PercolationState.OPEN)}
    };

    EdgePolicy finite = new FiniteEdgePolicy(0,0,firstGrid);
    NeighborPolicy neighborPolicy = new CompleteNeighborPolicy(finite);
    Neighborhood neighborhood = new PercolationNeighborhood(neighborPolicy);
    int[] position = new int[]{-1,-1};
    assertThrows(ModelException.class, () -> neighborhood.findPositionInNeighborhoodOfNeighbors(position));
  }

  @Test
  void applyReflectionToSubclassCreationClassNotFound() {
    State[][] firstGrid = new State[][] {
        {new State(PercolationState.OPEN), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.WATER), new State(PercolationState.OPEN), new State(PercolationState.OPEN)},
        {new State(PercolationState.OPEN), new State(PercolationState.OPEN), new State(PercolationState.OPEN)}
    };
    assertThrows(ModelException.class, () -> new Grid("Invalid type","Invalid type","Invalid type", firstGrid));
  }

}
