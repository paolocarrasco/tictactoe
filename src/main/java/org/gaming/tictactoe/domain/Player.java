package org.gaming.tictactoe.domain;

/**
 * All the players the game can have.
 */
public class Player {

  public static final Player Robot = new Player("C", "Robot");

  public static final Player Human1 = new Player("X", "Human 1");

  public static final Player Human2 = new Player("O", "Human 2");

  public static final Player NoOne = new Player(null, null);

  private String symbol;
  private final String playerName;

  private Player(String symbol, String playerName) {
    this.symbol = symbol;
    this.playerName = playerName;
  }

  static Player obtainPlayerBySymbol(String symbol) {
    if ("X".equals(symbol)) {
      return Human1;
    } else if ("O".equals(symbol)) {
      return Human2;
    } else if ("C".equals(symbol)) {
      return Robot;
    }
    return NoOne;
  }

  public String getSymbol() {
    return symbol;
  }

  public String name() {
    return playerName;
  }
}
