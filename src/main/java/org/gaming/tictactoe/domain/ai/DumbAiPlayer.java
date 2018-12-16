package org.gaming.tictactoe.domain.ai;

import org.gaming.tictactoe.domain.AiPlayer;
import org.gaming.tictactoe.domain.Coordinates;
import org.gaming.tictactoe.domain.Game;
import org.gaming.tictactoe.domain.Player;

/**
 * Simple AI player that only looks for the next available position.
 */
public class DumbAiPlayer extends Player implements AiPlayer {
  public DumbAiPlayer(String symbol) {
    super(symbol, "Simple Robot");
  }

  @Override
  public Coordinates calculateMove(Game game) {
    String[][] grid = game.getGrid();

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
