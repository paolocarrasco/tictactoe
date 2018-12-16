package org.gaming.tictactoe.domain;

/**
 * Represents a player that does not need human intervention.
 * It can play by itself.
 */
public interface AiPlayer {

  /**
   * Based on the state of the grid, it can calculate its next move.
   *
   * @return the coordinates of the next move of the AI player.
   */
  Coordinates calculateMove(Game game);
}
