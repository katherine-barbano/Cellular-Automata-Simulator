simulation
====

This project implements a cellular automata simulator.

Names: Katherine Barbano (kab134), Heather Grune (hlg20), Priya Rathinavelu (plr11)

### Timeline

Start Date: 10/2/2020

Finish Date: 10/19/2020

Hours Spent:
Katherine - around 80 hrs
Priya - around 70 hours 
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

Data files needed: The data files needed for this project to work at the basic level are 
the main property files. These are especially important for creating the different simulations extended 
from the simulation abstract class. With this, these configuration files are set to take in a file 
for initial configuration, so the data files containing the actual configuration (the csv file) needs 
to be within the initialConfigurations folder. If it is not located there, a custom error will be 
thrown and displayed. 

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
- The controller's main feature is the abstract simulation class which is what deals with reading in the 
properties file for a specific simulation and creating the necessary features of that simulation. In 
addition, this abstract class deals with storing the initial set up information so that when a new 
properties file needs to be created, it can access the correct information. 
-The controller also contains the main class that runs the program (Conroller main) class. In this class,
the main scene is set up that displays the correct simulation, and updates as necessary. In addition, 
this class includes the setting up of several buttons found on the display that allow the user 
to interact with the simulation.
-Another set of classes found within the controller is the statetypes for each simulation that implement
the StateType interface. These classes serve to illustrates with states are available for 
each simulation (for example, Game of Life's states are DEAD and ALIVE). In addition, 
these statetypes for each simulation store the colors of the states that are used 
as the default configuration.
-Another class within the controller is the State class. Each State takes in a statetype and can also
store the age (which is needed for a few of the simulations)


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

For the controller, the displaying the different simulation, this assumes that the default simulation 
is the Game of Life simulation, and so it first displays that. In addition, this program assumes that 
all simulations have a properties file listed in the simulationProperties folder (the code goes through 
this folder to determine all available simulations to be displayed). In addition, this controller 
assumes that the three types of configurations that are possible for setting up the simulation are "file",
where a file name must also be included that indicates the name of a file within the initialConfigurations 
folder that has the states as a CSV file. The second type is "probability". If this is listed,
then a probability must also be given in the properties file. This probability configuration assumes 
that the user is providing the percentage of values within the entire cell configuration that will be 
made up of the first state (whatever that may be for each simulation). The third option for 
grid configuration (which the code also assumes is the default if nothing else is provided or if an
invalid name is provided), creates a completely random configuration of the cells with various states for 
that simulation. It reads in the square size of the grid from a properties file so it can be adjusted.

Errors Tested: In the ConfigurationAndPropertiesTests Class, several different scenarios are tested 
to ensure that the custom controller exceptions are correctly being thrown at the correct 
time. For example, several incorrect configuration files were tested, such as having negative
numbers, a different amount of rows/cols than what is listed in the beginning of the file, 
having non-numerical values. In addition, aside from having incorrect values for the  actual values
 within the file, the code also tested for having the configuration file located in an incorrect
 position and having an invalid file name. Lastly, because the properties file were such an important 
 part of setting up the simulations, a few tests were created that looked at invalid properties
 files. Specifically, this tested if an error was thrown if the file listed that the configuration
 would be determined by "file" but didnt list a "fileName". In addition, this class 
 tested for if a properties file was not in the correct location. Lastly, a few tests were created 
 to ensure that the properties file was correctly being read and that the simulation would be 
 able to know if the configuration should be "file", "random", "probability". 

Interesting data files:

Known Bugs:

- There is a bug that prevents fish from getting eaten by sharks in WaTor world. The location of the bug is marked
as a TODO in a comment in WaTorWorldNeighborhood, but I wasn't able to figure out how to resolve it.
The WaTorWorldTest file has two failing tests - these tests both correspond to this bug. 

- The slow down button also speeds up the simulation instead of slowing it down as needed, despite 
the second delay time being increased. 
Extra credit:

- The Grid uses a List<List<Cell>> as its data structure instead of a 2D matrix. Although we did not have time to actually display differently shaped cells, this design decision
should make tilings of different shapes with different length rows or columns much easier (especially
given that we already have a hexagonal and triangle neighbor policy).


### Impressions
This project showed the importance of extensibility in software design. The time we spent
planning our inheritance hierarchies made things a lot easier in later iterations, since
code that was more extensible made it easier to add new features.

In addition, the way the project was structured illustrated how beneficial it is to use reflection.
This heavily reduced duplicated code and assisted in removing long if/else chains. 
