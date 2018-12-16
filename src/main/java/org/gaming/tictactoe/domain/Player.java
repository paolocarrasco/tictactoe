package org.gaming.tictactoe.domain;

import java.util.Objects;

/**
 * The person who plays the game.
 */
public class Player {

  private final String playerName;
  private String symbol;

  public Player(String symbol, String playerName) {
    this.symbol = symbol;
    this.playerName = playerName;
  }

  public String name() {
    return playerName;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbolToChange) {
    this.symbol = symbolToChange;
  }

  @Override
  public String toString() {
    return String.format("Player {playerName='%s'}", playerName);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Player player = (Player) o;
    return playerName.equals(player.playerName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(playerName);
  }
}
