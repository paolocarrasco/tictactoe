package org.gaming.tictactoe.domain;

/**
 * All the players the game can have.
 */
public enum Player {
  Robot("C"),
  Human1("X"),
  Human2("O"),
  NoOne(null);

  private String symbol;

  Player(String symbol) {
    this.symbol = symbol;
  }

  public static Player obtainPlayerBySymbol(String symbol) {
    if ("X".equals(symbol)) {
      return Human1;
    } else if ("O".equals(symbol)) {
      return Human2;
    } else if ("C".equals(symbol)){
      return Robot;
    }
    return NoOne;
  }

  public String getSymbol() {
    return symbol;
  }
}
