package org.gaming.tictactoe.domain;

/**
 * Simple AI player that only looks for the next available position.
 */
public class DumbAiPlayer extends Player implements AiPlayer {
  protected DumbAiPlayer(String symbol) {
    super(symbol, "Robot");
  }

  @Override
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
