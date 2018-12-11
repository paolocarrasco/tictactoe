package org.gaming.tictactoe.domain;

/**
 * All the players the game can have.
 */
public enum Player {
  Robot("C"),
  Human1("X"),
  Human2("O");

  private String symbol;

  Player(String symbol) {
    this.symbol = symbol;
  }

  public String getSymbol() {
    return symbol;
  }
}
