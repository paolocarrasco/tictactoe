package org.gaming.tictactoe.domain;

public class AiPlayer extends Player {
  protected AiPlayer(String symbol) {
    super(symbol, "Robot");
  }

  public Coordinates calculateMove(String[][] grid) {
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid.length; j++) {
        if (grid[i][j] == null) {
          return new Coordinates(i, j);
        }
      }
    }
    // This should not happen since it would mean that the grid is full
    // (impossible if the we are frequently checking if the game is over).
    return null;
  }
}
