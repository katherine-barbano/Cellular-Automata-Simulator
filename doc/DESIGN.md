# Simulation Design Final
### Names
Priya Rathinavelu (plr11), Heather Grune (hlg20), Katherine Barbano (kab134)

## Team Roles and Responsibilities

 * Team Member #1
Priya Rathinavelu - My responsibility was to focus on creating the controller aspect of the project.
I worked on ensuring that both the view and model would be able to work with each other without 
directly interacting with each other. In addition, I created the ControllerMain class 
which is responsible for running each Simulation and displaying it properly, while also working 
with the view to create an interactive user interface. 

 * Team Member #2

 * Team Member #3


## Design goals

#### What Features are Easy to Add


## High-level Design

#### Core Classes
Within the controller package, the main classes are the Simulation abstract class (which 
multiple other specific simulations implement), ControllerMain class, the State
class, and the StateType interface. To go into detail, the simulation main class deals 
with all of the setting up of the simulation by reading in the properties file associated with the 
simulation to better understand how exactly the simulation should be created. For example, depending 
on the keyname "stateConfiguration" within the properties file, the simulation can determine 
whether the initial configuration of states should be read in a from an indicated csv file,
based on randomness, or based on a given probability within the keyfile. Within this simulation class,
there is also an initial grid created that will first be displayed (so there is an interaction with the
Grid class). In the controller Main class, which is where the initial set up of the interface is 
created, there is an instance of the simulation view and graph view, so that the simulation can 
actually be displayed to the user. The controller main class is also where the buttons on the 
interface are also set up. The State class stores the state type as well as information about
the age of the cell. This class is important for setting up the Grid and ensuring that the different 
states possible for a simulation are represented. This class interacts with statetype, which is 
an interface that multiple enums implmenet. This interface is important for the frontend because it 
helps with displaying the default color for a cell depending on its state. 

## Assumptions that Affect the Design

#### Features Affected by Assumptions
Some features that are affected by assumptions is the creation of a new Simulation. The main assumptions
are related to the naming of the associated files as well as where they are located. To be 
more specific, when creating a new SImulation, the properties file associated with it is assumed
to have the same name as the new simulation class. In addition, this properties file is assumed 
to be located within the simulationProperties folder in data. In addition, if the property file
indicates that the initial configuration should be created from a filename, then that file 
should be within the initialConfigurations folder and is assumed to be a .csv file. 


## Significant differences from Original Plan
There were no major differences between our final version of the project and the original plan. We have 
added new classes within each package in order to advance our project and meet the newer requirements
for the assignment. 

## New Features HowTo

#### Easy to Add Features
One feature that is easy to add is to add a new Simulation. Given how each simulation is different,
there will need to be a new simulation class that extends the Abstract simulation class in addition a 
properties file that matches the name with the information indicating how to set up the 
simulation. In addition, a StateType class would need to be created that indicates the different
states associated with the simulation. 

#### Other Features not yet Done

