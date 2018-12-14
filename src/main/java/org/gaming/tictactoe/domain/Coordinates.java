package org.gaming.tictactoe.domain;

/**
 * It represents the position in the grid of a movement.
 */
public class Coordinates {

  private final int x;
  private final int y;

  public Coordinates(int x, int y) {
    this.x = x;
    this.y = y;
  }

  int getX() {
    return x;
  }

  int getY() {
    return y;
  }

  @Override
  public String toString() {
    return "Coordinates{" +
        "x=" + x +
        ", y=" + y +
        '}';
  }
}
