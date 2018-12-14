package org.gaming.tictactoe.io;

import org.gaming.tictactoe.domain.Player;

import static org.gaming.tictactoe.domain.PlayerContainer.NoOne;

class MockedWinnerContainer {
  private Player winner = NoOne;

  public Player getWinner() {
    return winner;
  }

  public void setWinner(Player winner) {
    this.winner = winner;
  }
}
