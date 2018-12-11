package org.gaming.tictactoe.domain;

/**
 * It applies the rules of the game.
 */
public class Game {
  private final String[][] grid;
  private final Player[] players;
  private final int gridSize;
  private int turn = 0;

  public Game(GameConfiguration configuration) {
    players = Player.values();
    gridSize = configuration.getSize();
    grid = new String[configuration.getSize()][gridSize];
  }

  public boolean isOver() {
    // evaluate column:
    for (int i = 0; i < gridSize; i++) {
      String symbolX = null;
      int counterSymbolX = 0;
      for (int j = 0; j < gridSize; j++) {
        String currentSlot = grid[i][j];
        if (currentSlot == null) {
          break;
        }
        if (symbolX == null) {
          symbolX = currentSlot;
        }
        if (symbolX.equals(currentSlot)) {
          counterSymbolX++;
        }
      }
      if (counterSymbolX == gridSize) {
        return true;
      }
    }
    return false;
  }

  public String[][] getGrid() {
    return grid;
  }

  public Player nextPlayer() {
    Player player = players[turn++];

    if (turn > 2) {
      turn = 0;
    }

    return player;
  }

  public void move(Coordinates coordinates, Player player) {
    grid[coordinates.getX()][coordinates.getY()] = player.getSymbol();
  }
}
