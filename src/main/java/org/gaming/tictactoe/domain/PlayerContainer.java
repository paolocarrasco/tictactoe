package org.gaming.tictactoe.domain;

import java.util.Arrays;

/**
 * The only purpose of this class is to hold the players that we have in the game.
 */
public final class PlayerContainer {

  public static final Player Robot = new AiPlayer("C");
  public static final Player Human1 = new Player("X", "Human 1");
  public static final Player Human2 = new Player("O", "Human 2");
  public static final Player NoOne = new Player(null, "No one");

  private PlayerContainer() {
    // To avoid instantiation.
  }

  static Player obtainPlayerBySymbol(String symbol, Player[] players) {
    return Arrays.stream(players)
        .filter(player -> player.getSymbol().equals(symbol))
        .findAny()
        .orElse(NoOne);
  }
}
