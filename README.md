simulation
====

This project implements a cellular automata simulator.

Names: Katherine Barbano (kab134), Heather Grune (hlg20), Priya Rathinavelu (plr11)

### Timeline

Start Date: 10/2/2020

Finish Date: 10/19/2020

Hours Spent:
Katherine - around 80 hrs
Priya - 
Heather - 

### Primary Roles

Katherine: model package, backend
Priya: controller package, putting backend and frontend together to run Main
Heather: view package, frontend

### Resources Used
https://www.tutorialspoint.com/javafx/javafx_event_handling.html - eventListeners in View
https://java2blog.com/invoke-constructor-using-reflection-java/ provided on course website - reflection in Grid
https://stackoverflow.com/questions/3680637/generate-a-random-double-in-a-range - generate random double in SpreadingOfFireNeighborhood
https://docs.oracle.com/javafx/2/charts/line-chart.htm#CIHGBCFI - simulation graph
https://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder - simulation chooser

Used DukeApplicationTest class provided by Robert Duvall for testing JavaFX.

### Running the Program

Main class: src/controller/ControllerMain.java

Data files needed: 

Features implemented:

Model
- Generation of the next grid based on the states in the current grid
- 6 different simulations with different rules for determining the next state
- 3 different edge policies (Finite, Toroidal, KleinBottle), which can be specified in an input file for any simulation
- 5 different neighbor policies, which can be specified in an input file for any simulation
- States that can maintain age and move into different cells between turns (demonstrated in Segregation and WaTorWorld)
- Optional probability that can be specified in input file with a different Grid constructor. Default probability used if optional not provided.
- Use of reflection in Grid, so that new neighborhoods (i.e. types of simulations), new neighbor policies,
or new edge policies can be added simply by creating subclasses, without any changes in Grid or Cell.
- Use of a custom runtime exception (ModelException) that is caught and handled apporpriately throughout Model.

View

Controller


### Notes/Assumptions

Assumptions or Simplifications:

- The optional probability provided cannot be 0.0 in the Grid constructor. If you do provide 0.0, it will use the default probability instead.
- You might be wondering why the number of XAgents and OAgents isn't constant over time in Segregation.
This is because we made the assumption that if an XAgent and OAgent try to move into
the same spot, there is a "fight" between them and only one survives.
Because of this, the number of XAgents or OAgents might decrease over time.
- The input file specifying the name of the simulation, edge policy, or neighbor policy
in the following format: if there are multiple words, you should either capitalize
the separate words and not use spaces like "SpreadingOfFire" or "KleinBottle", or you can use multiple
spaces with any capitalization like "spreading Of Fire" or "Klein bottle".

Interesting data files:

Known Bugs:

- There is a bug that prevents fish from getting eaten by sharks in WaTor world. The location of the bug is marked
as a TODO in a comment in WaTorWorldNeighborhood, but I wasn't able to figure out how to resolve it.
The WaTorWorldTest file has two failing tests - these tests both correspond to this bug. 

Extra credit:

- The Grid uses a List<List<Cell>> as its data structure instead of a 2D matrix. Although we did not have time to actually display differently shaped cells, this design decision
should make tilings of different shapes with different length rows or columns much easier (especially
given that we already have a hexagonal and triangle neighbor policy).


### Impressions
This project showed the importance of extensibility in software design. The time we spent
planning our inheritance hierarchies made things a lot easier in later iterations, since
code that was more extensible made it easier to add new features.
