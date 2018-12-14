package org.gaming.tictactoe.domain;

import static org.gaming.tictactoe.domain.PlayerContainer.obtainPlayerBySymbol;

/**
 * It applies the rules of the game by using simple calculations.
 * Use brute force algorithm to determine if there is a winner and if the game is over.
 */
public class SimpleGame extends Game {

  public SimpleGame(GameConfiguration configuration) {
    super(configuration);
  }

  @Override
  public boolean isOver() {
    return evaluateWinnerInRow()
        || evaluateWinnerInColumn()
        || evaluateWinnerInForwardDiagonal()
        || evaluateWinnerInBackDiagonal()
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
        winner = obtainPlayerBySymbol(symbolY, players);
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
        winner = obtainPlayerBySymbol(symbolX, players);
        return true;
      }
    }
    return false;
  }

  private boolean evaluateWinnerInForwardDiagonal() {
    String symbolD = null;

    int counterSymbolD = 0;

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
        winner = obtainPlayerBySymbol(symbolD, players);
        return true;
      }
    }

    return false;
  }

  private boolean evaluateWinnerInBackDiagonal() {
    String symbolD = null;

    int counterSymbolD = 0;

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
        winner = obtainPlayerBySymbol(symbolD, players);
        return true;
      }
    }

    return false;
  }

}

