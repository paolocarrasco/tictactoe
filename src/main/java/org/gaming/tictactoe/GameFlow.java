package org.gaming.tictactoe;

import java.io.InputStream;
import java.io.PrintStream;

import org.gaming.tictactoe.domain.Game;
import org.gaming.tictactoe.io.GameController;

/**
 * It controls the flow of the game.
 */
public class GameFlow {
  private final Game game;
  private final GameController gameController;

  public GameFlow(Game game, PrintStream out, InputStream in) {
    this.game = game;
    gameController = new GameController(game, out, in);
  }

  public void start() {
    while (!game.isOver()) {
      gameController.printSnapshot();
      gameController.enterNextMove();
    }

    gameController.printWinner();
  }

}
