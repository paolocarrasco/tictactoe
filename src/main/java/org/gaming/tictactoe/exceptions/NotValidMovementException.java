package org.gaming.tictactoe.exceptions;

/**
 * Thrown when a movement is not valid.
 */
public class NotValidMovementException extends Exception {

  public NotValidMovementException(String message) {
    super(message);
  }
}
