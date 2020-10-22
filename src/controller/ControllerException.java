package controller;

/*@author Priya Rathinavelu
The purpose of this class is to create a custom exception class that can display certain messages
related to the controller issues. This is so that exceptions that are not already found within java
and are specific to this code can be indicated to the user. It does not depend on any other classes.
To use this class, a new instance of it can be created by throwing a new controller exception that
takes in a string of the message to be shown to the user.
 */

public class ControllerException extends RuntimeException {

  public ControllerException(String message) {
    super(message);
  }

}
