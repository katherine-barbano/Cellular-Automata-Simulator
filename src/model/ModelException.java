package model;

/***
 * Custom runtime exception thrown and caught throughout Model package.
 * @author Katherine Barbano
 */
public class ModelException extends RuntimeException{

  /***
   * Constructor with String message
   * @param message String
   */
  public ModelException(String message) {
    super(message);
  }

  /***
   * Constructor with String message and cause
   * @param message String message
   * @param cause Throwable object
   */
  public ModelException(String message, Throwable cause) {
    super(message, cause);
  }
}
