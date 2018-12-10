package org.gaming.tictactoe.domain;

public class GameConfiguration {
  public static final int DEFAULT_SIZE = 3;

  private static final int MINIMUM_VALUE = 3;
  private static final int MAXIMUM_VALUE = 10;

  private int size;

  public GameConfiguration(int size) {
    this.size = size < MINIMUM_VALUE || size > MAXIMUM_VALUE
        ? DEFAULT_SIZE
        : size;
  }

  public int getSize() {
    return this.size;
  }
}
