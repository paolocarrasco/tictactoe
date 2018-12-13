package org.gaming.tictactoe.domain;

import org.gaming.tictactoe.exceptions.InvalidMovementException;

/**
 * Base for the rules of the game.
 */
public abstract class Game {
  int movements = 0;
  Player winner;

  final String[][] grid;
  final int gridSize;

  private final Player[] players;
  private int turn = 0;

  Game(GameConfiguration configuration) {
    gridSize = configuration.getSize();
    grid = new String[gridSize][gridSize];
    players = configuration.getPlayers();
    winner = Player.NoOne;
  }

  public abstract boolean isOver();

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

  public void move(Coordinates coordinates, Player player) throws InvalidMovementException {
    try {
      if (grid[coordinates.getX()][coordinates.getY()] != null) {
        throw new InvalidMovementException("Slot was already played");
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new InvalidMovementException(String.format(
          "Coordinates numbers of slot should be between 0 and %d",
          gridSize
      ));
    }

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
