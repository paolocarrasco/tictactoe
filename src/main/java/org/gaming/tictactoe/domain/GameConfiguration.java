package org.gaming.tictactoe.domain;

public class GameConfiguration {
  private final int size;

  public GameConfiguration(int size) {
    this.size = size;
  }

  public int getSize() {
    return this.size;
  }
}
