package org.gaming.tictactoe.domain;

/**
 * It applies the rules of the game.
 */
public class Game {
  private final String[][] grid;
  private final Player[] players;
  private final int gridSize;
  private int turn = 0;
  private int movements = 0;
  private Player winner;

  public Game(GameConfiguration configuration) {
    players = Player.values();
    gridSize = configuration.getSize();
    grid = new String[configuration.getSize()][gridSize];
    winner = Player.Unknown;
  }

  public boolean isOver() {
    return evaluateWinnerInRow()
        || evaluateWinnerInColumn()
        || evaluateWinnerInDiagonals()
        || evaluateDraw();
  }

  private boolean evaluateDraw() {
    return movements == gridSize * gridSize;
  }

  private boolean evaluateWinnerInColumn() {
    for (int i = 0; i < gridSize; i++) {
      String symbolY = null;

      int counterSymbolY = 0;

      for (int j = 0; j < gridSize; j++) {
        String currentSlot = grid[j][i];

        if (currentSlot == null) {
          break;
        }

        if (symbolY == null) {
          symbolY = currentSlot;
        }

        if (symbolY.equals(currentSlot)) {
          counterSymbolY++;
        }
      }

      if (counterSymbolY == gridSize) {
        winner = Player.obtainPlayerBySymbol(symbolY);
        return true;
      }
    }
    return false;
  }

  private boolean evaluateWinnerInRow() {
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
        winner = Player.obtainPlayerBySymbol(symbolX);
        return true;
      }
    }
    return false;
  }

  private boolean evaluateWinnerInDiagonals() {
    String symbolD = null;

    int counterSymbolD = 0;

    // forward diagonal
    for (int i = 0; i < gridSize; i++) {
      String currentSlot = grid[i][i];

      if (currentSlot == null) {
        break;
      }

      if (symbolD == null) {
        symbolD = currentSlot;
      }

      if (symbolD.equals(currentSlot)) {
        counterSymbolD++;
      }

      if (counterSymbolD == gridSize) {
        winner = Player.obtainPlayerBySymbol(symbolD);
        return true;
      }
    }

    // back diagonal
    for (int i = 0; i < gridSize; i++) {
      String currentSlot = grid[i][gridSize - i - 1];

      if (currentSlot == null) {
        break;
      }

      if (symbolD == null) {
        symbolD = currentSlot;
      }

      if (symbolD.equals(currentSlot)) {
        counterSymbolD++;
      }

      if (counterSymbolD == gridSize) {
        winner = Player.obtainPlayerBySymbol(symbolD);
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
    movements++;
  }

  public Player getWinner() {
    return winner;
  }

  public int getSize() {
    return gridSize;
  }
}

