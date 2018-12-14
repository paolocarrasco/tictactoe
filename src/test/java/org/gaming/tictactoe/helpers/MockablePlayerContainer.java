package org.gaming.tictactoe.helpers;

import org.gaming.tictactoe.domain.Player;

import static org.gaming.tictactoe.domain.PlayerContainer.Human2;
import static org.gaming.tictactoe.domain.PlayerContainer.NoOne;

/**
 * Contains classes but allows to change them for testing purposes.
 */
public class MockablePlayerContainer {
  private Player winner = NoOne;
  private Player player = Human2;

  public Player getWinner() {
    return winner;
  }

  public void setWinner(Player winner) {
    this.winner = winner;
  }

  public Player getPlayer() {
    return player;
  }

  public void setPlayer(Player player) {
    this.player = player;
  }
}
