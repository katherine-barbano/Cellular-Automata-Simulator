## Lab Discussion
### Team 23
### Names: Katherine Barbano (kab134), Heather Grune (hlg20), Priya Rathinavelu (plr11)


### Issues in Current Code

#### Method or Class
 * Design issues
 
 The View needs to support having multiple states in the dropdown menu even if
 they are not all currently in the Grid. To fix this and make the View work, we will
 create a StateType interface with private enums extending StateType in the subclasses of 
 Simulation.

 * Design issue

We need to store the StateType within the State class, so that it is able to compare
the StateType and have methods like .equals still work.

#### Method or Class
 * Design issues
 
 Right now there's a method that returns the Grid's data structure. We should get rid
 of this method so that the data structure is hidden from other classes.

 * Design issue

We are going to get rid of SimulationType enum, and instead read it in from the properties
file. This will make our code more flexible, so adding a new simulation is a lot easier
and you don't have to edit in as many locations.

### Refactoring Plan

 * What are the code's biggest issues?

There are a lot of changes that have to be made in order to make a new Simulation type.
There have to be changes to simulationProperties folder, SimulationType enum, Neighborhood
subclass, State subclass, Simulation subclass. 

 * Which issues are easy to fix and which are hard?
 
 Taking out SimulationType enum will be a lot easier because it entails more of
 just adjusting the type of parameter being passed around and a few methods,
 but taking out the State subclass will be much harder since we also have to
 add a new interface and new enums to the Simulation subclasses.

 * What are good ways to implement the changes "in place"?
 
 We are going to take out the SimulationType enum and State subclass so there
 are fewer places that have to be changed to add a new Simulation.


### Refactoring Work

 * Issue chosen: Fix and Alternatives

Get list of simulations for dropdown by iterating through properties file in the
 simulationProperties folder, and getting filenames of all properties files in there.
 
 An alternative to this is to keep the SimulationType enum, but this would not be good
 design since it adds another place where we have to change to add a new simulation.

 * Issue chosen: Fix and Alternatives

We will
 create a StateType interface with private enums extending StateType in the subclasses of 
 Simulation.
 
 An alternative is to just have different subclasses of States represent a different
 Simulation type, like GameOfLifeState, etc. but this would be poor design since it
 adds another place where we have to change to add a new simulation.