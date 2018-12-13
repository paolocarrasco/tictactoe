package org.gaming.tictactoe.io;

import org.gaming.tictactoe.domain.Player;

class MockedWinnerContainer {
  private Player winner = Player.NoOne;

  public Player getWinner() {
    return winner;
  }

  public void setWinner(Player winner) {
    this.winner = winner;
  }
}
