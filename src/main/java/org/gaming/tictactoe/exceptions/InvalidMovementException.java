package org.gaming.tictactoe.exceptions;

/**
 * Thrown when a movement is not valid.
 */
public class InvalidMovementException extends Exception {

  public InvalidMovementException(String message) {
    super(message);
  }
}
