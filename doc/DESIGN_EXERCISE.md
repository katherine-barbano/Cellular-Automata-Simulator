# Simulation Lab Discussion
## Heather Grune (hlg20), Katherine Barbano (kab134), Priya Rathinavelu (plr11)


## Rock Paper Scissors

### High Level Design Ideas
* Create a weapon abstract class, and design Rock, Paper, Scissors classes to extend from Weapon.
* Outcomes Class to keep track of relationships between weapons (what wins against what)
* Round class for each round 
* Game class to control the rounds 
* Player class - InteractivePlayer and ComputerPlayer classes extend Player
* UserInterface class to set up the UI - interacts with Game class

### CRC Card Classes

```java
 public class Weapon {
    public Weapon()
  
     @Override
     public int compareTo ()
 }
```

```java
 public class Outcomes {
    public Weapon determineWinningWeapon()
 }
```

```java
 public class Round {
    public void startRound()
    public void endRound()
    public Weapon getPlayerChoice()
 }
```

```java
 public class Game {
    public void startGame()
    public createUI()
    public void moveToNewRound()
    public void pauseGame()
    public void endGame()
    
 }
```

```java
 public class Player {
    public void chooseWeapon()
    public updateScore()
    
 }
```


### Use Cases

 * A new game is started with five players, their scores are reset to 0.
 ```java
for(int i=0;i<5;i++){Game.addPlayerToGame(new Player());}


 ```

 * A player chooses his RPS "weapon" with which he wants to play for this round.
 ```java
 Something thing = new Something();
 Value v = thing.getValue();
 v.update(13);
 ```

 * Given three players' choices, one player wins the round, and their scores are updated.
 ```java
 Something thing = new Something();
 Value v = thing.getValue();
 v.update(13);
 ```

 * A new choice is added to an existing game and its relationship to all the other choices is updated.
 ```java
 Something thing = new Something();
 Value v = thing.getValue();
 v.update(13);
 ```

 * A new game is added to the system, with its own relationships for its all its "weapons".
 ```java
 Something thing = new Something();
 Value v = thing.getValue();
 v.update(13);
 ```


## Cell Society

### High Level Design Ideas
* Model - Reads in the configuration file, creates and stores all components and their states
    * Cell - responsible for keeping track of what state it is in
    * Grid
    * BasicSimulation class to extend for different types
        * Basic starting Simulation
        * Keep track of the states that each simulation contains
    * State Abstract class - extend for all different states we need
* View  
    * Adds components to the scene
    * setupScene()
    * CellArray
    * GridDisplay - handle adding
* Controller (what the user interacts with)
    * Main - start simulation, stop, pause
    * Handle changing between types of simulations -> handleChangeSimulation()
    * step() function (updates both backend and frontend)
    * Update components in the simulation--> updates both view and model

### CRC Card Classes

This class's purpose or value is to manage something:
```java
public class Something {
    public int getTotal (Collection<Integer> data)
    public Value getValue ()
}
```

This class's purpose or value is to be useful:
```java
public class Value {
    public void update (int data)
}
```

### Use Cases

* Apply the rules to a cell: set the next state of a cell to dead by counting its number of neighbors using the Game of Life rules for a cell in the middle (i.e., with all of its neighbors)
```java
Something thing = new Something();
Value v = thing.getValue();
v.update(13);
```

* Move to the next generation: update all cells in a simulation from their current state to their next state
```java
Something thing = new Something();
Value v = thing.getValue();
v.update(13);
```

* Switch simulations: load a new simulation from a data file, replacing the current running simulation with the newly loaded one
```java
Something thing = new Something();
Value v = thing.getValue();
v.update(13);
```
