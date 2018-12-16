package org.gaming.tictactoe.domain;

import java.util.List;

import org.gaming.tictactoe.exceptions.InvalidMovementException;

/**
 * Base for the rules of the game.
 */
public abstract class Game {
  final String[][] grid;
  final int gridSize;
  final Player[] players;
  int movements;
  Player winner;
  int turn;

  public Game(GameConfiguration configuration, String[][] grid, int turn, int movements) {
    gridSize = grid.length;
    players = configuration.getPlayers();
    winner = PlayerContainer.NoOne;
    this.turn = turn;
    this.grid = grid;
    this.movements = movements;
  }

  public abstract boolean isOver();

  public Player nextPlayer() {
    Player player = players[turn++];

    if (turn > players.length - 1) {
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

  public String[][] getGrid() {
    return grid;
  }

  public Player getWinner() {
    return winner;
  }

  public int getSize() {
    return gridSize;
  }

  public abstract List<Coordinates> getAvailableMoves();

  public abstract Game copy();
}
