package org.gaming.tictactoe.domain;

import java.util.Arrays;

/**
 * All the players the game can have.
 */
public class Player {

  public static final Player Robot = new Player("C", "Robot");

  public static final Player Human1 = new Player("X", "Human 1");

  public static final Player Human2 = new Player("O", "Human 2");

  public static final Player NoOne = new Player(null, null);
  private final String playerName;
  private String symbol;

  private Player(String symbol, String playerName) {
    this.symbol = symbol;
    this.playerName = playerName;
  }

  static Player obtainPlayerBySymbol(String symbol) {
    return Arrays.stream(new Player[]{Robot, Human1, Human2})
        .filter(player -> player.getSymbol().equals(symbol))
        .findFirst()
        .orElse(NoOne);
  }

  public String name() {
    return playerName;
  }

  public String getSymbol() {
    return symbol;
  }

  void setSymbol(String symbolToChange) {
    this.symbol = symbolToChange;
  }

}
